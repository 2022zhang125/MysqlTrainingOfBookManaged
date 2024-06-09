# 基于MyBatis的图书管理系统
## 功能包括
    1.图书录入
    2.图书修改
    3.图书查询
    4.图书借阅
    5.创建用户
    6.用户查询
    7.用户修改
## 读者权限
    1.图书查询
    2.图书借阅
    3.用户查询（加好友之类的）
    4.用户修改
## 第一步：创建登录表单，注册表单(包括管理员的注册)
    login.html
    register.html
    将表单发送到---> /login | /register
## 第二步：登录功能
    前端发送请求：/mysqltraining/login
        到Servlet中验证
            1.账户是否存在
            2.密码是否正确
        跳转到success页面
        跳转到mainPage核心页面

## 第三步：新增功能
    紧接上一步跳转到mainPage页面
    当用户选择新增读者时，跳转到add_reader.jsp
    选择对应的格式，然后提交表单
    到/mysqltraining/reader/add
    被ReaderManagedServlet捕获
    在经过分发功能
    给对应的add执行方法addReader()方法
    最后添加成功跳转到successAdd页面，然后跳转到mainPage核心页面

## 第四步：修改功能
    紧接上一步跳转到mainPage页面
    用户选择修改功能时跳转到edit_reader.jsp页面
    此时用户会输入一个读者ID
    当前端接收到这个ID时，会以JSON的格式发送给/mysqltraining/reader/edit/selectReaderByNo
    路径
    经过查询后以JSON格式,用fastjson转化为JSON对象，传递给前端
    --------------------------------------------------------------------------------
    前端接收到数据后自动填充表格，然后用户对已有数据进行修改操作
    最后将这个表单数据提交到/mysqltraining/reader/edit的Servlet
    经过update操作更新数据库数据。