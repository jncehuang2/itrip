<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#div_register input[name='userCode']").blur(function () {
            var userCode = $("#div_register input[name='userCode']").val();
            $.ajax({
                "url": "http://gtlwechatmall.natapp1.cc/auth/api/ckusrToPhone.do",
                "type": "POST",
                "data": {"name": userCode},
                "dataType": "json",
                "success": function (data) {
                    console.log(data);
                }
            });
        });

        $("#tijiao").click(function () {
            $.ajax({
                "url": "http://gtlwechatmall.natapp1.cc/auth/api/register.do",
                "type": "POST",
                "data": {
                    "userCode": $("#div_register input[name='userCode']").val(),
                    "userName": $("#div_register input[name='userName']").val(),
                    "userPassword": $("#div_register input[name='userPassword']").val(),
                    "userType":0
                },
                "dataType": "json",
                "success": function (data) {
                    console.log(data);
                    if (data.success == "true") {
                        console.log("注册成功，激活码发送成功");
                        $("#div_activate").css("display", "block");
                    } else {
                        console.log("注册失败，激活码发送失败");
                        $("#div_activate_fail").html("注册失败，激活码发送失败");
                    }
                }
            });
        });

        $("#activateTijiao").click(function () {
            var user = $("#div_activate input[name='user']").val();
            var code = $("#div_activate input[name='code']").val();

            alert(user);
            alert(code);
            $.ajax({
                "url": "http://gtlwechatmall.natapp1.cc/auth/api/activate",
                "type": "POST",
                "data": {"userCode": user, "code": code},
                "dataType": "json",
                "success": function (data) {
                    console.log(data);
                    if (data.success=="true") {
                        window.location.href="http://gtlwechatmall.natapp1.cc/auth";
                    }

                }
            });
        });

        $("#activateId").click(function () {
            $("#div_activate").css("display", "block");
        })
    });
</script>
<body>
<div id="div_register">
    <form>
        <table align="center">
            <tr>
                <td>注册手机号码:</td>
                <td><input type="text" name="userCode"></td>
            </tr>
            <tr>
                <td>昵称:</td>
                <td><input type="text" name="userName"></td>
            </tr>
            <tr>
                <td>登录密码:</td>
                <td><input type="text" name="userPassword"></td>
            </tr>
            <tr>
                <td>确认密码:</td>
                <td><input type="text" name="userPassword2"></td>
            </tr>
            <tr>
                <td><input type="button" id="tijiao" value="提交">
                    <input type="reset" value="重置"></td>
                <td><input type="button" id="activateId" value="激活已经有账号"></td>
            </tr>
        </table>
    </form>
</div>
<div id="div_activate" style="display: none">
    <form>
        <table align="center">
            <tr>
                <td>激活账号:</td>
            </tr>
            <tr>
                <td>电话号码:</td>
                <td><input type="text" name="user"></td>
            </tr>
            <tr>
                <td>激活码:</td>
                <td><input type="text" name="code"></td>
            </tr>
            <tr>
                <td><input type="button" id="activateTijiao" value="提交"></td>
                <td><input type="reset" value="重置"></td>
            </tr>
        </table>
    </form>
</div>
<div id="div_activate_fail"></div>
</body>
</html>
