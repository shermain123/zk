layui.use([ 'form', 'layer' ], function() {
	var $ = layui.jquery,
	form = layui.form,
	layer = layui.layer;

	form.verify({
		userName : function(value) {
			if ($.trim(value).length <= 0) {
				return "姓名不能为空";
			}
		},

		userCode : function(value) {
			if ($.trim(value).length <= 0) {
				return "用户名不能为空";
			}
		},
		
		roleId : function(value) {
			if ($.trim(value).length <= 0) {
				return "请选择角色属性";
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
		url : "../user/editUser",
		type : "POST",
		data : json,
		dataType : "json",
		success : function(data) {
			if ("true" == data.result) {
				layer.alert("操作成功！", {
					//offset : '100px',
					closeBtn : 0
				}, function() {
					parent.window.location.reload(true);
				});
			} else if ("false" == data.result) {}
		}
	})
}
