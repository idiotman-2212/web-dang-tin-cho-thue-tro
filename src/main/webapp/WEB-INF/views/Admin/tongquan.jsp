<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<title>Thống kê</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/sb-admin-2.css'/>"  />
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
            class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow" style="margin-bottom:0;"
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
                    href=<c:url value='/logout' />"
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
                    <!-- Content Row -->
                    <blockquote class="blockquote text-center" style="background-color: white;padding: 20px 0;border-bottom:5px solid #26bac9; margin-bottom:40px">
                      <p class="h1">THỐNG KÊ & BIỂU ĐỒ</p>
                    </blockquote>

                    <div class="row" style="display:flex;justify-content:center;">
                        <!-- số lượng user -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Số lượng tài khoản
                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800" id ="Songuoidung"></div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-user fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Pending Requests Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-warning shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                Yêu cầu duyệt bài </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800" id= "Baichuaduyet"></div>
                                        </div>
                                        <div class="col-auto">
                                           <i class="fas fa-tasks fa-2x text-gray-300"></i>
                                           
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Content Row -->

                    <%--  <div class="row">

                        <!-- Area Chart -->
                        <div class="col-xl-8 col-lg-7">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">Earnings Overview</h6>
                                    <div class="dropdown no-arrow">
                                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                            aria-labelledby="dropdownMenuLink">
                                            <div class="dropdown-header">Dropdown Header:</div>
                                            <a class="dropdown-item" href="#">Action</a>
                                            <a class="dropdown-item" href="#">Another action</a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="#">Something else here</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="chart-area">
                                        <canvas id="myAreaChart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> --%>

                              <div class="row">
                                <div class="col-xl-8 col-lg-7" style="margin:20px auto;">
                                  <!-- Bar Chart -->
                                  <div class="card shadow mb-4">
                                    <div class="card-body" style="min-height:600px">
                                      <input type="number" id="year" min="1900" max="2099" step="1" value="2022" oninput="reload()" />
                                      <div class="chart-bar">
                                        <canvas id="myBarChart" style="pointer-events: none"></canvas>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>

                                      <div class="row">
                                        <div class="col-xl-8 col-lg-8"  style="margin:20px auto;">
                                          <!-- Bar Chart -->
                                          <div class="card shadow mb-6">
                                            <div class="card-body" style="min-height:600px">
                                              <input type="number" id="year2" min="1900" max="2099" step="1" value="2022"
                                                oninput="gettheothang()" />
                                              <input type="number" id="month" min="1" max="12" step="1" value="5" oninput="gettheothang()" />
                                              <div class="chart-bar">
                                                <canvas id="CharMonth" style="pointer-events: none"></canvas>
                                              </div>
                                            </div>
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
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
    <script>
     !function CountTk() {
        let url = "http://localhost:3000/api/countND";
        try {
            fetch(url)
            .then(res=>res.json())
            .then(data=>{
               $("#Songuoidung").text( data[0].tk);
            })
            
            
        } catch (error) {
            console.log(error);
        }
    }();
    
     !function CountBVCD() {
        let url = "http://localhost:3000/api/countBVCD";
        try {
            fetch(url)
            .then(res=>res.json())
            .then(data=>{
            	 $("#Baichuaduyet").text( data[0].bv);
     
            })
        }catch (error) {
            console.log(error);
        }
            
    }()
  
    </script>
   
    <script src="<c:url value='/resources/js/admin/global.js'/>"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
        <script src="<c:url value= '/resources/js/admin/bieudonam.js'/>"></script>
        <script src="<c:url value= '/resources/js/admin/bieudothang.js'/>"></script>
    <!-- End of Page Wrapper -->
</body>
</html>