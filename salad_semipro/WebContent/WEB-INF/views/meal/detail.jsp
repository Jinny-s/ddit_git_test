<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
				        	<i class="fa fa-dashboard"></i>공지사항
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
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h3 class="card-title">상세보기</h3>
						<div class="card-tools">
							<button type="button" id="modifyBtn" class="btn btn-warning" onclick="modify_go();">MODIFY</button>						
						    <button type="button" id="removeBtn" class="btn btn-danger" onclick="remove_go();">REMOVE</button>
						    <button type="button" id="listBtn" class="btn btn-primary" onclick="CloseWindow();">CLOSE</button>
					    </div>
					</div>
					<div class="card-body">
						<div class="form-group col-sm-12">
							<label for="pname">제 목</label>
							<input type="text" class="form-control" id="pname" readonly disabled value="${product.pname }" />							
						</div>
						<div class="row">	
							<div class="form-group col-sm-4" >
								<label for="writer">작성자</label>
								<input type="text" class="form-control" id="writer" readonly value="${product.writer }"/>
							</div>		
							<div class="form-group col-sm-4" >
								<label for="price">가격</label>
								<input type="number" class="form-control" id="price" readonly value="${product.price }"/>
							</div>		
						
							<div class="form-group col-sm-4" >
								<label for="viewcnt">조회수</label>
								<input type="text" class="form-control" id="viewcnt" readonly value="${product.viewcnt }"/>
							</div>
						</div>		
						<div class="form-group col-sm-12">
							<label for="content">내 용</label>
							<div id="content">${product.content }</div>	
						</div>
						<div class="card-footer text-center">
							<label for ="price">주문 수량
							<input type="number" class="form-control" id="qty" value="" /></label>
							
							<button type="button" id="modifyBtn" class="btn btn-warning" onclick="order_go();">주문하기</button>	
												
						    <button type="button" id="removeBtn" class="btn btn-danger" onclick="registCart_go();">장바구니담기</button>
					    </div>						
					</div>													
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row  -->
    </section>
    <!-- /.content -->
    
        <!-- Reply content -->
    <section class="content container-fluid">
    	<!-- reply component start --> 
		<div class="row">
			<div class="col-md-12">
				<div class="card card-info">					
					<div class="card-body">
						<!-- The time line -->
						<div class="timeline">
							<!-- timeline time label -->
							<div class="time-label" id="repliesDiv">
								<span class="bg-green">Replies List </span>							
							</div>
							
							
						</div>
						<div class='text-center'>
							<ul id="pagination" class="pagination justify-content-center m-0">
								
							</ul>
						</div>
					</div>
					<div class="card-footer">
						<label for="newReplyWriter">Writer</label>
						<input class="form-control" type="hidden" placeholder="USER ID"	 id="newReplyWriter" readonly value="${loginUser.id }"> 
						<label for="newReplyText">Reply Text</label>
						<input class="form-control" type="text"	placeholder="REPLY TEXT" id="newReplyText">
						<br/>
						<button type="button" class="btn btn-primary" id="replyAddBtn" onclick="replyRegist_go();">ADD REPLY</button>
					</div>				
				</div>			
				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
    </section>
  </div>
  <!-- /.content-wrapper -->

<!-- Modal -->
<div id="modifyModal" class="modal modal-default fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" style="display:none;"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>        
      </div>
      <div class="modal-body" data-rno>
        <p><input type="text" id="replytext" class="form-control"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" id="replyModBtn" onclick="replyModify_go();">Modify</button>
        <button type="button" class="btn btn-danger" id="replyDelBtn" onclick="replyRemove_go()">DELETE</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
    
    
  </div>
  <!-- /.content-wrapper -->
    
  </div>
  <!-- /.content-wrapper -->

<form role="form">
	<input type="hidden" name="pno" value="${product.pno }" />
	<input type="hidden" name="pname" value="${product.pname }" />
	<input type="hidden" name="qty" value="" />
	<input type="hidden" name="price" value="${product.price }" />
	<input type="hidden" name="totalprice" value="" /> 
	
</form>
	
  
<script>
	function order_go() {
		var formObj = $("form[role='form']");
		var qty = $('#qty').val();
		$("input[name='qty']").val($('#qty').val());
		$("input[name='totalprice']").val("${product.price}" * qty);
		formObj.attr({
			'action':'/order/orderProForm.do?pno=${product.pno}',
			'method':'post'
		});
		formObj.submit();
	}
	
	 function registCart_go() {
	      var formObj = $("form[role='form']");
	      if(confirm('장바구니에 추가하시겠습니까?')) {
	         $('input[name="qty"]').attr('value', $("#qty").val());
	         formObj.attr('action', "<%=request.getContextPath()%>/cart/regist.do");
	         formObj.attr("method", "post");
	         formObj.submit();
	      }
	   }
	
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
 
   <%@ include file="./reply_js.jsp" %>
 
 
 
 