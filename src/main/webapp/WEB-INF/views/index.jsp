<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: 蔡小蔚
  Date: 2020/10/19
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="utils.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>流程管理平台</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">

    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>

</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>

    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <%
                Cookie[] cookies = request.getCookies();
                String nick = null;
                boolean hasUser = false;
                if (cookies!=null){
                    for (Cookie cookie:cookies){
                        if (cookie.getName().equalsIgnoreCase("nick")){
                            nick = URLDecoder.decode(cookie.getValue(),"utf-8");
                            if(!nick.equals(null)){
                                hasUser=true;
                            }
                        }
                    }
                }
            %>

            <%
                if (hasUser){
            %>
            <a href="javascript:"><%= nick%></a>
            <%
            }else{}
            %>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onClick="x_admin_show('个人信息','http://www.baidu.com')">个人信息</a></dd>
                <dd><a href="${pageContext.request.contextPath}/login.html">退出</a></dd>
            </dl>
        </li>
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <%
                if (!nick.equals("root")){
            %>
            <li>
                <a href="javascript:">
                    <i class="iconfont">&#xe6b2;</i>
                    <cite>请假管理</cite>
                    <i class="iconfont nav_right">&#xe6a7;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <%--                        <a _href="${pageContext.request.contextPath}/html/order-list.html">--%>
                        <a _href="${pageContext.request.contextPath}/vacationinfo">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>请假信息列表</cite>
                        </a>
                    </li >
                </ul>
            </li>
            <li >
                <a href="javascript:">
                    <i class="iconfont">&#xe6eb;</i>
                    <cite>主页</cite>
                    <i class="iconfont nav_right">&#xe6a7;</i>
                </a>
                <ul class="sub-menu">
                    <li><a _href="${pageContext.request.contextPath}/html/welcome.html"><i class="iconfont">&#xe6a7;</i><cite>控制台</cite></a></li >
                </ul>
            </li>
            <%
                }
            %>
            <%
                if (nick.equals("root")){
            %>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b2;</i>
                    <cite>流程管理(root)</cite>
                    <i class="iconfont nav_right">&#xe6a7;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${pageContext.request.contextPath}/flowdefine/vocationflow">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>请假流程</cite>
                        </a>
                    </li >
                </ul>
            </li>
            <%
                }
            %>


        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='${pageContext.request.contextPath}/html/welcome.html' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<!--<div class="footer">
    <div class="copyright">Copyright ©2019 L-admin v2.3 All Rights Reserved</div>
</div>-->
<!-- 底部结束 -->
</body>
</html>