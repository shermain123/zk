layui.use([ 'form', 'layer' ], function() {
	var $ = layui.jquery, 
	form = layui.form, 
	layer = layui.layer;

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
	var json = $("#reset_User_form").serialize();
	$.ajax({
		url : "../user/resetUserPsd",
		type : "POST",
		data : json,
		dataType : "json",
		success : function(data) {
			if ("true" == data.result) {
				layer.alert("操作成功！", {
					//offset : '80px',
					closeBtn : 0
				}, function() {
					window.parent.location.reload();
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				});
			} else if ("false" == data.result) {

			}
		}
	})
}