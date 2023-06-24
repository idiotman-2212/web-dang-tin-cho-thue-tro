<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- FORM - LOGIN -->
        <div class="form-login">
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="overlay-login">
                    <div class="login active">
                        <div class="btn-close">
                        </div>
                        <div class="form">
                            <h2>Đăng nhập</h2>
                            <input type="text" name="username" placeholder="Tên đăng nhập">
                            <input type="password" name="password" placeholder="Mật khẩu">
                            <div class="login-sub-option">
                                <a href="${pageContext.request.contextPath}/forgotpass">Quên mật khẩu?</a>
                                <a href="${pageContext.request.contextPath}/dangki">Tạo tài khoản mới!</a>
                            </div>
                            <button type= "submit"class="button button--submit active">Đăng nhập</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>