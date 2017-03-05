/**
 * 
 */

/*function addgenre(){
		var genre = $("#genrename").val();
		if(genre.trim()==""){
			$("#genreerror").text("*Invalid Genre");
			$("#genreerror").css("color" , "red");
			return;
		}
        	$.ajax({
					type:"GET",
					url:"genrecheck.htm",
					data:{genre:genre},
					success:function(data){
						
						if(data == "false"){
							$("#addmore").attr("disabled",false);
							$("#add").attr("disabled",true);
							$("#genreerror").text("*Genre added successfully");
							$("#genreerror").css({"color":"green","font-weight":"bold"});
						}else{
							$("#genreerror").text("*Genre already Exists");
							$("#genreerror").css({"color":"red","font-weight":"bold"});
						}
					}
					
				});
	}*/


	
	function addgen(){
		$("#addmore").attr("disabled",true);
		$("#add").attr("disabled",false);
		$("#genrename").val("");
		$("#genreerror").text("");
		
	}