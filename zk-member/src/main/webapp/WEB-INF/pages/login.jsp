<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆页面</title>
<style type="text/css">
	.box{height:300px;width:435px;border:1px solid blue;margin:200px auto;padding:20px;}
</style>
</head>
<body>
	<div class="box">
		<form class="layui-form" id="edit_tradeInfo_form" action="${ctx }/user/login" method="post" >
			<div class="layui-form-item">
				<label class="layui-form-label">用户名：</label>
			    <div class="layui-input-inline">
			      <input type="text" name="userCode" placeholder="请输入用户名" autocomplete="off" lay-verify="" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密&nbsp;&nbsp;码:</label>
			    <div class="layui-input-inline">
			      <input type="password" name="passWord" placeholder="请输入密码" autocomplete="off" lay-verify="" class="layui-input">
			    </div>
			</div>
			<div class="layui-input-block" style="min-height: 30px;float: right;padding-right:10px;">
				<button class="layui-btn layui-btn-small layui-btn-normal" lay-submit="" lay-filter="login">提交</button>
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
		layui.config({base : 'layui/build/js/'}).use(['form','layer','layedit','laydate'],function(){
			var $ = layui.jquery,
			form = layui.form,
			layer = layui.layer,
			layedit = layui.layedit,
			laydate = layui.laydate;
			
			form.on("submit(login)",function(){
				//layer.alert('登录');
			})
			
		})
</script>
</body>
</html>