<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>专家评论</title>
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
			
			.form-group {
				margin-top: 5px;
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
				width: 30%;
			}
			
			.test-result {
				bottom: 30px;
				left: 50%;
				position: absolute;
				right: 15px;
				top: 85px;
				background-color: #fff;
				background-image: none;
				border: 1px solid #ccc;
				border-radius: 4px;
			}
			
			.table {
				margin-top: 20px;
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
			
			.commentInput {
				position: absolute;
				top: 85px;
				left: 15px;
				width: 47%;
				height: 500px;
				background-color: #FFFFFF;
				border: 1px solid #ccc;
				border-radius: 4px;
				overflow: hidden;
			}
			
			textarea {
				display: block;
				width: 100%;
				height: 100%;
				top: 0;
				bottom: 0;
				left: 0;
				right: 0;
				border: none;
				outline: none;
				position: absolute;
				padding: 10px;
				word-break: break-all;
				text-align: justify;
				resize: none;
				font-size: 16px;
			}
			.submitBtn{
				position: absolute;
				left: 15px;
				top: 600px;
				
			}
		</style>
	</head>

	<body>
		<div class="container">
			<div class="header">
				<ul>
					<li class="li1">
						<h1><font class="h1">专家评论</font></h1></li>
					<li class="li2">
						<h1><font class="h1 h2small" onclick="pageTurn()">返回</font></h1></li>
				</ul>
			</div>
			<div class="test-result table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>项目</th>
							<th>测量值</th>
							<th>正常范围</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<td>体重</td>
							<td>number</td>
							<td>min-max</td>
						</tr>
						<tr>
							<th scope="row">2</th>
							<td>去脂体重</td>
							<td>number</td>
							<td>min-max</td>
						</tr>
						<tr>
							<th scope="row">3</th>
							<td>体脂肪量</td>
							<td>number</td>
							<td>min-max</td>
						</tr>
						<tr>
							<th scope="row">4</th>
							<td>肌肉量</td>
							<td>number</td>
							<td>min-max</td>
						</tr>
						<tr>
							<th scope="row">5</th>
							<td>骨质量</td>
							<td>number</td>
							<td>min-max</td>
						</tr>
						<tr>
							<th scope="row">6</th>
							<td>蛋白质</td>
							<td>number</td>
							<td>min-max</td>
						</tr>
						<tr>
							<th scope="row">7</th>
							<td>身体水分</td>
							<td>number</td>
							<td>min-max</td>
						</tr>
						<tr>
							<th scope="row">8</th>
							<td>体脂百分比</td>
							<td>number</td>
							<td>min-max</td>
						</tr>
						<tr>
							<th scope="row">9</th>
							<td>体质指数</td>
							<td>number</td>
							<td>min-max</td>
						</tr>
						<tr>
							<th scope="row">10</th>
							<td>腰臀比</td>
							<td>number</td>
							<td>min-max</td>
						</tr>

					</tbody>
					<thead>
						<tr>
							<th>项目</th>
							<th>结果</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>内脏脂肪</td>
							<td>result</td>
						</tr>
						<tr>
							<td>控制脂肪</td>
							<td>result</td>
						</tr>
						<tr>
							<td>标准体重</td>
							<td>result</td>
						</tr>

						<tr>
							<td>控制肌肉</td>
							<td>result</td>
						</tr>
						<tr>
							<td>控制体重</td>
							<td>result</td>
						</tr>
						<tr>
							<td>生理年龄</td>
							<td>result</td>
						</tr>
						<tr>
							<td>基础代谢</td>
							<td>result</td>
						</tr>
						<tr>
							<td>健康评分</td>
							<td>result</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="commentInput">
				<textarea>
					
				</textarea>
			</div>
			<div class="submitBtn btn btn-info btn-lg">
				提交点评
			</div>
		</div>
		<script src="js/jquery.1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function() {
				$("textarea").val("")
				$(".submitBtn").on("click",function(){
					alert($("textarea").val().trim())
				})
			});
			//对数字做验证

			function pageTurn(type) {
				window.location.href = "searchUser.html"
			};
		</script>
	</body>

</html>