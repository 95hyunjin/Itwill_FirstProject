<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="<c:url value='/resources/bootstrap/css/style.css' />" rel="stylesheet">

<h1>rInspectionRead.jsp</h1>
<form action="/inspec/rInspectionRead" method="post" onsubmit="return validateForm();">

<table class="styled-table">
    <tr>
        <td>제품 번호:</td>
        <td><input type="text" name="pno" required readonly value="${vo.pno }"></td>
    </tr>
<%--     <tr>
        <td>담당자명:</td>
        <td><input type="text" name="id" required value="${vo.id }"></td>
    </tr> --%>
    <tr>
        <td>전체 수량:</td>
        <td><input type="number" name="count" required readonly value="${vo.count }"></td>
    </tr>
     <tr>
        <td>검수 수량:</td>
        <td><input type="number" name="finish_count" required value="${vo.finish_count }"></td>
    </tr>
    <tr>
        <td>남은 수량:</td>
        <td><input type="number" name="remain_count" required readonly="readonly" value="${vo.remain_count }"></td>
    </tr>
    <tr>
        <td>검수 여부:</td>
        <td>
            <select name="divcode" required>
                <option value="8">출고검수불량</option>
                <option value="6">출고완료</option>
            </select>
        </td>
    </tr>
    <tr>
        <td>창고위치:</td>
        <td>
            <select name="warehouse_code" required>
            	<c:forEach var="warehouseList" items="${warehouseList}">
            		<option value="${warehouseList.warehouse_code }">${warehouseList.location }</option>
            	</c:forEach>
            </select>
        </td>
    </tr>
   
</table>

	<c:choose>
		<c:when test="${vo.remain_count == 0}">
			<input type="text" value="수정할 것 없음" readonly>
		</c:when>
		<c:otherwise>
			<input type="submit" value="수정 완료">
		</c:otherwise>
	</c:choose>
	
</form>


<script>
function validateForm() {
    var finishCount = parseInt(document.getElementsByName("finish_count")[0].value);
    var remainCount = parseInt(document.getElementsByName("remain_count")[0].value);
    
    // finish_count는 음수가 될 수 없음
    if (finishCount < 0) {
        alert("검수 수량은 음수일 수 없습니다.");
        return false;
    }

    // finish_count가 remain_count를 초과할 수 없음
    if (finishCount > remainCount) {
        alert("검수 수량은 남은 수량을 초과할 수 없습니다.");
        return false;
    }

    return true; 
}
</script>




<%@ include file="../include/footer.jsp"%>