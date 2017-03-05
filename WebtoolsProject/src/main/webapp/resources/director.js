/**
 * 
 */

/*function adddirector(){
		var firstname = $("#firstname").val();
		var lastname = $("#lastname").val();
		var rating = $("#rating").val();
		if(firstname.trim()=="" || lastname.trim()==""){
			$("#directorerror").text("*Invalid Details");
			$("#directorerror").css("color" , "red");
			return;
		}
        	$.ajax({
					type:"GET",
					url:"directorcheck.htm",
					data:{first:firstname,last:lastname,rating:rating},
					success:function(data){
						
						if(data == "false"){
							$("#addmore").attr("disabled",false);
							$("#add").attr("disabled",true);
							$("#directorerror").text("*Director added successfully");
							$("#directorerror").css({"color":"green","font-weight":"bold"});
						}else{
							$("#directorerror").text("*Director already Exists");
							$("#directorerror").css({"color":"red","font-weight":"bold"});
						}
					}
					
				});
	}*/
	
	function adddir(){
		$("#addmore").attr("disabled",true);
		$("#add").attr("disabled",false);
		$("#firstname").val("");
		$("#lastname").val("");
		$("#rating").val(1);
		$("#directorerror").text("");
		
	}