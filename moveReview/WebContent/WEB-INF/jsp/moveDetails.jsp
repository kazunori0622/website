<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ja">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="css/moveDetails.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>作品詳細</title>
  </head>
  <body>

      <nav class="navbar navbar-expand navbar-dark bg-dark fixed-top">
          <div class="nav-wrapper container">
          <a href="ToppageServlet" class="navbar-brand">サイトタイトル</a>
          <ul class="navbar-nav">
              <li class="nav-item"><a href="LogoutServlet" class="nav-link">ログアウト</a></li>
          </ul>
          <form class="form-inline ml-auto" action="SearchServlet" method="post">
          <div class="search">
              <label class="sr-only" for="Kw">検索キーワード</label>
              <input type="search" name="search" class="form-control form-control-sm mr-2" placeholder="検索" id="Kw">
              <button type="submit" class="btn btn-warning btn-sm">検索</button>
           </div>
          </form>
              <div class="text-white"><h5>${userInfo.name}さん</h5></div>
        </div>
      </nav>

      <div class="container">
          <div class="main">

            <h3>作品詳細</h3>
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
                      <h6>平均評価</h6>

                          <div class="star-rating">
                            <div class="star-rating-front" style="width: ${move.reviewScoreAvg}%">★★★★★</div>
                            <div class="star-rating-back">★★★★★</div>
                        </div>

                  </div>
                  <div class="col-4">
                      <a href="PostReviewServlet?id=${move.id}">レビューを書く</a><br>
                      <a href="ReviewListServlet?id=${move.id}">レビューを見る</a><br>

                      <%
                      String fid = (String)request.getAttribute("fid");

                      %>

                      <c:if test="${favorite.id != null}">
                      <button type="button" class="btn btn-warning btn-sm" onclick="location.href='FavoriteServlet?fUserId=${userInfo.id}&fid=${favorite.id}'">気になる作品から外す</button>
                     </c:if>

                     <c:if test="${favorite.id == null }">
                     <form action="FavoriteServlet?fUserId=${userInfo.id}&fMoveId=${move.id}" method="post">
                      <button type="submit" class="btn btn-warning btn-sm">気になる作品</button>
                     </form>
                     </c:if>






              	</div>

                   </div>

              <div class="row border-bottom">
                  <div class="col-10">
                      <h4>解説</h4>
                      <p>${move.moveText}</p>
                      <p>製作年月日：${move.productionDate}年/上映時間：${move.runningTime}分/製作国：${move.country}/配給：${move.company}</p>
                  </div>
                  <div class="col-2">
                  </div>
              </div>

              <div class="row">
                  <div class="col-6">
                      <h5>スタッフ・キャスト</h5>
                  </div>
                  <div class="col-6">
                  </div>
              </div>

              <div class="row border-bottom">
                  <div class="col-6">
                      <h5>監督</h5>
                      <p>${move.director}</p>
                  </div>
                  <div class="col-6">
                      <h5>キャスト</h5>
                      <p>${move.cast}</p>
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