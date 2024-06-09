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
            <td><input type="text" id="reader_dept" name="reader_dept"/></td>
        </tr>
        <tr>
            <th>性别:</th>
            <td><input type="text" id="reader_sex" name="reader_sex"/></td>
        </tr>
        <tr>
            <th colspan="2">
                <button type="submit">提交</button>
            </th>
        </tr>
    </table>
</form>

<script>
    function searchReader() {
        var readerNo = $("#reader_no").val();
        $.ajax({
            // 填写后端处理查询读者信息的Servlet地址
            url: "/mysqltraining/read/edit",
            method: "GET",
            data: {readerNo: readerNo},
            success: function (data) {
                $("#reader_id").val(data.id);
                $("#reader_name").val(data.name);
                $("#reader_password").val(data.password);
                $("#reader_type").val(data.type);
                $("#reader_dept").val(data.dept);
                $("#reader_sex").val(data.sex);
                // 设置读者编号到对应的输入框中
                $("#modify_reader_no").val(data.reader_no);
                // 显示表格
                $("#modifyForm").show();
            },
            error: function () {
                alert("查找用户失败");
            }
        });
    }
</script>
</body>
</html>
