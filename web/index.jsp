<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<div class="header">

		<div class="dl-title">
			<!--<img src="/chinapost/Public/assets/img/top.png">-->
		</div>

		<div class="dl-log">
			欢迎您，<span class="dl-log-user">${sessionScope.loginUser.username}</span><a
				href="loginAction!loginOut" title="退出系统"
				class="dl-log-quit">[退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">系统管理</div></li>
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">

		</ul>
	</div>
	<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="assets/js/bui-min.js"></script>
	<script type="text/javascript" src="assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="assets/js/config-min.js"></script>
	<script>
		BUI
				.use(
						'common/main',
						function() {
							 var d=new Date();
					    	 var nowYear=d.getYear()+1900;

							var config = [ {
								id : '1',
								menu : [
										{
											text: '客户管理',
			                                items: [
			                                    {
			                                        id: '2',
			                                        text: '客户信息录入',
			                                        href: 'page/customer/addCustomer.jsp'
			                                    },
			                                    /*{
			                                        id: '3',
			                                        text: '客户信息修改',
			                                        href: 'page/customer/updateCustomer.jsp'
			                                    },*/
			                                    {
			                                        id: '4',
			                                        text: '客户信息查询',
			                                        href: 'CustomerAction!shoaPageHa?nowpage=1&thePath=no'
			                                    },
			                                    {
			                                        id: '5',
			                                        text: '客户充值',
			                                        href: 'page/customer/recharge.jsp'
			                                    }
			                                ]

										},
										 {
			                                text: '客房管理',
			                                items: [
			                                    {
			                                        id: '6',
			                                        text: '查询客房',
			                                        href: 'GuestroomAction!showPageHa?nowpage=1'
			                                    },
			                                    {
			                                        id: '7',
			                                        text: '添加客房',
			                                        href: 'page/Guestroom/addRoom.jsp'
			                                    } 
			                                ]
			                            },

										{
											text : '客户住宿',
											items : [
													{
														id : '11',
														text : '入住',
														href : 'page/checkInRoom/checkInRoom.jsp'
													}, {
														id : '14',
														text : '退房',
														href : 'HotelAction2!checkOutShowHa?nowpage=1&searchKey=noCheckOut&roomNo='
													} ]
										},
										{
											 text: '客房商品管理',
				                                items: [
				                                    {
				                                        id: '15',
				                                        text: '上新商品',
				                                        href: 'page/goods/addgoods.jsp'
				                                    },
				                                    {
				                                        id: '17',
				                                        text: '商品消费记录',
				                                        href: 'GoodsConsumeAction!showpageGoodsConsume?nowpage=1'
				                                    },
				                                   {
				                                        id: '16',
				                                        text: '添加消费记录',
				                                        href: 'page/goods/addgoodsconsume.jsp'
				                                    }, 
				                                    {
				                                        id: '31',
				                                        text: '查询商品',
				                                        href: 'GoodsAction!showpageGoods?nowpage=1&thepath=no'
				                                    }
				                                ]
				                            },

										{
											text : '账目管理',
											items : [
													{
														id : '19',
														text : '消费记录',
														href : 'HotelAction!shoaPageHa?nowpage=1&theKey=no&keyContent'
													},  {
														id : '31',
														text : '账单结算',
														href : 'HotelAction2!checkOutShowHa?nowpage=1&searchKey=noCheckOut&roomNo='
													},
												    {
														id : '21',
														text : '消费统计',
														href : 'AccountFindAction!countByRecords?year='+nowYear
													}
													
													]
										}, {
											text : '我的账户',
											items : [ {
												id : '23',
												text : '我的业绩',
												href : 'page/accountFind/employeePerfomance.jsp'
											}, {
												id : '24',
												text : '个人信息',
												href : 'page/user/listuser.jsp'
											}, {
												id : '25',
												text : '日志记录',
												href : 'page/logTable/addLog.jsp'
											}, {
												id : '26',
												text : '查看日志',
												href : 'LogAction!myLog'
											}]
										}
										/**管理员功能**/
								]
							} ];
							
							if('${sessionScope.loginUser.position}' == 'admin')
							{

								var config = [ {
									id : '1',
									menu : [
											{
												text: '客户管理',
				                                items: [
				                                    {
				                                        id: '2',
				                                        text: '客户信息录入',
				                                        href: 'page/customer/addCustomer.jsp'
				                                    },
				                                    /*{
				                                        id: '3',
				                                        text: '客户信息修改',
				                                        href: 'page/customer/updateCustomer.jsp'
				                                    },*/
				                                    {
				                                        id: '4',
				                                        text: '客户信息查询',
				                                        href: 'CustomerAction!shoaPageHa?nowpage=1&thePath=no'
				                                    },
				                                    {
				                                        id: '5',
				                                        text: '客户充值',
				                                        href: 'page/customer/recharge.jsp'
				                                    }
				                                ]

											},
											 {
				                                text: '客房管理',
				                                items: [
				                                    {
				                                        id: '6',
				                                        text: '查询客房',
				                                        href: 'GuestroomAction!showPageHa?nowpage=1'
				                                    },
				                                    {
				                                        id: '7',
				                                        text: '添加客房',
				                                        href: 'page/Guestroom/addRoom.jsp'
				                                    } 
				                                ]
				                            },

											{
												text : '客户住宿',
												items : [
														{
															id : '11',
															text : '入住',
															href : 'page/checkInRoom/checkInRoom.jsp'
														}, {
															id : '14',
															text : '退房',
															href : 'HotelAction2!checkOutShowHa?nowpage=1&searchKey=noCheckOut&roomNo='
														} ]
											},
											{
												 text: '客房商品管理',
					                                items: [
					                                    {
					                                        id: '15',
					                                        text: '上新商品',
					                                        href: 'page/goods/addgoods.jsp'
					                                    },
					                                    {
					                                        id: '17',
					                                        text: '商品消费记录',
					                                        href: 'GoodsConsumeAction!showpageGoodsConsume?nowpage=1'
					                                    },
					                                   {
					                                        id: '16',
					                                        text: '添加消费记录',
					                                        href: 'page/goods/addgoodsconsume.jsp'
					                                    }, 
					                                    {
					                                        id: '31',
					                                        text: '查询商品',
					                                        href: 'GoodsAction!showpageGoods?nowpage=1&thepath=no'
					                                    }
					                                ]
					                            },

											{
												text : '账目管理',
												items : [
														{
															id : '19',
															text : '消费记录',
															href : 'HotelAction!shoaPageHa?nowpage=1&theKey=no&keyContent'
														},  {
															id : '31',
															text : '账单结算',
															href : 'HotelAction2!checkOutShowHa?nowpage=1&searchKey=noCheckOut&roomNo='
														},
													    {
															id : '21',
															text : '消费统计',
															href : 'AccountFindAction!countByRecords?year='+nowYear
														}
														
														]
											}, {
												text : '我的账户',
												items : [  {
													id : '24',
													text : '个人信息',
													href : 'page/user/listuser.jsp'
												}, {
													id : '25',
													text : '日志记录',
													href : 'page/logTable/addLog.jsp'
												}, {
													id : '26',
													text : '查看日志',
													href : 'LogAction!myLog'
												}]
											}
											/**管理员功能**/
										
											, {
												text : '员工管理',
												items : [ {
													id : '28',
													text : '查询员工',
													href : 'UserAction!showPage?nowpage=1'
												},  {
													id : '22',
													text : '员工业绩',
													href : 'page/accountFind/allEmployeePerfomance.jsp'
												} , {
													id : '30',
													text : '查询单个员工',
													href : 'page/accountFind/employeePerfomance.jsp'
												}   ]
											}

									]
								} ];
							}
							
							
							new PageUtil.MainPage({
								modulesConfig : config
							});
						});
	</script>
</body>
</html>