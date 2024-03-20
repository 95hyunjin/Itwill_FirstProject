<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>

<!-- 품번 클릭시 제품 상세보기 코드 작성 -->
<script>
	// 팝업을 띄우는 함수 정의
	function openPopup(productCode) {
		// 팝업에 표시할 내용을 생성
		var popupContent = "제품 코드: " + productCode;
		
		// 팝업창 크기 조절
		var popupWidth = 400;
		var popupHeight = 300;

		// 팝업창 가운데 정렬
		var left = (window.screen.width - popupWidth) / 2;
		var top = (window.screen.height - popupHeight) / 2;

		// 팝업창 열기
		var popupWindow = window.open("", "_blank", "width=" + popupWidth
				+ ",height=" + popupHeight + ",left=" + left + ",top=" + top);

		// 팝업창 내용부분
		popupWindow.document.write("<html><head><title>제품 상세보기</title></head><body>");
		popupWindow.document.write("<h1> 제품 상세보기 </h1>");
		popupWindow.document.write("<p>" + popupContent + "</p>");
		popupWindow.document.write("</body></html>");
	}

	// 페이지가 로드될 때 실행되는 함수
	window.onload = function() {
		// 제품 코드가 있는 각 <td> 요소를 선택
		var productCells = document.querySelectorAll('td[data-product-code]');

		// 각 제품 코드를 클릭했을 때 팝업을 띄우도록 이벤트를 추가
		productCells.forEach(function(cell) {
			cell.addEventListener('click', function() {
				var productCode = cell.getAttribute('data-product-code');
				openPopup(productCode);
			});
		});
	};
</script>
<!-- 품번 클릭시 제품 상세보기 코드 작성 -->

<div class="box">
	<div class="box-header">
		<h3 class="box-title">현재 재고</h3>
	</div>
		<div class="box-body">
			<div id="example1_wrapper"
				class="dataTables_wrapper form-inline dt-bootstrap">
				<div class="row">
					<div class="col-sm-6">
	    				<div class="dataTables_length" id="example1_length">
	        				<label>한 페이지에 표시할 항목 수 :
	            				<select name="pageSize" id="pageSizeSelect" onchange="changePageSize()">
					                <option value="10" ${param.pageSize == 10 ? 'selected' : ''}>10</option>
					                <option value="25" ${param.pageSize == 25 ? 'selected' : ''}>25</option>
					                <option value="50" ${param.pageSize == 50 ? 'selected' : ''}>50</option>
					                <option value="100" ${param.pageSize == 100 ? 'selected' : ''}>100</option>
	            				</select>
	        				</label>
					    </div>
					</div>
				
			<!-- 한 페이지에 표시할 항목 수 스크립트 추가 -->	
			<script>
			    function changePageSize() {
			        var selectedPageSize = document.getElementById("pageSizeSelect").value;
			        location.href = "/stock/stockMainCri?page=1&pageSize=" + selectedPageSize;
			    }
			</script>
			<!-- 한 페이지에 표시할 항목 수 스크립트 추가 -->
				
				<div class="col-sm-6">
					<div id="example1_filter" class="dataTables_filter">
						<label>검색 : <input type="search"
							class="form-control input-sm" placeholder="입력하세요"
							aria-controls="example1">
						</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<table id="example1"
						class="table table-bordered table-striped dataTable" role="grid"
						aria-describedby="example1_info">
						<thead>
							<tr role="row">
								<th class="sorting_asc" tabindex="0" aria-controls="example1"
									rowspan="1" colspan="1" aria-sort="ascending"
									aria-label="Rendering engine: activate to sort column descending"
									style="width: 297.469px;">
									제품 코드(클릭 시 상세보기)</th>
								<th class="sorting" tabindex="0" aria-controls="example1"
									rowspan="1" colspan="1"
									aria-label="Browser: activate to sort column ascending"
									style="width: 361.984px;">
									제품 카테고리</th>
								<th class="sorting" tabindex="0" aria-controls="example1"
									rowspan="1" colspan="1"
									aria-label="Platform(s): activate to sort column ascending"
									style="width: 322.266px;">
									품목명</th>
								<th class="sorting" tabindex="0" aria-controls="example1"
									rowspan="1" colspan="1"
									aria-label="Engine version: activate to sort column ascending"
									style="width: 257.094px;">
									제조회사</th>
								<th class="sorting" tabindex="0" aria-controls="example1"
									rowspan="1" colspan="1"
									aria-label="CSS grade: activate to sort column ascending"
									style="width: 188.188px;">
									수량</th>
								<th class="sorting" tabindex="0" aria-controls="example1"
									rowspan="1" colspan="1"
									aria-label="CSS grade: activate to sort column ascending"
									style="width: 188.188px;">
									창고위치</th>
							</tr>
						</thead>
						<!-------------------------- 제품 내용 넣는 곳 -------------------------->
						<tbody>
						<c:forEach var="sVO" items="${stockList }" >
							<tr>
								<td data-product-code="${sVO.pno}">${sVO.pno}</td>
								<td>${sVO.category }</td>
								<td>${sVO.pname }</td>
								<td>${sVO.company }</td>
								<td>${sVO.count }</td>
								<td>${sVO.warehouse_code }</td>															
							</tr>
						</c:forEach>
						</tbody>
						<!-------------------------- 제품 내용 넣는 곳 -------------------------->
						<tfoot>
							<tr>
								<th rowspan="1" colspan="1">제품 코드</th>
								<th rowspan="1" colspan="1">제품 카테고리</th>
								<th rowspan="1" colspan="1">품목명</th>
								<th rowspan="1" colspan="1">제조회사</th>
								<th rowspan="1" colspan="1">수량</th>
								<th rowspan="1" colspan="1">창고위치</th>
								
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<div class="row">
                <div class="col-sm-5">
				 <c:choose>
				    <c:when test="${param.page * param.pageSize >= pageVO.totalCount}">
				    총 ${pageVO.totalCount}개의 항목 중 ${(param.page - 1) * param.pageSize + 1}번 부터 
				    ${pageVO.totalCount}번까지 표시 중
				    </c:when>
				    <c:otherwise>
				    총 ${pageVO.totalCount}개의 항목 중 ${(param.page * param.pageSize) - param.pageSize + 1}번 부터 
				    ${param.page * param.pageSize}번까지 표시 중
				    </c:otherwise>
				</c:choose>
                </div>
                <div class="col-sm-7">
                    <div class="dataTables_paginate paging_simple_numbers" id="example1_paginate">
                        <ul class="pagination">
                        <c:if test="${pageVO.prev }">
                            <li class="paginate_button previous" id="example1_previous">
                            <a href="/stock/stockMainCri?page=${pageVO.startPage -1 }&pageSize=${param.pageSize}" 
                            aria-controls="example1" data-dt-idx="0"
                            tabindex="0">이전</a></li>
                        </c:if>

                        <c:forEach var = "idx" begin= "${pageVO.startPage }"
                        end= "${pageVO.endPage}" step="1">
                            <li ${pageVO.cri.page == idx? "class='active'":""}>
                            <a href="/stock/stockMainCri?page=${idx }&pageSize=${param.pageSize}" 
                            aria-controls="example1" data-dt-idx="1" tabindex="0">${idx }</a></li>
                        </c:forEach>
                            <c:if test="${pageVO.next }">
                            <li class="paginate_button next" id="example1_next"><a
                                href="/stock/stockMainCri?page=${pageVO.endPage + 1 }&pageSize=${param.pageSize}" 
                                aria-controls="example1" data-dt-idx="7" tabindex="0">다음</a></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>




<%@ include file="../include/footer.jsp"%>