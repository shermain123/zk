<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="com.cm.counterparty.domain.system.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
int current =  page.getPage();
long begin = Math.max(1, current - paginationSize/2);
long end = Math.min(begin + (paginationSize - 1), page.getTotalPages());
request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
%>
<div class="flickr">
	<span class="disabled">共${page.count }条数据</span>
		 <% if ((page.isHasNext() && current != 1) || (current == end && current != 1)){%>
               	<a href="${url}&pageNo=1&pageSize=${page.limit}">&lt;&lt;</a>
                <a href="${url}&pageNo=${current-1}&pageSize=${page.limit}">&lt;</a>
         <%}else{%>
                <span class="disabled">&lt;&lt;</span>
                <span class="disabled">&lt;</span>
         <%} %>

		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <span class="current">${i}</span>
                </c:when>
                <c:otherwise>
                    <a href="${url}&page=${i}&limit=${page.limit}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

	  	 <% if (page.isHasNext()){%>
               	<a href="${url}&page=${current+1}&limit=${page.limit}">&gt;</a>
                <a href="${url}&page=${page.totalPages}&limit=${page.limit}">&gt;&gt;</a>
         <%}else{%>
                <span class="disabled">&gt;</span>
                <span class="disabled">&gt;&gt;</span>
         <%} %>
</div>