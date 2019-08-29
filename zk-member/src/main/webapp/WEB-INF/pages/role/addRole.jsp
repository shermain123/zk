<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>

<html lang="zh-cn">
<head>
<title>新增角色</title>
</head>
<body>
	<div class="padding_div">
		<div class="nrq">
			<form class="layui-form tip_form" id="info_list_form" onsubmit="return false;">
				<div id="self_cust" class="padding_div" style="clear: left;">
					<fieldset class="layui-elem-field">
						<div class="tab_s layui-field-box">
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label"><span style='color: red;'>*</span>&nbsp;&nbsp;角色名：</label>
									<div class="layui-input-inline">
										<input type="text" id="roleName" name="roleName"
											lay-verify="roleName" maxlength="100" autocomplete="off" class="layui-input" />
									</div>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label"><span style='color: red;'>*</span>&nbsp;&nbsp;角色标识：</label>
									<div class="layui-input-inline">
										<input type="text" id="roleLogo" name="roleLogo"
											lay-verify="roleLogo" maxlength="100" autocomplete="off" class="layui-input" />
									</div>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label"><span style='color: red;'>*</span>&nbsp;&nbsp;描述：</label>
									<div class="layui-input-inline layui-input-inline300" >
										<textarea rows="6" cols="50" name="description" id="description" lay-verify="description" class="layui-textarea">${role.description }</textarea>
									</div>
								</div>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block text_align_right" >
						<button class="layui-btn layui-btn-normal layui-btn-small" lay-submit="" lay-filter="save">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
<script type="text/javascript">
layui.use([ 'form', 'layer' ], function() {
	var $ = layui.jquery,
	form = layui.form,
	layer = layui.layer;

	form.verify({
		roleName : function(value) {
			if ($.trim(value).length <= 0) {
				return "角色名不能为空";
			}
		},
		roleLogo : function(value) {
			if ($.trim(value).length <= 0) {
				return "角色标识不能为空";
			}
		},
		description : function(value) {
			if ($.trim(value).length <= 0) {
				return "描述不能为空";
			}
		}
	});

	// 监听提交
	form.on('submit(save)', function(data) {
		save();
		return false;
	});
});

function save() {
	var json = $("#info_list_form").serialize();
	$.ajax({
		url : "../role/roleAdd",
		type : "POST",
		data : json,
		dataType : "json",
		success : function(data) {
			if ("true" == data.result) {
				layer.alert("添加成功！", {
					closeBtn : 0
				}, function() {
					parent.window.location.reload(true);
				});
			} else if ("false" == data.result) {
				layer.alert("角色标识已被占用！", {
					closeBtn : 0
				}, function() {
					window.location.reload(true);
				});
			}
		}
	})
}
</script>
</body>
</html>
