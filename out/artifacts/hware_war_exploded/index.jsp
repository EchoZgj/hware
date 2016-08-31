<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>


		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>人体测试</title>
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
				margin-bottom:10px;
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
				display: none;
				bottom: 30px;
				left: 35%;
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
			h3{
				margin-top: 10px;
			}
		</style>
	</head>

	<body>
		<div class="container">
			<div class="header">
				<ul>
					<li class="li1">
						<h1><font class="h1 h2small" onclick="pageTurn('m')">机器连接</font></h1></li>
					<li class="li2">
						<h1><font class="h1 " onclick="pageTurn('i')">人体测试</font></h1></li>
					<li class="li2">
						<h1><font class="h1 h2small" onclick="pageTurn('s')">用户查询</font></h1></li>
				</ul>
			</div>
			<form action="/startTest.htm" method="post">



			<div class="inputInfo">
				<div class="form-group">
					<label for="exampleInputEmail1">编号</label>
					<input type="tel" class="form-control " name="userNo" id="exampleInputEmail1" placeholder="编号" onblur="checkBH()">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">姓名</label>
					<input type="text" class="form-control " name="userName" id="exampleInputName1" placeholder="姓名" onblur="checkXM()">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">出生年份</label>
					<input type="tel" class="form-control" name="age" id="exampleInputPassword1" placeholder="年龄" onblur="checkNL()">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">身高</label>
					<input type="tel" class="form-control" name="height" id="exampleInputtext1" placeholder="身高" onblur="checkSG()">
				</div>
				<div class="form-group" id="Genderitem">
					<label for="exampleInputPassword1">性别1</label>
					<!--<label class="radio-inline">
						<input type="radio" checked="true"  name="Gender" id="inlineRadio1" value="option1">男
					</label>
					<label class="radio-inline">
  						<input type="radio" name="Gender" id="inlineRadio2" value="option2">女
					</label>-->
				</div>
				<button id="submitBtn" class="btn btn-info btn-lg">开始测试</button>
				<button id="searchBtn" class="btn btn-info btn-lg">查询用户</button>
			</div>
			</form>
			<div class="test-info">
				<div class="test test-ID">
					<span class="ID-text">ID</span>
					<span class="ID-value">2</span>
				</div>
				<div class="test test-time">
					<span class="time-text">测量时间</span>
					<span class="time-value">16-07-20 16:40:33</span>
				</div>
				<div class="test test-name">
					<span class="name-text">用户名称</span>
					<span class="name-value">呵呵</span>
				</div>
				<div class="test test-gender">
					<span class="gender-text">测量性别</span>
					<span class="gender-value">男性</span>
				</div>
				<div class="test test-age">
					<span class="age-text">测量年龄</span>
					<span class="age-value">28岁</span>
				</div>
				<div class="test test-height">
					<span class="height-text">测量身高</span>
					<span class="height-value">174cm</span>
				</div>
				<h3>体型判定</h3>
				<div class="body-style">
					<div id="type1" class="bodytype">
						隐形肥胖型
					</div>
					<div id="type2" class="bodytype">
						脂肪过多型
					</div>
					<div id="type3" class="bodytype">
						肥胖型
					</div>
					<div id="type4" class="bodytype">
						肌肉不足型
					</div>
					<div id="type5" class="bodytype check">
						健康均称型
					</div>
					<div id="type6" class="bodytype">
						超重肌肉型
					</div>
					<div id="type7" class="bodytype">
						消瘦型
					</div>
					<div id="type8" class="bodytype">
						低脂肪型
					</div>
					<div id="type9" class="bodytype">
						运动员型
					</div>
				</div>
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
		</div>
		<script src="js/jquery.1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			var checkvalue = "inlineRadio1";
			var BH = false;
			var NL = false;
			var SG = false;
			var XM=false;
			$(function() {
				$("input").val("")
				$('<label class="radio-inline"><input type="radio" checked="true"  name="Gender" id="inlineRadio1" value="option1">男</label><label class="radio-inline"><input type="radio" name="Gender" id="inlineRadio2" value="option2">女</label>').appendTo($("#Genderitem"))
				$("#inlineRadio1").attr("checked", "true")
				$("#submitBtn").on("click", function() {
					console.log(checkvalue)
					if(BH == false) {
						alert("请填入正确的编号")
						return false
					}
					if(NL == false) {
						alert("请填入正确的年龄")
						return false
					}
					if(SG == false) {
						alert("请填入正确的身高")
						return false
					}
					if(XM == false) {
						alert("请填入正确的姓名")
						return false
					}
					if(checkvalue == "inlineRadio1") {
						alert("男")
						$(".test-info").css("display", "block")
						$(".test-result").css("display", "block")
					} else {
						alert("女")
						$(".test-info").css("display", "block")
						$(".test-result").css("display", "block")
					}
				})
				$("#inlineRadio1,#inlineRadio2").on("click", function() {
					//					console.log($("input[name='Gender']:checked").attr("id"))
					checkvalue = $("input[name='Gender']:checked").attr("id")
					console.log(checkvalue)
				})
				$("#searchBtn").on("click", function() {
					window.location.href = "searchUser.html"
				})
			});
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

			function checkBH() {
				$("#exampleInputEmail1").removeClass("dengour")
				var input1 = $("#exampleInputEmail1").val().trim();
				if(input1.length == 0) {
					alert("编号不能为空！")
					$("#exampleInputEmail1").addClass("dengour")
					BH = false

				} else {
					if(testnum(input1)) {
						BH = true
					} else {
						alert("编号需为数字")
						$("#exampleInputEmail1").addClass("dengour")
						BH = false
					}
				}
			};

			function checkSG() {
				$("#exampleInputtext1").removeClass("dengour")
				var input1 = $("#exampleInputtext1").val().trim();
				if(input1.length == 0) {
					alert("身高不能为空！")
					$("#exampleInputtext1").addClass("dengour")
					SG = false
				} else {
					if(testnum(input1)) {
						SG = true
					} else {
						alert("身高需为数字")
						$("#exampleInputtext1").addClass("dengour")
						SG = false
					}
				}
			};

			function checkXM() {
				$("#exampleInputName1").removeClass("dengour")
				var input1 = $("#exampleInputName1").val().trim();
				if(input1.length == 0) {
					alert("年龄不能为空！")
					$("#exampleInputName1").addClass("dengour")
					XM = false
				} else {
					XM = true
				}
			}

			function checkNL() {
				$("#exampleInputPassword1").removeClass("dengour")
				var input1 = $("#exampleInputPassword1").val().trim();
				if(input1.length == 0) {
					alert("年龄不能为空！")
					$("#exampleInputPassword1").addClass("dengour")
					NL = false
				} else {
					if(testnum(input1)) {
						NL = true
					} else {
						alert("年龄需为数字")
						$("#exampleInputPassword1").addClass("dengour")
						NL = false
					}
				}
			};

			function pageTurn(type) {
				if(type == "i") {
					window.location.href = "/index.jsp"
				}else if(type == "m") {
				window.location.href = "/getIndex.htm";
			} else {
					window.location.href = "searchUser.html"
				}
			}
		</script>
	</body>

</html>