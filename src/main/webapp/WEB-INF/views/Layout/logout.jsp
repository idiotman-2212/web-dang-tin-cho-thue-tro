<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <style>
      #myCustomToast {
        position: fixed;
        top: 0;
        left: 0;
        width: 100vw;
        height: 100vh;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s ease-in-out;
        background-color: rgba(250,250,250);
        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 10000;
      }
      .custom-toast-content {
        min-width: 30vw;
        min-height: 30vh;
        padding: 20px;
        background-color: white;
        border-radius: 20px;
        box-shadow: rgba(149, 157, 165, 0.3) 0 0 50px 10px;
        border-left: 5px solid #26bac9;
        display: flex;
        flex-direction: column;
        transition: all 0.3s ease-in-out;
        opacity: 0;
        transform: translateY(-20%);
      }
      .custom-toast-content > .heading {
        text-align: center;
        margin: 10px 0;
        text-transform: uppercase;
        color: rgb(146, 143, 143);
      }
      .custom-toast-content > .body {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .custom-toast-content > .body > .text-center {
        font-size: 80px;
        font-weight: bold;
        padding-bottom: 20px;
        color: #26bac9;
      }
      #myCustomToast.active {
        visibility: visible;
        opacity: 1;
      }
      #myCustomToast.active > .custom-toast-content {
        opacity: 1;
        transform: translateY(0);
      }
    </style>
  </head>
  <body>
    <div id="myCustomToast" class="active">
      <div class="custom-toast-content">
        <h3 class="heading">Đăng xuất thành công, về trang chủ trong</h3>
        <div class="body">
          <div class="text-center">0</div>
        </div>
      </div>
    </div>
    <script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
    ></script>
    <script>
    window.location.href = "../login";
    </script>
  </body>
</html>
