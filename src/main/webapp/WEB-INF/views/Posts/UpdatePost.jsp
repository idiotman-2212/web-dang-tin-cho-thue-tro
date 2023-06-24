<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="icon"
      href="<c:url value='/resources/assets/images/Logo-icon.png'/>"
    />
    <!-- fontawesome -->
    <script
      src="https://kit.fontawesome.com/11a1459669.js"
      crossorigin="anonymous"
    ></script>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
      crossorigin="anonymous"
    />
    <!-- css external links-->
    <link
      rel="stylesheet"
      href="<c:url value='/resources/css/header_footer.css'/>"
    />
    <link rel="stylesheet" href="<c:url value='/resources/css/global.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/index.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/post.css'/>" />
    <!-- google fonts -->
    <style>
      @import url("https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap");
    </style>
    <!-- BOOSTRAP-->
    <title>Cập nhật bài viết</title>
  </head>
  <body id="app">
    <div id="toast"></div>
    <header id="header">
      <div class="header-wrapper">
        <div class="header-login">
          <div class="logo">
            <a href="" #>
              <img
                src="<c:url value='/resources/assets/images/logo.png'/>"
                alt="main logo"
              />
            </a>
          </div>
          <div class="acc-wrapper signed">
            <div class="acc-login acc-component">
              <button class="button btn-login">Đăng nhập</button>
            </div>
            <div class="acc-sign acc-component">
              <button class="button button--hl">Đăng ký</button>
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
                <a href="#account-general" class="acc-setting-item">
                  <i class="fa-solid fa-user"></i>
                  <span>Thông tin</span>
                </a>

                <a href="#" class="acc-setting-item">
                  <i class="fa-solid fa-bell"></i>
                  <span>Thông báo</span>
                </a>
                <a href="#" class="acc-setting-item">
                  <i class="fa-solid fa-file-signature"></i>
                  <span>Góp Ý</span>
                </a>
                <a href="#" class="acc-setting-item">
                  <i class="fa-solid fa-key"></i>
                  <span>Đổi mật khẩu</span>
                </a>
                <div class="separator"></div>
                <a
                  href="<c:url value='/logout' />"
                  class="acc-setting-item logout"
                >
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
              <li class="nav-item">
                <a class="nav-link" href="../../baiviet/index">Trang chủ</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Khám phá</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Cộng đồng</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="../admin/tongquan">Quản trị</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="../../nguoidung/trangcanhan"
                  >Cá nhân</a
                >
              </li>
            </ul>
            <div class="post">
              <a href="../../nguoidung/dangbai">
                <i class="fa-solid fa-pen-to-square"></i>
                <span>Đăng tin</span>
              </a>
            </div>
          </nav>
        </div>
      </div>
    </header>
    <div id="root">
      <!-- FORM - LOGIN -->
      <div class="form-login">
        <div class="overlay-login">
          <div class="login active">
            <div class="btn-close">
              <i class="fa-solid fa-xmark"></i>
            </div>
            <div class="form">
              <h2>Đăng nhập</h2>
              <input type="text" placeholder="Tên đăng nhập" />
              <input type="password" placeholder="Mật khẩu" />
              <div class="login-sub-option">
                <a href="#">Quên mật khẩu?</a>
                <a href="#">Tạo tài khoản mới!</a>
              </div>
              <button class="button button--submit active">Đăng nhập</button>
            </div>
          </div>
        </div>
      </div>
      <!-- FORM - SIGN UP -->
      <div class="form-sign-up">
        <div class="overlay-sign">
          <div class="sign active">
            <div class="btn-close">
              <i class="fa-solid fa-xmark"></i>
            </div>
            <div class="form">
              <h2>Đăng Kí</h2>
              <input type="text" placeholder="Tên tài khoản" />
              <input
                type="text"
                value="exam@gmail.com"
                placeholder="Email của bạn"
              />
              <input type="password" placeholder="Password" />
              <input type="password" placeholder="Confrim Password" />
              <button class="button button--submit active">Đăng Kí</button>
            </div>
            <!-- end header-->
          </div>
        </div>
      </div>
      <a href="#" class="gototop hide">
        <i class="fa-solid fa-angle-up"></i>
      </a>
      <main class="main">
        <div class="main-wrapper">
          <div class="main-breadcrumbs">
            <a href="../baiviet/index" class="mb-home">
              <i class="fa-solid fa-house"></i>
              <span>Home</span>
            </a>
            /
            <a href="#" class="mb-detail">
              <span>Cập nhật bài viết</span>
            </a>
          </div>
          <div class="main-content">
            <div class="left-content">
              <div class="container">
                <p class="h1 text-center form-title">Cập nhật bài viết</p>

                <div class="section">
                  <form id="info-form" enctype="multipart/form-data">
                    <p class="h3 section-heading">Địa chỉ cho thuê</p>
                    <hr />
                    <div class="form-group">
                      <label for="form-province"

                        >Tỉnh thành Phố <span style="color: red">*</span>
                      </label>
                      <select
                        name="province"
                        class="form-control"
                         data-value = "${post.chitietbaiviet.tinhtp}"
                        id="form-province"
                      ></select>
                      <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                      <label for="form-district"
                        >Quận Huyện <span style="color: red">*</span>
                      </label>
                      <select
                        name="district"
                        class="form-control"
                        data-value = "${post.chitietbaiviet.quanhuyen}"
                        id="form-district"
                      >
                        <option value="">Chọn Quận Huyện</option>
                      </select>
                      <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                      <label for="form-commue"
                        >Phường Xã <span style="color: red">*</span>
                      </label>
                      <select
                        name="wards"
                        data-value = "${post.chitietbaiviet.phuongxa}"
                        class="form-control"
                        id="form-commue"
                      >
                        <option value="">Chọn Phường Xã</option>
                      </select>
                      <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                      <label for="form-street">Đường phố/ số nhà</label>
                      <input
                        name="street"
                        type="text"
                        class="form-control"
                        id="form-street"
                        placeholder="Ví dụ: 14/1c đường 359"
                        value= "${post.diachi}"
                      />
                      <span class="form-message"></span>
                    </div>
                    <p class="h3 section-heading">Thông tin bài đăng</p>
                    <hr />
                    <div class="form-group">
                      <label for="form-title"
                        >Tiêu đề <span style="color: red">*</span>
                      </label>
                      <input
                        name="title"
                        type="text"
                        class="form-control"
                        value="${post.tieude}"
                        id="form-title"
                      />
                      <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                      <label for="form-desc"
                        >Nội dung mô tả <span style="color: red">*</span>
                      </label>
                      <textarea
                        class="form-control"
                        id="form-desc"
                        name="descriptions"
                        rows="6"
                      ></textarea>
                      <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                      <label for="form-price"
                        >Giá cho thuê (lưu ý đơn vị là triệu VND)<span
                          style="color: red"
                          >*</span
                        >
                      </label>
                      <input
                        name="price"
                        type="text"
                        value = "${post.gia}"
                        class="form-control"
                        id="form-price"
                        placeholder="Đơn vị: triệu VND"
                      />
                      <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                      <label for="form-area"
                        >Diện tích phòng <span style="color: red">*</span>
                      </label>
                      <input
                        name="area"
                         value = "${post.dientich}"
                        type="text"
                        class="form-control"
                        id="form-area"
                        placeholder="Đơn vị: m2"
                      />
                      <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                      <label for="form-image"
                        >Chọn hình ảnh cho bài đăng
                        <span style="color: red">*</span>
                      </label>
                      <div class="upload__box">
                        <div class="upload__btn-box">
                          <label class="upload__btn">
                            <p>Tải ảnh</p>
                            <input
                              type="file"
                              id="fileUpload"
                              multiple
                              accept=".jpg, .png"
                              data-max_length="6"
                              class="upload__inputfile"
                            />
                          </label>
                        </div>
                        <div class="upload__img-wrap">
                        <c:forEach var="image" items="${images}">
                            <div class="upload__img-box dataFill " data-imageid= "${image.maanh}" >
                              <div style="background-image: url(../../${image.linkanh})" data-file= "${image.maanh}" class="img-bg">
                                <div class="upload__img-close"></div>
                              </div>
                            </div>
                        </c:forEach>
                        </div>
                        <span class="form-message imageMessage"></span>
                      </div>
                    </div>
                    <hr />
                    <div class="form-group">
                      <label
                        >Chọn video cho bài đăng (<i
                          >mỗi bài viết chỉ được phép có 1 video</i
                        >)</label
                      >
                      <div class="upload__box">
                        <div class="upload__btn-box">
                          <label for="video_input" class="upload__btn">
                            <p>Tải Video</p>
                            <input
                              type="file"
                              id="video_input"
                              accept="video/mp4,video/x-m4v,video/*"
                              class="upload_videoFile"
                            />
                          </label>
                        </div>
                        <div class="upload__video-wrap">
                          <c:choose>
                          <c:when test="${video!=null}"><video id="video-tag" src="../../${video}" controls></video></c:when>
                          <c:when test="${video==null}"><video id="video-tag" src="" controls></video></c:when>
                          </c:choose>
                            <div class="close-video" style="cursor:pointer">
                                      <i class="fa-solid fa-xmark" style="color:white;font-size:2.3rem"></i>
                               </div>
                          <div class="video-placeholder">
                            <div class="video-icon">
                              <i class="fa-solid fa-film"></i>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <button
                      type="submit"
                      class="button button--submit button--hl"
                    >
                      LƯU THAY ĐỔI
                    </button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
    <jsp:include page="../Layout/footer.jsp" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
    ></script>
    <script src="<c:url value ='/resources/js/global.js'/>"></script>
    <script src="<c:url value ='/resources/js/validateLibrary.js'/>"></script>
    <script src="<c:url value ='/resources/js/toast.js'/>"></script>
    <script src="<c:url value ='/resources/js/update.js'/>"></script>
    <script src="<c:url value='/resources/js/signedIn.js'/>"></script>
    <script>
        $(()=>{
            initialText = "${post.chitietbaiviet.mota}".replaceAll('//#','\n');
            $("#form-desc").text(initialText);
            const ID = "${id}";
            const main  = $('.main').attr('id',ID);
            window.onload  = ()=>{
            const videoURL = $("#video-tag").attr('src');
            if(videoURL){
                $(".video-placeholder").addClass('hide');
            }
            }
        })
    </script>
  </body>
</html>
