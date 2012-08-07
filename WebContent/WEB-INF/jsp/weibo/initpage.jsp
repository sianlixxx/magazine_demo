<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="weimin.magazine.back.dao.pojo.TAccessToken" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>微博</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="${magroot}/css/main.css" />
    <link rel="stylesheet" type="text/css" href="${magroot}/css/bootstrap/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${magroot}/css/bootstrap/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${magroot}/css/upload/uploadify.css" />
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <style type="text/css">
    
        .mag-weibo-publish {
            width: 720px;
            border: solid 1px #eee;
            background-color: #EEEEEE;
            border-radius: 6px 6px 6px 6px;
            margin-bottom: 30px;
            padding: 10px;
        }
        
        .mag-weibo-publish > div {
            margin: 5px 8px;
        }
        
        .mag-weibo-publish #content {
            background-repeat: no-repeat;
            border: 1px solid #CCCCCC;
            font-size: 14px;
            height: 110px;
            line-height: 160%;
            margin: 10px;
            overflow: auto;
            padding: 10px;
            width: 660px;
            resize: none;
        }
        
        #weiboFooter {
            height: 20px;
        }
        
        .mag-weibo-publish #upload {
            float:left;
            width: 200px;
            margin-left: 20px;
        }
        
        .mag-weibo-publish #publish {
            float:right;
            width: 160px;
        }
        
        .mag-weibo-list {
            width: 700px;
            border: solid 1px #eee;
        }
        
        .mag-weibo-face {
            float: left;
            width: 50px;
        }
        
        .mag-weibo-content {
            float: right;
            width: 630px;
            word-wrap: break-word;
            /*border: solid 1px black;*/
        }
        
        .mag-weibo-content p {
            margin-top: 5px;
        }
        
        .mag-weibo-content span a:link {
            color: #497979;
            font-size: 16px;
            font-weight: bolder;
            text-decoration: none;
        }
        .mag-weibo-content span a {
            color: #497979;
        }
        
        .mag-weibo-footer {
            clear: both;
            padding: 5px 5px;
            height: 30px;
            /*border: solid 1px black;*/
        }
        
        .mag-weibo-footer a:nth-child(1) {
            margin-left: 8%;
        }
        .mag-weibo-footer div {
            float: right;
            margin-right: 2px;
        }
        
        #upload a{
            margin-right: 10px;
        }
        
        #weiboHead div:nth-child(1) {
            float: left;
            width: 40%;
        }
        .mag-show-msg {
            float: left;
            width: 30%;
            height: 30px;
            /*border: solid 1px red;*/
        }
        
        .mag-weibo-count {
            float: left;
            width: 20%;
            height: 30px;
            margin-left: 50px;
            /*border: solid 1px blue;*/
        }
        
        #upload .nav > li > a {
            color: #06F;
        }
        
        #upload > ul > li {
           width:80px;
        }
        
        #addImg ul li {
            width: 300px;
            margin-bottom: 2px;
        }
        
    </style>
</head>
   
<body>
    <jsp:include page="../module/header.jsp"></jsp:include>
    <div class="mag-clear-10"></div>
    <div class="content"> 
      <div class="main">
        <div class="rightmain">
        <div class="mag-weibo-publish">
          <div id="weiboHead">
            <div>
                <img src="${magroot}/images/weibook/pic1.png" width="207" height="28" />
            </div>
            <div class="mag-show-msg"></div>
            <div class="mag-weibo-count"><span>可输入140字</span></div>
          </div>
          <div id="weiboContent">
            <textarea id="content"></textarea>
          </div>
          <div id="weiboFooter">
            <div id="upload" class="navbar">
              <ul class="nav">
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#"><img src="${magroot}/images/weibook/smile.png"/><span>表情</span></a>
                    <ul class="dropdown-menu">
                      <li>
                          <ul id="myTab" class="nav nav-tabs">
                            <li class="active"><a href="#home" data-toggle="tab">Home</a></li>
                            <li><a href="#profile" data-toggle="tab">Profile</a></li>
                            <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                              <ul class="dropdown-menu">
                                <li><a href="#dropdown1" data-toggle="tab">@fat</a></li>
                                <li><a href="#dropdown2" data-toggle="tab">@mdo</a></li>
                              </ul>
                            </li>
                          </ul>
                          
                          <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                              <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica. Reprehenderit butcher retro keffiyeh dreamcatcher synth. Cosby sweater eu banh mi, qui irure terry richardson ex squid. Aliquip placeat salvia cillum iphone. Seitan aliquip quis cardigan american apparel, butcher voluptate nisi qui.</p>
                            </div>
                            <div class="tab-pane fade" id="profile">
                              <p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>
                            </div>
                            <div class="tab-pane fade" id="dropdown1">
                              <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork. Williamsburg banh mi whatever gluten-free, carles pitchfork biodiesel fixie etsy retro mlkshk vice blog. Scenester cred you probably haven't heard of them, vinyl craft beer blog stumptown. Pitchfork sustainable tofu synth chambray yr.</p>
                            </div>
                            <div class="tab-pane fade" id="dropdown2">
                              <p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they sold out farm-to-table VHS viral locavore cosby sweater. Lomo wolf viral, mustache readymade thundercats keffiyeh craft beer marfa ethical. Wolf salvia freegan, sartorial keffiyeh echo park vegan.</p>
                            </div>
                          </div>
                      </li>
                      <li><a href="#">Separated list fund seitan letterst fund seitan letternk</a></li>
                    </ul>
                </li>
                <li id="addImg" class="dropdown">
                  <a data-toggle="dropdown" class="dropdown-toggle" href="#"><img src="${magroot}/images/weibook/pic2.png"/><span>图片</span> </a>
                  <ul class="dropdown-menu">
                    <li>
                      <input type="file" name="uploadify" id="uploadify" />
                    </li>
                    <li>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
            <div id="publish">
              <input type="button" id="contributeWeibo" class="button" value="投稿并发布" />
              <input type="button" id="publishWeibo" class="button" value="发布" />
            </div>
          </div>
        </div>
        <c:forEach var="statuse" items="${statuses}">
          <div class="well mag-weibo-list">
          <div class="mag-weibo-face">
            <a title="${statuse.user.name}" href="http://weibo.com/${statuse.user.userDomain}">
              <img width="50" height="50" src="${statuse.user.profileImageUrl}" alt="" title="${statuse.user.name}">
            </a>
          </div>
          <div class="mag-weibo-content">
            <span>
              <a id="name" target="_blank" href="http://weibo.com/${statuse.user.userDomain}">${statuse.user.name}</a>
            </span>
            <p>
              ${statuse.text}
            </p>
            <c:if test="${statuse.thumbnailPic != null}">
                <ul class="">
                  <li>
                    <div class="">
                    <img class="bigcursor" alt="" src="${statuse.thumbnailPic}">
                    </div>
                  </li>
                </ul>
            </c:if>
            
          </div>
          <div class="mag-weibo-footer">
            <a href="http://weibo.com/${statuse.user.userDomain}"><fmt:formatDate value="${statuse.createdAt}" type="date" dateStyle="full"/></a>
                         来自
            <a rel="nofollow" href="http://weibo.com/${statuse.user.userDomain}" target="_blank">${statuse.user.name}</a>
            <div class="btn-toolbar">
                <div class="btn-group">
                    <a title="转发" class="btn btn-mini" href="#">
                      <i class="icon-share"></i>(${statuse.repostsCount})
                    </a>
                    <a title="评论" class="btn btn-mini" href="#">
                      <i class="icon-pencil"></i>(${statuse.commentsCount})
                    </a>
                    <a title="收藏" class="btn btn-mini" href="#">
                      <i class="icon-heart"></i>
                    </a>
                </div>
                <div class="btn-group" style="display:none">
                    <a title="删除" class="btn btn-mini" href="javascript:void(0)">
                      <i class="icon-trash"></i>
                    </a>
                </div>
            </div>
          </div>
          <input type="hidden" name="hiddenUid" value="${statuse.user.id}" />
          <input type="hidden" name="hiddenMid" value="${statuse.mid}" />
          </div>
        </c:forEach>
        </div>
        <div class="leftmain">
          <div class="mag-user-info">
            <span><img src="${user.profileImageUrl}" width="98" height="108" /></span>
            <p class="text">${user.screenName}</p>                  
            <p><a href="#">关注</a>&nbsp;&nbsp;${user.friendsCount}</p>
            <p><a href="#">粉丝</a>&nbsp;&nbsp;${user.followersCount}</p>
            <p><a href="#">微博</a>&nbsp;&nbsp;${user.statusesCount}</p>
          </div>
          <div class="mag-depart-info">
            <span><img src="${magroot}/images/weibook/jz1.png" width="57" height="19" /></span>
            <ul>
              <li><a href="">@提到我的</a></li>
              <li><a href="">我的评论</a></li>
              <li><a href="">我的私信</a></li>
              <li><a href="">我的收藏</a></li>
            </ul>
          </div>
          <div class="mag-put-list">
            <ul>
              <li><span><img src="${magroot}/images/weibook/sina.png" width="101" height="41" /></span>
              <p><a href="">田浩CTO</a></p><a href=""><img src="${magroot}/images/weibook/btn.png" width="68" height="23" /></a></li>
              <li><span><img src="${magroot}/images/weibook/sina.png" width="101" height="41" /></span>
              <p><a href="">春暖</a></p><a href=""><img src="${magroot}/images/weibook/btn.png" width="68" height="23" /></a></li>
              <li><span><img src="${magroot}/images/weibook/sina.png" width="101" height="41" /></span>
              <p><a href="">张总</a></p><a href=""><img src="${magroot}/images/weibook/btn.png" width="68" height="23" /></a></li>
              <li><span><img src="${magroot}/images/weibook/sina.png" width="101" height="41" /></span>
              <p><a href="">大海</a></p><a href=""><img src="${magroot}/images/weibook/btn.png" width="68" height="23" /></a></li>
            </ul>
          </div>
          <div class="mag-depart-list">
            <ul>
              <li>
                <span><i class="">奥运会开幕式</i></span>
                <span><i>123456</i></span>
              </li>
              <li>
                <span><i class="">刘翔vs冬日娜</i></span>
                <span><i>123456</i></span>
              </li>
              <li>
                <span><i class="">西班牙夺冠</i></span>
                <span><i>123456</i></span>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <input type="hidden" id="userId" value="${accToken.userId}" ></input>
    <input type="hidden" id="extName" value="" ></input>
    <input type="hidden" id="uid" value="${accToken.uid}" ></input>
    
    <script type="text/javascript" src="${magroot}/script/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${magroot}/script/jquery-ui-1.8.20.custom.min.js"></script>
    <script type="text/javascript" src="${magroot}/script/bootstrap/bootstrap-alert.js"></script>
    <script type="text/javascript" src="${magroot}/script/bootstrap/bootstrap-tab.js"></script>
    <script type="text/javascript" src="${magroot}/script/bootstrap/bootstrap-dropdown.js"></script>
    <script type="text/javascript" src="${magroot}/script/upload/jquery.uploadify-3.1.min.js"></script>
    <script type="text/javascript" src="${magroot}/script/magazine.js"></script>
    
    <script type="text/javascript">
        $(document).ready(function(){
            //发布
            $("#publishWeibo").click(function () {            
                $.ajax({
                    type: "POST",
                    url: "${magroot}/weibo/publish",
                    data: "content=" + $("#content").val() + "&extName=" + $("#extName").val(),
                    async:true,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                    dataType:"json",
                    success: function(res){
                        if(res) {
                            showMsg({
                                type: 'success',
                                showMsgElement:'.mag-show-msg',
                                msg:"微博发布成功!"
                             });
                            $("#extName").val("");
                        } else {
                            showMsg({
                                type: 'error',
                                showMsgElement:'.mag-show-msg',
                                msg:"微博发布失败!"
                             });
                        }
                    },
                    error: function(res){
                        alert("连接失败，请稍后重试！");
                    }
                });
            });
            
            $("#uploadify").uploadify( {//初始化函数
                'uploader' : '${magroot}/weibo/uploadImg?userId=' + $("#userId").val(),//后台处理的请求
                'swf' : '${magroot}/script/upload/uploadify.swf',//flash文件位置，注意路径
                'queueSizeLimit' : 1,//上传文件的数量
                'fileTypeDesc' : 'jpg,gif,png文件',//上传文件类型说明
                'fileTypeExts' :'*.jpg;*gif;*.png', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
                'method' : 'post',//数据传输方式
                'fileSizeLimit':500000,//文件上传的大小限制，单位是字节
                'auto' : true,//是否自动上传
                'multi' : false,//是否多张上传
                'buttonText' : '添加图片',//浏览按钮图片
                'onUploadSuccess' : function(file, data, response) {
                    showMsg({
                        type: 'info',
                        showMsgElement:'.mag-show-msg',
                        msg:'图片添加成功!'
                    });
                    $("#extName").val(file.name.substring(file.name.lastIndexOf(".")));
                },
                'onCancel' : function(file) {
                    showMsg({
                        type: 'warn',
                        showMsgElement:'.mag-show-msg',
                        msg:'图片添加取消!'
                    });
                    $("#extName").val("");
                },
                'onUploadError' : function(file, errorCode, errorMsg, errorString) {
                    showMsg({
                        type: 'error',
                        showMsgElement:'.mag-show-msg',
                        msg:'图片添加失败!'
                    });
                    $("#extName").val("");
                }
            });
            
            //光标聚焦在微博上显示删除标志，离开微博后隐藏删除标志
            $("div.mag-weibo-list").on("mouseover", function(){
                //判断是否自己发的微博
                if($("input[name='hiddenUid']",this).val() == $("#uid").val()) {
                    $(".btn-group:nth-child(2)", this).show();
                }
            }).on("mouseout", function(){
                $(".btn-group:nth-child(2)", this).hide();
            });
            
            //删除微博
            $(".btn-group:nth-child(2)").on("click", function(){
                var $this = $(this);
                $.ajax({
                    type: "GET",
                    url: "${magroot}/weibo/deleteWeibo",
                    data: "mid=" + $this.closest("div.mag-weibo-list").find("input[name='hiddenMid']").val(),
                    async:true,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                    dataType:"json",
                    success: function(res){
                        if(res) {
                            $this.closest("div.mag-weibo-list").remove();
                        }
                    },
                    error: function(res){
                        alert("连接失败，请稍后重试！");
                    }
                });
            });
         
        });
    </script>
</body>
</html>