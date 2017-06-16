/**
 * Created by I848568 on 23/05/2017.
 */
$(document).ready(function(){

    $("[data-toggle=tooltip]").tooltip();


    $('#adopt').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);

        var dogId = button.closest('tr').find('.dogId').val();

        var modal = $(this);
        modal.find('#dogId').val(dogId);
    })

    $('#adoptDog').on('click', function (event) {
        var modal = $(this);
        var dogId = modal.closest('div').find('#dogId').val();

        $.ajax({
            url: '/deleteDog',
            data: {'dogId': dogId},
            type: 'POST',
            success: function (response) {
                location.reload();
            }
        })
    })

    $('#doFilter').on('click', function (event) {
        var modal = $(this);
        var ageFrom = modal.closest('div').siblings('.modal-body').find('#ageFrom').val();
        var ageTo = modal.closest('div').siblings('.modal-body').find('#ageTo').val();

        $.ajax({
            url: '/filterDogByAge',
            data: {'ageFrom': ageFrom, 'ageTo': ageTo},
            type: 'POST',
            success: function (response) {
                location.reload();
            }
        })
    })

});
