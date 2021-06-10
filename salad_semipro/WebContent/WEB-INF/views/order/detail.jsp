<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="order" value="${dataMap.order}" />
<c:set var="detailList" value="${dataMap.detailList}" />

<body>

  <!-- Content Wrapper. Contains page content -->
  <div  style="max-width:800px;min-width:420px;margin:0 auto;min-height:812px;">
   
   
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>상세보기</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>구매내역
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	상세보기
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
	</section>
	 
   
      <!-- Main content -->
    <section class="content container-fluid">
		<div class="card card-outline card-primary">
			<div class="card-header with-border">
				<table class="table table-bordered text-center" >
					<tr style="font-size:0.95em;">
						<th>주문번호</th>
						<th>구매 ID</th>
						<th>구매금액</th>
						<th>구매일</th>
						<th>구매상태</th>
					</tr>
					<tr style="font-size:0.85em;">
						<td>${order.ono}</td>
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
					</tr>	
				</table>			
			</div>
			<div class="card-body">
				<table class="table table-bordered text-center" >
					<tr style="font-size:0.95em;">
						<th>상품명</th>
						<th>수량</th>
						<th>가격</th>
					</tr>
					<c:set var = "total" value = "0" />
					<c:forEach items="${detailList }" var="detail">
					<tr style='font-size:0.85em;'>
						<td>${detail.pname}</td>
						<td>${detail.qty}</td>
						<td>${detail.price} 원</td>
					</tr>
					<c:set var="total" value="${total+detail.price}" />
					</c:forEach>
					<tr style="font-size:0.95em;" class="text-center">
						<th colspan=3>
							총 금액 : <c:out value="${total}" /> 원
						</th>
					</tr>
				</table>				
			</div>
			<div class="card-footer">
				<h5 class="text-center">배송지 정보</h5>
				<table class="table table-bordered text-center bg-white" style="font-size:0.95em;">
					<tr>
						<th>이 름</th>
						<td>${detailList[0].name}</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${detailList[0].phone}</td>
					</tr>	
					<tr>
						<th>주 소</th>
						<td>${detailList[0].address}</td>
					</tr>	
				</table>
				<div class="col-sm-12 text-center">
            		<button type="button" id="closeBtn" onclick="CloseWindow();" class="btn btn-primary pull-right">닫 기</button>
            	</div>			
			</div>
		</div>
    </section>
  </div>
  <!-- /.content-wrapper -->

<!-- <form role="form"> -->
<%-- 	<input type="hidden" name="bno" value="${board.bno }" /> --%>
<!-- </form> -->
	
  
<script>

	function modify_go(){
		var formObj = $("form[role='form']");
		//alert("click modify btn");
		formObj.attr({
			'action':'modifyForm.do',
			'method':'post'
		});
		formObj.submit();
	}
	function remove_go(){
		var formObj = $("form[role='form']");
		//alert("click remove btn");
		var answer = confirm("정말 삭제하시겠습니까?");
		if(answer){		
			formObj.attr("action", "remove.do");
			formObj.attr("method", "post");
			formObj.submit();
		}
	}
 	
</script>

