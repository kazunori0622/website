<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ja">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="css/userInformUpdate.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>マイページ情報更新</title>
  </head>
  <body>

      <nav class="navbar navbar-expand navbar-dark bg-dark fixed-top">
          <div class="nav-wrapper container">
          <a href="ToppageServlet" class="navbar-brand">サイトタイトル</a>
          <ul class="navbar-nav">
              <li class="nav-item"><a href="LogoutServlet" class="nav-link">ログアウト</a></li>
          </ul>
        </div>
          <div class="text-white"><h5>${userInfo.name}さん</h5></div>
      </nav>


      <div class="container">
          <div class="main">
           <div class="loginErrMsg">
        	<c:if test="${errMsg != null }">
        		<div class="alert alert-danger" role="alert">${errMsg}</div>
        	</c:if>
        </div>
            <h3>マイページ情報更新</h3>

              <form action="UserInformUpdateServlet?id=${user.id}" method="post">
                  <div class="form-group row">
                      <div class="col-4 text-right">ログインID</div>
                      <div class="col-4"><p>${user.loginId}</p></div>
                  </div>
                  <div class="form-group row">
                      <label class="col-4 col-form-label text-right" for="password">パスワード</label>
                      <div class="col-4">
                          <input type="password" name="password" class="form-control" placeholder="パスワード" id="password">
                      </div>
                  </div>
                  <div class="form-group row">
                      <label class="col-4 col-form-label text-right" for="passwordCon">パスワード(確認)</label>
                      <div class="col-4">
                          <input type="password" name="passwordCon" class="form-control" placeholder="パスワード(確認)" id="passwordCon">
                      </div>
                  </div>
                  <div class="form-group row">
                      <label class="col-4 col-form-label text-right" for="userName">ユーザ名</label>
                      <div class="col-4">
                          <input type="text" name="name" class="form-control" placeholder="ユーザ名" id="userName" value="${user.name}">
                      </div>
                  </div>

                  <div class="row">
                      <div class="col-12 text-center">
                          <button type="submit" class="btn btn-outline-dark">更新</button>
                      </div>
                  </div>
              </form>

        </div>
      </div>


      <footer class="page-footer bg-dark fixed-bottom">
		<div class="container">
			Made by LIKEIT
		</div>
	</footer>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>