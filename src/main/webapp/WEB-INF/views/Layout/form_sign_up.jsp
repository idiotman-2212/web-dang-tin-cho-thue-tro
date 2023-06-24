 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="form-sign-up" >
            <div class="overlay-sign">
                <div class="sign active">
                        <div class="btn-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                        <div class="form">
                            <h2>Đăng Kí</h2>
                            <form method= "post" action="${pageContext.request.contextPath}/nguoidung/dangki">
                                <input type="text" placeholder="Tên tài khoản" name="username"/>
                                <input type="text" placeholder="Email của bạn" name = "email"/>
                                <input type="password" placeholder="Password" name="pass"/>
                                <input type="password" placeholder="Confrim Password" name="confirmpass" />
                                 <button type="submit"  class="button button--submit active" id="submit" >Đăng Ký</button>
                            </form>
                        </div>
                </div>
            </div>
    </div>
