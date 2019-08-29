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
