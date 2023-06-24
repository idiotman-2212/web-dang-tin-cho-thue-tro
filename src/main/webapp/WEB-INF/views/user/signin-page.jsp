<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="icon" href="<c:url value='/resources/assets/images/Logo-icon.png'/>">
    <!-- fontawesome -->
    <script
      src="https://kit.fontawesome.com/11a1459669.js"
      crossorigin="anonymous"
    ></script>
    <!-- css external links-->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="<c:url value='/resources/css/global.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/index.css'/>" />
    <!-- google fonts -->
    <style>
      @import url("https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap");
    </style>
    <title>Trang Đăng Nhập</title>
  </head>

  <body>
    <div id="form-login" class="form-login active iso-form" style="backdrop-filter: blur(2px) brightness(90%);position:relative")>
    <div style="position:absolute;top:20px;left:20px;color:white;font-size:2rem ;z-index:1000"><a href="./baiviet/index"><i class="fa-solid fa-house"></i> Trang Chủ</a></div>
     <form action="${pageContext.request.contextPath}/login" method="post">
      <div
        class="overlay-login"
        style="backdrop-filter: blur(2px) brightness(90%)">
        <div class="login active">
          <div class="btn-close">
            <i class="fa-solid fa-xmark"></i>
          </div>
          <div class="form">
            <h2>Đăng nhập</h2>
            <input type="text" name="username" placeholder="Tên đăng nhập" />
            <input type="password" name="password" placeholder="Mật khẩu" />
            <div class="login-sub-option">
              <a href="${pageContext.request.contextPath}forgotpass">Quên mật khẩu?</a>
              <a href="${pageContext.request.contextPath}/dangky">Tạo tài khoản mới!</a>
            </div>
            <button
              class="button button--submit active"
              onclick="submitHandler()"
              type= "submit"
            >
              Đăng nhập
            </button>
          </div>
        </div>
      </div>
    </form>
    </div>
    <script>
      function submitHandler() {
        console.log("submit form");
      }
    </script>
    <script
      src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
      integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
