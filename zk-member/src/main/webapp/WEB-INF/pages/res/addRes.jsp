<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script>
var id;
$(function(){
	id = '${id}';
});
</script>
</head>
<body>
	<div id="all" class="padding_div">
		<div class="nrq">
			<form class="layui-form tip_form" id="add_res_form">
				<div id="self_res" class="padding_div" style="clear: left;">
					<fieldset class="layui-elem-field">
						<div class="tab_s layui-field-box" >
							<div class="layui-form-item">
						    	<div class="layui-inline">
						    		<label class="layui-form-label" style="text-align: right;"><span style='color: red;'>*</span>&nbsp;&nbsp;资源名称：</label>
					      			<div class="layui-input-inline">
						        		<input type="text" id="resName" name="resName" maxlength="100" autocomplete="off" class="layui-input" lay-verify="resName" />
						     		</div>
						    	</div>
						    </div>
						    
						    <div class="layui-form-item">
						    	<div class="layui-inline">
						      		<label class="layui-form-label" style="text-align: right;"><span style='color: red;'>*</span>&nbsp;&nbsp;资源类型：</label>
					      			<div class="layui-input-inline">
										<select id="resType" name="resType" lay-verify="resType">
											<option value="">请选择</option>
											<option value="1">菜单</option>
											<option value="2">按钮</option>
										</select>						     		
									</div>
						    	</div>
						    </div>
						    
						    <div class="layui-form-item">
						    	<div class="layui-inline">
						      		<label class="layui-form-label" style="text-align: right;">资源路径：</label>
					      			<div class="layui-input-inline">
						        		<input type="text" id="url" name="url" maxlength="100" autocomplete="off" class="layui-input" />
						     		</div>
						    	</div>
						  	</div>
						  	
						  	<div class="layui-form-item">
						    	<div class="layui-inline">
						      		<label class="layui-form-label" style="text-align: right;"><span style='color: red;'>*</span>&nbsp;&nbsp;资源标识：</label>
					      			<div class="layui-input-inline">
						        		<input type="text" id="resLogo" name="resLogo" maxlength="100" autocomplete="off" class="layui-input" lay-verify="resLogo"/>
						     		</div>
						    	</div>
						  	</div>
						  	
						  	<div class="layui-form-item">
						    	<div class="layui-inline">
						      		<label class="layui-form-label" style="text-align: right;"><span style='color: red;'>*</span>&nbsp;&nbsp;资源排序：</label>
					      			<div class="layui-input-inline">
						        		<input type="text" id="resOrder" name="resOrder" maxlength="100" autocomplete="off" class="layui-input" lay-verify="resOrder"/>
						     		</div>
						    	</div>
						  	</div>
						  	
						</div>
					</fieldset>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block text_align_right">
						<button class="layui-btn layui-btn-small layui-btn-normal" lay-submit="" lay-filter="save">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	
<script type="text/javascript">
layui.config({
	base : 'layui/build/js/'
}).use([ 'form', 'layer', 'layedit' ],function() {
	var $ = layui.jquery, form = layui.form, layer = layui.layer, layedit = layui.layedit;
	
	form.verify({
		resName : function(value) {
			if ($.trim(value).length <= 0) {
				return "资源名称不能为空";
			}
		},
		resType : function(value) {
			if ($.trim(value).length <= 0) {
				return "请选择资源类型";
			}
		},
		resLogo : function(value) {
			if ($.trim(value).length <= 0) {
				return "资源标识不能为空";
			}
		},
		resOrder : function(value) {
			if ($.trim(value).length <= 0
					|| !testNumber($.trim(value))) {
				return "资源排序编号必须为数字";
			}
		}
	});
	
	// 监听提交
	form.on('submit(save)', function(data) {
		save();
		return false;
	});
});

function save(){
	var json = $("#add_res_form").serialize();
	$.ajax({
		url : "../res/addRes?parentId=" + id,
		type : "POST",
		data : json,
		dataType : "json",
		success : function(data) {
			if ("true" == data.result) {
				layer.alert("操作成功！", {
					//offset : '80px',
					closeBtn : 0
				}, function() {
					parent.window.location.reload(true);
				});
			} else if ("false" == data.result) {
				layer.alert("资源标识已被使用！", {
					//offset : '80px',
					closeBtn : 0
				}, function(index) {
					layer.close(index);
				});
			}
		}
	})
	
}
</script>
</body>
</html>