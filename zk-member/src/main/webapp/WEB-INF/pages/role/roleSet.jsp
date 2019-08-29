<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<html>
<head>
<title>角色授权</title>
<link rel="stylesheet" type="text/css" href="${ctx}/easyUI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/easyUI/themes/icon.css">
<script type="text/javascript" src="${ctx}/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript">
	layui.config({
	    base: 'layui/build/js/'
	}).use('layer', function(){
		var $ = layui.jquery, layer = layui.layer;
		
		var listJSON = jQuery.parseJSON('${listJSON}');
		var json = convert(listJSON);
	
		$('#tree').tree({
			data: json,
			lines:true,
			checkbox:true
		});
	});
		
</script>
<style type="text/css">
	#tree{overflow-y:scroll;height: 85%;border: 1px solid #ccc;}
	.button_group{padding: 5px 0 10px 0;}
</style>
</head>

<body>
	<div id="all" class="padding_div">
		<div class="nrq">
			<div class="layui-row">
			    <div class="">
			    	<div class="padding_div">
						<ul id="tree"></ul>
					</div>
			    </div>
		  	</div>
			<div style="float:right;padding:0px 10px 5px 0px;">
				<button class="layui-btn layui-btn-normal layui-btn-mini" id="roleSet">确认</button>
				<button class="layui-btn layui-btn-normal layui-btn-mini" id="close">关闭</button>
			</div>
		</div>
	</div>
</body>
<script>
layui.config({
    base: 'layui/build/js/'
}).use(['layer'], function(){
	var $ = layui.jquery, layer = layui.layer;
	$("#close").click(function(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	});
	
	$("#roleSet").click(function(){
		var node = $("#tree").tree("getChecked");
		var resIds = new Array();
		$(node).each(function(index,ele){
			resIds[index] = ele.id;
		});
		$.ajax({
			url:"${ctx}/role/setRoleRes?roleId=${roleId}",
			dataType:"json",
			data:{"resIds":resIds},
			type: "POST",  
			traditional: true,  
			success:function(data){
				if(data.result=="true"){
					layer.alert("授权成功!",{
						offset:"5px",closeBtn:0,resize:false,move: false
					},function(){ 
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
						window.parent.location.reload();
					}); 
				}else{
					layer.alert("授权失败!");
				}
			}
		});
	});
});

function convert(rows) {
	function exists(rows, parentId) {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].id == parentId)
				return true;
		}
		return false;
	}
	var nodes = [];
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row.parentId)) {
			nodes.push({
				id : row.resId,
				text : row.resName,
				checked : row.check
			});
		}
	}
	var toDo = [];
	for (var i = 0; i < nodes.length; i++) {
		toDo.push(nodes[i]);
	}
	while (toDo.length) {
		var node = toDo.shift(); 
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row.parentId == node.id) {
				var child = {
					id : row.resId,
					text : row.resName,
					checked : row.check
				};
				if (node.children) {
					node.children.push(child);
				} else {
					node.children = [ child ];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
};
</script>
</html>                                                                                
