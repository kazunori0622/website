<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ja">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="css/postReview.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>レビュー投稿</title>
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
            <h3>レビュー投稿</h3>
              <div class="row border-bottom">
                  <div class="col-2">
                      <p>作品名：${move.moveName}</p>
                      <p>公開日：${move.releaseDate}</p>
                  </div>
                  <div class="col-10">
                  </div>
              </div>

 			<form action="PostReviewServlet?rUserId=${userInfo.id}&rMoveId=${move.id}" method="post">
              <div class="row border-bottom">
                  <div class="col-4">
                    <p>作品名のレビューを書く</p>
                    <div class="carousel slide" id="c1" data-ride="carousel">
                      <ol class="carousel-indicators">
                          <li data-target="#c1" data-slide-to="0" class="active"></li>
                          <li data-target="#c1" data-slide-to="1"></li>
                          <li data-target="#c1" data-slide-to="2"></li>
                      </ol>
                      <div class="carousel-inner">
                          <div class="carousel-item active"><img src="image/${move.fileName}" class="d-block w-100"></div>
                          <div class="carousel-item"><img src="image/${move.fileNameSub1}" class="d-block w-100"></div>
                          <div class="carousel-item"><img src="image/${move.fileNameSub2}" class="d-block w-100"></div>
                      </div>
                      <a href="#c1" class="carousel-control-prev" data-slide="prev">
                          <span class="carousel-control-prev-icon"></span>
                          <span class="sr-only">前の画像へ</span>
                      </a>
                      <a href="#c1" class="carousel-control-next" data-slide="next">
                          <span class="carousel-control-next-icon"></span>
                          <span class="sr-only">次の画像へ</span>
                      </a>
                    </div>
                    <h6>採点する</h6>

                          <div class="evaluation">
                              <input id="star1" type="radio" name="star" value="5"/>
                              <label for="star1"><span class="text">5</span>★</label>
                              <input id="star2" type="radio" name="star" value="4" />
                              <label for="star2"><span class="text">4</span>★</label>
                              <input id="star3" type="radio" name="star" value="3" />
                              <label for="star3"><span class="text">3</span>★</label>
                              <input id="star4" type="radio" name="star" value="2" />
                              <label for="star4"><span class="text">2</span>★</label>
                              <input id="star5" type="radio" name="star" value="1" />
                              <label for="star5"><span class="text">1</span>★</label>
                          </div>
                  </div>
                  <div class="col-4">
                  </div>
                  <div class="col-4">
                  </div>
              </div>


              <div class="form-group row">
                  <label class="col-2 col-form-label" for="reviewName">レビュータイトル</label>
                  <div class="col-7">
                    <input type="text" name="reviewName" class="form-control" placeholder="レビュータイトル" id="reviewName">
                  </div>
              </div>

              <div class="form-group row">
                  <label class="col-2 col-form-label" for="reviewContent">本文</label>
                  <div class="col-7">
                      <textarea rows="10" name="reviewContent" class="form-control" placeholder="本文" id="reviewContent"></textarea>
                  </div>
              </div>

              <div class="row">
                    <div class="col-6 text-right">
                        <button type="button" class="btn btn-outline-dark" onclick="location.href='MoveDetailsServlet?id=${move.id}'">キャンセル</button>
                    </div>
                    <div class="col-6">
                        <button type="submit" class="btn btn-outline-dark">OK</button>
                    </div>
              </div>

			</form>

              <div class="row border-bottom">
                  <div class="col-6">
                  </div>
                  <div class="col-6">
                  </div>
              </div>

        </div>
      </div>


      <footer class="page-footer bg-dark">
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