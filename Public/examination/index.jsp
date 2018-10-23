<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>ホーム画面</title>
<link href="/MoralCheckTest/Public/common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/MoralCheckTest/Public/common/css/original.css" rel="stylesheet" />
</head>

<body>
	<div class="container">
		<!-- ヘッダー部 -->
		<div class="col-lg-10 col-md-12 col-sm-12 col-xs-12" style="padding: 40px 0 0 0">
			<nav class="navbar navbar-default navbar-fixed-top" id="navbar_overall_home">
				<div class="navbar-header">
					<a class="navbar-brand" id="navbar_text_left_side">情報モラル適応型テスト</a>
				</div>
			</nav>

			<!-- イメージ図の表示 -->
			<div class="row">
				<img src="/MoralCheckTest/Public/common/img/explain.png" class="img-responsive">
			</div>

			<!-- コンテンツ部 -->
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<h3 class="heading text_centering">情報モラルレベルチェックテストとは</h3>
					<ul>
						<li><h5>これは情報モラルがどの程度身についているかチェックできるテストです。</h5></li>
						<li><h5>あなたの情報モラルレベルがわかります。</h5></li>
					</ul>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<h3 class="heading text_centering">注意事項について</h3>
					<ul>
						<li><h5>「問題に回答する画面に進む」ボタンをおすとテストを開始します。</h5></li>
						<li><h5>問題画面で「答える」ボタンをおすと次の問題に進みます。</h5></li>
						<li><h5>前の問題に戻ることはできません。</h5></li>
						<li><h5>情報モラルレベルは「レベル1~レベル3」の3段階です。</h5></li>
						<li><h5>推奨環境</h5></li>
					</ul>
				</div>
			</div>

			<div class="row" style="padding: 20px 0 0 0">
				<div style="text-align: right">
					<a class="btn btn-success btn-lg" href="/MoralCheckTest/Public/examination/IdentifyUserServlet" role="button">問題に回答する画面に進む</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>