<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/js.jsp"%>
<html lang="en">
<head>
	<title>重新登录</title>
	<script>
	
        layui.config({
            base: '/layui/build/js/'
        }).use(['layer'], function() {
            var layer = top.layui.layer;
            layer.alert('会话过期或未登录,请关闭当前页面!', {icon: 3, title:'提示', }, function(index){
           		window.top.location.href = '${ctx}/';
           	});
        });

    </script>
</head>

<body>
</body>
</html>
