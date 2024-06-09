<%--
  Created by IntelliJ IDEA.
  User: victus
  Date: 2024/6/9
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>新增用户</title>
  <style>
    /* 使用内联样式设置字体大小和加粗 */
    #reader_type,#department {
      font-size: 16px; /* 设置字体大小 */
      font-weight: bold; /* 设置字体加粗 */
    }
  </style>
</head>
<body>
<h2>新增用户</h2>
<hr>
<form action="/mysqltraining/reader/add" method="post">
  <input type="hidden" name="timestamp" value="<%= System.currentTimeMillis() %>">
  <table>
    <tr>
      <th align="left">
        读者编号 <input type="number" id="reader_id" name="reader_id"/> <br>
        读者姓名 <input type="text" id="reader_name" name="reader_name"/><br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码 <input type="text" id="reader_password" name="reader_password"/><br>
        读者类别
        <select id="reader_type" name="reader_type">
          <option value="1">教职工</option>
          <option value="2">研究生</option>
          <option value="3">本科生</option>
        </select>
        <br>
        所属院系
        <select id="department" name="department">
          <option value="计算机学院">计算机学院</option>
          <option value="软件学院">软件学院</option>
          <option value="数信学院">数信学院</option>
        </select>
        <br>
        性别
        <label>
          <input type="radio" name="gender" value="男">
          男
        </label>
        <label>
          <input type="radio" name="gender" value="女">
          女
        </label><br>
      </th>
    </tr>
  </table>
  <input type="submit" value="提交" onclick="alert('提交成功') && goBack()"/>
  <input type="button" value="返回" onclick="goBack()"/>
</form>
<script>
  function goBack() {
    window.location.href = '../mainPage.jsp'
  }
</script>

</body>
</html>

