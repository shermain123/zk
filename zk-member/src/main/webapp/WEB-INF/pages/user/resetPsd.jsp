<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>

<html lang="zh-cn">
<head>
<title>密码重置</title>
</head>
<body>
	<div class="padding_div">
		<div class="nrq">
			<div class="padding10_div tip_form">
				<form class="layui-form " id="info_list_form"
					onsubmit="return false;">
					<input type="hidden" id="id" name="id" value="${user.id}" />
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">用户名：</label>
							<div class="layui-input-inline">
								<input type="text" id="userCode" name="userCode"
									readonly="readonly" style="border: none;" maxlength="100"
									autocomplete="off" class="layui-input" value="${user.userCode}" />
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">姓名：</label>
							<div class="layui-input-inline">
								<input type="text" id="userName" name="userName"
									readonly="readonly" style="border: none;" maxlength="100"
									autocomplete="off" class="layui-input" value="${user.userName}" />
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label"><span style='color: red;'>*</span>&nbsp;&nbsp;密码：</label>
							<div class="layui-input-inline">
								<input type="password" id="passWord" name="passWord"
									lay-verify="passWord" maxlength="100" autocomplete="off"
									class="layui-input" />
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label"><span style='color: red;'>*</span>&nbsp;&nbsp;确认密码：</label>
							<div class="layui-input-inline">
								<input type="password" id="rePassWord" name="rePassWord"
									lay-verify="rePassWord" maxlength="100" autocomplete="off"
									class="layui-input" />
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block" style="margin-left: 323px;">
							<button class="layui-btn layui-btn-normal layui-btn-small"
									lay-submit="" lay-filter="save">保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	layui.use([ 'form', 'layer' ], function() {
		var $ = layui.jquery, form = layui.form, layer = layui.layer;

		form.verify({
			passWord : function(value) {
				if (value.length <= 0) {
					return "密码不能为空";
				}
			},
			rePassWord : function(value) {
				var passWord = $('#passWord').val();

				if (value != passWord) {
					return "两次密码输入不同";
				}
			}
		});

		//监听提交
		form.on('submit(save)', function(data) {
			save();
			return false;
		});

	});
	function save() {
		var json = $("#info_list_form").serialize();
		$.ajax({
			url : "../user/resetPsd",
			type : "POST",
			data : json,
			dataType : "json",
			success : function(data) {
				if ("true" == data.result) {
					layer.alert("操作成功！", {
						//offset : '200px',
						closeBtn : 0
					}, function() {
						window.location.reload(true);
					});
				} else if ("false" == data.result) {

				}
			}
		})
	}
</script>
</html>
