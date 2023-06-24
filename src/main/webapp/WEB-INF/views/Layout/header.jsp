<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <header id="header">
        <div class="header-wrapper">
            <div class="header-login">
                <div class="logo">
                    <a href="" #>
                        <img src="<c:url value='/resources/assets/images/logo.png'/>" alt="main logo">
                    </a>
                </div>
                <div class="acc-wrapper">
                    <div class="acc-login acc-component">
                        <button class="button btn-login">Đăng nhập</button>
                    </div>
                    <div class="acc-sign acc-component">
                        <button class="button button--hl">Đăng ký</button>
                    </div>
                    <div class="acc-search acc-component">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </div>
                </div>
            </div>
            <div class="header-nav">
                <nav class="navigation">
                    <ul class="nav-list">
                        <li class="nav-item ">
                            <a class="nav-link " href="../baiviet/index">Trang chủ</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Khám phá</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Cộng đồng</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Dịch vụ</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Hướng dẫn</a>
                        </li>
                    </ul>
                       <div class="post">
                        <a class="nav-link" href="../nguoidung/dangbai">
                            <i class="fa-solid fa-pen-to-square"></i>
                            <span>Đăng tin</span>
                        </a>
                    </div>
                   
                </nav>
            </div>
        </div>
    </header>