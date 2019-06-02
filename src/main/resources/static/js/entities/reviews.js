$(function(){

    //  DELETING REVIEW
    $('.my-review-to-delete').on('click', function (e){
        e.preventDefault();

        var $review = $(this);
        var reviewId = $review.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/reviews/" + reviewId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-review-id="' + reviewId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Review #" + reviewId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of review #" + reviewId);
            
            }
            
        });
        
    });

});
