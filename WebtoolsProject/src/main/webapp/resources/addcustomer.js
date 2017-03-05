/**
 * 
 */

	
	/*function userchange(){
		var user = $("#user").val();

        	$.ajax({
					type:"GET",
					url:"usernamecheck.htm",
					data:{username:user},
					success:function(data){
							
						if(data.trim() == "false"){
							$("#submit").attr("disabled",false);
							$("#usernameerror").text("");

						}else{
							$("#usernameerror").text("*Username already in use");
							$("#usernameerror").css("color" , "red");
							$("#submit").attr("disabled",true);
						}
					}
					
				});
	}*/
	
	/*function emailchange(){
		var email = $("#email").val();

        	$.ajax({
					type:"GET",
					url:"emailidcheck.htm",
					data:{email:email},
					success:function(data){
							
						if(data.trim() == "false"){
							$("#submit").attr("disabled",false);
							$("#emailerror").text("");

						}else{
							$("#emailerror").text("*Email already in use");
							$("#emailerror").css("color" , "red");
							$("#submit").attr("disabled",true);
						}
					}
					
				});
	}*/
	
	function login(){
		
		location.href = "home.htm?action=login";
	}
	function logout(){
				
				location.href = "home.htm?action=logout";
			}
	function register(){
		
		location.href = "addcustomer.htm";
	}
	