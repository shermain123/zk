<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>

<html lang="zh-cn">
<head>
<title>角色列表</title>
<script type="text/javascript" src="${ctx }/js/role/roleList.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx }/js/easyui-1.5.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx }/js/easyui-1.5.1/themes/icon.css">
<script type="text/javascript" src="${ctx }/js/easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/easyui-1.5.1/jquery.easyui.min.js"></script>

</head>
<body>
	<div class="padding_div">
		<div class="nrq">
			<div class="padding10_div search_form">
				<form class="layui-form" id="role_list_form"
					onsubmit="return false;">
					<div class="layui-form-item" style="text-align: left;">
						<div class="layui-inline">
							<label class="layui-form-label layui-form-label53" >角色名：</label>
							<div class="layui-input-inline">
								<input type="text" name="roleName" id="roleName"
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
						<shiro:hasPermission name="roleedit">	
						<button class="layui-btn layui-btn-normal layui-btn-mini"
							onclick="add();">新增</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="roleedit">	
						<button class="layui-btn layui-btn-normal layui-btn-mini"
							onclick="edit();">编辑</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="roledel">
						<button class="layui-btn layui-btn-normal layui-btn-mini"
							onclick="del();">删除</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="roleset">
						<button class="layui-btn layui-btn-normal layui-btn-mini"
							data-type="roleSet">权限设置</button>
						</shiro:hasPermission>
					</div>
				</div>
			</div>
			<div class="sperator_div"></div>
			<table class="layui-hide" id="LAY_role_table" lay-filter="info_user"></table>
		</div>
		<div id='resourceDialog' style='width: 350px; height: 500px; text-align: left; display: none;'>
			<ul id="resourceTress" class="easyui-tree"></ul>
		</div>
	</div>
</body>
<script type="text/html" id="radioTpl">
	<input type="radio" id="id" name="id" class="singleRadio" value="{{d.id}}">
</script>

<script type="text/html" id="flagTpl">
	{{#  if(d.flag == '1'){ }}
  		{{'是'}}
	{{#  } else if(d.flag == '0'){ }}
  		{{ '否'}}
	{{#  } }}
</script>

<script type="text/html" id="updateTime">
	{{ dateToStr(d.updateTime,true,false)}}
</script>
</html>
