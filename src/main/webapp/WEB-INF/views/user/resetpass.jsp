<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

    <!-- google fonts -->
    <style>
      @import url("https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap");
    </style>
    <style>
        body{
        height: 100vh;
        display:flex;
        justify-content: center;
        align-items:center;
        background-color: rgba(0,0,0,0.02);
        }
        input{
        width:100%;
        border: none;
        outline:none;
        border-bottom: 2px solid #26bac9;
        }
        .main{
        box-shadow: 0 0 30px 4px rgba(0,0,0,0.05);
        width: 500px;
        min-height: 200px;
        border-radius: 20px;
        padding: 30px;
        background-color: white;
        }
        .title{
        text-transform: uppercase;
        color: rgba(0,0,0,0.4);
        font-size: 1.3rem;
        margin: 0 0 30px 0;
        }
        .form-group{
         margin-bottom: 1.5rem;
        }
        .form-group > input{
        padding: 6px;
        font-size: 1.2rem;
        }
        input[type=submit]:not(:disabled){
        padding: 0.6rem 0.7rem;
        margin:16px 0;
        text-transform: uppercase;
        font-weight: bold;
        background-color: #26bac9;
        border: none;
        }
        .form-message{
        display:inline-block;
        font-size: 0.8rem;
        color:red;
        }
        #toast {
          position: fixed;
          top: 71px;
          right: 12px;
          z-index: 999999;
        }

        .toast {
          display: flex;
          position: relative;
          align-items: center;
          background-color: #fff;
          border-radius: 12px;
          padding: 20px 40px 20px 0;
          min-width: 400px;
          max-width: 500px;
          opacity: 1 !important;
          border-right: none;
          border-bottom: none;
          border-top: none;
          border-left: 7px solid;
          box-shadow: 0 5px 8px rgba(0, 0, 0, 0.08);
          transition: all linear 0.3s;
        }

        @keyframes slideInLeft {
          from {
            opacity: 0;
            transform: translateX(calc(100% + 32px));
          }
          to {
            opacity: 1;
            transform: translateX(0);
          }
        }

        @keyframes fadeOut {
          to {
            opacity: 0;
          }
        }

        .toast--success {
          border-color: #47d864;
        }

        .toast--success .toast__icon {
          color: #47d864;
        }

        .toast--info {
          border-color: #2f86eb;
        }

        .toast--info .toast__icon {
          color: #2f86eb;
        }

        .toast--warning {
          border-color: #ffc021;
        }

        .toast--warning .toast__icon {
          color: #ffc021;
        }

        .toast--error {
          border-color: #ff623d;
        }

        .toast--error .toast__icon {
          color: #ff623d;
        }

        .toast + .toast {
          margin-top: 24px;
        }

        .toast__icon {
          font-size: 24px;
        }

        .toast__icon {
          padding: 0 16px;
        }

        .toast__body {
          display: inline-block;
          flex-grow: 1;
        }

        .toast__title {
          font-size: 1.7rem;
          font-weight: 600;
          color: #333;
          margin-right: 10px;
        }

        .toast__msg {
          font-size: 1.5rem;
          color: #888;
          margin-top: 6px;
          line-height: 1.5;
        }
        .toast__close {
          position: absolute;
          top: 10px;
          right: 10px;
          display: flex;
          align-items: center;
          justify-content: center;
          width: 26px;
          height: 26px;
          border-radius: 50%;
          font-size: 20px;
          color: rgb(255, 170, 170);
          cursor: pointer;
          margin: 0 3px;
        }
    </style>
    <title>Đặt lại mật khẩu</title>
  </head>
<body>
<div id="toast"></div>
<form id="resetPassword" th:action="@{/reset_password}" method="post" style="max-width: 350px; margin: 0 auto;">
    <input type="hidden" name="token" th:value="${token}" />
<div class="main border-secondary ">
    <h3 class='title text-center'>Đặt lại mật khẩu</h2>
    <div>
                      <div class="form-group">
                        <input
                          type="password"
                          id="password"
                          name="password"
                          placeholder="Mật khẩu"
                        />
                        <span class="form-message"></span>
                      </div>
                      <div class="form-group">
                        <input
                          type="password"
                          id="confirmPassword"
                          placeholder="Xác nhận mật khẩu"
                        />
                        <span class="form-message"></span>
                      </div>

        <p class="text-center">
            <input type="submit" value="Thay đổi" class="btn btn-primary" />
        </p>
    </div>
</div>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="<c:url value ='/resources/js/validateLibrary.js'/>"></script>
<script src="<c:url value ='/resources/js/toast.js'/>"></script>
<script>
    Validator({
            form: "#resetPassword",
            formGroupSelector: ".form-group",
            errorSelector: ".form-message",
            rules: [
              Validator.isPassword(
                "#password",
                "Mật khẩu phải có ít nhất 8 ký tự và có ít nhất 1 chữ hoa và 1 chữ thường !"
              ),
              Validator.isConfirmed(
                "#confirmPassword",
                () => $("#password").val(),
                "Mật khẩu xác nhận không trùng khớp!"
              ),
            ],
            onSubmit: function (userInfo) {
            const formData = new FormData();
            const data = {
            pass: $("#password").val(),
            token: ""
            };
            const index = window.location.href.lastIndexOf('token=');
            const token = window.location.href.slice(index+6);
            data.token = token;
            formData.append("data", JSON.stringify(data));
            $.ajax({
                                url: "./reset_password",
                                type: "POST",
                                data: formData,
                                enctype: "multipart/form-data",
                                processData: false,
                                contentType: false,
                                cache: false,
                                success: function (res) {
                                  if (res == 1) {
                                    window.location.reload();
                                  }
                                },
                                error: function () {
                                  toast({
                                    title: "Có lỗi xảy ra khi gửi request về server !",
                                    message: "Vui lòng liên hệ quản trị viên để giải quyết !",
                                    type: "error",
                                    duration: 5000,
                                  });
                                },
                              });

            },
            onFormInvalid() {
              toast({
                title: "Các trường dữ liệu nhập không hợp lệ!",
                message: "Vui lòng chỉnh sửa lại nội dung thông tin tài khoản !",
                type: "error",
                duration: 5000,
              });
            },
          });
</script>
</body>
</html>