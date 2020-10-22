<%--
  Created by IntelliJ IDEA.
  User: 蔡小蔚
  Date: 2020/10/20
  Time: 18:41
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

    <%
        String baseurl = null;
    %>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
      </span>
    <a class="layui-btn layui-btn-primary layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:38px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input class="layui-input" placeholder="开始日" name="start" id="start">
            <div class="layui-input-inline">
                <select name="contrller">
                    <option value="">请假信息状态</option>
                    <option value="0">待通过</option>
                    <option value="1">已通过</option>
                    <option value="2">未通过</option>
                </select>
            </div>
        </form>
    </div>
    <xblock>
<%--        <button class="layui-btn" onclick="x_admin_show('添加用户','${pageContext.request.contextPath}/html/order-add.html')"><i class="layui-icon"></i>添加</button>--%>
<%--        <button class="layui-btn" onclick="x_admin_show()">--%>
<%--            <i class="layui-icon"></i>--%>
<%--            <a _href="${pageContext.request.contextPath}/form/displayVocation">添加</a>--%>
<%--        </button>--%>
        <nav>
            <i class="layui-icon"></i>
            <a href="${pageContext.request.contextPath}/form/displayVocation">添加</a>
        </nav>


        <span class="x-right" style="line-height:40px">共有数据：${vocationlist.size() + 1}条</span>
    </xblock>
    <table class="layui-table">
        <thead>
<%--        <tr>--%>
<%--            <th>--%>
<%--                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>--%>
<%--            </th>--%>
            <th>申请人姓名</th>
            <th>申请状态</th>
            <th>申请创建时间</th>
            <th>开始时间</th>
            <th>持续时间</th>
            <th>请假事由</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>stu</td>
            <td>待通过</td>
            <td>2020-09-10 18:23</td>
            <td>2020-10-01 00:00</td>
            <td>8天</td>
            <td>nothing to do</td>
<%--            <td class="td-manage">--%>
<%--                <a title="查看"  onclick="x_admin_show('编辑','order-view.html')" href="javascript:">--%>
<%--                    <i class="layui-icon">&#xe63c;</i>--%>
<%--                </a>--%>
<%--            </td>--%>
        </tr>
        <c:if test="${not empty vocationlist}">
            <c:forEach var="vocation" items="${vocationlist}" varStatus="c">
                <tr>
                    <td>${vocation.nick}</td>
                    <c:if test="${vocation.formstatus == 0}"><td>待通过</td></c:if>
                    <c:if test="${vocation.formstatus == 1}"><td>已通过</td></c:if>
                    <c:if test="${vocation.formstatus == 2}"><td>未通过</td></c:if>
                    <td>${vocation.createdate}</td>
                    <td>${vocation.starttime}</td>
                    <td>${vocation.lasttime}天</td>
                    <td>${vocation.descript}</td>
<%--                    <td class="td-manage">--%>
<%--                        <a title="查看"  onclick="x_admin_show('编辑','order-view.html')" href="javascript:">--%>
<%--                            <i class="layui-icon">&#xe63c;</i>--%>
<%--                        </a>--%>
<%--                    </td>--%>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="page">
        <div>
            <a class="prev" href="">&lt;&lt;</a>
            <span class="current">1</span>
            <a class="next" href="">&gt;&gt;</a>
        </div>
    </div>

</div>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });

    /*用户-停用*/
    function member_stop(obj,id){
        layer.confirm('确认要停用吗？',function(index){

            if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

            }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
            }

        });
    }

    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            //发异步删除数据
            $(obj).parents("tr").remove();
            layer.msg('已删除!',{icon:1,time:1000});
        });
    }



    function delAll (argument) {

        var data = tableCheck.getData();

        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }
</script>
</body>

</html>