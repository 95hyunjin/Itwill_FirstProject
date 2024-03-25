<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<%@ include file="../include/header.jsp"%>
	
	<h1>release.jsp</h1>
	
	${vo} <br>
	${code} <br>
	
	<input type="button" value="전체" onclick="location.href='/release/main'">
	<input type="button" value="검수" onclick="location.href='/release/inspection'">
	
	<table border="1">
		<tr>
			<td>divcode</td>
			<td>id</td>
			<td>pno</td>
			<td>pname</td>
			<td>category</td>
			<td>release_date</td>
			<td>update_date</td>
			<td>price</td>
			<td>order_date</td>
			<td>order_count</td>
			<td>delivery_company</td>
			<td>delivery_phone</td>
			<td>delivery_manager</td>
		</tr>
	
		<c:forEach var="vo" items="${vo}">
			<c:if test="${vo.divcode==6}">
				<tr>
					<td>
					<input type="text" value="출고완료" readonly="readonly">
					</td>
					<td>${vo.id}</td>
					<td>${vo.pno}</td>
					<td>${vo.pname}</td>
					<td>${vo.category}</td>
					<td>${vo.release_date}</td>
					<td>${vo.update_date}</td>
					<td>${vo.price}</td>
					<td>${vo.order_date}</td>
					<td>${vo.order_count}</td>
					<td>${vo.delivery_phone}</td>
					<td>${vo.delivery_manager}</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	
	
	
	<%@ include file="../include/footer.jsp"%>