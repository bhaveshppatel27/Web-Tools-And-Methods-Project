/**
 * 
 */

/*function addactor(){
		var firstname = $("#firstname").val();
		var lastname = $("#lastname").val();
		var skills = $("#skills").val();
		if(firstname.trim()=="" || lastname.trim()=="" || skills.trim()==""){
			$("#actorerror").text("*Invalid Details");
			$("#actorerror").css("color" , "red");
			return;
		}
        	$.ajax({
					type:"GET",
					url:"actorcheck.htm",
					data:{first:firstname,last:lastname,skills:skills},
					success:function(data){
						
						if(data == "false"){
							$("#addmore").attr("disabled",false);
							$("#add").attr("disabled",true);
							$("#actorerror").text("*Actor added successfully");
							$("#actorerror").css({"color":"green","font-weight":"bold"});
						}else{
							$("#actorerror").text("*Actor already Exists");
							$("#actorerror").css({"color":"red","font-weight":"bold"});
						}
					}
					
				});
	}*/
	
	function addact(){
		$("#addmore").attr("disabled",true);
		$("#add").attr("disabled",false);
		$("#firstname").val("");
		$("#lastname").val("");
		$("#skills").val("");
		$("#actorerror").text("");
		
	}