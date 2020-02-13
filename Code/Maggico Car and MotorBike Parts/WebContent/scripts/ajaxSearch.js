$(document).ready(function(){	
	$('#searchBarProd').keydown(function(event)
	{
		var nameProd = $('#searchBarProd').val();
		$.get('VerifyServlet', {
			search : nameProd,
			actionAjax : 'searchAjax'
		}, function(responseText){
			if(responseText == "false")
			{
				$('#invalidUser').text("L'username non è disponibile! ");
				$('#invalidUser').css("color", "red");
				verifyUser = false;
			}
			else
			{
				$('#invalidUser').text("");
				verifyUser = true;
			}
		});
	});
});