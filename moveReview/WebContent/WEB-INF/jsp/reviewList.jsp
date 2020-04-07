<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ja">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="css/reviewList.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>レビュー一覧</title>
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

            <h3>レビュー一覧</h3>
              <div class="row border-bottom">
                  <div class="col-2">
                      <p>作品名：${move.moveName}</p>
                      <p>公開日：${move.releaseDate}</p>
                  </div>
                  <div class="col-10">
                  </div>
              </div>

              <div class="row border-bottom">
                  <div class="col-5">
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
                    <h6>評価</h6>
                      <form>
                          <div class="star-rating">
                            <div class="star-rating-front" style="width: 10%">★★★★★</div>
                            <div class="star-rating-back">★★★★★</div>
                        </div>
                      </form>
                  </div>
                  <div class="col-4">
                  </div>
                  <div class="col-3">
                  </div>
              </div>

			<c:forEach var="review" items="${reviewList}">

              <div class="review">
              <div class="row border-bottom">
                  <div class="col-2 imgPro">
                      <img src="image/img13.jpg">
                  </div>
                  <div class="col-2">
                      <p>${review.name}</p>
                  </div>
                  <div class="col-4">
                      <h6>評価</h6>

                          <div class="star-rating">
                            <div class="star-rating-front" style="width: ${review.reviewScorePercent}%">★★★★★</div>
                            <div class="star-rating-back">★★★★★</div>
                        </div>

                  </div>
                  <div class="col-4">
                  <c:if test="${userInfo.loginId == 'admin'}">
                      <a href="ReviewUpdateServlet?moveId=${move.id}&reviewId=${review.id}">更新する</a><br>
                      <a href="ReviewDeleteServlet?moveId=${move.id}&reviewId=${review.id}">削除する</a>
                  </c:if>
                  <c:if test="${userInfo.loginId != 'admin'}">
                  <c:if test="${userInfo.id == review.rUserId}">
                      <a href="ReviewUpdateServlet?moveId=${move.id}&reviewId=${review.id}">更新する</a><br>
                      <a href="ReviewDeleteServlet?moveId=${move.id}&reviewId=${review.id}">削除する</a>
                  </c:if>
                  </c:if>

                  </div>

              </div>

              <div class="row border-bottom">
                  <p>レビュータイトル：${review.reviewName}</p>
              </div>

              <div class="row">
                 <p>投稿日時：${review.reviewDate}</p>
              </div>

              <div class="row border-bottom">
                  <p>更新日時：${review.reviewUpdateDate}</p>
              </div>

              <div class="row border-bottom">
                  <div class="col-2"><h6>本文</h6></div>
                  <div class="col-10"><p>${review.reviewContent}</p></div>
              </div>
            </div>

            </c:forEach>

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