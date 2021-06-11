<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav main-menu-list">
   	  <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
     	<!-- main menu list -->
     	<c:forEach var="menu" items="${menuList }">
	    	<li class="nav-item d-none d-sm-inline-block">
		    	<a class="nav-link" href="javascript:subMenu('${menu.mcode }'); goPage('${menu.murl }','${menu.mcode}');">
		    		<i class="${menu.micon }"></i>
		    		${menu.mname }
		    	</a>
	    	</li>
     	</c:forEach>
    </ul>

	<!-- Right navbar links -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex navbar-nav ml-auto">
        <div class="image">
          <img src="/member/getPicture.do?picture=${loginUser.picture }" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
         <div class="row">
         	<a href="javascript:OpenWindow('/member/detail.do?id=${loginUser.id }','',800,900)" class="d-block nav-link"><b>${loginUser.name }</b> 님 </a>
         	<button onclick="location.href='<%=request.getContextPath() %>/logout.do';" class="btn btn-xs btn-primary col-xs-3 mr-3" type="button">LOGOUT</button>
         </div>
        </div>
      </div>
  </nav>
  <!-- /.navbar -->
