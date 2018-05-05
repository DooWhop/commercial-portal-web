var sends = {
	
	InterValObj: null, //timer变量，控制时间
	count: 5, //间隔函数，1秒执行
	curCount: null, //当前剩余秒数
	checked:false,

	sendMessage: function(requestUrl,phoneNo) {
		if (sends.checked) {
			return false;
		}
		sends.checked = true;
		sends.curCount = sends.count;
		$(".getSms").addClass("disabled");
		$(".getSms").html(sends.curCount + "秒");
		sends.InterValObj = window.setInterval(sends.SetRemainTime, 1000); //启动计时器，1秒执行一次
		　　 //向后台发送处理数据
		$.ajax({　　
			type: "GET", //用POST方式传输
			　　dataType: "JSON", //数据格式:JSON
			　　url: requestUrl, //目标地址
			　　data: {
						phoneNo:phoneNo				
				},
			　　error: function(XMLHttpRequest, textStatus, errorThrown) {},
			　　success: function(msg) {
				//alert(msg.code)
			}
		});
	},

	//timer处理函数
	SetRemainTime: function() {
		if(sends.curCount == 0) {
			window.clearInterval(sends.InterValObj); //停止计时器
			$(".getSms").removeClass(("disabled")); //启用按钮
			sends.checked = false;
			$(".getSms").html("立即获取");
		} else {
			sends.curCount--;
			$(".getSms").html(sends.curCount + "秒");
		}
	}
}
