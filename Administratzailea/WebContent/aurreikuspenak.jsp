<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Adnimistratzailea</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">


<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
	integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
	crossorigin="" />
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
	integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
	crossorigin=""></script>


<!-- link href="//fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,900" rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700" rel="stylesheet"-->




</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="home">
				<div class="sidebar-brand-icon">
					<img src="img/BIDEZAIN.png" alt="Smiley face" height="42"
						width="42">
					<!--<i   class="fas fa-laugh-wink" src=""></i>-->
				</div>
				<div class="sidebar-brand-text mx-3">Bidezain</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Adnimistratzailea -->
			<li class="nav-item active"><a class="nav-link" href="home">
					<i class="fas fa-fw fa-briefcase"></i> <span>Administratzailea</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">Aukerak</div>

			<!-- Nav Item - Charts -->
			<li class="nav-item"><a class="nav-link" href="erabiltzaileak">
					<i class="fas fa-fw fa-users"></i> <span>Erabiltzaileak</span>
			</a></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="aurreikuspenak">
					<i class="fas fa-fw fa-car-crash"></i> <span>Aurreikuspenak</span>
			</a></li>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-cog"></i> <span>Eragiketak</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Administratzaile aukerak:</h6>
						<a class="collapse-item" href="register">Langilea gehitu</a> <a
							class="collapse-item" href="cards.html">Besteak</a>
					</div>
				</div></li>


			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>


					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">


						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <c:if
									test="${not empty sessionScope.user}">
									<span class="mr-2 d-none d-lg-inline text-gray-600 small">
										<c:out value="${sessionScope.user}" />
									</span>
								</c:if> <i class="fas fa-user rounded-circle"></i>
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Content Row -->
					<div class="row">

						<!-- Content Column -->
						<div class="col-lg-12 mb-4">

							<!-- Color System -->
							<div class="row">

								<c:if test="${not empty requestScope.success}">
									<div class="col-lg-4 mb-4">
										<div class="card bg-danger text-white shadow">
											<div class="card-body">

												<div class="col-lg-4 mb-4">
													<div class="card bg-success text-white shadow">
														<div class="card-body">
															<c:out value="${requestScope.success}" />
														</div>
													</div>
												</div>

											</div>
										</div>
									</div>
								</c:if>

							</div>

						</div>


					</div>

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">Aurreikuspenak</h1>
					<p class="mb-4">
						<b>Adimen Artifizial</b> bitartez aurreikusitako intzidentziak.
					</p>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">DataTables
								Example</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="myDataTable"
									width="100%" cellspacing="0"></table>
							</div>
						</div>
					</div>


				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Bidezain 2019</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Irteteko sakatu "Logout" botoia.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>


	<!-- Page level plugins -->
	<script src="vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/datatables-demo.js"></script>
	<script>
		$(window)
				.on(
						'load',
						function() {

							var req = new XMLHttpRequest();
							req
									.open(
											'GET',
											'http://localhost:8080/ZerbitzariaBidezain/webresources/database.utils.aurreikuspena/aurreikuspenak',
											true);
							req.onreadystatechange = function(aEvt) {
								if (req.readyState == 4) {
									if (req.status == 200) {

										console.log(req.responseText);
										var dataSet = req.responseText;
										table = $('#myDataTable').DataTable({

											columns : [ {
												title : "Errepidea"
											}, {
												title : "Probintzia"
											}, {
												title : "Herria"
											}, {
												title : "Asteko eguna"
											}, {
												title : "Data"
											}, {
												title : "Hasiera Kilometroa"
											}, {
												title : "Amaiera Kilometroa"
											} ],
											data : JSON.parse(dataSet)
										});
									}

									else
										console.log("Error loading page\n");
								}
							};
							req.send(null);

						});
	</script>
</body>

</html>
