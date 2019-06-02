$(function(){

    //  DELETING PRODUCT LENGTH
    $('.my-product-length-to-delete').on('click', function (e){
        e.preventDefault();

        var $productsLength = $(this);
        var productsLengthId = $productsLength.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/products-lengths/" + productsLengthId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-product-length-id="' + productsLengthId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Product length #" + productsLengthId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of product length #" + productsLengthId);
            
            }
            
        });
        
    });

});
