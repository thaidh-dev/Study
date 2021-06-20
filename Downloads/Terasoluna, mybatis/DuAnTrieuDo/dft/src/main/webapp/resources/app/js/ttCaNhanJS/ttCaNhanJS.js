$(document).ready(function() {
    $(".cboTinh").change(function () {
        $.ajax({
            dataType: "json",
            url : "/ttCaNhan/ajax/quanHuyens",
            data: {idTinh: $(".cboTinh").val()},
            success : function(response) {
                // xóa toàn bộ option của select
                $(".cboQuanHuyen").find("option").remove().end();
                $(".cboXaPhuong").find("option").remove().end();
                $(".cboThonXom").find("option").remove().end();

                // đổ option mới vào select
                $.each(response, function (index, value) {
                    $(".cboQuanHuyen").append($("<option/>", {
                        value: value.id,
                        text: value.ten
                    }));
                });
            }
        });
    });

    $(".cboQuanHuyen").change(function () {
        $.ajax({
            dataType: 'json',
            url : "/ttCaNhan/ajax/xaPhuongs",
            data: {idQuanHuyen: $(".cboQuanHuyen").val()},
            success : function(response) {
                // xóa toàn bộ option của select
                $(".cboXaPhuong").find("option").remove().end();
                $(".cboThonXom").find("option").remove().end();

                // đổ option mới vào select
                $.each(response, function (index, value) {
                    $(".cboXaPhuong").append($("<option/>", {
                        value: value.id,
                        text: value.ten
                    }));
                });
            }
        });
    });

    $(".cboXaPhuong").change(function () {
        $.ajax({
            dataType: 'json',
            url : "/ttCaNhan/ajax/thonXoms",
            data: {idXaPhuong: $(".cboXaPhuong").val()},
            success : function(response) {
                // xóa toàn bộ option của select
                $(".cboThonXom").find("option").remove().end();

                // đổ option mới vào select
                $.each(response, function (index, value) {
                    $(".cboThonXom").append($("<option/>", {
                        value: value.id,
                        text: value.ten
                    }));
                });
            }
        });
    });
});