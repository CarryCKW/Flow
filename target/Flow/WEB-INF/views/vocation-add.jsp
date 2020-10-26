<%--
  Created by IntelliJ IDEA.
  User: 蔡小蔚
  Date: 2020/10/22
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="utils.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-L-admin1.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-body">
    <mvc:form modelAttribute="newVocation" action="${pageContext.request.contextPath}/form/addVocation" cssClass="layui-form" >
        <div class="layui-form-item">
            <label class="layui-form-label">
                <span class="x-red">*</span><mvc:label path="nick">申请人姓名</mvc:label>
            </label>
            <div class="layui-input-inline">
<%--                <input type="text" id="username" name="nickname" class="layui-input">--%>
                <mvc:input path="nick"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                <span class="x-red">*</span><mvc:label path="stime">开始时间</mvc:label>
            </label>
            <div class="layui-input-inline">
<%--                <input type="date" id="starttime" name="starttime" class="layui-input">--%>
                <mvc:input path="stime"/>
            </div>
            <label class="layui-form-label">
                <span class="x-red">yyyy-MM-dd HH:mm:ss</span>
            </label>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                <span class="x-red">*</span><mvc:label path="lasttime">持续时间</mvc:label>
            </label>
            <div class="layui-input-inline">
<%--                <input type="text" id="lasttime" name="lasttime" class="layui-input">--%>
                <mvc:input path="lasttime"/>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">
                <mvc:label path="descript">描述</mvc:label>
            </label>
            <div class="layui-input-block">
<%--                <textarea placeholder="请输入内容" id="desc" name="desc" class="layui-textarea"></textarea>--%>
                <mvc:input path="descript" />
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
            </label>
<%--            <button  class="layui-btn" lay-submit lay-filter="add" >--%>
<%--                增加--%>
<%--            </button>--%>
            <input type="submit" value="新增" id="sub"/>
        </div>
    </mvc:form>


<%--    <form class="layui-form">--%>
<%--        <div class="layui-form-item">--%>
<%--            <label for="username" class="layui-form-label">--%>
<%--                <span class="x-red">*</span>申请人姓名--%>
<%--            </label>--%>
<%--            <div class="layui-input-inline">--%>
<%--                <input type="text" id="username" name="nickname" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="layui-form-item">--%>
<%--            <label for="username" class="layui-form-label">--%>
<%--                <span class="x-red">*</span>开始时间--%>
<%--            </label>--%>
<%--            <div class="layui-input-inline">--%>
<%--                <input type="date" id="starttime" name="starttime" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="layui-form-item">--%>
<%--            <label for="username" class="layui-form-label">--%>
<%--                <span class="x-red">*</span>持续时间--%>
<%--            </label>--%>
<%--            <div class="layui-input-inline">--%>
<%--                <input type="text" id="lasttime" name="lasttime" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="layui-form-item layui-form-text">--%>
<%--            <label for="desc" class="layui-form-label">--%>
<%--                描述--%>
<%--            </label>--%>
<%--            <div class="layui-input-block">--%>
<%--                <textarea placeholder="请输入内容" id="desc" name="desc" class="layui-textarea"></textarea>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="layui-form-item">--%>
<%--            <label class="layui-form-label">--%>
<%--            </label>--%>
<%--            <button  class="layui-btn" lay-submit lay-filter="add" >--%>
<%--                增加--%>
<%--            </button>--%>
<%--        </div>--%>
<%--    </form>--%>
</div>