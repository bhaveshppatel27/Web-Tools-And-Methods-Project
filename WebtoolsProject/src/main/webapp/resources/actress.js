/**
 * 
 */

/*function addactress(){
		var firstname = $("#firstname").val();
		var lastname = $("#lastname").val();
		var skills = $("#skills").val();
		if(firstname.trim()=="" || lastname.trim()=="" || skills.trim()==""){
			$("#actresserror").text("*Invalid Details");
			$("#actresserror").css("color" , "red");
			return;
		}
        	$.ajax({
					type:"GET",
					url:"actresscheck.htm",
					data:{first:firstname,last:lastname,skills:skills},
					success:function(data){
						
						if(data == "false"){
							$("#addmore").attr("disabled",false);
							$("#add").attr("disabled",true);
							$("#actresserror").text("*Actress added successfully");
							$("#actresserror").css({"color":"green","font-weight":"bold"});
						}else{
							$("#actresserror").text("*Actress already Exists");
							$("#actresserror").css({"color":"red","font-weight":"bold"});
						}
					}
					
				});
	}*/
	
	function addactr(){
		$("#addmore").attr("disabled",true);
		$("#add").attr("disabled",false);
		$("#firstname").val("");
		$("#lastname").val("");
		$("#skills").val("");
		$("#actresserror").text("");
		
	}