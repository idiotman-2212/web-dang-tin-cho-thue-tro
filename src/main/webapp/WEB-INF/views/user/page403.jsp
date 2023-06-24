<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<c:url value='/resources/assets/images/Logo-icon.png'/>">
    <!-- fontawesome -->
    <script src="https://kit.fontawesome.com/11a1459669.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <!-- css external links-->
   
    <!-- google fonts -->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');
    </style>
    <!-- BOOSTRAP-->
    <title>ĐĂNG TIN</title>
    <style>

/*======================
    404 page
=======================*/


.page_404{ padding:40px 0; background:#fff; font-family: 'Arvo', serif;
}

.page_404  img{ width:100%;}

.four_zero_four_bg{

 background-image: url(https://cdn.dribbble.com/users/285475/screenshots/2083086/dribbble_1.gif);
    height: 400px;
    background-position: center;
 }


 .four_zero_four_bg h1{
 font-size:80px;
 }

  .four_zero_four_bg h3{
			 font-size:80px;
			 }

 .link_404{
	color: #fff;
	font-weight: bold;
	border-radius: 6px;
    padding: 10px 20px;
    background: #26bac9;
    margin: 20px 0;
    transition: all 0.3s ease;
    box-shadow: 0 0 20px 0px rgba(0,0,0,0.1);
    display: inline-block;}
	.contant_box_404{ margin-top:-50px;}
	.link_404:hover{
	text-decoration: none;
	background-color: white;
	color: #26bac9;
	}
    </style>
</head>
<body>
<section class="page_404" style="height:100vh;padding:220px 0">
	<div class="container">
		<div class="row">
		<div class="col-sm-12 ">
		<div class="col-sm-10 col-sm-offset-1  text-center" style="margin: 0 auto">
		<div class="four_zero_four_bg">
			<h1 class="text-center ">404</h1>
		</div>
		<div class="contant_box_404">
		<h3 class="h2">
		Bạn không có quyền truy cập vào địa chỉ này!
		</h3>
		<a href="../" class="link_404">Về trang chủ</a>
	</div>
		</div>
		</div>
		</div>
	</div>
</section>
</body>
</html>