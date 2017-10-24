<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户查询</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-table.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-table.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-table-zh-CN.js"></script>
		<style type="text/css">
			.head{
				height: 60px;
			}
			.head-back{
				/*border: 1px solid red;*/
				height: 60px;
				background-color: #5CACEE;
				margin-bottom: 10px;
			}
			
		</style>
</head>
<body>
	<div class="head-back">

		</div>
		<div class="head" style="position: fixed; top: 0px;left: 10px;">
			欢迎访问XXX后台管理系统！     
			
		</div>
		<div class="h" style="position: fixed;top: 0;right: 10px;">
				XXX早上好！|注销
		</div>
		<div class="row"  style="margin: 0;padding: 0;">
			<div class="col-lg-2" style="margin: 0;padding: 0;">
				<div class="panel-group" id="accordion">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="#collapseTwo">
                菜单1
            </a>
            </h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse">
							<ul class="list-group">
								<li class="list-group-item">子菜单1</li>
								<li class="list-group-item">子菜单2</li>
								<li class="list-group-item">子菜单3</li>
								<li class="list-group-item">子菜单4</li>
								<li class="list-group-item">
									<a href="https://www.baidu.com/">google</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="#collapseTwo1">
                菜单2
            </a>
            </h4>
						</div>
						<div id="collapseTwo1" class="panel-collapse collapse">
							<ul class="list-group">
									<li class="list-group-item">
										<a href=""> 百度</a>
									</li>
									<li class="list-group-item">
										<a href=""> 知乎</a>
									</li>
									<li class="list-group-item">
										<a href="">google</a>
									</li>
								</ul>
						</div>
					</div>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="#collapseThree">
                菜单3
                </a>
            </h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse">
							<ul class="list-group">
								<li class="list-group-item">子菜单</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-10" >
				<form class="form-horizontal" role="form">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label for="firstname" class="col-sm-4 control-label">用户名</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="userName" id="userName" placeholder="请输入名字">
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">邮箱</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="email" name="email"  placeholder="请输入邮箱">
								</div>
							</div>
						</div>

						<div class="col-lg-3">
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">状态</label>
								<div class="col-sm-7">
									<select class="form-control" name="isActive" id="isActive">
										<option value=""></option>
										<option value="0">未激活</option>
										<option value="1">激活</option>
										<option value="2">注销</option>
										<option value="3">删除</option>
									</select>
								</div>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-lg-offset-11">
						<div class="row">
							<div class="col-lg-offset-4">
							<input type="button" id="search_btn" class="btn btn-primary" value="查询" />
							</div>
						</div>
						</div>
					</div>
				</form>
				<table id="table"></table>
				<div id="toolbar" class="btn-group pull-right" style="margin-right: 20px;">
	            <button id="btn_edit" type="button" class="btn btn-default">
	                <span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>修改
	            </button>
	            <button id="btn_delete" type="button" class="btn btn-default">
	                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
	            </button>
	            <button id="btn_add" type="button" class="btn btn-default">
	                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
	            </button>
        	</div>
			</div>
		</div>
		<script type="text/javascript">
			//根据窗口调整表格高度
			$(window).resize(function() {
				$('#table').bootstrapTable('resetView', {
					height: tableHeight()
				})
			})

			function tableHeight() {
				//根据页面高度自动调整
				return $(window).height() - 180;
			}
			//初始化页面数据
			$('#table').bootstrapTable({
				method: 'post',
				//必须设置，默认为'application/json'
				contentType: "application/x-www-form-urlencoded",
				//请求数据路径
				url: "/SSM/user/list",
				//高度调整
				height: tableHeight(),
				//指定工具栏
				toolbar: '#toolbar',
				//是否显示行间隔色
				striped: true, 
				//初始化加载第一页，默认第一页
				pageNumber: 1,
				//是否显示分页按钮
				pagination: true,
				//设置分页不循环
				paginationLoop:false,
				//默认为limit，发送符合 RESTFul 格式的参数
				queryParamsType: '',
				//请求服务器时所传的参数
				queryParams: queryParams,
				//指定服务器端分页，默认为client
				sidePagination: 'server',
				//单页记录数
				pageSize: 5,
				//设置可供选择的页面数据条数
				pageList: [2, 5, 10, 30,'All'],
				//刷新按钮
				showRefresh: false,
				//显示列切换按钮
				showColumns: true,
				//是否启用点击选中行
				clickToSelect: true,
				//中文支持
				locale: 'zh-CN',
				columns: [{
					title: '全选',
					//复选框
					checkbox: true,
					align: 'center',
					valign: 'middle'
				}, {
					title: '用户名',
					field: 'userName',
					width: 200,
					sortable: true
				}, {
					title: '密码',
					field: 'pwd',
				}, {
					title: '邮箱',
					field: 'email',
					width: 200
				}, {
					title: '手机号',
					field: 'tel',
					width: 200
				}, {
					title: '是否激活',
					field: 'isActive',
					sortable: true,
					width: 60,
					formatter: statusOperateFormatter
				}, {
					title: '注册时间',
					field: 'createDate',
					width: 150,
					sortable: true
				}, {
					title: '操作',
					field: 'attribute',
					align: 'center',
					width: 120,
					//列数据格式化
					formatter: attrOperateFormatter
				}],
			})

			function statusOperateFormatter(value, row, index) {
				//0表示未激活
				if(value == 0) {
					return '<i style="color:red">未激活</i>'
				} else if(value == 1) { //1表示激活
					return '<i style="color:green">激活</i>'
				} else if(value == 2) { //2表示注销
					return '<i style="color:green">注销</i>'
				} else if(value == 3) { //3表示删除
					return '<i style="color:green">删除</i>'
				} else {
					return '数据错误'
				}
			}

			function attrOperateFormatter(value, row, index) {
				var element =
					"<a class='edit' data-id='" + row.id + "'>编辑</a> " +
					"<a class='delet' data-id='" + row.id + "'>删除</a> ";
				return element;
			}
			//请求服务数据时所传参数
			function queryParams(params) {
				return {
					//每页多少条数据
					pageSize: params.pageSize,
					//请求第几页
					pageNumber: params.pageNumber ,
					userName: $('#userName').val(),
					email: $('#email').val(),
					isActive:$('#isActive option:selected').val()
				}
			}
			//查询按钮事件
			$('#search_btn').click(function() {
				//先销毁数据
				/* $('#table').bootstrapTable('destroy'); */
				$('#table').bootstrapTable('refresh', {
					url: '/SSM/user/list'
				});
			})
		</script>
</body>
</html>