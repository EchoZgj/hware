<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>人体测试仪器连接</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
	<style type="text/css">
		html,
		body {
			font-family: "微软雅黑";
			height: 100%;
		}

		.container {
			position: relative;
			height: 100%;
		}

		.header {
			padding-left: 40px;
			overflow: hidden;
			width: 100%;
			background-color: rgba(0, 191, 255, 0.2);
		}

		.test-info {
			background-color: #fff;
			background-image: none;
			border-radius: 4px;
			bottom: 30px;
			left: 15px;
			position: absolute;
			right: 15px;
			top: 70px;
		}

		ul {
			margin: 0;
			padding: 0;
		}

		.header li {
			float: left;
			list-style: none;
			font-weight: bold;
			font-size: 24px;
			color: #00BFFF;
			cursor: pointer;
		}

		.dropdown-menu li {
			width: 100%;
			list-style: none;
			font-weight: bold;
			font-size: 24px;
			color: #00BFFF;
			cursor: pointer;
			text-align: center;
		}

		.h2small:hover,
		li:hover {
			color: #C0C0C0;
		}

		.li2 {
			margin-left: 20px;
		}

		.h2small {
			font-size: 20px;
			color: #333;
		}

		.btn-group>.btn:first-child {
			margin: 100px auto 0;
		}

		.btn-style {
			font-size: 24px;
			width: 300px;
			height: 45px;
		}

		.btn-group,
		.btn-group-vertical {
			left: 50%;
			margin-left: -150px;
		}

		.dropdown-menu {
			width: 100%;
		}
		.submit{
			position: absolute;
			top: 300px;
			left: 50%;
			width: 300px;
			height: 45px;
			font-size: 24px;
			line-height: 45px;
			margin-left: -150px;
			padding: 0;
		}
	</style>
</head>

<body>
<div class="container">
	<div class="header">
		<ul>
			<li class="li1">
				<h1><font class="h1 " onclick="pageTurn('m')">机器连接</font></h1></li>
			<li class="li2">
				<h1><font class="h1 h2small" onclick="pageTurn('i')">人体测试</font></h1></li>
			<li class="li2">
				<h1><font class="h1 h2small" onclick="pageTurn('s')">用户查询</font></h1></li>
		</ul>
	</div>

	<form action="/openSerical.htm" method="post" id="form1">
	<div class="test-info">
		<!-- Single button -->
		<div class="btn-group">
			<button type="button" class="btn btn-info dropdown-toggle btn-style" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				选择机器类型
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<input type="hidden" name="port" id="hideinp" value="" />

				<c:forEach items="${portList}" var="com">

				<li>
					<a href="javascript:void(0);" id="S_1">${com}</a>
				</li>
				</c:forEach>

			</ul>
		</div>
		<input type="submit" id="connectLine" class="btn btn-info submit" onclick="sub()">

	</div>
	</form>
</div>
<script src="js/jquery.1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		$("a").on("click",function(event){
			var txt=$(event.currentTarget).html()
			$(".btn-style").html(txt)
			$("#hideinp").val($(event.currentTarget).html())
		})
		$("#connectLine").on("click",function(){
			window.location.href = "index.html"
		})
	})
	function pageTurn(type) {
		if(type == "i") {
			window.location.href = "/index.jsp";
		} else if(type == "m") {
			window.location.href = "/getIndex.htm";
		}else{
			window.location.href = "searchUser.html";
		}
	}

	function sub(){
		alert($("#hideinp").val());
		if($("#hideinp").val()!=""){
			alert(2);
			return true;
		}else{
			return false;
		}
	}
</script>
</body>

</html>