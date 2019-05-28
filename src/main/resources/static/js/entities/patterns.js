$(function(){

    //  DELETING PATTERN
    $('.my-pattern-to-delete').on('click', function (e){
        e.preventDefault();

        var $pattern = $(this);
        var patternId = $pattern.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/patterns/" + patternId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-pattern-id="' + patternId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Pattern #" + patternId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of pattern #" + patternId);
            
            }
            
        });
        
    });

});
