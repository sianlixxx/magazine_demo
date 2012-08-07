<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<!--[if lt IE 7]><html class="ie6"><![endif]--> 
<!--[if IE 7]><html class="ie7"><![endif]--> 
<!--[if IE 8]><html class="ie8"><![endif]--> 
<!--[if gt IE 8]><html><![endif]-->
<!--[if !IE]>--><html><!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <title>预览杂志</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" href="${magroot}/css/mag.css" />
        <link rel="stylesheet" type="text/css" href="${magroot}/css/mag.show.css" />
        <link rel="stylesheet" type="text/css" href="${magroot}/css/prettyPhoto.css" />
        <link rel="stylesheet" type="text/css" href="${magroot}/css/main.css" />
    </head>
    <body>
        <jsp:include page="../module/header.jsp"></jsp:include>
        <div id="page">
        </div>
        
        <script type="text/javascript" src="${magroot}/script/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="${magroot}/script/jquery-ui-1.8.20.custom.min.js"></script>
        <script type="text/javascript" src="${magroot}/script/jquery.prettyPhoto.js"></script>
        <script type="text/javascript" src="${magroot}/script/show/show-mag.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $.ajax({
                    type: "GET",
                    url: "${magurl}",
                    async:true,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                    dataType:"html",
                    success: function(res){
                        $("#page").html(res);
                        $("a[rel^='prettyPhoto']").prettyPhoto({animation_speed:'normal',theme:'light_square',slideshow:3000, autoplay_slideshow: false});
                        move();
                    },
                    error: function(res){
                        alert("连接失败，请稍后重试！");
                    }
                });
                
            });
        </script>
    </body>
</html>