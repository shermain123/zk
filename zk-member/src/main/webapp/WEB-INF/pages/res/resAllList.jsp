<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>

<html>
  	<head>
	    <title>资源管理</title>
		<script src="${ctx }/js/treetable/js/jquery.treetable.js" type="text/javascript"> </script>
		<link href='${ctx }/js/treetable/css/jquery.treetable.css' rel='stylesheet' type='text/css' />
		<link href='${ctx }/js/treetable/css/jquery.treetable.theme.default.css' rel='stylesheet' type='text/css' />
	
		<script type="text/javascript" src="${ctx }/js/res/resAllList.js"></script>
		<style>
			table.treetable tbody tr td {
	   	 		padding: 6px 10px;
			}
		</style>
  </head>
  <body>
	<div class="padding_div" >
		<div class="nrq">
			<div class="padding10_div search_form">
				<div class="layui-inline" >
					<div class="btn_group text_align_left1">
						<input type="button" onclick="jQuery('#example-advanced').treetable('expandAll'); return false;"  value="展开" class="layui-btn layui-btn-normal layui-btn-mini"/>
						<input type="button" onclick="jQuery('#example-advanced').treetable('collapseAll'); return false;" value="合并" class="layui-btn layui-btn-normal layui-btn-mini"/>
					</div>
				</div>
			</div> 
	  		<div class="tab_x" style="padding: 10px 10px 0px;">
				<table id="example-advanced">
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
			        	<c:forEach items="${list }" var="res">
			        		<c:if test="${res.resLevel == 1 }">
			        			<tr data-tt-id='${res.resId }' id='1'>
			        		</c:if>
			        		<c:if test="${res.resLevel != 1 }">
			        			<tr data-tt-id='${res.resId }' data-tt-parent-id='${res.parentId }' id="${res.resId }">
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
								<td class="ope_td">
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
  </body>
</html>
