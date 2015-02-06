<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<link href="/css/plugins/bootstrap.min.css" rel="stylesheet">
	<link href="/css/plugins/sb-admin.css" rel="stylesheet">
	<link href="/css/plugins/morris.css" rel="stylesheet">
	<link href="/css/admin_base.css" rel="stylesheet">
	<title><decorator:title default="미래식당 관리자" /></title><decorator:head />
</head>
<body>

<div id="wrapper">

	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">미래식당</a>
		</div>
		<!-- Top Menu Items -->
		<ul class="nav navbar-right top-nav">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> User Name <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li>
						<a href="/admin/profile"><span class="glyphicon glyphicon-inbox" aria-hidden="true"></span> Profile</a>
					</li>
					<li class="divider"></li>
					<li>
						<a href="/admin/logout"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> Log Out</a>
					</li>
				</ul>
			</li>
		</ul>

		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">
				<li class="active">
					<a href="/"><span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span> Dashboard </a>
				</li>
				<li>
					<a href="javascript:;" data-toggle="collapse" data-target="#shop"><span class="glyphicon glyphicon-gift" aria-hidden="true"></span>  상품관리 </a>
					<ul id="shop" class="collapse">
						<li>
							<a href="/item/list">상품 목록</a>
						</li>
						<li>
							<a href="/item/add">상품 추가</a>
						</li>
						<li>
							<a href="/item/charge">배송비 관리</a>
						</li>
						<li>
							<a href="/item/category">카테고리</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" data-toggle="collapse" data-target="#item"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 상점관리 </a>
					<ul id="item" class="collapse">
						<li>
							<a href="/item/list">상점 목록</a>
						</li>
						<li>
							<a href="/item/add">상점 추가</a>
						</li>
						<li>
							<a href="/item/charge">상점 소식</a>
						</li>
						<li>
							<a href="/item/category">상점 지역</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" data-toggle="collapse" data-target="#user"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 회원관리 </a>
					<ul id="user" class="collapse">
						<li>
							<a href="/user/list">회원 목록</a>
						</li>
						<li>
							<a href="/user/add">회원 추가</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" data-toggle="collapse" data-target="#order"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> 주문관리 </a>
					<ul id="order" class="collapse">
						<li>
							<a href="/item/list">주문</a>
						</li>
						<li>
							<a href="/item/list">발주</a>
						</li>
						<li>
							<a href="/item/list">결제</a>
						</li>
						<li>
							<a href="/item/add">쿠폰</a>
						</li>
						<li>
							<a href="/item/charge">포인트</a>
						</li>
						<li>
							<a href="/item/category">배송관리</a>
						</li>
						<li>
							<a href="/item/category">내보내기</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" data-toggle="collapse" data-target="#communicate"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span> 커뮤니케이션 </a>
					<ul id="communicate" class="collapse">
						<li>
							<a href="/item/list">상품 리뷰</a>
						</li>
						<li>
							<a href="/item/add">상품 QnA</a>
						</li>
						<li>
							<a href="/item/charge">상점 QnA</a>
						</li>
						<li>
							<a href="/item/category">고객 1:1</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" data-toggle="collapse" data-target="#meesig"><span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span> 미래식당 </a>
					<ul id="meesig" class="collapse">
						<li>
							<a href="#">공지사항</a>
						</li>
						<li>
							<a href="#">미식뉴스</a>
						</li>
						<li>
							<a href="#">미식댓글</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" data-toggle="collapse" data-target="#report"><span class="glyphicon glyphicon-signal" aria-hidden="true"></span> 보고서 </a>
					<ul id="report" class="collapse">
						<li>
							<a href="/report/list">주문 보고서</a>
						</li>
						<li>
							<a href="/report/add">고객 보고서</a>
						</li>

					</ul>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</nav>

	<decorator:body />


</div>


<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<decorator:getProperty property="page.local_script"></decorator:getProperty>

</body>
</html>