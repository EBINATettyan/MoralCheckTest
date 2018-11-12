<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>受験者情報入力画面</title>
<link href="../common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../common/css/original.css" rel="stylesheet" />
</head>

<body>
	<div class="container">
		<!-- ヘッダー部 -->
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<nav class="navbar navbar-default navbar-fixed-top" id="navbar_overall_home">
				<div class="navbar-header">
					<a class="navbar-brand" id="navbar_text_left_side"><font size="+2">情報モラル適応型テスト</font></a>
				</div>
			</nav>

			<!-- コンテンツ部 -->
			<form method="POST" action="IdentifyUserServlet">
				<div class="row" style="padding: 30px 0 0 0">
					<div style="display: none" id="warnning">
						<span style="color: #ff0000;">＊入力に不備があります</span>
					</div>
					<h4>Q1:あなたの性別を選択して下さい</h4>
					<hr style="margin: 13px 0;">
					<select name="sex" required>
						<option value="">性別を選択してください</option>
						<option value="0">女性</option>
						<option value="1">男性</option>
					</select>
				</div>

				<div class="row" style="padding: 30px 0 0 0">
					<h4>Q2:あなたの年齢を選択して下さい</h4>
					<hr style="margin: 13px 0;">
					<select name="age" required>
						<option value="">年齢を選択してください</option>
						<option value="0">20歳未満</option>
						<option value="1">20-24歳</option>
						<option value="2">25-29歳</option>
						<option value="3">30-34歳</option>
						<option value="4">35-39歳</option>
						<option value="5">40-44歳</option>
						<option value="6">45-49歳</option>
						<option value="7">50歳以上</option>
					</select>
				</div>

				<div class="row" style="padding: 30px 0 0 0">
					<h4>Q3:どのような目的で受講しますか</h4>
					<hr style="margin: 13px 0;">
					<select name="purpose" required>
						<option value="">適切なものを選択してください</option>
						<option value="0">学校の授業でテストを受ける</option>
						<option value="1">企業や学校の活動の一環として受ける</option>
						<option value="2">自分の情報モラル力を知るために受ける</option>
						<option value="3">その他</option>
					</select>
				</div>

				<div class="row" style="padding: 30px 0 0 0">
					<h4>Q4:あなたは普段どの機械を使用していますか。当てはまるものを全て選択して下さい。</h4>
					<hr style="margin: 13px 0;">
					<label><input type="checkbox" name="machine" value="0">スマートフォン</label><br> <label><input type="checkbox"
						name="machine" value="1">ガラパゴス携帯</label><br> <label><input type="checkbox" name="machine" value="2">ノートパソコン</label><br>
					<label><input type="checkbox" name="machine" value="3">デスクトップパソコン</label><br> <label><input type="checkbox"
						name="machine" value="4">iPad等のタブレット端末</label>
				</div>

				<div class="row" style="padding: 30px 0 0 0">
					<h4>Q5:あなたは普段どれくらいの時間インターネットを使用しますか</h4>
					<hr style="margin: 13px 0;">
					<select name="useInternetTime" required>
						<option value="">適切なものを選択してください</option>
						<option value="0">1時間未満</option>
						<option value="1">1時間-1時間30分</option>
						<option value="2">1時間30分-2時間</option>
						<option value="3">2時間-2時間30分</option>
						<option value="4">2時間30分-3時間</option>
						<option value="5">3時間-3時間30分</option>
						<option value="6">3時間30分-4時間</option>
						<option value="7">4時間以上</option>
					</select>
				</div>

				<div class="row" style="padding: 30px 0 0 0">
					<h4>Q6:あなたはインターネットの危険性について、これまで説明を受けたり学んだりしたことがありますか。当てはまるものを全て選択して下さい。</h4>
					<hr style="margin: 13px 0;">
					<label><input type="checkbox" name="judgeExplain" value="0">学校で教えてもらった</label><br> <label><input type="checkbox"
						name="judgeExplain" value="1">親から教えてもらった</label><br> <label><input type="checkbox" name="judgeExplain" value="2">兄弟・姉妹から教えてもらった</label><br>
					<label><input type="checkbox" name="judgeExplain" value="3">機器の購入時に販売員に説明してもらった</label><br> <label><input
						type="checkbox" name="judgeExplain" value="4">機器の購入時に資料をもらった</label><br> <label><input type="checkbox"
						name="judgeExplain" value="5">友だちから教えてもらった</label><br> <label><input type="checkbox" name="judgeExplain" value="6">テレビや本・パンフレットなどで知った</label><br>
					<label><input type="checkbox" name="judgeExplain" value="7">インターネットで知った</label><br> <label><input type="checkbox"
						name="judgeExplain" value="8">その他</label><br> <label><input type="checkbox" name="judgeExplain" value="9">特に教えてもらったり学んだりしたことはない</label>
				</div>

				<div class="row" style="padding: 30px 0 0 0">
					<h4>Q7:あなたは、インターネットを使っていて、このようなことがありますか。当てはまるものを全て選択して下さい。</h4>
					<hr style="margin: 13px 0;">
					<label><input type="checkbox" name="judgeExperience" value="0">悪口やいやがらせのメッセージやメールを送られたり、書き込みをされたことがある</label><br> <label><input
						type="checkbox" name="judgeExperience" value="1">悪口やいやがらせのメッセージやメールを送ったり、書き込みをしたことがある</label><br> <label><input
						type="checkbox" name="judgeExperience" value="2">親に話しにくいサイトを見たことがある</label><br> <label><input type="checkbox"
						name="judgeExperience" value="3">ゲームやアプリで、お金を使いすぎたことがある</label><br> <label><input type="checkbox"
						name="judgeExperience" value="4">自分が知らない人や、お店などからメッセージやメールが来たことがある</label><br> <label><input type="checkbox"
						name="judgeExperience" value="5">インターネットで知り合った人とメッセージやメールなどのやりとりをしたことがある</label><br> <label><input type="checkbox"
						name="judgeExperience" value="6">インターネットで知り合った人との人間関係で悩んだことがある</label><br> <label><input type="checkbox"
						name="judgeExperience" value="7">インターネットにのめりこんで勉強に集中できなかったり、睡眠不足になったりしたことがある</label><br> <label><input type="checkbox"
						name="judgeExperience" value="8">その他</label><br> <label><input type="checkbox" name="judgeExperience" value="9">あてはまるものはない</label>
				</div>
				<div class="row" style="padding: 35px 0 0 0">
					<div style="text-align: right">
						<button type="submit" class="btn btn-success btn-lg" id='answer_button'>問題に回答する画面に進む</button>
					</div>
					<br> <br>
				</div>
			</form>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<!--カウントアップタイマー-->
<script>
	$(function() {
		$('#answer_button').on('click', function() {//解答ボタンを押した際に選択肢が選択されていなかった場合に警告を出す．
			var checkboxChecked = $('[name=machine]:checked').val();
			if (!checkboxChecked) {
				$('#warnning').css('display', '');
				return false;
			}
		});
		$('input[name=machine]').on('click', function() {//選択肢を選択した場合，警告を消す．
			$('#warnning').css('display', 'none');
		});
	});
</script>
<script>
	$(function() {
		$('#answer_button').on('click', function() {//解答ボタンを押した際に選択肢が選択されていなかった場合に警告を出す．
			var checkboxChecked = $('[name=judgeExplain]:checked').val();
			if (!checkboxChecked) {
				$('#warnning').css('display', '');
				return false;
			}
		});
		$('input[name=judgeExplain]').on('click', function() {//選択肢を選択した場合，警告を消す．
			$('#warnning').css('display', 'none');
		});
	});
</script>
<script>
	$(function() {
		$('#answer_button').on('click', function() {//解答ボタンを押した際に選択肢が選択されていなかった場合に警告を出す．
			var checkboxChecked = $('[name=judgeExperience]:checked').val();
			if (!checkboxChecked) {
				$('#warnning').css('display', '');
				return false;
			}
		});
		$('input[name=judgeExperience]').on('click', function() {//選択肢を選択した場合，警告を消す．
			$('#warnning').css('display', 'none');
		});
	});
</script>
</html>