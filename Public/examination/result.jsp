<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%
	double ability = (double) request.getAttribute("postAbility");
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
				<a class="navbar-brand" id="navbar_text_left_side"> <img src="../../img/titleSmall.png">
				</a>
			</div>
		</nav>
		<div class="row">
			<!-- main -->
			<div class="col-sm-12 col-md-12 col-lg-10 col-lg-offset-1">
				<!-- apply custom style -->
				<div class="page-header" style="margin-top: -20px; padding-bottom: 0px;">
					<h1>
						結果 <small> 受検時間 <script>
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
							document.write(hourNumber + ':' + minNumber + ':'
									+ secNumber);

							//localStrageに保存されている値をクリアにする．
							window.localStorage.clear();
						</script>
						</small>
					</h1>
				</div>
				<h2>
					あなたの情報モラルレベルは<span style="font-size: xx-large;">&nbsp;&nbsp;<span style="color: #FF1493;"> <%
 	if (ability < 2.0) {
 		out.println("小学生レベル");
 	} else if (ability >= 2.0 && ability < 3.3) {
 		out.println("中学生レベル");
 	} else {
 		out.println("高校生レベル");
 	}
 %></span></span>&nbsp;&nbsp;です。
				</h2>
				<div id="chart" style="min-width: 310px; max-width: 100%; height: 300px; margin: 0 auto"></div>
				<div class="well well-sm">
					グラフの<span style="background-color: #FAFA96">黄色は小学生</span>、<span style="background-color: #96FAAA">黄緑色は中学生</span>、<span
						style="background-color: #96FAFA">水色は高校生</span>、のレベルにそれぞれ対応しています。
				</div>
				<br>
			</div>
		</div>
	</div>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="../common/bootstrap/js/bootstrap.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript">
	Highcharts.chart('chart', {
		chart : {
			borderColor : '#000000',//線の色
			borderWidth : 0,//外枠の太さ
			type : 'bar',//グラフのタイプ
			//plotBackgroundColor: '#ff4325',//バックグランドカラー
			plotBorderWidth : 0,
			plotBorderColor : '#000000',
		//backgroundColor:'#435210'//全ての要素の背景色
		},
		colors : [ '#33BBFF' ],
		title : {
			text : '',//情報モラル力
			//floating: true,
			floating : true,
			style : {
				color : '#12f342',
				fontSize : '36px'
			}

		},
		subtitle : {
			text : ''
		},
		xAxis : {
			categories : [ '' ],//グラフの一つの値の名前
			lineWidth : 0,//開始線の太さ
			tickColor : '#FF0000',//x軸の補助線の色
			tickWidth : 0,//y軸の補助線の太さ
			title : {
				text : null
			},

		},
		yAxis : {
			min : 0,//最大値
			max : 5.5,//最小値
			gridLineColor : '#000000',//線の色
			gridLineWidth : 1,//線の太さ
			//tickInterval: 0.5,
			title : {
				text : null,
			},
			labels : {
				enabled : false
			//true//x軸上のメモリが表示されるか否か。
			},
			plotBands : [ {//グラフの背景色を変化することができる
				color : '#FAFA96',
				from : 0,
				to : 2.0,
			}, {//
				color : '#96FAAA',
				from : 2.0,
				to : 3.3,
			}, {
				color : '#96FAFA',
				from : 3.3,
				to : 5.5
			} ],
		//categories: [1,2,3,4,5]//x軸上の値を確認する

		},
		tooltip : {//グラフを表示したい際に，何かが出てくる．
			backgroundColor : '#000000',
			enabled : false
		//利用できるか否か。
		//valueSuffix: 'millions'
		},
		plotOptions : {
			bar : {
				dataLabels : {
					enabled : false
				//「true」の場合には，値がグラフ上に出力される．
				}
			}
		},
		legend : {//凡例について
			enabled : false,//表示するか否か。
		},
		credits : {
			enabled : false
		//highchatのお金を支払う画面に移動する．
		},
		exporting : {//画像保存ボタンの非表示
			enabled : false
		},
		series : [ {
			//name: null,
			data : [
<%out.println(ability);%>
	]
		//入力した値を出力する．//
		} ]

	});
</script>

<script type="text/javascript">
	$(window).load(function() {//ページが読みこまれた後に，実行する処理
		//0.01秒後に処理を実行
		setInterval(function() {
			//URLにハッシュを付ける
			location.hash = "hash";
		}, 10);
	});
</script>
</html>