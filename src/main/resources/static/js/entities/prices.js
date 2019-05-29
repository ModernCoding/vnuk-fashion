$(function(){

    //  DELETING COLLAR
    $('.my-price-to-delete').on('click', function (e){
        e.preventDefault();

        var $price = $(this);
        var priceId = $price.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/prices/" + priceId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-price-id="' + priceId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Price #" + priceId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of price #" + priceId);
            
            }
            
        });
        
    });

});
