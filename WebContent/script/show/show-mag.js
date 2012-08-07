$(function(){
	//move();
});
function move(){
	var lastBtn=$(".gUbtnPre");
	var nextBtn=$(".gUbtnNext");
	var picParent=$(".gUContF");
	var allPic=picParent.find(".gUcommon");
	var picWidth=parseInt(allPic.width());   //获取每一张图片的宽度
	var conLeft=$(".guideUserbot").offset().left;     //获取中间区块的左偏移量即据页面左边的距离
	picParent.width(allPic.length*picWidth); //给所有图片的父集设置宽度既所有图片宽度之和
	var maxLeft=picParent.width();           //图片父集的宽度
	var flag=true;	
	lastBtn.hide();
	//点击向左移动
	lastBtn.click(function(){
		//防止运动停止之前又触发点击事件
		if(flag==false){
			return false;
		}
		flag=false;
		var startLeft=picParent.offset().left-conLeft;
		newLeft=startLeft+picWidth;
		if(startLeft<conLeft-picWidth){
			nextBtn.show();
			//picParent.animate({left:newLeft+200},600);
			picParent.animate({left:newLeft},600,function(){
				flag=true;
			});
		}else{
			flag=true;
		}
		if(startLeft>=conLeft-picWidth*2){
			lastBtn.hide();
		}
		
	});
	
	//点击向右移动
	nextBtn.click(function(){
		if(flag==false){
			return false;
		}
		flag=false;
		
		var startLeft=picParent.offset().left-conLeft;//获取当前图片父集的左偏移量
		newLeft=startLeft-picWidth;   //向左运动一张图片的宽度的距离，计算图片父集新的左偏移量
		if(startLeft>conLeft-maxLeft+picWidth){
			lastBtn.show();
			//picParent.animate({left:newLeft-200},600);  //先向左多运动一点
			picParent.animate({left:newLeft},600,function(){  //再向右运动之前向左运动的超出的距离
				flag=true;
			});
		}else{
			flag=true;
		}
		if(startLeft<=conLeft-maxLeft+picWidth*2){
			nextBtn.hide();
		}
	});
}