<%--
  Created by IntelliJ IDEA.
  User: 蔡小蔚
  Date: 2020/7/21
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="utils.jsp"%>
<html>
<head>
    <title>Login</title>
    <style type="text/css">
        .formFieldError{background-color: brown}
    </style>
    <style>
        *{
            margin: 0px;
            padding: 0px;
            box-sizing: border-box;
        }
        body{
            background: antiquewhite center;
        }
        a{
            text-decoration: none;
            color: beige;
        }
        .layout{
            width: 900px;
            height: 500px;
            border: 5px solid #EEEEEE;
            background-color: white;
            margin: auto;
        }
        .left{
            float: left;
            margin: 20px;
        }
        .left>p:first-child{
            color: #FFD026;
            font-size: 20px;
        }
        .left>p:last-child{
            color:#A6A6A6;
            font-size: 20px;
            margin-top: 15px;
        }
        .center{
            float: left;
            width: 450px;
            margin-left: 50px;
            margin-top: 100px;

        }
        .right{
            float: right;
            margin: 20px;
            font-size: 15px;
            font-family: "Microsoft YaHei UI", serif;
            color: #A6A6A6;
        }
        .td_left{
            width: 100px;
            text-align: right;
            height: 45px;
            font-family: "Microsoft YaHei UI", serif;
        }
        .td_right{
            padding-left:30px;
        }
        #username,#password,#email,#email,#name,#tel,#birthday{
            width: 201px;
            height: 28px;
            border: 1px solid #A6A6A6;
            border-radius: 5px;
            padding-left: 5px;
        }
        #sub{
            width: 130px;
            height: 40px;
            background-color: #FFD026;
            border: 1px solid #FFD026;
            margin-top: 20px;
            font-size: 15px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <div class="layout">
        <div class="center">
            <%--@elvariable id="user" type="java"--%>
            <mvc:form modelAttribute="user" action="${pageContext.request.contextPath}/login">
                <table>
                    <tr>
                        <td class="td_left"><mvc:label path="nick">Name:</mvc:label></td>
                        <td class="td_right"><mvc:input path="nick"/></td>
                    </tr>
                    <tr>
                        <td class="td_left"><mvc:label path="password">Password:</mvc:label></td>
                        <td class="td_right"><mvc:password path="password"/></td>
                    </tr>
                    <tr>
                        <td align="center">
                            <input type="submit" value="登录" id="sub"/>
                        </td>
                    </tr>
                </table>
            </mvc:form>
        </div>
    </div>


</body>
</html>
