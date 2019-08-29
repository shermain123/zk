var message;
var element;
layui.config({
	base : '/layui/build/js/'
}).use([ 'app', 'message', 'navbar', 'element' ], function() {
	var app = layui.app, $ = layui.jquery, layer = layui.layer;
	element = layui.element;

	// 将message设置为全局以便子页面调用
	message = layui.message;

	// 主入口
	app.set({
		type : 'iframe'
	}).init();

	element.on('nav(kitNavbar)', function(elem) {
		var frameHeight = $("#container").height() - 42;
		var id = elem.children('a').attr("data-id");
		var url = elem.children('a').attr('data-url');
		var text = $.trim(elem.children('a').html());
		tabAdd(frameHeight, id, url, text);
	});

	tabAdd(frameHeight, id, url, text);
});

function tabAdd(frameHeight, id, url, text) {
	element.tabDelete("kitTab", id);
	element.tabAdd('kitTab', {
		title : text,
		content : '<iframe height="' + frameHeight + '" src="' + url
				+ '" frameborder="0"></iframe>',
		id : id
	});
	element.tabChange('kitTab', id);
}

function logout() {
	layer.confirm('是否退出系统？', {
		icon : 3,
		title : '提示',
	}, function(index) {
		layer.close(index);
		$.get(ctx + '/user/logout');
		window.top.location = ctx + '/';
	});
}