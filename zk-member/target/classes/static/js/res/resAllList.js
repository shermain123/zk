layui.config({
	base : 'layui/build/js/'
}).use([ 'element', 'table', 'layer' ], function() {
	var $ = layui.jquery, table = layui.table, element = layui.element, // Tab的切换功能，切换事件监听等，需要依赖element模块
	layer = layui.layer;
});

$(function() {
	var option = {
		theme : 'vsStyle',
		expandLevel : 2,
		expandable : true,
		beforeExpand : function($treetable, id) {
			// 判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
			if ($('.' + id, $treeTable).length) {
				return;
			}
			// 这里的html可以是ajax请求
			var html = '<tr id="8" pId="6"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
					+ '<tr id="9" pId="6"><td>5.2</td><td>动态的内容</td></tr>';

			$treetable.addChilds(html);
		},
		onSelect : function($treetable, id) {
			window.console && console.log('onSelect:' + id);
		}

	};
	$('#example-advanced').treetable(option);
	// $('#example-advanced').treetable("expandNode", "1");
	//默认展开
	$('#example-advanced').treetable('expandAll');
});

function addRes(id) {
	layui.layer.open({
		title : "新增资源",
		type : 2,
		content : '../res/toAddRes?id=' + id,
		//offset : '250px',
		area : [ '600px', '420px' ],
		success : function(layero, index) {
		}
	});
}

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
							$('#example-advanced').treetable("removeNode", id);
							window.location.reload(true);
						});
					}
				}
			});
		}, function() {});
	}
}