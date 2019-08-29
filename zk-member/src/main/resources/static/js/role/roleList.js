layui.use(['table','layer'], function(){
	var table = layui.table, 
	$ = layui.jquery,
	layer = layui.layer;
	var searchFormHeight = layui.jquery(".search_form").height();
	searchFormHeight = 20 + searchFormHeight + 10 + 20 + 2  + 1;
          
	// 方法级渲染
	table.render({
		elem: '#LAY_role_table'
		,url: '../role/rolePage'
		,height : 'full-' + searchFormHeight
		,cols: [[
			{field:'', title: '',templet:'#radioTpl', width:40,align : 'center'}
		   ,{field:'roleName', title: '角色名称', width:267,align : 'center'}
		   ,{field:'roleLogo', title: '角色标识', width:267,align : 'center'}
		   ,{field:'updateTime', title: '创建时间', width:267,align : 'center',templet:'#updateTime'}
		   ,{field:'flag', title: '是否有效', width:267,templet:'#flagTpl',align : 'center'}
		]]
		,id: 'roleTableReload'
		,page : true
		,even : true
		,response : {
			// 成功的状态码，默认：0
			statusCode : 200
		}
	});
		  
	var active = {
		reload : function() {
			var demoReload = $('#role_list_form').serialize();
			// 执行重载
			table.reload('roleTableReload', {
				page : {
				curr : 1
				// 重新从第 1 页开始
				},
				where : DataDeal.fromJsonStrToJsonObj(DataDeal.formStrToJsonStr(demoReload))
			});
		},
		
		//权限设置
		roleSet : function(){
			var id = $("input[name='id']:checked").val();
			if(id==undefined){
				layer.alert("请选择角色!");
			}else{
				layui.layer.open({
					title : "资源授权",
					type : 2,
					content : "../role/toSetRoleRes?id="+id,
					area : ['380px', '570px'],
					success : function(layero, index){ }
				});
			}
		}
	};
	$('.search_form .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
});
		
function add(){
	layui.layer.open({
		title : "新增角色",
		type : 2,
		content : '../role/toAddRole',
		area : [ '550px', '400px' ],
		success : function(layero, index) {}
	});
}
		
function del(){
	var id = $('input:radio[name="id"]:checked').val();

	if(id!='' && id!=null && 'undefined'!= id){
		layer.confirm('确定要删除此选项？', {
			btn : [ '确定', '取消' ]
		// 按钮
		}, function() {
			$.ajax({
				url : "../role/deleteRole",
				type : "POST",
				data : {
					id:id
				},
				dataType : "json",
				success : function(data) {
					if ("true" == data.result) {
						layer.alert("操作成功！", {
							//offset : 'auto',
							closeBtn : 0
						}, function() {
							window.location.reload(true);
						});
					} else if ("false" == data.result) {
						layer.alert("系统角色无法删除！", {
							//offset : '200px',
							closeBtn : 0
					}, 
					function() {
						window.location.reload(true);
						});
					}
				}
			});
		}, function() {});
	}else{
		layer.alert('请选择需要删除的选项');
	}
}

function edit(){
	var id = $('input:radio[name="id"]:checked').val();
    if(id!='' && id!=null && 'undefined'!= id){
    	layui.layer.open({
    		title : "编辑角色",
		    type : 2,
		    content : '../role/toEditRole?id='+id,
		    area : [ '580px', '400px' ],
		    success : function(layero, index) {}
	    });
    }else{
		layer.alert('请选择需要编辑的选项');
	}
}

//权限设置
function roleBindRes(){
	var id = $('input:radio[name="id"]:checked').val();
	if ('undefined' == id || "" == id || null == id) {
		layer.alert("请选择角色对应的资源");
		return;
	}
	
	//绑定角色弹出框
	$("#resourceDialog").dialog({
		title:'权限设置',
		iconCls:'icon-attview',
		modal:true,
		closed:false,
		maximizable:true,
		onClose:function(){},
		onOpen:function(){
			resourceTree(id);
		},
		buttons:[{
			text:'确定',
			handler:function(){
				var urlpath = '../role/setRoleRes';
				var ids = [];
				var nodes = $('#resourceTress').tree('getChecked');
				for(var i=0;i<nodes.length;i++){
					ids.push(nodes[i].id);
				}
				if(ids.length == 0){
					layer.alert('请您选择资源!');
					return false;
				}
				var resIds = ids.join(',');
				$.ajax({
					url:urlpath,
					dataType:'json',
					type:'post',
					data:{roleId:id,resIds:resIds},
					beforeSend:function(XMLHttpRequest ){
						
					},
					success:function(data){
						$("#resourceDialog").dialog("close");
						if("true" == data.result){
							layer.alert('绑定成功!');
						}else{
							layer.alert('绑定失败!');
						}
					}
				});
			}
			},{
				text:'关闭',
				handler:function(){
						$("#resourceDialog").dialog("close");
					}
			}]
	 });
	
}

	//资源树列表
function resourceTree(id){
	$('#resourceTress').tree({
	    url: '../role/toSetRoleRes?id=' + id,
	    checkbox:true,
	    loadFilter: function(rows){
	    	return convert(rows);
	    }
	});
}

	//拆分资源树
function convert(rows) {
	var nodes = [];
	// get the top level nodes 判断有没有id是这一行的parentId
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row.parentId)) {
			nodes.push({
				id : row.id,
				text : row.resName
			});
		}
	}
	var toDo = [];
	for (var i = 0; i < nodes.length; i++) {
		toDo.push(nodes[i]);
	}
	while (toDo.length) {
		var node = toDo.shift(); // the parent node 把数组的第一个元素从其中删除，并返回第一个元素的值。
		// get the children nodes
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row.parentId == node.id) {
				var child = {
					id : row.id,
					text : row.resName,
					/*iconCls:row.icon,*/
					checked : row.check,
					attributes:{
						url : row.url
						/*isleaf:row.isleaf*/
					}
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
}

function exists(rows, parentId) {
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].id == parentId)
			return true;
	}
	return false;
}
