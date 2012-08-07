<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="${magroot}/css/main.css" />
    <link rel="stylesheet" type="text/css" href="${magroot}/css/bootstrap/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${magroot}/css/bootstrap/bootstrap-responsive.css" />
    <style type="text/css">
        .mag-book-operate {
            margin-left: 72%;
            width: 200px;
            height: 30px;
            padding-top: 10px;
            padding-left: 50px;
            padding-right: 5px;
            /*border: solid 1px red;*/
        }
    </style>
</head>
   
<body>
    <jsp:include page="../module/header.jsp"></jsp:include>
    <div class="mag-clear-10"></div>
    <div class="content"> 
      <div class="main">
        <div class="rightmain">
        <c:forEach var="subscribeRelation" items="${subscribeRelations}">
          <div class="mag-subscribe-book">
            <input type="hidden" name="hiddenDepId" value="${subscribeRelation.department.departmentId}" />
            <span>
              <img src="${subscribeRelation.department.logoUrl}" width="137" height="137" />
            </span>
            <p class="text"><a href="#">${subscribeRelation.department.name}</a></p> 
            <p class="text1"><a href="${subscribeRelation.department.departmentDomain}">编辑部域名</a> 创建时间：<fmt:formatDate value="${subscribeRelation.department.createdAt}" type="date" dateStyle="long"/></p>
            <p >${subscribeRelation.department.description}
            </p>
            <div class="mag-book-operate">
              <c:if test="${subscribeRelation.flag == 0}">
                  <a name="subscribe" class="btn btn-mini btn-success" href="javascript:void(0)"><i class="icon-plus icon-white"></i>订阅</a>
              </c:if>
              <c:if test="${subscribeRelation.flag == 1}">
                  <a name="cancleSubscribe" class="btn btn-mini btn-danger" href="javascript:void(0)"><i class="icon-minus icon-white"></i>取消订阅</a>
              </c:if>
              <a class="btn btn-mini btn-primary" href="javascript:void(0)"><i class="icon-pencil icon-white"></i>评论</a>
              
            </div>
          </div>
        </c:forEach>
        </div>
        <div class="leftmain">
          <div class="mag-user-info">
            <span><img src="${user.profileImageUrl}" width="98" height="108" /></span>
            <p class="text">${user.screenName}</p>                  
            <p>订&nbsp;&nbsp;&nbsp;阅&nbsp;&nbsp;<span id="subscribeCount">${user.subscribeCount}</span></p>
            <p>编辑部&nbsp;&nbsp;${user.participateCount}</p>
            <p>投&nbsp;&nbsp;&nbsp;稿&nbsp;&nbsp;${user.submissionCount}</p>
          </div>
          <div class="mag-depart-info">
            <span><img src="${magroot}/images/weibook/jz1.png" width="57" height="19" /></span>
            <ul>
              <li><a href="">我的约稿</a></li>
              <li><a href="">我的评论</a></li>
              <li><a href="">我的转发</a></li>
              <li><a href="">我的邀请</a></li>
            </ul>
          </div>
          <div class="mag-put-list">
            <ul>
              <li><span><img src="${magroot}/images/weibook/sina.png" width="101" height="41" /></span>
              <p><a href="">春暖基金</a></p><a href=""><img src="${magroot}/images/weibook/btn.png" width="68" height="23" /></a></li>
              <li><span><img src="${magroot}/images/weibook/sina.png" width="101" height="41" /></span>
              <p><a href="">春暖基分贝</a></p><a href=""><img src="${magroot}/images/weibook/btn.png" width="68" height="23" /></a></li>
              <li><span><img src="${magroot}/images/weibook/sina.png" width="101" height="41" /></span>
              <p><a href="">春暖基金爱</a></p><a href=""><img src="${magroot}/images/weibook/btn.png" width="68" height="23" /></a></li>
              <li><span><img src="${magroot}/images/weibook/sina.png" width="101" height="41" /></span>
              <p><a href="">春暖基金爱</a></p><a href=""><img src="${magroot}/images/weibook/btn.png" width="68" height="23" /></a></li>
            </ul>
          </div>
          <div class="mag-depart-list">
            <ul>
              <c:forEach var="depart" items="${showTop}">
                <c:if test="${depart.key < 6}">
                <li>
                  <img src="${depart.value.logoUrl}" width="45" height="45" />
                  <span>${depart.value.name} <i class="icon-arrow-up"></i></span>
                  <span><i>${depart.value.subscribeCount}</i>人订阅</span>
                </li>
                </c:if>
              </c:forEach>
            </ul>
          </div>
        </div>
      </div>
    </div>   
    
    <script type="text/javascript" src="${magroot}/script/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${magroot}/script/jquery-ui-1.8.20.custom.min.js"></script>
    <script type="text/javascript" src="${magroot}/script/bootstrap/bootstrap-alert.js"></script>
    <script type="text/javascript" src="${magroot}/script/magazine.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            //阅读杂志
            $(".mag-subscribe-book span img, .mag-subscribe-book p.text a").click(function () {             
                var url = "${magroot}/init/viewMagazine?magId=" + 
                    $(this).closest("div.mag-subscribe-book").find("input[type='hidden']").val();
                window.open(url,"blank");
            });
            
            //取消订阅
            $(".mag-book-operate > a[name='cancleSubscribe']").on("click", function(){
                var $this = $(this);
                $.ajax({
                    type: "GET",
                    url: "${magroot}/home/cancelSubscribe",
                    data: "departmentId=" + $this.closest("div.mag-subscribe-book").find("input[name='hiddenDepId']").val(),
                    async:true,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                    dataType:"json",
                    success: function(res){
                        if(res) {
                            $this.closest("div.mag-subscribe-book").remove();
                            $("#subscribeCount").text($("#subscribeCount").text() - 1);
                        }
                    },
                    error: function(res){
                        alert("连接失败，请稍后重试！");
                    }
                });
            });
            
            //订阅杂志
            $(".mag-book-operate > a[name='subscribe']").on('click', function () {
                var $this = $(this);
                var subMagId = $this.closest("div.mag-subscribe-book").find("input[name='hiddenDepId']").val()
                //点击已经订阅
                $.ajax({
                    type: "POST",
                    url: "${magroot}/init/subscibeMagazine",
                    data: "magId=" + subMagId,
                    async:true,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                    dataType:"json",
                    success: function(res){
                        if(res[subMagId]) {
                            $this.outerHTML('<a name="cancleSubscribe" class="btn btn-mini btn-danger" href="javascript:void(0)"><i class="icon-minus icon-white"></i>取消订阅</a>');
                            $("#subscribeCount").text($("#subscribeCount").text() - 0 + 1);
                        } else {
                            alert(1)
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