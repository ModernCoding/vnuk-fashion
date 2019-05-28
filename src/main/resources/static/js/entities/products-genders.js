$(function(){

    //  DELETING PRODUCT GENDER
    $('.my-product-gender-to-delete').on('click', function (e){
        e.preventDefault();

        var $productsGender = $(this);
        var productsGenderId = $productsGender.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/products-genders/" + productsGenderId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-product-gender-id="' + productsGenderId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Product gender #" + productsGenderId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of product gender #" + productsGenderId);
            
            }
            
        });
        
    });

});
