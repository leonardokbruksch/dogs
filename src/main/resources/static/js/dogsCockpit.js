/**
 * Created by I848568 on 23/05/2017.
 */
$(document).ready(function(){
    $("#mytable #checkall").click(function () {
        if ($("#mytable #checkall").is(':checked')) {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", true);
            });

        } else {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", false);
            });
        }
    });

    $("[data-toggle=tooltip]").tooltip();

    $('#edit').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);

        var dogName = button.closest('tr').find('.dogName').html();
        var dogAge = button.closest('tr').find('.dogAge').html();
        var dogId = button.closest('tr').find('.dogId').val();

        var modal = $(this)
        modal.find('#dogName').val(dogName);
        modal.find('#dogAge').val(dogAge);
        modal.find('.dogId').val(dogId);
    })

    $('#delete').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);

        var dogId = button.closest('tr').find('.dogId').val();

        var modal = $(this)
        modal.find('#dogId').val(dogId);
    })

    $('#deleteDog').on('click', function (event) {
        var modal = $(this)
        var dogId = modal.closest('div').find('#dogId').val()

        $.ajax({
            url: '/deleteDog',
            data: {'dogId': dogId},
            type: 'POST',
            success: function (response) {
                location.reload();
            }
        })
    })

    $('#updateDog').on('click', function (event) {
        var modal = $(this);

        var modalBody = modal.closest('div').siblings('.modal-body');

        var dogAge = modalBody.find('#dogAge').val();
        var dogName = modalBody.find('#dogName').val();
        var dogId = modalBody.find('.dogId').val();

        $.ajax({
            url: '/editDog',
            data: {'dogId': dogId, 'dogAge': dogAge, 'dogName': dogName},
            type: 'POST',
            success: function (response) {
                location.reload();
            }
        })
    })

    $('#addDog').on('click', function (event) {
        var modal = $(this);

        var modalBody = modal.closest('div').siblings('.modal-body');

        var dogAge = modalBody.find('#dogAgeAdd').val();
        var dogName = modalBody.find('#dogNameAdd').val();

        $.ajax({
            url: '/addDog',
            data: {'dogAge': dogAge, 'dogName': dogName},
            type: 'POST',
            success: function (response) {
                location.reload();
            }
        })
    })
});
