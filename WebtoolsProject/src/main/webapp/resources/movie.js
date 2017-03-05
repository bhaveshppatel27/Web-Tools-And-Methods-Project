/*function checkMovieTitle(){
		var title = $("#title").val();

        	$.ajax({
					type:"GET",
					url:"checkmovietitle.htm",
					data:{title:title},
					success:function(data){
							
						if(data.trim() == "false"){
							$("#submit").attr("disabled",false);
							$("#movietitleerror").text("");

						}else{
							$("#movietitleerror").text("*Movie already in Database");
							$("#movietitleerror").css("color" , "red");
							$("#submit").attr("disabled",true);
						}
					}
					
				});
	}*/