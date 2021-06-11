<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<script>
	alert("결제가 성공하였습니다.");
	window.opener.parent.location.reload();
	window.close();
</script>