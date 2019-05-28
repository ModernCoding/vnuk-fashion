$(function(){

    //  DELETING CUSTOMER
    $('.my-product-pattern-to-delete').on('click', function (e){
        e.preventDefault();

        var $productPattern = $(this);
        var productPatternId = $productPattern.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/products-patterns/" + productPatternId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-product-pattern-id="' + productPatternId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Product pattern #" + productPatternId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of product pattern #" + productPatternId);
            
            }
            
        });
        
    });

});
