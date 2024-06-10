<%--
  Created by IntelliJ IDEA.
  User: victus
  Date: 2024/6/9
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改用户</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        /* 设置按钮与文本框之间的间距 */
        button {
            margin-left: 20px;
        }

        /* 隐藏表格 */
        #modifyForm {
            display: none;
        }

        /* 设置表格布局 */
        table {
            border-collapse: collapse;
        }

        table th {
            text-align: right;
            padding: 5px;
        }

        table th input {
            margin-left: 10px;
        }
    </style>
</head>
<body>
<h1>修改读者</h1>
<hr>
<table>
    <tr>
        <th>读者编号<input type="text" id="reader_no" name="reader_no"/></th>
        <th>
            <button onclick="searchReader()">查找</button>
        </th>
    </tr>
</table>
<form id="modifyForm" action="/mysqltraining/reader/edit" method="POST">
    <!-- 添加隐藏的 input 元素保存时间戳的值 -->
    <input type="hidden" id="timestampOfSubmit" name="timestampOfSubmit">
    <table>
        <tr>
            <th>读者编号:</th>
            <td><input type="text" id="modify_reader_no" name="modify_reader_no"/></td>
        </tr>
        <tr>
            <th>读者ID:</th>
            <td><input type="text" id="reader_id" name="reader_id" readonly/></td>
        </tr>
        <tr>
            <th>读者姓名:</th>
            <td><input type="text" id="reader_name" name="reader_name"/></td>
        </tr>
        <tr>
            <th>密码:</th>
            <td><input type="text" id="reader_password" name="reader_password"/></td>
        </tr>
        <tr>
            <th>读者类别:</th>
            <td>
                <select id="reader_type" name="reader_type">
                    <option value="本科生">本科生</option>
                    <option value="研究生">研究生</option>
                    <option value="教职工">教职工</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>所属院系:</th>
            <td>
                <select id="reader_dept" name="reader_dept">
                    <option value="软件学院">软件学院</option>
                    <option value="计算机学院">计算机学院</option>
                    <option value="数信学院">数信学院</option>
                    <%--<option value="理工学部">理工学部</option>--%>
                </select>
            </td>
        </tr>
        <tr>
            <th>性别:</th>
            <td>
                <input type="radio" id="male" name="reader_sex" value="male">
                <label for="male">男</label>
                <input type="radio" id="female" name="reader_sex" value="female">
                <label for="female">女</label>
            </td>
        </tr>

        <tr>
            <th colspan="2">
                <button type="button" onclick="submitForm()">提交</button>
            </th>
        </tr>
    </table>
</form>

<script>
    function searchReader() {
        var readerNo = $("#reader_no").val();
        // 获取当前时间戳（毫秒级）这是，搜索时候的时间戳
        var timestampOfSelect = new Date().getTime();

        $.ajax({
            // 填写后端处理查询读者信息的Servlet地址
            url: "/mysqltraining/reader/edit",
            method: "GET",
            data: {readerNo: readerNo,timestampOfSelect : timestampOfSelect},
            success: function (jsonStr) {
                console.log(jsonStr)
                var data = JSON.parse(jsonStr);
                $("#reader_id").val(data.id);
                $("#reader_name").val(data.name);
                $("#reader_password").val(data.password);
                $("#reader_type").val(data.type);
                $("#reader_dept").val(data.dept);
                // 设置性别
                if (data.sex === "男") {
                    $("#male").prop("checked", true);
                } else {
                    $("#female").prop("checked", true);
                }
                // 设置读者编号到对应的输入框中
                $("#modify_reader_no").val(data.no);
                // 显示表格
                $("#modifyForm").show();
            },
            error: function () {
                alert("查找用户失败");
            }
        });
    }

    function submitForm() {
        // 获取当前时间戳（毫秒级）这是，提交表单时的时间戳
        var timestampOfSubmit = new Date().getTime();
        // 将时间戳设置到隐藏字段中
        $("#timestampOfSubmit").val(timestampOfSubmit);

        // 构建表单数据对象
        var formData = {
            no: $("#modify_reader_no").val(),
            id: $("#reader_id").val(),
            name: $("#reader_name").val(),
            password: $("#reader_password").val(),
            type: $("#reader_type").val(),
            dept: $("#reader_dept").val(),
            sex: $("input[name='reader_sex']:checked").val(),
        };

        // 发送表单数据给后端
        $.ajax({
            url: "/mysqltraining/reader/edit",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function (response) {
                console.log(response);
                // 处理成功响应
            },
            error: function () {
                alert("提交表单失败");
            }
        });
    }
</script>
</body>
</html>
