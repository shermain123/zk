<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

 <script type="text/javascript" src="${ctx }/js/user/main.js"></script>
    <script type="text/javascript">
    	var ctx;
    	var frameHeight;
    	var id;
    	var url;
    	var text;
    	$(document).ready(function(){
			ctx = '${ctx}';
			frameHeight = $("#container").height() - 42;
		    id = '${firstResId}';
		    url = '${ctx }/${firstResUrl}';
		    text = '<span> ${firstResName}</span>';
    	});
    </script>
</head>
<!--  class="kit-theme" 白色背景 -->
<body>
	<div class="layui-layout layui-layout-admin kit-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">分布式项目</div>
            <div class="layui-logo kit-logo-mobile"></div>
            <ul class="layui-nav layui-layout-left kit-nav" ><!-- kit-one-level -->
            	<c:forEach items="${topList}" var="res" varStatus="st">
            		<li class="layui-nav-item ${res.resId == resId ? 'layui-this' : ''}">
            			<a href="${ctx }/user/main?resId=${res.resId}">${res.resName}</a>
            		</li>
				</c:forEach>
            </ul>
            <ul class="layui-nav layui-layout-right kit-nav">
                <li class="layui-nav-item"><a href="javascript:;"> ${userName} </a></li>
                <li class="layui-nav-item"><a href="javascript:;" onclick="logout();"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
            </ul>
		</div>
		 <div class="layui-side layui-bg-black kit-side">
		 	<div class="layui-side-scroll">
		 		<div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
		 		 <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" id="kitNavbar" kit-navbar>
                    <c:forEach items="${list }" var="res" varStatus="status">
	                    <li class="layui-nav-item ${status.index eq 0 ? 'layui-nav-itemed' : ''}">
	                        <a class="" href="javascript:;"><i class="fa fa-user" ></i><span>&nbsp;&nbsp;${res.resName }</span></a>
	                        <dl class="layui-nav-child">
	                            <c:forEach items="${res.ress }" var="ress" varStatus="status1">
									<dd class="${((status.index eq 0) && (status1.index eq 0)) ? 'layui-this' : ''}">
										<a href="javascript:;" data-url="${ctx }/${ress.url }" data-title="${ress.resName }" data-id="${ress.resId }">
											<span> ${ress.resName }</span>
										</a>
									</dd>
								</c:forEach>
	                        </dl>
	                    </li>
					</c:forEach>
				</ul>
		 	</div>
		 </div>
		 <div class="layui-body" id="container">
            <!-- 内容主体区域 -->
            <div style="padding: 15px;">主体内容加载中,请稍等...</div>
        </div>
        <!-- <div class="layui-footer">
            底部固定区域
            <div align="center">版权所有 &copy;2017 国泰君安证券股份有限公司</div>
        </div> -->
	</div>
</body>
</html>