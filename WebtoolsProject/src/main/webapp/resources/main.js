/**
 * 
 */
	function showactive(that,category){
		
    		$(that).attr("class","active");
    		var a = $(that).parent().siblings();
    		for(var i = 0;i<a.length;i++){
    			$(a[i]).children().eq(0).attr("class","");
    		}

    		$.ajax({
				type:"GET",
				url:"displaycategory.htm",
				data:{category:category},
				success:function(data){
					
					$("#row3").empty();
					$("#row3").append(data);
					
				}
				
			});

	}
	
	function viewMore(category){

		$.ajax({
			type:"GET",
			url:"showmore.htm",
			data:{category:category},
			success:function(data){
				
				$("#showmore").remove();
				$("#row3").append(data);
				
			}
			
		});

}
		
		
		function login(){
			
			location.href = "home.htm?action=login";
		}
		
		function logout(){
					
					location.href = "home.htm?action=logout";
				}
		function register(){
			
			location.href = "addcustomer.htm";
		}
		
		function adminpanel(){
			
			location.href = "adminhome.htm?action=home";
		}

		
		function theatrechange(){
			
    		$(that).attr("class","active");
    		var a = $(that).parent().siblings();
    		for(var i = 0;i<a.length;i++){
    			$(a[i]).children().eq(0).attr("class","");
    		}

    		$.ajax({
				type:"GET",
				url:"displaycategory.htm",
				data:{category:category},
				success:function(data){
					
					$("#row3").empty();
					$("#row3").append(data);
					
				}
				
			});

	}
		
		