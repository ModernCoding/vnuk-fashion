$(function(){

    //  DELETING SLEEVE
    $('.my-subcategories-body-part-to-delete').on('click', function (e){
        e.preventDefault();

        var $subcategoriesBodyPart = $(this);
        var subcategoriesBodyPartId = $subcategoriesBodyPart.val();


        $.ajax({

            type: "DELETE",
            url: "/subcategories-body-parts/" + subcategoriesBodyPartId,

            data: {},

            success: function() {

                $('tr[data-subcategories-body-part-id="' + subcategoriesBodyPartId + '"]').remove();

                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Subcategories body part #" + subcategoriesBodyPartId + " has successfully been deleted.");

            },


            error: function() {

                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of subcategories body part #" + subcategoriesBodyPartId);

            }

        });

    });

});
