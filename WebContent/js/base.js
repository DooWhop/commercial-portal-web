function setRightW() {
	$('.mh1').css('min-height', $(window).height()  - 201);
	$('.mh2').css('min-height', $(window).height()  - 171);
}

function showModalDialog(title,text,fn) {
	$('.modal-backdrop').remove();
	$('#myalert').remove();
	$('body').append('<div class="modal fade" id="myalert" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">'+
				'<div class="modal-dialog">'+
					'<div class="modal-content">'+
						'<div class="modal-header">'+
							'<h4 class="modal-title" id="myModalLabel">'+title+'</h4>'+
						'</div>'+
						'<div class="modal-body">'+
						text+
						'</div>'+
						'<div class="modal-footer">'+
							'<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>');
			$('#myalert .btn-primary').click(function(){
                fn();  
			})
			$('#myalert').modal('show');
}

function showLoading(){
	$('body').append('<div class="loading-wrap"><div class="loading-img"></div></div>');
}
function hideLoading(){
	$('.loading-wrap').remove();
}
function showErrorTips(txt) {
				$('.errorTips').html(txt);
				$('.errorTips').show();
			}
$(function() {
		setRightW();
		$('.collapse').on('hide.bs.collapse', function() {
			$(this).siblings('div').find('span.iconfont').removeClass('icon-shouqi')
			$(this).siblings('div').find('span.iconfont').addClass('icon-zhankai')
		})
		$('.collapse').on('show.bs.collapse', function() {
			$(this).siblings('div').find('span.iconfont').addClass('icon-shouqi')
		})
		$('.logOut').on('click', function() {
			window.location = '登陆.html'
		})
		$('.zhanghu').on('click', function() {
			window.location = '个人资料.html'
		})
		$(window).resize(function() {
			setRightW();
		});
		//判断暗提示插件存在的就初始化
		if( jQuery.isFunction(jQuery.fn.placeholder)){    
			$('input').placeholder({isUseSpan:true});
		}
		//选择日期后隐藏input框模拟的暗提示
		$('.form_date').on('changeDate', function(e) {
			$(this).find('.wrap-placeholder').hide();
		})
		$('.noticeLine .noticeClose').on('click', function() {
			$(this).parent().slideUp(100);
		})
	})
