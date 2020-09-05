<template>


<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">

<!-- Title Page-->
<title>다다익선 - 신규 회원가입</title>



<!-- Font special for pages-->


<!-- Vendor CSS-->
<link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
<link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

<!-- Main CSS-->
<link href="css/main.css" rel="stylesheet" media="all">

	<div class="page-wrapper bg-red p-t-180 p-b-100 font-robo">
		<div class="wrapper wrapper--w960">
			<div class="card card-2">
				<div class="card-heading"></div>
				<div class="card-body">
					<h2 class="title">서비스 신규 회원가입</h2>
					<form action="/members/new" method="POST">
						<div class="input-group">
							<input class="input--style-2" type="text" placeholder="아이디" name="mem_id">
						</div>
						<div class="input-group">
							<input class="input--style-2" type="text" placeholder="이름" name="mem_name">
						</div>
						<div class="input-group">
							<input class="input--style-2" type="password" placeholder="비밀번호" name="mem_pw">
						</div>
						<!-- <div class="input-group">
							<input class="input--style-2" type="password" placeholder="비밀번호 확인" name="password_check">
						</div>
						<div class="input-group">
							<input class="input--style-2" type="text" placeholder="휴대폰 번호 입력" name="phone_number">
						</div>
						<div class="row row-space ">
							<div class="col-2 ">
								<div class="input-group ">
									<div class="rs-select2 js-select-simple select--no-search ">
										<select name="gender ">
											<option disabled="disabled" selected="selected">성별</option>
											<option>남자</option>
											<option>여자</option>
										</select>
										<div class="select-dropdown"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="input-group">
							<div class="rs-select2 js-select-simple select--no-search">
								<select name="mem_class">
									<option disabled="disabled" selected="selected ">수강중인 수업 선택</option>
									<option>인공지능 P반</option>
									<option>인공지능 A반</option>
									<option>데이터과학 영등포</option>
									<option>데이터과학 서초</option>
								</select>
								<div class="select-dropdown "></div>
							</div>
						</div>
						<div class="row row-space">
							<div class="col-2 ">
								<div class="input-group ">
									<input class="input--style-2 " type="file" placeholder="Registration Code " name="res_code ">
								</div>
							</div>
						</div> -->
						<div class="p-t-30 ">
							<button class="btn btn--radius btn--green" type="submit" id="btn_memberJoin">회원가입</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Jquery JS-->
	<script src="vendor/jquery/jquery.min.js "></script>

    <!-- Vendor JS-->
    <script src="vendor/select2/select2.min.js "></script>
    <script src="vendor/datepicker/moment.min.js "></script>
    <script src="vendor/datepicker/daterangepicker.js "></script>

    <!-- Main JS-->
    <script src="js/global.js "></script>


</template>

<script>
    export default {
        name: "Member"
    }
</script>

<style scoped>
	@import url("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css");
	@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
</style>
