$(function () {

    $('.goals').change(function () {
        $('#betForm').submit();
    });

    $('button').click(function (event) {
        event.preventDefault();
        alert('hei');
    });

});

