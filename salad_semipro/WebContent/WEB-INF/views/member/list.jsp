<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cri" value="${pageMaker.cri }" />

<title>회원 목록</title>
<head></head>
<body>
 <!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1>회원목록</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="list.do">
								<i class="fa fa-dashboard"></i>
								회원관리
							</a>
						</li>
						<li class="breadcrumb-item active">목록</li>
					</ol>
				</div>
			</div>
		</div>
	</section>

	<section class="content">
		<div class="card">
			<div class="card-header with-border">
			<c:if test="${loginUser.enabled == 1 }">
				<button type="button" class="btn btn-primary" onclick="OpenWindow('registForm.do', '회원등록', 800, 900);">회원등록</button>
			</c:if>
				<div id="keyword" class="card-tools" style="width:550px;">
					<div class="input-group row">
						<select class="form-control col-md-3" name="perPageNum" id="perPageNum"
							onchange="list_go(1)">
							<option value="10">10개씩 보기</option>
							<option value="2" ${cri.perPageNum eq 2 ? 'selected' : "" }>2개씩 보기</option>
							<option value="3" ${cri.perPageNum eq 3 ? 'selected' : "" }>3개씩 보기</option>
							<option value="5" ${cri.perPageNum eq 5 ? 'selected' : "" }>5개씩 보기</option>
						</select>
						<select class="form-control col-md-3" name="searchType" id="searchType" >
							<option value="">검색구분</option>
							<option value="i" ${cri.searchType eq 'i' ? 'selected' : "" }>아이디</option>
							<option value="p" ${cri.searchType eq 'p' ? 'selected' : "" }>전화번호</option>
							<option value="e" ${cri.searchType eq 'e' ? 'selected' : "" }>이메일</option>
						</select>
						<input class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${cri.keyword }">
						<span class="input-group-append">
							<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
					</div>
				</div>
			</div>
			<div class="card-body">
				<div class="col-sm-12">
					<div class="row">
						<c:forEach var="member" items="${memList }" >
						<div class="col-12 col-sm-6 col-md-3 d-flex align-items-stretch flex-column">
			              <div class="card bg-light d-flex flex-fill">
			              	<div class="card-header">
			              		<div class="row">
				                    <div class="col-7">
				                      <h2 class="lead"><b>${member.name }</b></h2>
				                      <p class="text-muted text-sm"><b>Authority : </b> ${member.authority } </p>
				                    </div>
				                    <div class="col-5 text-center">
				                      <img class="img-circle img-fluid" src="/member/getPictureById.do?id=${member.id }" alt="member-picture" >
				                    </div>
			              		</div>
			              	</div>
			                <div class="card-body pt-0">
			                  <div class="row">
			                      <ul class="ml-4 mb-0 fa-ul text-muted">
			                        <li class="small"><span class="fa-li"><i class="fas fa-solid fa-building"></i></span> Address<br>${member.address }<hr></li>
			                        <li class="small"><span class="fa-li"><i class="fas fa-solid fa-phone"></i></span> Phone<br>${member.phone }<hr></li>
			                        <li class="small"><span class="fa-li"><i class="fas fa-solid fa-envelope"></i></span> Email<br>${member.email }<hr></li>
			                        <li class="small"><span class="fa-li"><i class="fas fa-solid fa-촏차"></i></span> Enabled<br>${member.enabled == 1 ? "활동중" : "정지됨" }</li>
			                      </ul>
			                  </div>
			                </div>
			                <div class="card-footer">
			                  <div class="text-right">
			                    <a href="javascript:OpenWindow('detail.do?id=${member.id }','','800','900')" class="btn btn-sm btn-primary">
			                      <i class="fas fa-user"></i>&nbsp;&nbsp;정보 보기
			                    </a>
			                  </div>
			                </div>
			              </div>
			            </div>
			          </c:forEach>
					</div>
				</div>
			</div>
			<div class="card-footer">
				<c:set var="list_url" value="list.do"/>
				<%@ include file="/WEB-INF/views/common/pagination.jsp" %>
			</div>
		</div>
	</section>
</body>
