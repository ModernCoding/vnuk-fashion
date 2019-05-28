$(function(){

    //  DELETING LENGTH
    $('.my-length-to-delete').on('click', function (e){
        e.preventDefault();

        var $length = $(this);
        var lengthId = $length.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/lengths/" + lengthId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-length-id="' + lengthId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Length #" + lengthId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of length #" + lengthId);
            
            }
            
        });
        
    });

});
