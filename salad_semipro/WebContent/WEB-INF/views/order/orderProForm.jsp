<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body>

  <!-- Content Wrapper. Contains page content -->
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>주문/결제</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>구매하기
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	주문/결제
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
						<th>상품정보</th>
						<th>수량</th>
						<th>상품금액</th>
					</tr>
					<tr style="font-size:0.85em;">
						<td>${pname}</td>
						<td>${qty} 개</td>
						<td>${totalprice} 원</td>
					</tr>
				</table>			
			</div>
			<div class="card-body">
				<h5>배송지정보</h5>
				<div class="register-card-body">
					<form name="orderForm" role="form" class="form-horizontal" action="orderPro.do" method="post">
						<div class="form-group row">
							<label for="name" class="col-sm-3" style="font-size: 0.9em;">
								<span style="color: red; font-weight: bold;">*</span>수령인
							</label>
							<div class="col-sm-9 input-group-sm">
								<input class="form-control" name="name" type="text" class="form-control" id="name" placeholder="이름을 입력하세요" />
							</div>
						</div>
						<div class="form-group row">
							<label for="phone" class="col-sm-3" style="font-size: 0.9em;">
								<span style="color: red; font-weight: bold;">*</span>연락처
							</label>
							<div class="col-sm-9 input-group-sm">
								<input class="form-control" name="phone" type="text" class="form-control" id="phone" placeholder="연락처를 입력하세요" />
							</div>
						</div>
						<div class="form-group row">
							<label for="address" class="col-sm-3" style="font-size: 0.9em;">
								<span style="color: red; font-weight: bold;">*</span>배송지 주소
							</label>
							<div class="col-sm-9 input-group-sm">
								<input class="form-control" name="address" type="text" class="form-control" id="address" placeholder="주소를 입력하세요" />
							</div>
						</div>
						
						<div class="card-footer">
							<div class="col-sm-12 text-center">
								<span style="font-size: 1.5em;font-weight: bold;">주문금액 : 총 ${totalprice} 원</span><br>
								<span style="font-size: 1.0em;font-weight: bold;">${qty}개 * ${price}원</span>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<button type="button" id="registBtn" onclick="order_go();" class="btn btn-info">결제하기</button>
								</div>

								<div class="col-sm-6">
									<button type="button" id="cancelBtn" onclick="CloseWindow();" class="btn btn-default float-right">
										취소
									</button>
								</div>
							</div>
						</div>
						<input type="hidden" name="pname" value="${pname}">
						<input type="hidden" name="pno" value="${pno}">
						<input type="hidden" name="price" value="${totalprice}">
						<input type="hidden" name="id" value="${loginUser.id}">
						<input type="hidden" name="totalprice" value="${totalprice }">
						<input type="hidden" name="qty" value="${qty}">
					</form>
				</div>			
			</div>
		</div>
    </section>
  
<script>
function order_go() {
	var form = document.orderForm;
	if(form.name.value=="") {
		alert("수령인은 필수입니다.");
		return;
	}
	if(form.phone.value=="") {
		alert("연락처는 필수입니다.");
		return;
	}
	if(form.address.value=="") {
		alert("수령지 주소는 필수입니다.");
		return;
	}
	form.submit();
}
 	
</script>

