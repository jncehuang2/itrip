<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<script type="text/javascript">
    $(function () {
        $("#bt1").click(function () {
            var name = $("#name").val();
            var passwrod = $("#password").val();
            alert(name + " " + passwrod);
            $.ajax({
                "url": "http://gtlwechatmall.natapp1.cc/auth/api/dologin",
                "type": "GET",
                "data": {"name": name, "password": passwrod},
                "dataType": "json",
                "success": function (data) {
                    console.log(data);
                    if(data.success=="true"){
                        // setCookie("token", data.data.token);
                        // setCookie("expTime", data.data.expTime);
                    }else{
                        //location.href="/authDemo/tokenError.jsp";
                    }
                }
            });
        });
        $("#bt2").click(function () {
            window.location.href="http://gtlwechatmall.natapp1.cc/auth/api/wechatlogin";
        });
        $("#bt3").click(function () {
            $.ajax({
                "url": "http://gtlwechatmall.natapp1.cc/auth/api/logout",
                "type": "POST",
                "data": {},
                "dataType": "json",
                "success": function (data) {
                    console.log(data);
                }
            });
        });
    });

    function setCookie(name, value) {
        document.cookie = name + '=' + escape(value);
    }
</script>
<body>
<h2>Hello world!</h2>
<form action="http://gtlwechatmall.natapp1.cc/auth/api/dologin"
      method="post">
    <div>
        <input type="text" id="name" name="name"/>
    </div>
    <div>
        <input type="password" id="password" name="password"/>
    </div>
    <div>
        <input type="button" id="bt1" value="登录"/>
        <input type="reset" value="重置"/>
        <a href="validateToken.jsp">验证token</a>
        <a href="getUserList.jsp">获取用户列表</a>
        <a href="refrToken.jsp">置换token</a>
        <input type="button" id="bt2" value="微信登录"/>
        <input type="button" id="bt3" value="登出"/>
    </div>
</form>
</body>
</html>
