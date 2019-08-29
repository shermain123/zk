
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<html>
	<head>
	    <title>数据字典数据列表</title>
		<style type="text/css">
			.typeInfo{/* width:200px; */display:inline-block;}
			#add{float:right;margin-right:28px;}
		</style>
	</head>
<body>
	<div class="padding_div">
		<div class="nrq">
			<div class="padding_div">
				<div class="tab_s" >
	    			<table border="0" cellspacing="0" cellpadding="0" class="formTable">
						<tr>
							<td><p class="typeInfo">分类名: ${type.name }</p><p class="typeInfo" style="margin-left: 20px;">类型: <c:if test="${type.dicType == 1 }">描述</c:if><c:if test="${type.dicType == 2 }">数值</c:if></p></td>
						    <td class="tip"><button class="layui-btn layui-btn-normal layui-btn-mini" id="add" onclick="javascript:void(0);">新增</button></td>
					  	</tr>
					</table>
				</div>
			</div>
			<div style="clear:left;border-top:1px solid #9A9A9A;"></div>
			<table width="700px" border="0" cellspacing="0" cellpadding="5" class="layui-table">
				<colgroup>
					<col widht="20%">
					<col widht="40%">
					<col widht="20%">
					<col widht="20%">
				</colgroup>
				<tbody>
					<c:forEach var="cust" items="${list }" varStatus="i">
						<tr>
							<td>${cust.id }</td>
							<td>${cust.name }</td>
							<td>${cust.parent }</td>
							<td>${cust.orderNum }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>