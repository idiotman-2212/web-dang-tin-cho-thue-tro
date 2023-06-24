<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="icon" href="<c:url value='/resources/assets/images/Logo-icon.png'/>">
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
      crossorigin="anonymous"
    />
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
    <title>Trang Đăng Ký</title>
  </head>
  <body>
    <div id="toast"></div>
    <div class="form-sign-up active iso-form">
      <div
        class="overlay-sign"
        style="backdrop-filter: blur(2px) brightness(90%)"
      >
        <div class="sign">
          <div class="form">
            <form  id="signup-form" >
              <h2>Đăng Ký</h2>
              <div class="form-group">
                <input
                  type="text"
                  id="fullName"
                  name="fullname"
                  placeholder="Họ và tên"
                />
                <span class="form-message"></span>
              </div>
              <div class="form-group">
                <input
                  type="text"
                  id="email"
                  name="email"
                  placeholder="Email"
                />
                <span class="form-message"></span>
              </div>
              <div class="form-group">
                <input
                  type="text"
                  id="phoneNumber"
                  name="phoneNumber"
                  placeholder="Số điện thoại"
                />
                <span class="form-message"></span>
              </div>
              <div class="form-group">
                <input
                  type="text"
                  id="address"
                  name="address"
                  placeholder="Địa chỉ"
                />
                <span class="form-message"></span>
                </div>

                <div class="form-group">
                  <input
                    type="text"
                    id="username"
                    name="username"
                    placeholder="Tên đăng nhập"
                  />
                 <span class="form-message"></span>
                </div>
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

              <button type="submit" class="button button--submit active">
                Đăng Ký
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
     <script
          src="https://code.jquery.com/jquery-3.6.0.min.js"
          integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
          crossorigin="anonymous"
        ></script>
    <script src="<c:url value ='/resources/js/validateLibrary.js'/>"></script>
    <script src="<c:url value ='/resources/js/toast.js'/>"></script>
    <script>
      Validator({
        form: "#signup-form",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
          Validator.isRequired("#fullName", "Vui lòng nhập tên đầy đủ !"),
          Validator.isEmail("#email"),
          Validator.isPhoneNumber(
            "#phoneNumber",
            "Không đúng định dạng số điện thoại !"
          ),
          Validator.isPassword(
            "#password",
            "Mật khẩu phải có ít nhất 8 ký tự và có ít nhất 1 chữ hoa và 1 chữ thường !"
          ),
           Validator.isRequired(
             "#address",
            "Vui lòng nhập địa chỉ  !"
           ),
          Validator.minLength(
            "#username",
            8,
            " Tên đăng nhập phải có ít nhất 8 ký tự !"
          ),
          Validator.isConfirmed(
            "#confirmPassword",
            () => $("#password").val(),
            "Mật khẩu xác nhận không trùng khớp!"
          ),
        ],
        onSubmit: function (userInfo) {
          const formData = new FormData();
          formData.append("account-info", JSON.stringify(userInfo));
          console.log(userInfo);
          $.ajax({
            url: "./api/user-signup",
            type: "POST",
            data: formData,
            enctype: "multipart/form-data",
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
              console.log(data);
              toast({
                title: "Thông báo!",
                message: data,
                type: "success",
                duration: 5000,
              });
              setTimeout(() => {
                window.location.href = "./baiviet/index";
              }, 2000);
            },
            error: function (data) {
              toast({
                title: "Có lỗi gửi dữ liệu về server!",
                message: data,
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
