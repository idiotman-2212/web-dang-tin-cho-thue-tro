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
    <link rel="stylesheet" href="<c:url value='/resources/css/global.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/index.css'/>" />
    <!-- google fonts -->
    <style>
      @import url("https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap");
    </style>
    <title>Quên mật khẩu</title>
  </head>
  <body>
    <div id="form-login" class="form-login active iso-form" style="backdrop-filter: blur(2px) brightness(90%);")>

         <div
           class="overlay-login"
           style="backdrop-filter: blur(2px) brightness(90%)">
           <div class="login active">
             <div class="btn-close">
               <i class="fa-solid fa-xmark"></i>
             </div>
             <div class="form">
               <h2>Đăng nhập</h2>
               <input type="text" name="email" id="email" placeholder="Nhập email đã đăng ký" />
               <button
                 class="button button--submit active"
                 onclick="submit()"
               >
                 Xác nhận
               </button>
             </div>
           </div>
         </div>

       </div>
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
       <script src="<c:url value='/resources/js/toast.js'/>"></script>
       <script>
         function submit() {
           $.ajax({
           type: "GET",
           url:"checkmailforgot",
           data: { email: $("#email").val()},
           timeout: 10000,
           success: function(data){
                      alert(data)
                 },
           error : function(error){
                     toast({
                           title: "Thông báo",
                           message: "Có lỗi xảy ra, vui lòng thử lại sau",
                           type: "error",
                           duration: 5000,
                            });
           }
           });
         }
       </script>
       <script
         src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"
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