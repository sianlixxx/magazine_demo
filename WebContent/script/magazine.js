//������
var Util = function () {
	
}

Util.prototype = {
	getLength: function(msg) {//�����ַ�ĳ��ȣ�һ������ռ2���ַ��
		var len = 0;
		for(var i = 0; i < msg.length; i++) {
			if(msg[i].match(/[^\x00-\xff]/ig) != null) {
				len += 2;
			} else {
				len += 1;
			}
		}
		return len;
	}
}

var MagUtil = new Util();

/**
 * 拼接错误消息
 */
function showMsg(showMsgElement, operate) {
    var msgObj = arguments[0];
    //清空消息
    if(operate == 'clear') {
        $(msgObj.showMsgElement).html("");
        return;
    }
    
    var msgType = "";
    switch(msgObj.type) {
        case "success" : msgType = "alert-success";break;
        case "info" : msgType = "alert-info";break;
        case "warn" : msgType = "";break;
        case "error" : msgType = "alert-error";break;
    }
    var msgHtml = 
        '<div class="alert ' + msgType + '">' +
        '   <a class="close" data-dismiss="alert" href="#">×</a>' + msgObj.msg +
        '</div>';
    $(msgObj.showMsgElement).html(msgHtml);
    
    setTimeout(function(){$('.alert').alert();},2000);
}

/*
 * 实现outerHTML功能
 */
(function ($) {
    'use strict';
    var ns = 'outerHTML';
    if (!$.fn[ns]) {
        $.fn[ns] = function outerHTML(replacement) {
            var $this = $(this),
                content;
            // Replace is already baked into jQuery
            // This is here for consistency with .html()
            if (replacement) {
                return $this.replaceWith(replacement);
            // Fall back to native if it's supported
            } else if ($this[0].hasOwnProperty && $this[0].hasOwnProperty('outerHTML')) {
                return $this[0].outerHTML;
            // Fake it 'till you make it!
            } else {
                // Don't use clone because of textarea bug?
                content = $this.wrap('<div>').parent().html();
                $this.unwrap();
                return content;
            }
        };
    }
}(jQuery));