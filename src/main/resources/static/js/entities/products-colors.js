$(function(){

    //  DELETING PRODUCT COLOR
    $('.my-product-color-to-delete').on('click', function (e){
        e.preventDefault();

        var $productsColor = $(this);
        var productsColorId = $productsColor.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/products-colors/" + productsColorId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-product-color-id="' + productsColorId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Product color #" + productsColorId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of product color #" + productsColorId);
            
            }
            
        });
        
    });

});
