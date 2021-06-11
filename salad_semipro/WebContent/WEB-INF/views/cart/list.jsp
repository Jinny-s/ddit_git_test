<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="cri" value="${pageMaker.cri }" />

<head></head>

<title>장바구니</title>

<body>
	 <!-- Main content -->
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>장바구니</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>장바구니
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
	 
	 
   	<section class="content">
   		<div class="card">
   			<div class="card-body" style="text-align:center;">
    		  <div class="row">
	             <div class="col-sm-12">	
		    		<table class="table table-bordered">
		    			<tr>
		    				<th>
								<input type="checkbox" id="chkAll">
							</th>
		                	<th colspan="2">제품</th>
<!-- 		                	<th class="col-sm-1">가격</th> -->
		                	<th>수량</th>
		                	<th></th>
		               	</tr>
		               	<c:forEach items="${cartList }" var="cart" >
		               	  <tr>
		               	  	<td>
		               	  		<input type="checkbox" value="${cart.cno }">
		               	  	</td>
		              		<td >
		              			${cart.pname }		
		              		</td>
		              		<td>
		              			${cart.price }원
		              		</td>
<%-- 		              		<td>${cart.price }</td> --%>
		              		<td>
		              			<input type="number" value="${cart.qty }" max="100" min="0" step="1" id="qty"/>
		              			<button type="button" class="btn btn-block btn-outline-secondary btn-xs-1" onclick="qty_change('${cart.cno}');">수량변경</button>
		              		</td>
		              		<td>
		              			<button type="button" class="btn btn-success col-sm-2" onclick="OpenWindow('/order/orderForm.do?cno=${cart.cno}&price=${cart.price}','주문하기',1000,850);">
									<span>주문</span>
								</button>
<%-- 								<button type="button" class="btn btn-warning col-sm-2" onclick="cartRemove_go('${cart.cno}');"> --%>
<!-- 			                        <span>삭제</span> -->
<!-- 								</button> -->
		              		</td>
		              	  </tr>
		               	</c:forEach>
		            </table>
		            <button type="button" class="btn btn-warning col-sm-2" onclick="cartCheckedRemove_go();">
                        <span>선택삭제</span>
					</button>
    		     </div> <!-- col-sm-12 -->
    	       </div> <!-- row -->
    		</div> <!-- card-body -->
	     </div>
   	</section>
   	<form role="form">
   		<input type="hidden" name="cno" value="">
   	</form>
<script>
	window.onload=function(){
	  	$("#chkAll").click(function(){
	  		if($("#chkAll").prop("checked")) {
	  			$(":checkbox").prop("checked", true);
	  		} else {
	  			$(":checkbox").prop("checked", false);
	  		}
	  	});
	  	
	  	$(":checkbox").not("#chkAll").click(function(){
	  	var chekedBox = $(":checkbox:checked").not("#chkAll").length;
	  		if(chekedBox == "${listCnt}") {
	  			$("#chkAll").prop("checked", true);
	  		} else {
	  			$("#chkAll").prop("checked", false);
	  		}
	  	});
	};

	function cartCheckedRemove_go() {
		var formObj = $("form[role='form']");
		if(confirm('선택하신 회원정보를 삭제하시겠습니까?')) {
			var list="";
			$(":checkbox:checked").not("#chkAll").each(function(index,cno){
			    list += cno.value + ',';
			});
			list = list.substr(0, list.length-1);
			$('input[name="cno"]').attr('value', list);
			formObj.attr('action', "remove.do");
			formObj.attr("method", "post");
			formObj.submit();
		}
	}
	
	function cartRemove_go(cno) {
		$('input[name="cno"]').val(cno); 
//		alert("click remove btn");
		var answer = confirm("정말 삭제하시겠습니까?");
		if(answer){
			formObj.attr("action", "remove.do");
			formObj.attr("method", "post");
			formObj.submit();
		}
	}
	
	function qty_change(cno) {
		console.log("수량 변경시작1:" + cno);
		console.log("수량 변경시작2:" + $('#qty').val());
		var qtycno = {
				qty : $('#qty').val(),
				cno : cno
		};
		$.ajax({
			url : "<%=request.getContextPath()%>/cart/qtyChange.do"
			, type : "post"
			, data : JSON.stringify(qtycno)
			, contentType : "application/json"
			, success : function(result) {
				console.log("수량 수정완료");
			}
			, error : function(error) {
				console.log("수량 수정실패");
			}
		});
	}
	
</script>
<script src="/resources/js/common.js" ></script>

</body>







