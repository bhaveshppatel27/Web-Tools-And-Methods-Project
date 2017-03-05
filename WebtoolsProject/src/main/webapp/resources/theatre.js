/*function checkTheatreDetail(){
		var name = $("#name").val();
		var location = $("#location").val();
        	$.ajax({
					type:"GET",
					url:"checktheatre.htm",
					data:{name:name,location:location},
					success:function(data){
							
						if(data.trim() == "false"){
							$("#submit").attr("disabled",false);
							$("#theatreerror").text("");

						}else{
							$("#theatreerror").text("*Theatre Already Exists at this location");
							$("#theatreerror").css("color" , "red");
							$("#submit").attr("disabled",true);
						}
					}
					
				});
	}*/