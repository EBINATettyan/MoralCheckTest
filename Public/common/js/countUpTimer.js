$(function () {

	if(typeof(window.localStorage.getItem('secLocalStrage')) != "undefined" || typeof(window.localStorage.getItem('minLocalStrage')) != "undefined" || typeof(window.localStorage.getItem('hourLocalStrage')) != "undefined"){
		//String型からNumber型への変換
		var sec = Number(window.localStorage.getItem('secLocalStrage'));
		var min = Number(window.localStorage.getItem('minLocalStrage'));
		var hour = Number(window.localStorage.getItem('hourLocalStrage'));
	}else{
		var sec = 0;
		var min = 0;
		var hour = 0;
	}

	setInterval(function() {
    // カウントアップ
    sec += 1;

    if (sec > 59) {
      sec = 0;
      min += 1;
    }

    if (min > 59) {
      min = 0;
      hour += 1;
    }

    // 0埋め
    secNumber = ('0' + sec).slice(-2);
    minNumber = ('0' + min).slice(-2);
    hourNumber = ('0' + hour).slice(-2);

    $('#timer').html(hourNumber + ':' +  minNumber + ':' + secNumber);
  },1000);

    $(function () {
        $('#answer_button').on('click', function () {//解答ボタンを押した際に，受験時間をlocalStrageに保存
        window.localStorage.setItem('secLocalStrage',sec);
        window.localStorage.setItem('minLocalStrage',min);
        window.localStorage.setItem('hourLocalStrage',hour);
    })
    });

});