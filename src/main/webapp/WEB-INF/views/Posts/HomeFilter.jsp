<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<c:url value='/resources/assets/images/Logo-icon.png'/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- fontawesome -->
    <script src="https://kit.fontawesome.com/11a1459669.js" crossorigin="anonymous"></script>
    <!-- css external links-->
    <link rel="stylesheet" href="<c:url value='/resources/css/header_footer.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/global.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/index.css'/>">
     <script src="<c:url value='/resources/js/main.js'/>"></script>
    <!-- google fonts -->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap');
    </style>
        <style>
           input{
           outline:none !important;
           }
           .toast:not(.show) {
               display: flex;
           }
           .toast__title{
           margin: 10px 0;
           }
            .msp-item{
            cursor:pointer;
            color: var(--primary-color);
            }
        </style>
    <title>Thuê nhà trọ</title>
</head>
<body id="app" onload="loginHandler()">
    <div id= "toast"></div>
    <div id="instruction">
      <div class="text-effect">
       <i class="fa-solid fa-angles-left"></i>
       <span>Lọc</span>
      </div>
    </div>
<div class="filter">
      <div class="filter-container">
        <h3
          class="text-center"
          style="text-transform: uppercase; font-size: 1.8rem; margin: 0 0"
        >
          <i
            class="fa-solid fa-filter"
            style="color: #ccc; margin-right: 2px"
          ></i>
          Lọc bài viết
        </h3>
        <hr />
        <form action="" style="font-size: 17px">
          <div class="form-group">
            <label >
            <div class="form-check form-switch">
            <label class="form-check-label" for="province_status">Tỉnh - Thành phố</label>
            <input class="form-check-input" type="checkbox" role="switch" id="province_status" checked>
            </div>
            </label>
            <select
              id="filter-province"
              style="
                padding: 8px 3px;
                border-radius: 4px;
                border: 1px solid rgba(0, 0, 0, 0.1);
              "
            >
              <option>Chọn tỉnh - thành phố</option>
              <option>TP HCM</option>
              <option>Đà Nẵng</option>
            </select>
          </div>
          <div class="form-group">
            <label>
            <div class="form-check form-switch">
            <label class="form-check-label" for="district_status">Quận - Huyện</label>
            <input class="form-check-input" type="checkbox" role="switch" id="district_status" checked>
            </div>
            </label>
            <select
              id="filter-district"
              style="
                padding: 8px 3px;
                border-radius: 4px;
                border: 1px solid rgba(0, 0, 0, 0.1);
              "
            >
              <option value="">Chọn Quận , Huyện</option>
            </select>
          </div>
          <div class="form-group">
            <label>
            <div class="form-check form-switch">
            <label class="form-check-label" for="price_status">Giá</label>
            <input class="form-check-input" type="checkbox" role="switch" id="price_status" checked>
            </div>
            </label>
            <div class="range">
              <input
                type="range"
                max="50"
                min="0"
                step="0.1"
                value="0"
                class="form-range"
                id="range-price1"
              />
              <input
                type="range"
                max="50"
                min="0"
                step="0.1"
                value="5"
                class="form-range"
                id="range-price2"
              />
            </div>
            <div class="price-desc">
              Từ
              <span class="price-start">0</span>
              triệu -
              <span class="price-end">5</span>
              triệu
            </div>
          </div>
          <div class="form-group">
            <label>
            <div class="form-check form-switch">
            <label class="form-check-label" for="area_status">Diện Tích</label>
            <input class="form-check-input" type="checkbox" role="switch" id="area_status" checked>
            </div>
            </label>
            <div class="range">
              <input
                type="range"
                max="100"
                min="0"
                step="1"
                value="0"
                class="form-range"
                id="range-area1"
              />
              <input
                type="range"
                max="100"
                min="0"
                step="1"
                value="30"
                class="form-range"
                id="range-area2"
              />
            </div>
            <div class="area-desc">
              Từ
              <span class="area-start">0</span>
              m<sup>2</sup> -
              <span class="area-end">30</span>
              m<sup>2</sup>
            </div>
          </div>
          <button
            id="filter-submit"
            class="button button--hl"
            style="width: 100%; margin: 20px 0"
          >
            Lọc
          </button>
        </form>
        <div class="filter-label">
          <i class="fa-solid fa-angle-right"></i>
          <i class="fa-solid fa-angle-left"></i>
        </div>
      </div>
    </div>
<header id="header">
           <jsp:include page="../Layout/form_login.jsp" />
            <div class="header-wrapper">
              <div class="header-login">
                <div class="logo">
                  <a href="../index">
                    <img src="<c:url value='/resources/assets/images/logo.png'/>" alt="main logo" />
                  </a>
                </div>
                <div class="acc-wrapper ">
                  <div class="acc-login acc-component">
                    <button class="button btn-login">Đăng nhập</button>
                  </div>
                  <div class="acc-sign acc-component">
                    <a href="../dangki"><button class="button button--hl">Đăng ký</button></a>
                  </div>
                  <div class="acc-search acc-component">
                    <i class="fa-solid fa-magnifying-glass"></i>
                  </div>
                  <div class="acc-component acc-control">
                    <div class="acc-profile">
                      <div class="acc-name">
                        <span>${user.tenND}</span>
                      </div>
                      <div class="acc-avatar">
                        <img src="../${user.linkanhdaidien}" alt="avatar" />
                      </div>
                    </div>
                    <div class="acc-setting">
                      <a href="../../nguoidung/trangcanhan#account-general" class="acc-setting-item">
                        <i class="fa-solid fa-user"></i>
                        <span>Thông tin</span>
                      </a>
                       <a href="../../nguoidung/trangcanhan#account-post" class="acc-setting-item">
                           <i class="fa-solid fa-paste"></i>
                           <span>Quản lý tin</span>
                       </a>
                      <a href="../../nguoidung/trangcanhan#account-notifications" class="acc-setting-item">
                        <i class="fa-solid fa-bell"></i>
                        <span>Thông báo</span>
                      </a>
                      <a href="../../nguoidung/trangcanhan#account-feedback" class="acc-setting-item">
                        <i class="fa-solid fa-file-signature"></i>
                        <span>Góp Ý</span>
                      </a>
                      <a href="../../nguoidung/trangcanhan#account-change-password" class="acc-setting-item">
                        <i class="fa-solid fa-key"></i>
                        <span>Đổi mật khẩu</span>
                      </a>
                      <div class="separator"></div>
                      <a href="<c:url value='/logout' />" class="acc-setting-item logout">
                        <i class="fa-solid fa-right-from-bracket"></i>
                        <span>Đăng xuất</span>
                      </a>
                    </div>
                  </div>
                </div>
              </div>
              <div class="header-nav">
                <nav class="navigation">
                  <ul class="nav-list">
                    <li class="nav-item active">
                      <a class="nav-link" href="../index">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="../../admin/tongquan">Quản trị</a>
                    </li>
                    <li class="nav-item ">
                      <a class="nav-link" href="../../nguoidung/trangcanhan">Cá nhân</a>
                    </li>
                  </ul>
                  <div class="post" style="padding: 0">
                    <a href="../../nguoidung/dangbai">
                      <i class="fa-solid fa-pen-to-square"></i>
                      <span>Đăng tin</span>
                    </a>
                  </div>
                </nav>
              </div>
            </div>
          </header>
          <a href="#" class="gototop hide">
                      <i class="fa-solid fa-angle-up"></i>
                    </a>

            <jsp:useBean id="baiviet" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
    <div id="root">
        <section class="filter-section">
            <div class="filter-bg">
                <img src="<c:url value='/resources/assets/images/slide-bg.jpg'/>" alt="">
            </div>
            <div class="filter-wrapper">
                <div class="filter-title">
                    <h1 class="title">Website đăng tin cho thuê phòng trọ tại Việt Nam</h1>
                </div>
            </div>
        </section>
        <%-- form login and sign up --%>

        <main class="main">
            <div class="main-wrapper">
                <div class="main-breadcrumbs">
                    <a href="../index" class="mb-home">
                        <i class="fa-solid fa-house"></i>
                        <span>Home</span>
                    </a>
                </div>
                <div class="main-locations">
                    <h3 class="main-loca-title">Khu vực nổi bật</h3>
                    <ul class="main-loca-list">
                        <li class="main-loca-item">
                            <div onclick="sendValue('Thành phố Hồ Chí Minh')" class="main-local-link" >
                                <div class="ml-img">
                                    <img src="<c:url value='/resources/assets/images/location_hcm.jpg'/>" alt="">
                                </div>
                                <div class="ml-content">
                                    Hồ Chí Minh
                                </div>
                            </div>
                        </li>
                        <li class="main-loca-item">
                            <div class="main-local-link" onclick="sendValue('Thành phố Hà Nội')">
                                <div class="ml-img">
                                    <img src="<c:url value='/resources/assets/images/location_hn.jpg'/>" alt="">
                                </div>
                                <div class="ml-content">
                                    Hà Nội
                                </div>
                            </div>
                        </li>
                        <li class="main-loca-item">
                            <div class="main-local-link" onclick="sendValue('Thành phố Đà Nẵng')">
                                <div class="ml-img">
                                    <img src="<c:url value='/resources/assets/images/location_dn.jpg'/>" alt="">
                                </div>
                                <div class="ml-content">
                                    Đà Nẵng
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="main-content">
                <div class="left-content">
                    <div class="main-post">
                        <h3 class="main-post-title">Danh sách tin đăng</h3>
                        <div class="main-post-sort">
                            <span>Sắp xếp:</span>
                            <button onclick="sendFilterValue(1)" class="button active">Mặc định</button>
                            <button onclick="sendFilterValue(2)" class="button">Mới nhất</button>
                            <button onclick="sendFilterValue(3)" class="button">Có video</button>
                        </div>
                        <ul class="post-list">
                            <c:forEach var="item" items="${baiviet.pageList}">
                                <c:if test="${item.isDisplay()}">
                                 <li class="post-item">
                                     <div class="post-img">
                                         <a href="../../baiviet/chitiet/${item.mabaiviet}">
                                             <img src="<c:url value='/${item.getAnh().iterator().next().getLinkanh()}'/>" alt="">
                                         </a>
                                     </div>
                                     <div class="post-content">
                                         <a href="../../baiviet/chitiet/${item.mabaiviet}" class="post-title">
                                         ${item.tieude}
                                         </a>
                                         <div class="post-area">
                                             <i class="fa-solid fa-chart-area"></i>
                                             <span class="post-area-value">${item.dientich} m²</span>
                                         </div>
                                         <div class="post-location">
                                             <i class="fa-solid fa-map-location-dot"></i>
                                             <span class="post-location-value">
                                              ${item.diachi}, ${item.chitietbaiviet.phuongxa}, ${item.chitietbaiviet.quanhuyen}, ${item.chitietbaiviet.tinhtp}
                                             </span>
                                         </div>
                                         <div class="post-price">
                                             <span class="post-price-value">${item.gia} triệu/tháng</span>
                                         </div>
                                         <div class="post-lessor">
                                             <a href= "#">
                                                 <div class="pl-info">
                                                     <div class="pl-img">
                                                         <img src="../${item.nguoidung.linkanhdaidien}" alt="hình đại diện">
                                                     </div>
                                                     <span>${item.nguoidung.tenND}</span>
                                                 </div>
                                             </a>
                                             <button class="button">Gọi ${item.nguoidung.sdt}</button>
                                         </div>
                                     </div>
                                 </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                    <tg:paging pagedListHolder="${baiviet}"
				         pagedLink="${pagedLink}" />
                    </div>
                    <div class="right-content">
                    <div class="main-sidebar">

                        <div class="ms-section ms-prices">
                            <h3 class="ms-title">Xem theo giá</h3>
                            <div class="ms-content">
                                <ul class="msp-list">
                                    <li class="msp-item" onclick="sendPriceValue('0','1')">
                                        <div  class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Dưới 1 triệu</span>
                                        </div>
                                    </li>
                                    <li class="msp-item" onclick="sendPriceValue('1','2')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Từ 1 - 2 triệu</span>
                                        </div>
                                    </li>
                                    <li class="msp-item" onclick="sendPriceValue('2','3')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Từ 2 - 3 triệu</span>
                                        </div>
                                    </li>
                                    <li class="msp-item" onclick="sendPriceValue('3','5')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Từ 3 - 5 triệu</span>
                                        </div>
                                    </li>
                                    <li class="msp-item" onclick="sendPriceValue('5','7')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Từ 5 - 7 triệu</span>
                                        </div>
                                    </li>
                                    <li class="msp-item" onclick="sendPriceValue('7','10')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Từ 7 - 10 triệu</span>
                                        </div>
                                    </li>
                                    <li class="msp-item" onclick="sendPriceValue('10','15')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Từ 10 - 15 triệu</span>
                                        </div>
                                    </li>
                                    <li class="msp-item" onclick="sendPriceValue('15','200')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Trên 15 triệu</span>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="ms-section">
                            <h3 class="ms-title">Xem theo diện tích</h3>
                            <div class="ms-content">
                                <ul class="msp-list">
                                    <li class="msp-item" onclick="sendAreaValue('0','20')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Dưới 20 m2</span>
                                        </div>
                                    </li>
                                    <li class="msp-item" onclick="sendAreaValue('20','30')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Từ 20 - 30 m2</span>
                                        </div>
                                    </li>
                                    <li class="msp-item" onclick="sendAreaValue('30','50')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Từ 30 - 50 m2</span>
                                        </div>
                                    </li>
                                    <li class="msp-item">
                                        <div class="msp-link" onclick="sendAreaValue('50','70')">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Từ 50 - 70 m2</span>
                                        </div>
                                    </li>
                                    <li class="msp-item" onclick="sendAreaValue('70','90')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Từ 70 - 90 m2</span>
                                        </div>
                                    </li>
                                    <li class="msp-item" onclick="sendAreaValue('90','1000')">
                                        <div class="msp-link">
                                            <i class="fa-solid fa-angle-right"></i>
                                            <span>Trên 90 m2</span>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <jsp:include page="../Layout/footer.jsp" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
                integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous">
                </script>

    <script src="<c:url value='/resources/js/global.js'/>"></script>
    <script src="<c:url value='/resources/js/signedIn.js'/>"></script>
    <script src="<c:url value='/resources/js/main.js'/>"></script>
    <script src="<c:url value='/resources/js/toast.js'/>"></script>
    <script src="<c:url value='/resources/js/filter.js'/>"></script>
    <script>
        if($(".post-item").length === 0){
        const span = document.createElement('span');
        $(".post-list").text("Hiện tại không có bài viết nào để hiển thị !");
        }
        const sendFilterValue = (data) => {
          const formData = new FormData();
          formData.append("filter", JSON.stringify({ data: String(data) }));
          let URL = "../post-filter-by-option";
          $.ajax({
            url: URL,
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
        };
    </script>
</body>
</html>