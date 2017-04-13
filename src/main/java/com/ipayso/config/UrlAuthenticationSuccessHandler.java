package com.ipayso.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * MySimpleUrlAuthenticationSuccessHandler.class -> This is the implementation of AuthenticationSuccessHandler,
 * 													we can set our default behavior when users submit login form.
 * @author Cleber Oliveira
 * @version 1.0
 * @see AuthenticationSuccessHandler
 */
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
    protected final Log logger = LogFactory.getLog(this.getClass());
    
    /**
     * Create a RedirectStrategy attribute to resolve our redirect control on login
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    protected UrlAuthenticationSuccessHandler() {
        super();
    }
    
    /**
     * Here override the superclass method that handle what to do after user has been logged in,
     * it call the method handle and after clean all attributes from user authentication
     */
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    /**
     * First verify from where the authentication is coming, verify if is possible to redirect and do it using redirectStrategy
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */

    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    
    /**
     * Verify whether is user or admin based on the authority they has and set the redirect target URL based on it
     * @param authentication
     * @return
     */
    protected String determineTargetUrl(final Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if (isUser) {
            return "/home";
        } else if (isAdmin) {
            return "/home";
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * Removes temporary authentication-related data which may have been stored in the session
     * during the authentication process.
     */
    protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
