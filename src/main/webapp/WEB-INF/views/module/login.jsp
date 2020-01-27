<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div class="loginBox">
						<%
						String userID = (String) session.getAttribute("userID");
							if (userID == null) {
						%>
						<form action="/ex/Main/logIn" method="POST" class="form">
							<div class="login_left">
								<div class="col-md-12">
									<label for="inputID"></label> <input name="id" type="text"
										id="inputID"
										class="form-control form-control-sm bg-secondary text-white-50">
								</div>

								<div class="col-md-12">
									<label for="inputPassowrd"></label> <input name="pw"
										type="password" id="inputPassword"
										class="form-control form-control-sm bg-secondary text-white-50">
								</div>
							</div>
							<div class="login_right">
								<div class="form-group">
									<input type="submit" class="btn btn-default bg-dark text-white"
										value="전송">
								</div>
							</div>
						</form>
						<div>
						<input type="button" class="btn btn-default bg-dark text-white" value="회원가입" onClick="signUpPage()"></input>
						</div>
						<%
							} else {
						%>
						<div
							style="text-align: center; display: inline-block; width: 80%;">
							<div>
								<div class="border">
									<img src="#" style="min-width: 90px; min-height: 90px;">
									<%=userID%>님 로그인 됨
									<button type="submit" class="btn btn-default"
										onClick="logout()">로그아웃</button>
									<button type="submit" class="btn btn-default"
										onClick="mypageClick()">마이페이지</button>
								</div>
								<div class="row m-0">
									<div class="col-3 border p-2 bg-secondary"
										onClick="getAlarmList()">
										알람
										<div class="alarm"
											style="display: inline-block; position: relative; width: 10px; height: 10px;"></div>
									</div>
									<div class="col-3 border p-2 bg-secondary">쪽지</div>
									<div class="col-3 border p-2 bg-secondary"
										onClick="getMeetingList()">모임</div>
									<div class="col-3 border p-2 bg-secondary">삐롱</div>
								</div>
							</div>
						</div>
						<%
							}
						%>
					</div>