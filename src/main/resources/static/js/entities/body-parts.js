$(function(){

    //  DELETING CATEGORY
    $('.my-body-part-to-delete').on('click', function (e){
        e.preventDefault();

        var $bodyPart = $(this);
        var bodyPartId = $bodyPart.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/body-parts/" + bodyPartId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-body-part-id="' + bodyPartId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Body part #" + bodyPartId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of body part #" + bodyPartId);
            
            }
            
        });
        
    });

});
