<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
<l:template title="Trang chu">
    <jsp:attribute name="content">
         <form:form action="/" method="post" modelAttribute="TtCaNhan">
                <div class="row">
                    <span >Địa phương</span>
                    <div>
                        <form:select path="ttTinh" id="tinh">
                            <form:option selected="selected"  disabled="true"  value="" label="-- Chọn Tỉnh/Thành phố --"/>
                            <form:options items="${litsTinhTP_Selects}" itemValue="ma" itemLabel="ten"/>
                        </form:select>
                    </div>
                    <div >
                        <form:select path="ttHuyen" id="quanHuyen">
                            <form:option selected="selected"  disabled="true" value="" label="-- Chọn Quận huyện --"/>
                            <form:options items="${listQuanHuyen_Selects}" itemValue="ma" itemLabel="ten"/>
                        </form:select>
                    </div>
                    <div>
                        <form:select path="ttXa" id="phuongXa">
                            <form:option selected="selected"  disabled="true" value="" label="-- Chọn Phường xã --"/>
                            <form:options items="${listPhuongXa_Selects}" itemValue="ma" itemLabel="ten"/>
                        </form:select>
                    </div>
                    <div >
                        <form:select path="ttThonXom" id="thonXom">
                            <form:option  selected="selected"  disabled="true" value="" label="-- Chọn Thôn xóm"/>
                            <form:options items="${listThonXom_Selects}" itemValue="ma" itemLabel="ten"/>
                        </form:select>
                    </div>
                </div>

            <div class="col-sm" style="padding-top: 10px; text-align: center;">
                <input type="submit" value="Tìm kiếm">
            </div>
        </form:form>
        <div style="padding-top: 20px">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                    <th scope="col">Handle</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>

                </tbody>
            </table>
            <div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
    <script type="text/javascript" charset="utf-8">
        $("select#tinh").change(function(){
            $.getJSON(
                "/ajax/QuanHuyen",
                {tinhMa: $(this).val()},
                function(data){
                var html  = '<option selected="selected"  disabled="true"  value=""> -- Chọn Quận huyện --</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].ma + '">' + data[i].ten + '</option>';
                }
                $("select#quanHuyen").html(html);
                $("select#phuongXa").html('<option selected="selected"  disabled="true"  value=""> -- Chọn Phường Xã --</option> ');
                $("select#thonXom").html('<option selected="selected"  disabled="true"  value=""> -- Chọn Thôn Xóm --</option> ');
            });
        });

        $("select#quanHuyen").change(function(){
            $.getJSON(
                "/ajax/PhuongXa",
                {quanHuyenMa: $(this).val()},
                function(data){
                    var html  = '<option selected="selected"  disabled="true"  value=""> -- Chọn Phường Xã --</option>';
                    for (var i = 0; i < data.length; i++) {
                        html += '<option  value="' + data[i].id + '">' + data[i].ten + '</option>';
                    }
                    $("select#phuongXa").html(html);
                    $("select#thonXom").html('<option selected="selected"  disabled="true"  value=""> -- Chọn Thôn Xóm --</option> ');
                });
        });

        $("select#phuongXa").change(function(){
            $.getJSON(
                "/ajax/ThonXom",
                {phuongXaMa: $(this).val()},
                function(data){
                    var html  = '<option selected="selected"  disabled="true"  value=""> -- Chọn Thôn Xóm --</option>';
                    for (var i = 0; i < data.length; i++) {
                        html += '<option value="' + data[i].ma + '">' + data[i].ten + '</option>';
                    }
                    $("select#thonXom").html(html);
                });
        });
    </script>
</jsp:attribute>
</l:template>
