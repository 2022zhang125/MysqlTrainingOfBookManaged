<%--
  Created by IntelliJ IDEA.
  User: victus
  Date: 2024/6/9
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>系统主页面</title>
  <style>
    /* 使用内联样式设置字体大小和加粗 */
    #options {
      font-size: 16px; /* 设置字体大小 */
      font-weight: bold; /* 设置字体加粗 */
    }
  </style>
  <script>
    // JavaScript 代码
    function redirectToReaderPage() {
      var selectElement = document.getElementsByName("readerManaged");
      var selectedValue = selectElement[0].value;
      // 根据选中的值进行页面跳转
      switch (selectedValue) {
        case "新增读者":
          window.location.href = "./usermanaged/add_reader.jsp";
          break;
        case "修改读者":
          window.location.href = "edit_reader.html";
          break;
        case "删除读者":
          window.location.href = "delete_reader.html";
          break;
        case "查询读者":
          window.location.href = "search_reader.html";
          break;
        case "注销读者":
          window.location.href = "logout_reader.html";
          break;
        case "读者挂失":
          window.location.href = "report_loss.html";
          break;
        default:
          break;
      }
    }
    function redirectToBookPage(){
      var selectElement = document.getElementsByName("bookManaged");
      var selectedValue = selectElement[0].value;
      switch (selectedValue){
        case "图书新增":
          window.location.href = "add_book.html"
          break;
        case "图书删除":
          window.location.href = "del_book.html"
          break;
        case "图书修改":
          window.location.href = "update_book.html"
          break;
        case "图书查询":
          window.location.href = "select_book.html"
          break;
        default:
          break;
      }
    }
  </script>
</head>
<body>
<table>
  <tr> <!-- 使用 <tr> 表示表格的行 -->
    <th> <!-- 使用 <th> 表示表头单元格 -->
      <select name="readerManaged" id="options" onchange="redirectToReaderPage()">
        <option value="读者管理">读者管理</option> <!-- 设置默认选项 -->
        <option value="新增读者">新增读者</option> <!-- 选项1 -->
        <option value="修改读者">修改读者</option> <!-- 选项2 -->
        <option value="删除读者">删除读者</option> <!-- 选项3 -->
        <option value="查询读者">查询读者</option> <!-- 选项4 -->
        <option value="注销读者">注销读者</option> <!-- 选项5 -->
        <option value="读者挂失">读者挂失</option> <!-- 选项6 -->
      </select>
      <select name="bookManaged" id="options" onchange="redirectToBookPage()">
        <option value="图书管理">图书管理</option>
        <option value="图书新增">图书新增</option>
        <option value="图书删除">图书删除</option>
        <option value="图书修改">图书修改</option>
        <option value="图书查询">图书查询</option>
      </select>
      借阅管理
    </th>
  </tr>
</table>
<hr>
</body>
</html>

