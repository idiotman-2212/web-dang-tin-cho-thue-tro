<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link rel="icon" href="<c:url value='/resources/assets/images/Logo-icon.png'/>">
    <meta name="description" content="" />
    <meta name="author" content="" />
<title>Quản lý thông báo</title>
<link
      href="<c:url value= '/resources/vendor/fontawesome-free/css/all.min.css'/>"
      rel="stylesheet"
      type="text/css"
    />
<!-- Google Fonts -->
    <style>
      @import url("https://fonts.googleapis.com/css2?family=Noto+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap");
    </style>
    <!-- Custom styles for this template-->
    <link href="<c:url value='/resources/css/admin/sb-admin-2.css'/>" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/main.css'/>" />
</head>
<body>
<!-- Page Wrapper -->
    <div id="wrapper">
      <!-- Sidebar -->
         <jsp:include page="../Admin/layout/menu.jsp" />

      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
          <!-- Topbar -->
          <nav
            class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow"
          >
            <!-- Sidebar Toggle (Topbar) -->
            <button
              id="sidebarToggleTop"
              class="btn btn-link d-md-none rounded-circle mr-3"
            >
              <i class="fa fa-bars"></i>
            </button>

            <!-- Topbar Navbar -->
            <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown no-arrow d-sm-none">
                            <a
                              class="nav-link dropdown-toggle"
                              href="#"
                              id="searchDropdown"
                              role="button"
                              data-toggle="dropdown"
                              aria-haspopup="true"
                              aria-expanded="false"
                            >
                              <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div
                              class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                              aria-labelledby="searchDropdown"
                            >
                              <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                  <input
                                    type="text"
                                    class="form-control bg-light border-0 small"
                                    placeholder="Search for..."
                                    aria-label="Search"
                                    aria-describedby="basic-addon2"
                                  />
                                  <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                      <i class="fas fa-search fa-sm"></i>
                                    </button>
                                  </div>
                                </div>
                              </form>
                            </div>
                          </li>
                          <!-- Nav Item - Alerts -->
                          <li
                            style="display: flex; align-items: center"
                            class="nav-item dropdown no-arrow mx-1"
                          >
                            <button
                              type="button"
                              class="btn"
                              data-toggle="modal"
                              data-target=".bd-example-modal-lg"
                              data-whatever="@mdo"
                            >
                              <i style="color: #26bac9" class="fas fa-bell fa-fw"></i>
                            </button>
                            <!-- Dropdown - Alerts -->
                            <div
                              class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                              aria-labelledby="alertsDropdown"
                            >
                              <h6 class="dropdown-header">Alerts Center</h6>
                              <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                  <div class="icon-circle bg-primary">
                                    <i class="fas fa-file-alt text-white"></i>
                                  </div>
                                </div>
                                <div>
                                  <div class="small text-gray-500">December 12, 2019</div>
                                  <span class="font-weight-bold"
                                    >A new monthly report is ready to download!</span
                                  >
                                </div>
                              </a>
                              <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                  <div class="icon-circle bg-success">
                                    <i class="fas fa-donate text-white"></i>
                                  </div>
                                </div>
                                <div>
                                  <div class="small text-gray-500">December 7, 2019</div>
                                  $290.29 has been deposited into your account!
                                </div>
                              </a>
                              <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                  <div class="icon-circle bg-warning">
                                    <i class="fas fa-exclamation-triangle text-white"></i>
                                  </div>
                                </div>
                                <div>
                                  <div class="small text-gray-500">December 2, 2019</div>
                                  Spending Alert: We've noticed unusually high spending for
                                  your account.
                                </div>
                              </a>
                              <a
                                class="dropdown-item text-center small text-gray-500"
                                href="#"
                                >Show All Alerts</a
                              >
                            </div>
                          </li>
                          <!-- Nav Item - Messages -->
                          <div class="topbar-divider d-none d-sm-block"></div>
              <!-- Nav Item - User Information -->
              <li class="nav-item dropdown no-arrow">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  id="userDropdown"
                  role="button"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                      <span class="mr-2 d-none d-lg-inline text-gray-600 small"
                                      >${user.tenND}</span
                                    >
                                    <div style="width:40px;height:40px;border-radius:50%;overflow:hidden;">
                                                      <img style="width: 100%;height: 100%;object-fit:cover" src="${user.linkanhdaidien}"/>
                                                      </div>
                </a>
                <!-- Dropdown - User Information -->
                <div
                  class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                  aria-labelledby="userDropdown"
                >
                  <a class="dropdown-item" href="../nguoidung/trangcanhan">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Thông tin
                  </a>
                  <a class="dropdown-item" href="#">
                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                    Đổi mật khẩu
                  </a>
                  <div class="dropdown-divider"></div>
                  <a
                    class="dropdown-item"
                    href="<c:url value='/logout' />"
                    data-toggle="modal"
                    data-target="#logoutModal"
                  >
                    <i
                      class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"
                    ></i>
                    Đăng xuất
                  </a>
                </div>
              </li>
            </ul>
          </nav>
          <!-- End of Topbar -->
<!-- Begin Page Content -->
		 <div class="container-fluid">
		 <jsp:useBean id="thongbao" scope="request"
                        type="org.springframework.beans.support.PagedListHolder" />
                        <c:url value="" var="pagedLink">
                        <c:param name="p" value="~" />
                       </c:url>
            <h1 class="h3 mb-4 text-gray-800">QUẢN LÝ THÔNG BÁO</h1>
            <!-- DataTales Example -->
            <div class="card shadow mb-4">
              <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Thông tin thông báo</h6>
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table
                    class="table table-bordered"
                    id="dataTable"
                    width="100%"
                    cellspacing="0"
                  >
                    <thead>
                      <tr>
                        <th>Mã thông báo</th>
                        <th>Thời gian thông báo</th>
                        <th>Nội dung thông báo</th>
                        <th>Tài khoản người nhận</th>
                      </tr>
                    </thead>
                    <tfoot>
                      <tr>
                        <th>Mã thông báo</th>
                        <th>Thời gian thông báo</th>
                        <th>Nội dung thông báo</th>
                        <th>Tài khoản người nhận</th>
                      </tr>
                    </tfoot>
                    <tbody>
                       <c:forEach var="tb" items="${thongbao.pageList}">
							<tr>
								<td>${tb.matb}</td>
								<td>${tb.thoigian}</td>
								<td>${tb.noidung}</td>
								<td>${tb.nguoidung.email}</td>
							</tr>
						</c:forEach>
                    </tbody>
                  </table>
                  <tg:paging pagedListHolder="${thongbao}"
                  				pagedLink="${pagedLink}" />
                </div>
              </div>
            </div>
          </div>
          <!-- /.container-fluid -->
        </div>
 <!-- Footer -->
        <footer class="sticky-footer bg-white">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>Copyright &copy; timtro.com 2022</span>
            </div>
          </div>
        </footer>

        <!-- End of Footer -->
      </div>
      <!-- End of Content Wrapper -->
    </div>

      <div class="noti-create-wrapper">
          <div
            class="modal fade bd-example-modal-lg"
            id="notifcation-modal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="myLargeModalLabel"
            aria-hidden="true"
          >
            <div class="modal-dialog modal-lg" role="document">
              <form class="modal-content" action="ThongBao.html" method="post">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">
                   Gửi thông báo cho người dùng
                  </h5>
                  <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-label="Close"
                  >
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <div >
                    <div class="form-group">
                      <label for="userID" class="col-form-label">
                      Địa chỉ email người gửi</label>
                      <input type="text" class="form-control" name = "from" id="userID" />
                    </div>
                    <div class="form-group">
                      <label for="userID" class="col-form-label">
                        Địa chỉ email người nhận</label>
                      <input type="email" name="to" class="form-control" id="notiEmail" />
                    </div>
                    <div class="form-group">
                      <label for="userID" class="col-form-label">
                        Tiêu đề thông báo</label>
                      <input type="text" name="subject" class="form-control" id="notiTitle" lang="vi" />
                    </div>
                    <div class="form-group">
                      <label for="noti-content" class="col-form-label">
                        Nội dung thông báo:</label>
                      <textarea
                        class="form-control"
                        rows="5"
                        id="noti-content"
                        name="body"
                        lang="vi"
                      ></textarea>
                    </div>
                  </div>
                </div>
                <div class="modal-footer">
                  <button
                    type="button"
                    class="btn btn-secondary"
                    data-dismiss="modal"
                  >
                    Hủy
                  </button>
                  <a href="../admin/ThongBao.htm?guiMail">
                  <button type="submit" name ="guiMail"id="noti-submit" class="btn btn-primary">
                    Gửi thông báo
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
          <a class="scroll-to-top rounded" href="#page-top">
              <i class="fas fa-angle-up"></i>
            </a>
    <script>
          $(() => {
            const notiContent = $("#noti-content");
            const notiTitle = $("#notiTitle");
            const notiEmail = $("#notiEmail");
            const userID = $("#userID");
            const submitBtn = $("#noti-submit");
            submitBtn.click(() => {
              console.log(notiContent.val());
              console.log(userID.val());
              console.log(notiTitle.val());
              console.log(notiEmail.val());
            });
          });
    </script>
</body>
</html>