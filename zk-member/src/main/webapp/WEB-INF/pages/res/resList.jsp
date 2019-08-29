<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>路径管理页面</title>
	<script src="${ctx }/js/treetable/js/jquery.treetable.js" type="text/javascript"> </script>
	<link href='${ctx }/js/treetable/css/jquery.treetable.css' rel='stylesheet' type='text/css' />
	<link href='${ctx }/js/treetable/css/jquery.treetable.theme.default.css' rel='stylesheet' type='text/css' />


</head>
<body>
	<div class="padding_div" >
		<div class="nrq">
			<div class="padding10_div search_form">
				<div class="layui-inline" >
					<div class="btn_group text_align_left1">
						<input type="button" onclick="jQuery('#treeTable1').treetable('expandAll'); return false;"  value="展开" class="layui-btn layui-btn-normal layui-btn-mini"/>
						<input type="button" onclick="jQuery('#treeTable1').treetable('collapseAll'); return false;" value="合并" class="layui-btn layui-btn-normal layui-btn-mini"/>
					</div>
				</div>
			</div>
			
			<div class="tab_x" style="padding: 10px 10px 0px;">
				<table id="treeTable1" >
					<thead>
			          <tr>
			            <th>资源名称</th>
			            <th>资源标识</th>
			            <th>类型</th>
			            <th>url</th>
			            <th>排序</th>
			            <th style="text-align: center;">操作</th>
			          </tr>
			        </thead>
			        <tbody>
			        	<c:forEach items="${list }" var="res" varStatus="i">
			        		<c:if test="${res.resLevel == 1 }">
			        			<tr data-tt-id='${res.resId }' id='1'>
			        		</c:if>
			        		<c:if test="${res.resLevel != 1 }">
			        			<tr data-tt-id='${res.resId }' data-tt-parent-id='${res.parentId }' id="${res.resId }" >
			        		</c:if>
			        			<c:if test="${res.resType == 1}">
									<td><span class='folder'>${res.resName }</span></td>
									<td><span >${res.resLogo }</span></td>
									<td><span >菜单</span></td>
								</c:if>
								<c:if test="${res.resType == 2}">
									<td><span class='file'>${res.resName }</span></td>
									<td><span >${res.resLogo }</span></td>
									<td><span >按钮</span></td>
								</c:if>
								
								<td><span >${res.url }</span></td>
								<td><span >${res.resOrder }</span></td>
								
								<td>
									<shiro:hasPermission name="resadd">
									<c:if test="${res.resLevel != 5 }">
										<button class="layui-btn layui-btn-normal layui-btn-mini" lay-submit="" onclick="addRes('${res.resId}');">新增</button>
									</c:if>
									</shiro:hasPermission>
									<shiro:hasPermission name="resedit">
										<button class="layui-btn layui-btn-normal layui-btn-mini" lay-submit="" onclick="editRes('${res.resId}');">编辑</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="resdel">
										<button class="layui-btn layui-btn-normal layui-btn-mini" lay-submit="" onclick="delRes('${res.resId}');">删除</button>
									</shiro:hasPermission>
								</td>
			        		
			        		</tr>
			        	</c:forEach>
			        </tbody>
				</table>
			</div>
		</div>
	</div>
<script type="text/javascript" >
	layui.config({
		base : 'layui/build/js/'
	}).use([ 'element', 'table', 'layer' ], function() {
		var $ = layui.jquery, table = layui.table, element = layui.element, // Tab的切换功能，切换事件监听等，需要依赖element模块
		layer = layui.layer;
	});
	//树形节点加载
	$(function(){
	    var option = {
	        theme:'vsStyle',
	        expandLevel : 2,
	        expandable : true,
	        beforeExpand : function($treeTable, id) {
	            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
	            if ($('.' + id, $treeTable).length) { return; }
	            //这里的html可以是ajax请求
	            var html = '<tr id="8" pId="6"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
	                     + '<tr id="9" pId="6"><td>5.2</td><td>动态的内容</td></tr>';

	            $treeTable.addChilds(html);
	        },
	        onSelect : function($treeTable, id) {
	            window.console && console.log('onSelect:' + id);
	            
	        }

	    };
	    $('#treeTable1').treetable(option);
		// $('#example-advanced').treetable("expandNode", "1");
		//默认展开
		$('#treeTable1').treetable('expandAll');
	});
	
	//打开弹出层
	function addRes(resId){
		layui.layer.open({
			title : "新增资源",
			type : 2,
			content : '../res/toAddRes?id=' + resId,
			//offset : '250px',
			area : [ '600px', '420px' ],
			success : function(layero, index) {
			}
		});
	}
	
	//编辑页面
	function editRes(id) {
		layui.layer.open({
			title : "编辑资源",
			type : 2,
			content : '../res/toEditRes?id=' + id,
			//offset : '250px',
			area : [ '600px', '420px' ],
			success : function(layero, index) {
			}
		});
	}
	
	//删除
	function delRes(id) {
		var parentTr = $("#" + id);
		if (parentTr.hasClass("branch")) {
			layer.alert("请先删除子级目录", {
				//offset : '250px',
				closeBtn : 0
			}, function(index) {
				layer.close(index);
			});
			return;
		} else if (parentTr.hasClass("leaf")) {
			layer.confirm('删除此资源，同时删除角色对应的此资源？', {
				btn : [ '确定', '取消' ], // 按钮
				//offset : '250px',
				closeBtn : 0
			}, function() {
				$.ajax({
					url : "../res/deleteRes",
					type : "POST",
					data : {
						id : id
					},
					dataType : "json",
					success : function(json) {
						if ("false" == json.result) {
							layer.alert("系统资源无法删除", {
								//offset : '200px',
								closeBtn : 0
							}, function(index) {
								layer.close(index);
							});
						} else {
							layer.alert("操作成功", {
								//offset : '200px',
								closeBtn : 0
							}, function() {
								$('#treeTable1').treetable("removeNode", id);
								window.location.reload(true);
							});
						}
					}
				});
			}, function() {});
		}
	}
	
</script>
</body>

</html>