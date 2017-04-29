package com.ipayso.util.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * ValidEmail.class -> Here create a Annotation as @ValidEmail to validate e-mail on field which will be validated by EmailValidator
 * @author Cleber Oliveira
 * @version 1.0
 * @see EmailValidator
 * @see @Target
 * @see @Retention
 * @see @Constraint
 * @see @Documented
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE}) 
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {
	
	String message() default "{ValidEmail.email.invalid}";
    Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}
