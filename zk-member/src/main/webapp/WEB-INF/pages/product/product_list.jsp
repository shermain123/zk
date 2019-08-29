<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
	<head>
		<title>商品列表</title>
	</head>
<body>
	<div class="padding_div" >
   		<div class="nrq">
   			<div class="padding10_div search_form">
   				<form class="layui-form" id="info_list_form" onsubmit="return false;">
   					<div class="layui-form-item">
   						<div class="layui-inline">
							<label class="layui-form-label  layui-form-label80">编号 </label>
							<div class="layui-input-inline">
								<!-- <input type="text" name="" id="" autocomplete="off" class="layui-input" /> -->
								<input type="text" name="proId" id="proId" autocomplete="off" class="layui-input" />
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label  layui-form-label80">名称 </label>
							<div class="layui-input-inline">
								<!-- <input type="text" name="" id="" autocomplete="off" class="layui-input" /> -->
								<input type="text" name="proName" id="proName" autocomplete="off" class="layui-input" />
							</div>
						</div>
						<div class="layui-inline">
							<div class="btn_group btn_group234">
					    		<button class="layui-btn layui-btn-normal layui-btn-mini" data-type="reload">查询</button>
					    		<button class="layui-btn layui-btn-warm layui-btn-mini" data-type="add">新增商品</button>
							</div>
				    	</div>
					</div>
   				</form>
   				
   				<div class="sperator_div" ></div>	
				<table class="layui-hide" id="LAY_info_table"></table>
   			</div>
   		</div>
   	</div>
   	
<script type="text/javascript">
layui.use([ 'element', 'table', 'layer', 'form','laydate','laytpl' ], function() {
	var $ = layui.jquery,
	table = layui.table,
	element = layui.element, // Tab的切换功能，切换事件监听等，需要依赖element模块
	layer = layui.layer;
	form = layui.form,
	laydate = layui.laydate;
	
	var searchFormHeight = layui.jquery(".search_form").height();
	searchFormHeight = 20 + searchFormHeight + 10 + 20 + 3;
	
	table.render({
		elem : '#LAY_info_table',
		url : '',
		height : 'full-' + searchFormHeight,
		cols : [ [ {
			field : '',
			title : '客户代码',
			width : 92,
			align : 'center'
		}, {
			field : '',
			title : '客户姓名/机构名称',
			width : 321
			
		}, {
			field : '',
			title : '业务类型',
			width : 131
		}, {
			field : '',
			title : '客户标志',
			width : 73,
			align : 'center'
		}, {
			field : '',
			title : '项目状态',
			width : 200
		}, {
			field : '',
			title : '尽调报告状态',
			width : 160
		}, {
			field : '',
			title : '融资总金额(万元)',
			width : 92,
			align : 'center'
		}, {
			field : '',
			title : '标的名称',
			width : 171,
			align : 'center'
		}, {
			field : '',
			title : '标的代码',
			width : 214,
			align : 'center'
		}, {
			field : '',
			title : '意向融资期限(天)',
			width : 214,
			align : 'center'
		}, {
			field : '',
			title : '意向融资利率',
			width : 214,
			align : 'center'
		}, {
			field : '',
			title : '股份性质',
			width : 214,
			align : 'center'
		}, {
			field : '',
			title : '操作',
			width : 214,
			align : 'center'
		} ] ],
		id : 'infoTableReload',
		page : true,
		even : true,
		response : {
			// 成功的状态码，默认：0
			statusCode : 200
		}
	});
	
	var active = {
			reload : function() {
				var demoReload = $('#info_list_form').serialize();
				// 执行重载
				table.reload('infoTableReload', {
					page : {
						curr : 1
					// 重新从第 1 页开始
					},
					where : DataDeal.fromJsonStrToJsonObj(DataDeal
							.formStrToJsonStr(demoReload))
				});
			},
			add : function() {
				var url = '../approve/toAddInfo';
				window.location.href = url;
			}
		};
	
	//日期
  	laydate.render({
    	elem: '#stratDate'
  	});
  	laydate.render({
    	elem: '#endDate'
  	});
  	
});

</script>
</body>
</html>