var ADMIN = {
	//Scrolling keys
	keys : [37, 38, 39, 40],
	aOptRow : [ "<div class='form-inline'> <input name='iom.optionBlocks[","].optionRow[","].description' class='form-control' type='text' placeholder='옵션 이름'><input name='iom.optionBlocks[","].optionRow[", "].price' class='form-control' type='text' placeholder='가격'><input type='button' class='btn btn btn-warning' value='삭제' onclick='ADMIN.delOptRow(this)'></div>"],
	aOptBlock : ["<div class='form-group'><div class='form-inline'><input name='iom.optionBlocks[","].optionTitle' class='form-control' placeholder='항목 이름' type='text'><input type='button' class='btn btn btn-warning' value='항목삭제' onclick='ADMIN.delItemOption(this)'></div><div class='form-inline'><input name='iom.optionBlocks[","].optionRow[0].description' class='form-control' type='text' placeholder='옵션 이름'><input name='iom.optionBlocks[","].optionRow[0].price' class='form-control' type='text' placeholder='가격'><input type='button' class='btn btn btn-warning' value='삭제' onclick='ADMIN.delOptRow(this)'></div><input type='button' class='btn btn-primary' id='adddpo' value='옵션추가' onclick='ADMIN.addOptRow(this)'></div>"],
	radioList : function(n, t) {
		var radios = t.children;
		var len = radios.length;
		for(var i =0; i<len; i++){
			radios[i].addEventListener('click',this.selectClick.bind(this));
		}
	},
	
	findParentElement : function (t, n) {
		if(n==0){
			return t;
		}
		t = t.parentElement;
		return this.findParentElement(t,n-1);
	},
	
	selectClick : function (e) {
		var target = e.target;
		if(target.tagName === "INPUT"){
			var t = this.findParentElement(target,5);
			var cName = t.classList[1];
			var div = t.children[1];
			this.showSub(div, (target.value));
			
		}
	},
	
	selected : function(n, t) {
		var radios = t.children;
		var len = radios.length;
		for(var i =0; i<len; i++){
			var t = radios[i].children[0];
			if(!t){
				return;
			}
			if(t.tagName === "INPUT" && t.checked ){
				var p = this.findParentElement(t,5);
				if(!p) return;
				var c = p.children[1].children[i];
				if(!c) return;
				c.style.display="block";
			}
		}
	},
	
	itemSelectEvent : function() {
		var sList = $(".mes-cho");
		
		//클릭이벤트 리스너 달기
		sList.map(this.radioList.bind(this));
		//선택항목 있을때 활성화
		sList.map(this.selected.bind(this));
	},
	
	itemInit : function() {
		this.itemSelectEvent();
		
		var pop = $(".pop-layer");
		var bg = pop.find('.bg')[0];
		var cbtn = pop.find(".close-btn")[0];
		var sbtn = pop.find(".btn")[0];
		var fileInput = pop.find("input")[0];
		fileInput.addEventListener("click",this.clearErrorMsg.bind(this));
		bg.addEventListener("click",this.closePhotoUpload.bind(this));
		cbtn.addEventListener("click",this.closePhotoUpload.bind(this));
		sbtn.addEventListener("click",this.submitPhotoImage.bind(this));
	},
	
	showSub : function(e,n) {
		var eList = e.children;
		var len = eList.length;
		var t = e.getElementsByClassName(n)[0];
		
		for(var i =0; i<len; i++){
			var et = eList[i];
			if(et!=t){
				et.style.display="none";
			}else{
				et.style.display="block";
			}
		}
		
	},
	addDeliveryPriceOptringInput : function() {
		var t = event.target;
		var oHtml = "<div class='form-inline'> <input id='spm.priceOpt3Des' name='spm.priceOpt3Des' class='form-control' type='text' ><input id='spm.priceOpt3Pri' name='spm.priceOpt3Pri' class='form-control' type='text' > <input type='button' class='btn btn btn-warning' value='삭제' onclick='ADMIN.delDpo(this)'/></div> ";
		// Internet Explorer, Opera, Google Chrome and Safari
		if (t.insertAdjacentHTML) {        
			t.insertAdjacentHTML("beforeBegin", oHtml);
        }
		
	},
	delDpo : function() {
		var t = event.target.parentElement;
		t.remove(t);
	},
	clearErrorMsg : function(e) {
		var eMsg = e.target.parentElement.children[1];
		eMsg.innerText = "";
	},
	addItemOptinInput : function() {
		var t = event.target;
		var oHtml = "<div class='form-inline'> <input id='itemOptionDes' name='itemOptionDes' class='form-control' type='text' ><input id='itemOptionPri' name='itemOptionPri' class='form-control' type='text' > <input type='button' class='btn btn btn-warning' value='삭제' onclick='ADMIN.delDpo(this)'/></div> ";
		if (t.insertAdjacentHTML) {        
			t.insertAdjacentHTML("beforeBegin", oHtml);
        }
	},
	photoUpload : function() {
		var pop = $(".pop-layer");
		this.disable_scroll();
		this.popLayerRepositioning(pop);
		this.containerRepositioning(pop);
		pop.fadeIn();
	},
	
	popLayerRepositioning : function(e) {
		e.css('top', $(document).scrollTop()+'px');
	},
	
	containerRepositioning : function(e) {
		var con = e.find(".pop-container");
		var left = $(window).width()/2 - con.width()/2;
		var top = $(window).height()/2 - 100;
		con.css('top', top+'px');
		con.css('left', left+'px');
	},
	
	submitPhotoImage : function(e) {
		e.preventDefault();
		var target = e.target;
		var form = this.findParentElement(target, 3);
		var data = new FormData(form);
		var fileInput = form.getElementsByTagName("input")[0];
		var hasFile = fileInput.files.length;
		
		if(hasFile){
			 $.ajax({
		         url: "/media/photoUpload",
		         type: "POST",
		         data: data,
		         async: true,
		         cache: false,
		         processData: false,
		         contentType: false,
		         success:  function(result){
		        	 if(result.error){
		        		 alert(result.msg);
		        	 }else{
		        		 ADMIN.imgUploadComplete(result);
		        	 }
		         },
		         error : function(){
		        	 alert("서버에 업로드할수 없습니다.");
		         }
		     });
		}else {
			var eMsg = fileInput.parentElement.children[1];
			eMsg.innerText = "파일을 선택해주세요.";
		}
	},
	
	closePhotoUpload : function(e) {
		if (e)
		    e.preventDefault();
		this.enable_scroll();
		var pop = $(".pop-layer");
		pop.fadeOut();
	},
	
	preventDesfault : function(e) {
		e = e || window.event;
		if (e.preventDefault)
		    e.preventDefault();
		e.returnValue = false;  
	},

	keydown : function (e) {
		var len = this.keys.length;
		for (var i = len; i--;) {
		    if (e.keyCode === this.keys[i]) {
		    	this.preventDesfault(e);
		        return;
		    }
		}
	},

	wheel : function (e) {
		this.preventDesfault(e);
	},

	disable_scroll : function () {
		if (window.addEventListener) {
		    window.addEventListener('DOMMouseScroll', this.wheel.bind(this), false);
		}
		window.onmousewheel = document.onmousewheel = this.wheel.bind(this);
		document.onkeydown = this.keydown.bind(this);
	},

	enable_scroll : function () {
		if (window.removeEventListener) {
		    window.removeEventListener('DOMMouseScroll', this.wheel.bind(this), false);
		}
		window.onmousewheel = document.onmousewheel = document.onkeydown = null;  
	},
	
	imgUploadComplete : function(result) {
		this.closePhotoUpload();
		var div = $(".image-block")[0];
		var eImg = div.children[1];
		var eId = div.children[2];
		var eImgUrl = div.children[3];
		eImg.src = result.image;
		eId.value = result.img_id;
		eImgUrl.value = result.image;
	},
	delItemOption : function() {
		var t = event.target.parentElement;
		
		//
		console.log();
		qq = t;
		//
		
		t.parentElement.remove();
		
		
	},
	delOptRow : function() {
		var t = event.target.parentElement;
		console.log(t);
		qq = t;
	},
	
	addOptRow: function() {
		var t = event.target;
		var mRowSize = 2;
		var mBloSize = 3;
		var arrNum = t.parentElement.parentElement.children.length - mBloSize;
		var rowNum = t.parentElement.children.length - mRowSize;
		var oHtml = this.aOptRow[0] + arrNum + this.aOptRow[1] + rowNum + this.aOptRow[2] + arrNum + this.aOptRow[3] + rowNum + this.aOptRow[4];
		if (t.insertAdjacentHTML) {
			t.insertAdjacentHTML("beforeBegin", oHtml);
        }
	},
	
	addItemOption: function() {
		var t = event.target;
		var mRowSize = 2;
		var arrNum = t.parentElement.children.length - mRowSize;
		var oHtml = this.aOptBlock[0] + arrNum + this.aOptBlock[1] + arrNum+this.aOptBlock[2] + arrNum + this.aOptBlock[3];
		if (t.insertAdjacentHTML) {
			t.insertAdjacentHTML("beforeBegin", oHtml);
        }
	}
}

ADMIN.itemInit();