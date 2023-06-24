<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix = "c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link rel="icon" href="<c:url value='/resources/assets/images/Logo-icon.png'/>">
    <title>Thống kê người dùng</title>
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
    <link
      rel="stylesheet"
      href="<c:url value='/resources/css/admin/sb-admin-2.css'/>"
    />
    <link
      rel="stylesheet"
      href="<c:url value='/resources/css/admin/main.css'/>"
    />
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
                      <jsp:useBean
                        id="nguoidung"
                        scope="request"
                        type="org.springframework.beans.support.PagedListHolder"
                      />
                      <c:url value="" var="pagedLink">
                        <c:param name="p" value="~" />
                      </c:url>
                      <h1 class="h3 mb-4 text-gray-800">QUẢN LÝ TÀI KHOẢN</h1>
                      <!-- DataTales Example -->
                      <div class="card shadow mb-4">
                        <div class="card-header py-3">
                          <h6 class="m-0 font-weight-bold text-primary">
                            Thông tin tài khoản
                          </h6>
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
                                  <th>Tài khoản</th>
                                  <th>SĐT</th>
                                  <th>Email</th>
                                  <th>Địa chỉ</th>
                                  <th>Gửi thông báo</th>
                                  <th>Vai trò</th>
                                </tr>
                              </thead>
                              <tfoot>
                                <tr>
                                  <th>Tài khoản</th>
                                  <th>SĐT</th>
                                  <th>Email</th>
                                  <th>Địa chỉ</th>
                                  <th>Gửi thông báo</th>
                                  <th>Vai trò </th>
                                </tr>
                              </tfoot>
                              <tbody>
                                <c:forEach var="nd" items="${nguoidung.pageList}">
                                  <tr>
                                    <td>${nd.taikhoan.tenDN}</td>
                                    <td>${nd.sdt}</td>
                                    <td>${nd.email}</td>
                                    <td>${nd.diachi}</td>
                                    <td><button type="button" id="${nd.maND}" class="btn" onclick="getid(this.id)" data-toggle="modal" data-target=".bd-example-modal-lg" data-whatever="@mdo">
                                                <i style="color: #26bac9" class="fas fa-bell fa-fw"></i>
                                                </button></td>
                                    <td>${nd.taikhoan.quyen.tenquyen}</td>

                                  </tr>

                                </c:forEach>
                              </tbody>
                            </table>
                            <tg:paging
                              pagedListHolder="${nguoidung}"
                              pagedLink="${pagedLink}"
                            />
                          </div>
                        </div>
                      </div>
                    </div>
          <!-- /.container-fluid -->
        </div>
        <!-- End of Main Content -->
        <!-- Footer -->
        <div class="noti-create-wrapper">
                  <div class="modal fade bd-example-modal-lg" id="notifcation-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog modal-lg" role="document">
                      <div class="modal-content" >
                        <div class="modal-header">
                          <h5 class="modal-title" id="exampleModalLabel">
                           Gửi thông báo cho người dùng
                          </h5>
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                          </button>
                        </div>
                        <div class="modal-body">
                          <div>
                            <div class="form-group">
                              <label for="userID" class="col-form-label">
                                Tiêu đề thông báo</label>
                              <input type="text" name="subject" class="form-control" id="notiTitle" lang="vi">
                            </div>
                            <div class="form-group">
                              <label for="noti-content" class="col-form-label">
                                Nội dung thông báo:</label>
                              <textarea class="form-control" rows="5" id="noti-content" name="body" lang="vi"></textarea>
                            </div>
                          </div>
                        </div>
                        <div class="modal-footer">
                          <button type="button"  class="btn btn-secondary" data-dismiss="modal">
                            Hủy
                          </button>
                          <button name="guiMail" type="submit" id="noti-submit"  onclick="send()" class="btn btn-primary">
                            Gửi thông báo
                          </button>
                        </div>
                      </div>
                 </div>
                 </div>
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
    <!-- End of Page Wrapper -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<c:url value='/resources/js/toast.js'/>"></script>
    <script src="<c:url value='/resources/js/admin/sendnoti.js'/>"></script>
  </body>
</html>
