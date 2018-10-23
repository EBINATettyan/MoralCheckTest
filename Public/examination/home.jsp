<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!--<%@ page language="java" contentType="text/html; charset=UTF-8"%>-->

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" name="viewport"
	content="width=device-width, initial-scale=1">
<title>ホーム画面</title>
<link href="../../css/vender/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link href="../../css/original.css" rel="stylesheet" />
<style>
body {
	padding-top: 50px;
	padding-bottom: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="col-sm-12 col-md-12 col-lg-10 col-lg-offset-1">
			<nav class="navbar navbar-default navbar-fixed-top"
				id="navbar_overall_home">
				<div class="navbar-header">
					<a class="navbar-brand" id="navbar_text_left_side"><img
						src="../../img/titleMiddle.png"></a>
				</div>
			</nav>
			<div class="row">
				<br> <br> <img src="../../img/explain.png"
					class="img-responsive"> <br>
				<div class="col-sm-6">
					<h3 class="heading text_centering">情報モラルレベルチェックテストとは</h3>
					<ul>
						<li><h5>これは情報モラルがどの程度身についているかチェックできるテストです。</h5></li>
						<li><h5>あなたの情報モラルレベルがわかります。</h5></li>
					</ul>
				</div>
				<div class="col-sm-6">
					<h3 class="heading text_centering">注意事項について</h3>
					<ul>
						<li><h5>所要時間は約〇〇分です。</h5></li>
						<li><h5>「スタート」ボタンをおすとテストを開始します。</h5></li>
						<li><h5>問題画面で「答える」ボタンをおすと次の問題に進みます。</h5></li>
						<li><h5>前の問題に戻ることはできません。</h5></li>
						<li><h5>テスト終了後に情報モラルレベルと各問題の正解・不正解が表示されます。</h5></li>
						<li><h5>情報モラルレベルは小学生、中学生、高校生の３段階です。</h5></li>
					</ul>
				</div>
			</div>
			<form action='./schoolCheck.jsp' method='post'>
				<center>
					<input type="submit" class="btn btn-primary btn-lg" value="まずは学年を入力しよう">
					<center>
			</form>
		</div>
	</div>
	</div>
</body>
</html>