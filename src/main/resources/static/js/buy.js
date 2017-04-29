
var serverContext = [[@{/}]];

function buy(){
	$(".alert").html("").hide();
	var formData= $('form').serialize();
	$.post(serverContext + "/buy",formData ,function(data){
		if(data.message == "success"){
			window.location.href = serverContext +"/successBuy.html";
		}
	})
	.fail(function(data) {
		if(data.responseJSON.error.indexOf("MailError") > -1)
		{
			window.location.href = serverContext + "/emailError.html";
		}
		else if(data.responseJSON.error.indexOf("InternalError") > -1){
			window.location.href = serverContext + 
			"/login.html?message=" + data.responseJSON.message;
		}
		else if(data.responseJSON.error == "UserAlreadyExist"){
			$("#emailError").show().html(data.responseJSON.message);
		}
		else{
			var errors = $.parseJSON(data.responseJSON.message);
			$.each( errors, function( index,item ){
				$("#"+item.field+"Error").show().html(item.defaultMessage);
			});
			errors = $.parseJSON(data.responseJSON.error);
			$.each( errors, function( index,item ){
				$("#globalError").show().append(item.defaultMessage+"<br>");
			});
		}
	}
};