$(function(){

    //  DELETING PRODUCT
    $('.my-product-to-delete').on('click', function (e){
        e.preventDefault();

        var $product = $(this);
        var productId = $product.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/products/" + productId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-product-id="' + productId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Productl #" + productId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of product #" + productId);
            
            }
            
        });
        
    });

});
