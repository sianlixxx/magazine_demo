<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首次登陆</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="${magroot}/css/main.css" />
    <link rel="stylesheet" type="text/css" href="${magroot}/css/bootstrap/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${magroot}/css/bootstrap/bootstrap-responsive.css" />
    <style type="text/css">
        .mag-label-top {
            margin: 10px auto 1px auto;
            width: 800px;
            height: 150px;
            background: none repeat scroll 0 0 #EDFAFF;
            padding-top: 0;
            /*border: solid 1px black;*/
        }
        
        .mag-label-top-left {
            float: left;
            margin: 2px 10px;
            width: 360px;
            height: 140px;
            /*border: solid 1px black;*/
        }
        
        div.mag-label-top-left .addlabel {
            margin-left: 30px;
            margin-top: 40px;
            background-color: #F5F5F5;
            border: 1px solid rgba(0, 0, 0, 0.05);
            border-radius: 4px 4px 4px 4px;
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05) inset;
            margin-bottom: 10px;
            min-height: 16px;
            padding: 10px 19px 5px 19px;
        }
        
        .mag-show-msg .alert{
           padding: 8px 35px 8px 14px;
           margin-top: 2px;
           margin-bottom: 2px;
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
            margin: 2px 10px;
            width: 360px;
            height: 140px;
            /*border: solid 1px black;*/
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
            /*border: solid 1px black;*/
         }
         
         .mag-label-rand p a {
            margin-right: 2px;
            margin-bottom: 2px;
         }
        
        .mag-label-down {
            margin: 0px auto;
            width: 800px;
            height: 70px;
            background: none repeat scroll 0 0 #EDFAFF;
            /*border: solid 1px red;*/
        }
        
        .mag-label-down div:nth-child(1) {
            margin: 10px 2px 2px 20px;
            padding: 2px;
        }
        
        .mag-label-added {
            margin: 0px 2px 2px 20px;
            padding: 2px;
        }
        
        .mag-label-added p a{
            color: #0088CC;
            font-weight: bold;
            margin: 0px 2px;
        }
        
        .mag-label-change span a {
            cursor: pointer;
        }
        
        .mag-label-explain {
            margin: 0px auto;
            width: 800px;
            height: 70px;
            /*border: solid 1px blue;*/
        }
        .mag-label-explain p {
            font-size: 14px;
        }
        
        .mag-label-explain ul {
            margin-top: 5px;
            margin-right: 51%;
            margin-bottom: 2px;
            float: right;
        }
        
        .mag-label-explain ul > li{
            display: inline;
            margin: 0px 10px;
            color: #999999;
            font-family: "SimSun";
        }
        .mag-label-explain button {
             clear: both;
             margin-top: 5px;
             margin-left: 40%;
             width: 100px;
        }
        
        .mag-put {
            display: none;
        }
        
        .mag-put table td {
            width: 394px;
            height: 136px;
        }
        
        .mag-put-view {
            /*border: solid 1px red;*/
        }
        
        div.row-fluid {
            margin: 10px 3px;
            padding: 2px;
        }
        
        .mag-put-pic {
            float: left;
            width: 15%;
        }
        
        .mag-put-detail {
            float: left;
            width: 50%;
            margin: 0 5px;
            padding: 5px;
            word-wrap: break-word;
        }
        
        .mag-put-operate {
            float: left;
            width: 25%;
        }
        
        .mag-put-operate button {
            float: right;
            margin: 2px;
        }
        
        .mag-put-operate p {
            color: #999999;
        }
        
        #complete {
            clear: both;
            margin-left: 45%;
        }
        
        .mag-put-msg {
            margin-left:35%;
            width: 300px;
            height: 30px;
            /*border: solid 1px red;*/
        } 
       
    </style>
</head>
   
<body>
    <jsp:include page="../module/header.jsp"></jsp:include>
    <div class="mag-clear-10"></div>
	<div class="contentlogin">
      <div class="title">我的标签:</div>
      <div class="list">
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
                   <p>
               </div>
            </div>
        </div>
        <div class="mag-label-down">
            <div><span class="badge">我已经添加的标签 </span></div>
            <div class="mag-label-added">
               <p>
            </div>
        </div>
        <div class="mag-label-explain">
            <ul>
                <li>·丰富你的标签</li>
                <li>·推荐给你最适合阅读的杂志</li>
            </ul>
            <p>关于标签
            <button id="labelNext" class="btn">下一步</button>
            <div class="mag-put-msg"></div>
        </div>
      </div>
      <div class="mag-put">
          <div class="title">推荐的杂志:</div>
          <div class="span9 mag-put-view">
              <div class="titleread">根据您的标签分析，我们给您推荐一些杂志，希望你能喜欢。
                  <button class="btn btn-success btn-mini">全部订阅</button>
                  <button class="btn btn-inverse btn-mini">换一批</button>
              </div>
              <div id="department">
              </div>
          </div>
          <button id="complete" class="btn btn-large btn-primary">完成订阅</button>
      </div>
      
    </div>
    
    <script type="text/javascript" src="${magroot}/script/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${magroot}/script/jquery-ui-1.8.20.custom.min.js"></script>
    <script type="text/javascript" src="${magroot}/script/bootstrap/bootstrap-alert.js"></script>
    <script type="text/javascript" src="${magroot}/script/magazine.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
        	//最多一次显示多少个标签
        	var MAX_SHOW_LABELS = 9;
        	//所有标签
        	var labels = ${showLabels};
        	var userLabels = ${showUserLabels};
        	for(var i = 0; i < labels.length; i++) {
        		if(i == MAX_SHOW_LABELS) break;
        		$("div.mag-label-rand p").append('<a href="#" class="btn btn-small btn-info"><i class="icon-plus icon-white"></i>' + labels[i] + '</a>');
        	}
        	for(var i = 0; i < userLabels.length; i++) {
        		$("div.mag-label-added p").append('<a href="#" class="btn btn-small">' + userLabels[i] + '<i></i></a>');
        	}
        	
            //换一换
            $('.mag-label-change span a').click(function() {
            	$("div.mag-label-rand p").empty();
            	var selectLabel = [];
            	for(var j = 0; j < MAX_SHOW_LABELS; j++) {
            		var rand = Math.floor(Math.random()*labels.length);
            		if($.inArray(rand, selectLabel) == -1) {
            			selectLabel.push(rand);
            		}
            	}
            	
            	for(var k = 0; k < selectLabel.length; k++) {
            		$("div.mag-label-rand p").append('<a href="#" class="btn btn-small btn-info"><i class="icon-plus icon-white"></i>' + labels[selectLabel[k]] + '</a>');
            	}
            });
            
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
                // TODO 检查$(".addlabel input").val()合法性
                if($.trim($(".addlabel input").val()).length <=0) {
                    showMsg({
                            type: 'warning',
                            showMsgElement:'.mag-show-msg',
                            msg:"标签名称为空"
                            });
                    return;
                } else if(MagUtil.getLength($.trim($(".addlabel input").val())) > 14) {
                	showMsg({
                        type: 'warning',
                        showMsgElement:'.mag-show-msg',
                        msg:"标签名称不能大于14个字符"
                        });
                    return;
                }
                
                if(!addLabelCheck($(".addlabel input").val())){return;}
                $(".mag-label-added p").append('<a href="#" class="btn btn-small">' + $.trim($(".addlabel input").val()) + '<i></i></a>');
                //数组中删除该标签
                /*
                if($.inArray($.trim($(".addlabel input").val())) != -1) {
                    labels.slice($.inArray($.trim($(".addlabel input").val())), 1);
                }
                */
                
            });
            $(".mag-label-rand p a").live("click", function(){
                if(!addLabelCheck( $(this).text())){return;}
                $(".mag-label-added p").append('<a href="#" class="btn btn-small">' + $(this).text() + '<i></i></a>');
                $(this).addClass("disabled");
            });
            //添加标签check
            function addLabelCheck(labelText) {
                if($(".mag-label-added p a").size() >= 10) {
                	showMsg({
                        type: 'warning',
                        showMsgElement:'.mag-show-msg',
                        msg:"标签个数不能超过10"
                        });
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
            
            //存放推荐的杂志json
            var magDepartments = [];
            //选中标签，下一步
            $('#labelNext').click(function() {
            	var selectLabels = [];
            	$(".mag-label-added p a").each(function(){
            		selectLabels.push('"' + $(this).text() + '"');
            	});
          		$.ajax({
                    type: "POST",
                    url: "${magroot}/init/getRecomMagazine",
                    data: "selectLabels=["+selectLabels + "]",
                    async:true,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                    dataType:"json",
                    success: function(res){
                    	magDepartments = res;
                    	showRecomMag(magDepartments);
                    	$('#labelNext').hide();
                    	$('.mag-put').show('slide',{direction:"up"},'slow');
                    },
                    error: function(res){
                        alert("连接失败，请稍后重试！");
                    }
      	        });
            });
            
            //显示推荐的杂志
            function showRecomMag(magDepartments) {
            	var departHtml = "";
            	for(var i = 0; i < 6; i++) {
            		var department = magDepartments[i];
            		if(department == null) continue;
            		if(i % 2 == 0) {
            			departHtml += '<div class="row-fluid">';
            		}
            		departHtml += 
            		    '<div class="span6">' +
                    		'<img class="mag-put-pic" alt="" src="' + department.logoUrl + '" width="70" height="70">' +
                    		'<div class="mag-put-detail">' +
                            '<p><h4>' + department.name + '</h4>' +
                            '<p>' + department.description +
                        '</div>' +
                        '<div class="mag-put-operate">' +
                            '<p>[' + department.subscribeCount + ']人订阅' +
                            '<button class="btn btn-mini"><i class="icon-book"></i>预览</button>' +
                            '<button class="btn btn-success btn-mini"><i class="icon-plus icon-white"></i>订阅</button>' +
                            '<input type="hidden" value="' + department.departmentId + '" />' +
                        '</div>' +
                    '</div>';
            		if(i % 2 == 1) {
            			departHtml += '</div>';
            		}
            	}
            	$("#department").html(departHtml);
            }
            
            //全部订阅
            $(".titleread button:nth-child(1)").click(function () {
                var $this = $(this);
                //点击已经订阅
                if($this.hasClass("disabled")) {
                    showMsg({
                        type: 'warn',
                        showMsgElement:'.mag-put-msg',
                        msg:"下面的杂志已经订阅"
                     });
                    return;
                }
                var magIds = "";
                $(".mag-put-operate input[type='hidden']").each(function(){
                    //已经订阅过的不再重新订阅
                    if(!$(this).prev().hasClass("disabled")) {
                        magIds += $(this).val() + ",";
                    }
                });
                
                magIds = magIds.substring(0, magIds.length - 1);
                //console.log(magIds)
                
                $.ajax({
                    type: "POST",
                    url: "${magroot}/init/subscibeMagazineBat",
                    data: "magIds=" + magIds,
                    async:true,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                    dataType:"json",
                    success: function(res){
                        //TODO 订阅杂志一部分失败，如何处理
                        if(res) {
                            $this.addClass("disabled");
                            
                            showMsg({
                                type: 'success',
                                showMsgElement:'.mag-put-msg',
                                msg:"下面的杂志订阅成功"
                            });
                            $(".mag-put-operate button:nth-child(2)").each(function(){
                                //console.log(res[$(this).next().val()]);
                                if(res[$(this).next().val()]) {
                                    $(this).addClass("disabled");
                                    $(this).html('<i class="icon-plus icon-white"></i>已订阅');
                                }
                            });
                        } else {
                            showMsg({
                                type: 'error',
                                showMsgElement:'.mag-put-msg',
                                msg:"杂志批量订阅失败"
                            });
                        }
                    },
                    error: function(res){
                        alert("连接失败，请稍后重试！");
                    }
                });
                
            });
            
            //开始点击换一批时，初始值为6
            var magChangeNum = 0;            
            $(".titleread button:nth-child(2)").click(function () {
                //推荐的杂志小于等于6本，或者换到最后一页时
                if(magDepartments.length <= 6 || magDepartments.length - magChangeNum <= 6) {
                    //TODO 检查是否订阅杂志，如何提示？
                    showMsg({
                        type: 'info',
                        showMsgElement:'.mag-put-msg',
                        msg:'已经是最后一批了'
                     });
                    return;
                }
                magChangeNum += 6;            	
                showRecomMag(magDepartments.slice(magChangeNum));
                $(".titleread button:nth-child(1)").removeClass("disabled");
                showMsg({showMsgElement:'.mag-put-msg'}, "clear");
            });
            
            //订阅杂志
            $(".mag-put-operate button:nth-child(2)").live('click', function () {
                var $this = $(this);
                //点击已经订阅
                if($this.hasClass("disabled")) {
                    showMsg({
                        type: 'warn',
                        showMsgElement:'.mag-put-msg',
                        msg:"杂志《" + $this.closest("div.span6").find("div.mag-put-detail h4").text() + "》已经订阅"
                     });
                    return;
                }
                $.ajax({
                    type: "POST",
                    url: "${magroot}/init/subscibeMagazine",
                    data: "magId=" + $this.next().val(),
                    async:true,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                    dataType:"json",
                    success: function(res){
                        if(res[$this.next().val()]) {
                            showMsg({
                                type: 'success',
                                showMsgElement:'.mag-put-msg',
                                msg:"杂志《" + $this.closest("div.span6").find("div.mag-put-detail h4").text() + "》订阅成功"
                             });
                            $this.addClass("disabled");
                            $this.html('<i class="icon-plus icon-white"></i>已订阅');
                        } else {
                            showMsg({
                                type: 'error',
                                showMsgElement:'.mag-put-msg',
                                msg:"杂志《" + $this.closest("div.span6").find("div.mag-put-detail h4").text() + "》订阅失败"
                             });
                        }
                    },
                    error: function(res){
                        alert("连接失败，请稍后重试！");
                    }
                });
            });
            
            //预览杂志
            $(".mag-put-operate button:nth-child(1)").live('click', function () {
                
                var url = "${magroot}/init/viewMagazine?magId=" + $(this).next().next().val();
                window.open(url,"blank");
                /*
                //window.open(url,"blank",' left=0,top=0,width='+ (screen.availWidth - 10) +',height='+ (screen.availHeight-50) +',scrollbars,resizable=yes,toolbar=no');
                showMsg({
                    type: 'error',
                    showMsgElement:'.mag-put-msg',
                    msg:"很抱歉，该功能正在完善中。。。"
                 });
                */
            });
            //点击完成订阅按钮
            $("#complete").click(function(){
                window.location.href = "${magroot}/home/initpage";
            });
            
        });
    </script>
</body>
</html>