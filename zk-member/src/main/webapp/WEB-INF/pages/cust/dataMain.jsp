<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<html lang="zh-cn">
	<head>
		<title>资料库</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/easyUI/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/easyUI/themes/icon.css">
		<script type="text/javascript" src="${ctx}/easyUI/jquery.easyui.min.js"></script>
		
		<script type="text/javascript">
		$(function(){
			var listJSON = jQuery.parseJSON('${jsonList}');
			var json = convert(listJSON);
			var sh = $(window.parent.document).find("#iframeH").val();
			
			$('#tree').tree({
				data: json,
				cascadeCheck:false,
				onlyLeafCheck:true,
				lines:true,
				
				formatter:function(node){
					var nodeType = node.attributes.flag;
					if(nodeType == 0){
						return "<span style='color: red;'>*</span>"+node.text;
					}else{
						return node.text;
					}
				},
				onClick:function(node){
					var nodeType = node.attributes.dicType;
					if(nodeType == 3){
						$("#right").html("");
					}else{
	 					$("#right").html("<iframe frameBorder='no' height='" + ($("#tree").height() - 10) + "' src='${ctx}/custData/attList?id="+node.id+"&name="+node.text+"' scrolling='auto' style='border:0px' width='100%' height='100%'></iframe>");
					}
				}
			});
			
			/* 刪除节点  */
			$("#del").click(function(){
				var node = $("#tree").tree('getSelected');
				if (node!=null || node=="undefind"){
					
					if (node.id == '1') {
						layer.alert("不能删除根节点！");
						return;
					}
					
					var hasChildren = $("#tree").tree('getChildren',node.target);
					if(hasChildren.length==0){
						layer.confirm("确定删除名为   "+node.text+"  的分类吗？",function(index){
							layer.close(index);
							var delUrl = encodeURI("${ctx}/dic/deleteType?id="+node.id); 
							$.getJSON(delUrl,function(data){
			        			if(data.result == "true"){
			           				$("#tree").tree("remove",node.target);
			           				$("span.tree-checkbox").remove();
			        			}else {
									layer.alert("不能删除根节点！");
								}
			        		}); 
						});
					}else{
						layer.alert("有子节点存在不能删除!");
					}
				}else{
					layer.alert("请选择节点");
	    		}
			});
			
			/* 新增节点  */
			$("#add").click(function(){
				var node = $("#tree").tree('getSelected');
				var nodeId;
	    		if (node!=null || node=="undefind"){
	    			nodeId = node.id;
	    			var addUrl = encodeURI("${ctx}/dic/toAddType?parentId=" + nodeId +"&name="+ node.text);
	    			var index = layui.layer.open({
						title : "新增分类",
						type : 2,
						content : addUrl,
						area : ['600px', '340px'],
						offset: (sh-340)/2,
						success : function(layero, index){ }
					});
	    		}else{
					layer.alert("请选择节点");
	    		}
			}); 
			
			/* 修改  */
			$("#alt").click(function(){
				var node = $("#tree").tree('getSelected');
				var nodeId;
				if (node!=null || node=="undefind"){
	    			nodeId = node.id;
	    			var altUrl = encodeURI("${ctx}/dic/toUpdateType?id=" + nodeId);
	    			var index = layui.layer.open({
						title : "编辑分类",
						type : 2,
						content : altUrl,
						area : ['500px', '250px'],
						offset: (sh-250)/2,
						success : function(layero, index){ }
					});
	    		}else{
	    			layer.alert("请选择节点");
	    		}
			});
			
		});
		
		layui.config({
		    base: 'layui/build/js/'
		}).use('layer', function(){
			var $ = layui.jquery, layer = layui.layer;
		});
		</script>
		
		<style type="text/css">
			#right{padding-top: 37px; height: 90%;}
			#tree{overflow-y:scroll;height: 90%;border: 1px solid #ccc;}
			.button_group{padding: 5px 0 10px 0;}
		</style>
	</head>
<body>
	<div id="all" class="padding_div">
		<div class="nrq">
			<div class="layui-row">
			    <div class="layui-col-xs3">
			    	<div class="padding_div">
						<div class="button_group">
							<input type="button" name="add" id="add" value="新增" class="layui-btn layui-btn-normal layui-btn-mini"/>
							<input type="button" name="alt" id="alt" value="编辑" class="layui-btn layui-btn-normal layui-btn-mini"/>
							<input type="button" name="sub" id="del" value="删除" class="layui-btn layui-btn-normal layui-btn-mini"/>
						</div>
						<ul id="tree"></ul>
					</div>
			    </div>
			    <div class="layui-col-xs9">
		    		<div id="right"></div>
			    </div>
		  	</div>
		</div>
	</div>
</body>
</html>