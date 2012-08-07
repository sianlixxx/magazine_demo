<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>测试页面</title>
		<link rel="stylesheet" type="text/css" href="../../css/bootstrap/bootstrap.css" />
		<style type="text/css">
		    .mag-label-top {
		        margin: 60px auto 1px auto;
		        width: 800px;
		        height: 200px;
		    	border: solid 1px black;
		    }
		    
		    .mag-label-top-left {
		        float: left;
		    	margin: 10px;
		        width: 360px;
		        height: 180px;
		    	border: solid 1px black;
		    }
		    
		    div.mag-label-top-left .addlabel {
		        margin-left: 40px;
		    	margin-top: 60px;
			    background-color: #F5F5F5;
			    border: 1px solid rgba(0, 0, 0, 0.05);
			    border-radius: 4px 4px 4px 4px;
			    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05) inset;
			    margin-bottom: 20px;
			    min-height: 20px;
			    padding: 19px 19px 10px 19px;
		    }
		     
		    .addlabel input[type=text] {
		    	margin-bottom: 0px;
		    }
		    
		    .mag-label-top-left button {
		        height: 30px;
		        margin-bottom: 5px;
		    }
		    
		    .mag-label-top-right {
		        float: left;
		    	margin: 10px;
		        width: 360px;
		        height: 180px;
		    	border: solid 1px black;
		    }
		    
		    .mag-label-top-right .mag-label-change {
		        margin: 10px 2px 5px 20px;
		        padding: 5px;
		    }
		    
		     .mag-label-change span {
		     	float: right;
		     	margin-rigth: 20px;
		     }
		     
		     .mag-label-rand {
		     	margin: 0px 2px 5px 20px;
		        padding: 5px;
		        border: solid 1px black;
		     }
		     
		     .mag-label-rand p a {
		     	margin-bottom: 2px;
		     }
		    
		    .mag-label-down {
		        margin: 0px auto;
		        width: 800px;
		        height: 200px;
		    	border: solid 1px red;
		    }
		    
		    .mag-label-down div:nth-child(1) {
		    	margin: 10px 2px 5px 20px;
		        padding: 5px;
		    }
		    
		    .mag-label-added {
		    	margin: 0px 2px 5px 20px;
		        padding: 5px;
		    }
		    .mag-label-added p a{
		    	color: #0088CC;
		    	font-weight: bold;
		    	margin: 0px 2px;
		    }
		</style>
	</head>
	<body>
	    <form>
	    <div class="mag-label-top">
	    	<div class="mag-label-top-left">
	    	    <div class="addlabel">
	    	    	<input type="text" class="input-medium control-group warning" placeholder="输入你的标签"/>
	    	    	<button class="btn btn-success">添加标签</button>
	    	    	<div class="mag-show-msg">
	    	    	</div>
	    	    </div>
	    	</div>
	    	<div class="mag-label-top-right">
	           <div class="mag-label-change">你可能感兴趣的标签  <span><a>换一换</a></span></div>
	           <div class="mag-label-rand">
	               <p><a href="#" class="btn btn-small btn-info"><i class="icon-plus icon-white"></i>音乐</a>
	               <a href="#" class="btn btn-small btn-info"><i class="icon-plus icon-white"></i>双鱼座</a>
	               <a href="#" class="btn btn-small btn-info"><i class="icon-plus icon-white"></i>好性格</a>
	               <a href="#" class="btn btn-small btn-info"><i class="icon-plus icon-white"></i>文学</a>
	               <a href="#" class="btn btn-small btn-info"><i class="icon-plus icon-white"></i>羽毛球</a>
	               <a href="#" class="btn btn-small btn-info"><i class="icon-plus icon-white"></i>手机</a>
	               <a href="#" class="btn btn-small btn-info"><i class="icon-plus icon-white"></i>八卦</a>
	               <a href="#" class="btn btn-small btn-info"><i class="icon-plus icon-white"></i>浪漫</a>
	               <a href="#" class="btn btn-small btn-info"><i class="icon-plus icon-white"></i>欧洲杯</a>
	           </div>
	    	</div>
	    </div>
	    <div class="mag-label-down">
	    	<div><span class="badge">我已经添加的标签 </span></div>
            <div class="mag-label-added">
               <p>
            </div>
	    </div>
	    </form>
		
	    <script type="text/javascript" src="../../script/jquery-1.7.2.min.js"></script>
	    <script type="text/javascript" src="../../script/jquery-ui-1.8.20.custom.min.js"></script>
	    <script type="text/javascript" src="../../script/bootstrap/bootstrap-alert.js"></script>
	    <script type="text/javascript">
	    	$(document).ready(function(){
	    		//已选择的标签添加/删除图标
	    		$(".mag-label-added p a").live("mouseover",function() {
	    			$("i", this).addClass("icon-remove");
		        }).live("mouseout",function(){
		        	$("i", this).removeClass("icon-remove");
		        });
		        //删除已选择的标签
		        $("div.mag-label-added p a i.icon-remove").live("click",function(){
		        	//console.log(2)
					$(this).parent().remove();
		        });
		        //添加标签
		        $(".addlabel button").click(function(){
		        	//TODO 检查$(".addlabel input").val()合法性
		        	if($.trim($(".addlabel input").val()).length <=0) {
		        		showMsg({
		        				type: 'warning',
		        				showMsgElement:'.mag-show-msg',
		        				msg:"标签名称为空"
		        				});
		        		return;
		        	}
		        	if(!addLabelCheck($(".addlabel input").val())){return;}
		        	$(".mag-label-added p").append('<a href="#" class="btn btn-small">' + $.trim($(".addlabel input").val()) + '<i></i></a>');
		        });
		        $(".mag-label-rand p a").click(function(){
		        	if(!addLabelCheck( $(this).text())){return;}
		        	$(".mag-label-added p").append('<a href="#" class="btn btn-small">' + $(this).text() + '<i></i></a>');
		        	$(this).addClass("disabled");
		        });
		        
		        function addLabelCheck(labelText) {
		        	if($(".mag-label-added p a").size() >= 10) {
		        		showMsg('.mag-show-msg', "标签个数不能超过10");
		        		return false;
		        	}
		        	
		        	var hasLabelRepeat = false;
		        	$(".mag-label-added p a").each(function() {
		        		if($.trim($(this).text()) == $.trim(labelText)){
		        			hasLabelRepeat = true;
		        			var thisLabel = $(this);
		        			thisLabel.css("border-color", '#c09853');
		        			setTimeout(function(){thisLabel.css("border-color", '#e6e6e6 #e6e6e6 #bfbfbf');},2000);
		        			return;
		        		}
		        	});
		        	
		        	if(hasLabelRepeat){
		        		return false;
		        	}
		        	
		        	return true;
		        }
		        
		        /**
		         * 拼接错误消息
		         */
		        function showMsg(showMsgElement, msg) {
		        	var msgObj = arguments[0];
		        	var msgHtml = 
				        '<div class="alert">' +
						'  	<a class="close" data-dismiss="alert" href="#">×</a>' +
						'   <strong>错误!</strong>' + msgObj.msg +
					    '</div>';
					$(msgObj.showMsgElement).html(msgHtml);
					
					setTimeout(function(){$('.alert').alert();},2000);
		        }
		        
		        //换一换
		        $('.mag-label-change').click(function() {
		        	
		        });
	    		
		        
	    	});
	    </script>
	</body>
</html>