<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="orderList" value="${dataMap.orderList }" />

<head></head>

<title>구매내역</title>

<body>
	 <!-- Main content -->
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>구매 목록</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>주문내역
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	목록
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
	</section>
	 
	 <!-- Main content -->
    <section class="content">		
		<div class="card">
			<div class="card-header with-border">
				<div id="keyword" class="card-tools" style="width:450px;">
					<div class="input-group row">
						<select class="form-control col-md-3" name="perPageNum" id="perPageNum"
					  		onchange="list_go();">
					  		<option value="10" >정렬개수</option>
					  		<option value="5" ${cri.perPageNum == 5 ? 'selected':''}>5개씩</option>
					  		<option value="20" ${cri.perPageNum == 20 ? 'selected':''}>20개씩</option>
					  		<option value="50" ${cri.perPageNum == 50 ? 'selected':''}>50개씩</option>
					  		
					  	</select>						
						<select class="form-control col-md-4" name="searchType" id="searchType">
							<option value="i"  ${cri.searchType eq 'i' ? 'selected':'' }>전 체</option>
							<option value="i" ${cri.searchType eq 'i' ? 'selected':'' }>구매ID</option>
						</select>					
						<input  class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${param.keyword }"/>
						<span class="input-group-append">
							<button class="btn btn-primary" type="button" onclick="list_go(1);" 
							data-card-widget="search">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
					</div>
				</div>						
			</div>
			<div class="card-body">
				<table class="table table-bordered text-center" >					
					<tr style="font-size:0.95em;">
						<th>주문번호</th>
						<th>상품명</th>
						<th>구매 ID</th>
						<th>구매금액</th>
						<th>구매일</th>
						<th>구매상태</th>
						<th>취소하기</th>
					</tr>				
					<c:if test="${empty orderList }" >
						<tr>
							<td colspan="7">
								<strong>해당 내용이 없습니다.</strong>
							</td>
						</tr>
					</c:if>						
					<c:forEach items="${orderList }" var="order">
						<tr style='font-size:0.85em;'>
							<td id="ono" style="text-align:center;max-width: 100px; overflow: hidden; 
												white-space: nowrap; text-overflow: ellipsis;">
								<a href="javascript:OpenWindow('detail.do?ono=${order.ono }','상세보기',1000,800);">
									<span class="col-sm-12"> ${order.ono}</span>
								</a>
							</td>
							<td>
								${order.pname}
								<c:if test="${order.pcount > 1 }">
									외 ${order.pcount -1}건
								</c:if>
							
							</td>
							<td>${order.id }</td>
							<td>${order.totalprice }</td>
							<td>
								<fmt:formatDate value="${order.orderdate }" pattern="yyyy-MM-dd"/>
							</td>
							<td>
							<c:if test="${order.orderstatus eq 1 }">
								<span class="badge bg-primary">구매완료</span>
							</c:if>
							<c:if test="${order.orderstatus eq 2 }">
								<span class="badge bg-red">구매취소</span>
							</c:if>
							</td>
							<td>
							<c:if test="${order.orderstatus eq 1 }">
								<button type="button" id="removeBtn" class="btn btn-danger btn-xs" onclick="cancel_go(${order.ono});">취소하기</button>
							</c:if>
							<c:if test="${order.orderstatus eq 2 }">
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>				
			</div>
			<div class="card-footer">
				<%@ include file="/WEB-INF/views/common/pagination.jsp" %>				
			</div>
		</div>
		
    </section>
    <!-- /.content -->
<form role="form">
	<input type="hidden" name="ono" value="" />
</form>
<script>
function cancel_go(ono){
		var formObj = $("form[role='form']");
		$("input[name='ono']").val(ono);
		var answer = confirm("주문을 취소하시겠습니까?");
		if(answer){		
			formObj.attr("action", "cancel.do");
			formObj.attr("method", "post");
			formObj.submit();
		}
	}
</script>
<script src="/resources/js/common.js" ></script>

</body>