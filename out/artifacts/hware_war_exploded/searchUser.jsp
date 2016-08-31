<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>用户查询</title>
		<script src="${webPath}/js/jquery.1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${webPath}/js/jquery.shop.common.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="${webPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="${webPath}/css/bootstrap-theme.min.css" />
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
			
			.form-group {
				margin-top: 25px;
				margin-bottom: 10px;
			}
			
			label {
				font-size: 20px;
			}
			
			.dengour {
				border-color: red;
				box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6);
			}
			
			.form-control {
				width: 20%;
			}
			
			a {
				cursor: pointer;
			}
			
			.test-result {
				overflow: auto;
				left: 23%;
				position: absolute;
				right: 15px;
				top: 100px;
				background-color: #fff;
				background-image: none;
				border: 1px solid #ccc;
				border-radius: 4px;
			}
			
			.table {
				margin-top: 2px;
				margin-bottom: 10px;
			}
			
			.table-hover> tbody>tr:hover {
				background-color: rgba(0, 191, 255, 0.1);
			}
			
			.test-info {
				display: none;
				overflow: hidden;
				background-color: #fff;
				background-image: none;
				border: 1px solid #ccc;
				border-radius: 4px;
				bottom: 30px;
				left: 15px;
				position: absolute;
				right: 70%;
				top: 490px;
			}
			
			.test {
				position: relative;
				margin-top: 5px;
				width: 100%;
				height: 37px;
				font-size: 20px;
				border-bottom: 1px solid #ccc;
			}
			
			.name-text,
			.ID-text,
			.time-text,
			.gender-text,
			.age-text,
			.height-text {
				margin-left: 20px;
				font-size: 16px;
			}
			
			.name-value,
			.ID-value,
			.time-value,
			.gender-value,
			.age-value,
			.height-value {
				margin-left: 20px;
			}
			
			h3 {
				text-align: center;
			}
			
			.body-style {
				font-size: 0;
				position: relative;
				margin: 0;
				width: 101%;
				height: 129px;
				border-top: 1px solid #ccc;
			}
			
			.bodytype {
				margin: 0;
				border-right: 1px solid #ccc;
				border-bottom: 1px solid #ccc;
				display: inline-block;
				vertical-align: middle;
				position: relative;
				width: 33.3333%;
				height: 33%;
				line-height: 43px;
				font-size: 16px;
				text-align: center;
			}
			
			.check {
				background-color: #00bfff;
				color: #fff;
			}
			
			.ID-value {
				margin-left: 67px;
			}
			
			#searchBtn {
				margin-left: 70px;
			}
			
			ul {
				margin: 0;
				padding: 0;
			}
			
			li {
				float: left;
				list-style: none;
				font-weight: bold;
				font-size: 24px;
				color: #00BFFF;
				cursor: pointer;
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
			
			h3 {
				margin-top: 10px;
			}
			
			tbody> tr> td {
				color: #666666;
			}
			
			.test-result tbody> tr:nth-child(odd)> th,
			.test-result tbody> tr:nth-child(odd)> td {
				background-color: rgba(0, 191, 255, 0.1);
			}
			
			nav {
				position: absolute;
				top: 850px;
				left: 50%;
				-webkit-transform: translate(-50%, 0);
				-moz-transform: translate(-50%, 0);
				-ms-transform: translate(-50%, 0);
				-o-transform: translate(-50%, 0);
				transform: translate(-50%, 0);
			}

			.page{
				position: absolute;
				top: 550px;
				left:61.5%;
				-webkit-transform: translate(-50%, 0);
				-moz-transform: translate(-50%, 0);
				-ms-transform: translate(-50%, 0);
				-o-transform: translate(-50%, 0);
				transform: translate(-50%, 0);
			}
		</style>
	</head>

	<body>
	<form action="${webPath}/admin/dataList.htm" method="post" id="ListForm">
		<div class="container">
			<div class="header">
				<ul>
					<li class="li1">
						<h1><font class="h1 h2small" onclick="pageTurn('i')">人体测试</font></h1></li>
					<!--<li class="li2">
						<h1><font class="h1 h2small" onclick="pageTurn('i')">人体测试</font></h1></li>-->
					<li class="li2">
						<h1><font class="h1 " onclick="pageTurn('s')">用户查询</font></h1></li>
				</ul>
			</div>
			<div class="inputInfo">
				<div class="form-group">
					<label for="exampleInputEmail1">编号查询</label>
					<input type="tel" name="id" class="form-control " id="exampleInputEmail1" placeholder="编号"  >
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">姓名查询</label>
					<input type="text" name="userName" class="form-control " id="exampleInputName1" placeholder="姓名" >
				</div>
				<button id="submitBtn" class="btn btn-info btn-lg">查询</button>
			</div>

			<div class="test-result table-responsive">
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>编号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
							<th>体型判断</th>
							<th>查看</th>
						</tr>


					</thead>
					<tbody id="listtbody">
					<c:forEach items="${objs}" var="obj">
						<tr>
						<td>${obj.userNo}</td>
						<td>${obj.userName}</td>
						<td>
							<c:choose>
							<c:when test="${obj.sex== 0}">
								男
							</c:when>
								<c:otherwise>
									女
								</c:otherwise>
							</c:choose>
						</td>
						<td>${obj.age}</td>
						<td>
							<c:choose>
							<c:when test="${obj.bodyConfirm== 1}">
							隐形肥胖型
							</c:when>
							<c:when test="${obj.bodyConfirm== 2}">
							脂肪过多型
							</c:when>
							<c:when test="${obj.bodyConfirm== 3}">
							肥胖型
							</c:when>
							<c:when test="${obj.bodyConfirm== 4}">
							肌肉不足型
							</c:when>
							<c:when test="${obj.bodyConfirm== 5}">
							健康匀称型
							</c:when>
							<c:when test="${obj.bodyConfirm== 6}">
							超重肌肉型
							</c:when>
							<c:when test="${obj.bodyConfirm== 7}">
							销售型
							</c:when>
							<c:when test="${obj.bodyConfirm== 8}">
							低脂肪型
							</c:when>
							<c:otherwise>
								运动员型
							</c:otherwise>
							</c:choose>
						</td>
						<td><a href="${webPath}/masterComment.html?id=${obj.id}">查看</a></td>
						</tr>
					</c:forEach>

					</tbody>
				</table>
			</div>
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
			<div class="page">${gotoPageFormHTML}</div>

		</div>
	</form>
		<%--<script src="${webPath}/js/config.js" type="text/javascript" charset="utf-8"></script>--%>
		<%--<script src="${webPath}/js/jquery.1.11.3.min.js" type="text/javascript" charset="utf-8"></script>--%>
		<%--<script src="${webPath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>--%>
		<script type="text/javascript">
			var sexG, jiankangType;
			/*$(function() {
				//进入页面
				getInfoList(1);
			});*/
			//对数字做验证
			function testnum(num) {
				var reg = /^[0-9]*$/
				console.log("验证码正误：" + reg.test(num))
				if(reg.test(num)) {
					return true;
				} else {
					return false;
				}
			};

			function pageTurn(type) {
				if(type == "i") {
					window.location.href = "${webPath}/index.html"
				} else if(type == "m") {
					window.location.href = "machineConnection.html"
				} else {
					window.location.href = "${webPath}/admin/dataList.htm"
				}
			}

			function info(id) {
				window.location.href = "masterComment.html?id=" + id;
			}

			/*function getInfoList(cuPage) {
				$.ajax({
					type: "post",
					dataType: "json",
					url: localWebUrl + "json/dataList.htm",
					data: {
						"currentPage": cuPage
					},
					success: function(data) {
						console.log(data)
						var bodyFa = $("#listtbody");
						for(var i = 0; i < data.objs.length; i++) {
							if(data.objs[i].sex == '1') {
								sexG = "女"
							} else {
								sexG = "男"
							}
							if(data.objs[i].bodyConfirm == 1) {
								jiankangType = "隐形肥胖型";
							} else if(data.objs[i].bodyConfirm == 2) {
								jiankangType = "脂肪过多型";
							} else if(data.objs[i].bodyConfirm == 3) {
								jiankangType = "肥胖型";
							} else if(data.objs[i].bodyConfirm == 4) {
								jiankangType = "肌肉不足型";
							} else if(data.objs[i].bodyConfirm == 5) {
								jiankangType = "健康均称型";
							} else if(data.objs[i].bodyConfirm == 6) {
								jiankangType = "超重肌肉型";
							} else if(data.objs[i].bodyConfirm == 7) {
								jiankangType = "消瘦型";
							} else if(data.objs[i].bodyConfirm == 8) {
								jiankangType = "低脂肪型";
							} else if(data.objs[i].bodyConfirm == 9) {
								jiankangType = "运动员型";
							}
							var str = '<tr><th scope="row">' + (i + 1) + '</th><td>' + data.objs[i].id + '</td>' +
								'<td>' + data.objs[i].userName + '</td><td>' + sexG + '</td><td>' + data.objs[i].birthday + '</td>' +
								'<td>' + jiankangType + '</td><td><a id="xq' + data.objs[i].id + '" > 详情 </a></td> </tr>';
							$(str).appendTo(bodyFa);
						}
						//分页
						var allPage = data.totalPage;
						var currPage = data.currentPage;
						//

						if(currPage >= 10) {
							for(var i = 0; i < 10; i++) {
								var str = '<li><a id="currPage' + i + 1 + '">' + i + 1 + '</a></li>'
								$(str).before($("#preC"))
							}
						} else {
							for(var i = 0; i < currPage; i++) {
								var str = '<li><a id="currPage' + i + 1 + '">' + i + 1 + '</a></li>'
								$(str).before($("#preC"))
							}
							$("#preC").addClass('disabled')
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {},
				});
			}*/
		</script>
	</body>

</html>