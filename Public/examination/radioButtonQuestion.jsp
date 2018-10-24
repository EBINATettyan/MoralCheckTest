<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="beans.Question"%>
<%
	Question question = (Question) session.getAttribute("question");
	int countId = (Integer) request.getAttribute("countId");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>テスト画面</title>
<link href="../common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../common/css/original.css" rel="stylesheet" />
</head>

<body>
	<div class="container">
		<nav class="navbar navbar-default navbar-fixed-top" id="navbar_overall">
			<div class="navbar-header">
				<a class="navbar-brand" id="navbar_text_left_side">情報モラル適当型テスト</a>
			</div>
			<nav class="collapse navbar-collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="navbar-text"><span class="navbar_text_right_side"><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp; <script>
						//localStrageに保存された受検時間を取得
						var sec = Number(window.localStorage
								.getItem('secLocalStrage'));
						var min = Number(window.localStorage
								.getItem('minLocalStrage'));
						var hour = Number(window.localStorage
								.getItem('hourLocalStrage'));

						// 0埋め
						secNumber = ('0' + sec).slice(-2);
						minNumber = ('0' + min).slice(-2);
						hourNumber = ('0' + hour).slice(-2);

						//	受検時間の表示
						document.write('<span id=timer>' + hourNumber + ':'
								+ minNumber + ':' + secNumber + '</span>');
					</script> </span></li>
				</ul>
			</nav>
		</nav>
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-10 col-lg-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<font size="5"> <%
 	out.println(countId + "問目");
 %>
						</font>
					</div>
					<div class="panel-body">
						<div style="display: none" id="warnning">
							<span style='color: #ff0000'>＊答えを選択してください</span>
						</div>
						<div>
							<font size="3">&nbsp; <%
 	out.println(question.getContent() + "");
 %>
							</font>
						</div>
						<br>
						<form method="POST" action="SelectQuestionServlet">
							<div class="well well-sm">
								<label><input type='radio' name='answer' value='1' />&nbsp;&nbsp; <%
 	out.println(question.getChoice1());
 %></label>
							</div>
							<div class="well well-sm">
								<label><input type='radio' name='answer' value='2' />&nbsp;&nbsp; <%
 	out.println(question.getChoice2());
 %></label>
							</div>
							<div class="well well-sm">
								<label><input type='radio' name='answer' value='3' />&nbsp;&nbsp; <%
 	out.println(question.getChoice3());
 %></label>
							</div>
							<div class="well well-sm">
								<label><input type='radio' name='answer' value='4' />&nbsp;&nbsp; <%
 	out.println(question.getChoice4());
 %></label>
							</div>
							<input type='hidden' name='countId' value=<%out.println(countId);%>> <input type="submit" class="btn btn-success btn-lg"
								id="answer_button" value="答える">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="../../js/countUpTimer.js"></script>
<!--カウントアップタイマー-->
<script>
	$(window).load(function() {//ページが読みこまれた後に，実行する処理
		//0.01秒後に処理を実行
		setInterval(function() {
			//URLにハッシュを付ける
			location.hash = "hash";
		}, 10);
	});
	$(function() {
		$('#answer_button').on('click', function() {//解答ボタンを押した際に選択肢が選択されていなかった場合に警告を出す．
			var radioChecked = $('[name=answer]:checked').val();
			if (!radioChecked) {
				$('#warnning').css('display', '');
				return false;
			}
		});
		$('input[name=answer]').on('click', function() {//選択肢を選択した場合，警告を消す．
			$('#warnning').css('display', 'none');
		});
	});
</script>
</html>