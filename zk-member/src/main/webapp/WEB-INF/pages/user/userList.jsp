<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>

<html lang="zh-cn">
<head>
<title>用户列表</title>

</head>
<body>
	<div class="padding_div">
		<div class="nrq">
			<div class="padding10_div search_form">
				<form class="layui-form" id="user_list_form"
					onsubmit="return false;">
					<div class="layui-form-item" style="text-align: left;">
						<div class="layui-inline">
							<label class="layui-form-label layui-form-label53">用户名：</label>
							<div class="layui-input-inline">
								<input type="text" name="userCode" id="userCode"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label layui-form-label53" >姓名：</label>
							<div class="layui-input-inline">
								<input type="text" name="userName" id="userName"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<div class="btn_group text_align_left1" >
								<button class="layui-btn layui-btn-normal layui-btn-mini"
									data-type="reload">查询</button>
							</div>
						</div>
					</div>
				</form>

				<div class="layui-inline">
					<div class="btn_group text_align_left1" >
						<shiro:hasPermission name="useradd">
						<button class="layui-btn layui-btn-normal layui-btn-mini"
							onclick="add();">新增</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="useredit">
						<button class="layui-btn layui-btn-normal layui-btn-mini"
							onclick="edit();">编辑</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="userdel">
						<button class="layui-btn layui-btn-normal layui-btn-mini"
							onclick="del();">删除</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="userpsdupd">
						<button class="layui-btn layui-btn-normal layui-btn-mini"
							onclick="reset();">密码重置</button>
						</shiro:hasPermission>
					</div>
				</div>
			</div>
			<div class="sperator_div"></div>
			<table class="layui-hide" id="LAY_user_table" lay-filter="info_user"></table>
		</div>
	</div>

</body>
<script type="text/html" id="radioTpl">
	<input type="radio" id="id" name="id" class="singleRadio" value="{{d.id}}">
</script>
<script type="text/javascript">
layui.use([ 'table', 'layer' ], function() {
	var table = layui.table, $ = layui.jquery, layer = layui.layer;

	var searchFormHeight = layui.jquery(".search_form").height();
	searchFormHeight = 20 + searchFormHeight + 10 + 20 + 2 + 1;

	// 方法级渲染
	table.render({
		elem : '#LAY_user_table',
		url : '../user/userPage',
		height : 'full-' + searchFormHeight,
		cols : [ [ {
			field : '',
			title : '',
			templet : '#radioTpl',
			width : 40,
			align : 'center'
		}, {
			field : 'userCode',
			title : '用户名',
			width : 267,
			align : 'center'
		}, {
			field : 'userName',
			title : '姓名',
			width : 267,
			align : 'center'
		}, {
			field : 'roleName',
			title : '角色',
			width : 267,
			align : 'center'
		}, {
			field : 'deptId',
			title : '部门',
			width : 267
		} ] ],
		id : 'userTableReload',
		page : true,
		even : true,
		response : {
			// 成功的状态码，默认：0
			statusCode : 200
		}
	});

	var active = {
		reload : function() {
			var demoReload = $('#user_list_form').serialize();
			// 执行重载
			table.reload('userTableReload', {
				page : {
					curr : 1
				// 重新从第 1 页开始
				},
				where : DataDeal.fromJsonStrToJsonObj(DataDeal
						.formStrToJsonStr(demoReload))
			});
		}
	};
	$('.search_form .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
});

function add() {
	layui.layer.open({
		title : "新增用户",
		type : 2,
		content : '../user/toAddUser',
		area : [ '520px', '470px' ],
		success : function(layero, index) {}
	});
}

function reset() {
	var id = $('input:radio[name="id"]:checked').val();
	if (id != '' && id != null && 'undefined' != id) {
		layui.layer.open({
			title : "密码重置",
			type : 2,
			content : '../user/toResetUserPsd?id=' + id,
			area : [ '455px', '370px' ],
			success : function(layero, index) {}
		});
	} else {
		layer.alert('请选择需要密码重置的选项');
	}
}

function del() {
	var id = $('input:radio[name="id"]:checked').val();

	if (id != '' && id != null && 'undefined' != id) {
		layer.confirm('确定要删除此选项？', {
			btn : [ '确定', '取消' ]
		// 按钮
		}, function() {
			$.ajax({
				url : "../user/deleteUser",
				type : "POST",
				data : {
					id : id
				},
				dataType : "json",
				success : function(data) {
					if ("true" == data.result) {
						layer.alert("操作成功！", {
							closeBtn : 0
						}, function(index) {
							window.location.reload(true);
						});
					} else if ("false" == data.result) {
						layer.alert("系统角色无法删除！", {
							closeBtn : 0
						}, function() {
							parent.window.location.reload(true);
						});
					}
				}
			});
		}, function() {});
	} else {
		layer.alert('请选择需要删除的选项');
	}

}

function edit() {
	var id = $('input:radio[name="id"]:checked').val();
	if (id != '' && id != null && 'undefined' != id) {
		layui.layer.open({
			title : "编辑用户",
			type : 2,
			content : '../user/toEditUser?id=' + id,
			area : [ '520px', '470px' ],
			success : function(layero, index) {}
		});
	} else {
		layer.alert('请选择需要编辑的选项');
	}
}

</script>
</html>
