$(function () {
    $("#hinh").click(function () {
        $("#btnHinh").trigger('click');
    });

    $('#btnHinh').change(function () {
        var reader = new FileReader();
        var input = document.getElementById('btnHinh');

        reader.onload = function (e) {
            $('#hinh').attr('src', e.target.result);
            $('#hinhHidden').attr('value', "Hinh/" + input.files[0].name);
        };
        reader.readAsDataURL(input.files[0]);
    });
});

