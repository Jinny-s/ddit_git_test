<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="productList" value="${dataMap.productList }" />

<head></head>

<title>샐러드</title>

<body>
	 <!-- Main content -->
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>샐러드 목록</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>샐러드
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
				<button type="button" class="btn btn-primary" id="registBtn" onclick="OpenWindow('registForm.do','샐러드등록',800,700);">샐러드 등록</button>				
				<div id="keyword" class="card-tools" style="width:450px;">
					
				</div>						
			</div>
			<div class="card-body">					
					<c:if test="${empty productList }" >
						<tr>
							<td colspan="5">
								<strong>해당 내용이 없습니다.</strong>
							</td>
						</tr>
					</c:if>						
					<div class="row d-flex align-items-stretch">
					<c:forEach items="${productList }" var="product">
							<div class="col-3 mb-3"
								onclick="OpenWindow('detail.do?pno=${product.pno }', '' , '800','900')"
								style="cursor: pointer;"">
								<div class="card h-100">
									<!-- Sale badge-->
									<div class="ribbon-wrapper ribbon-xl ">
									<div class="ribbon bg-danger text-xl text-center"
										style="top: 0.5rem; right: 0.5rem">Sale</div>
									</div>
									<!-- Product details-->
									<div class="row card-body ">
										<div class="text-center">
											<!-- Product name-->
											<h3 class="fw-bolder">${product.pname }</h3>
											<!-- Product price-->
											<h5>${product.price }￦</h5>
											<br>
											${product.content }
										</div>
									</div>
									<!-- Product actions-->
									<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
										<div class="text-center">
											<a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a>
										</div>
									</div>
								</div>
							</div>
							<!-- carding -->	
							</c:forEach>	
				</div>
			</div>
			<div class="card-footer">
				<%@ include file="/WEB-INF/views/common/pagination.jsp" %>				
			</div>
		</div>
		
    </section>
    <!-- /.content -->

<script src="/resources/js/common.js" ></script>

</body>
