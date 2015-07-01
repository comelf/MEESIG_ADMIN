ADMIN = {
		//Scrolling keys
		keys : [37, 38, 39, 40],
		popupCallback : function() {},
		findParentElement : function (t, n) {
			if(n==0){
				return t;
			}
			t = t.parentElement;
			return this.findParentElement(t,n-1);
		},	
		wheel : function (e) {
			this.preventDesfault(e);
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

		enable_scroll : function () {
			if (window.removeEventListener) {
			    window.removeEventListener('DOMMouseScroll', this.wheel.bind(this), false);
			}
			window.onmousewheel = document.onmousewheel = document.onkeydown = null;  
		},
		disable_scroll : function () {
			if (window.addEventListener) {
			    window.addEventListener('DOMMouseScroll', this.wheel.bind(this), false);
			}
			window.onmousewheel = document.onmousewheel = this.wheel.bind(this);
			document.onkeydown = this.keydown.bind(this);
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
		
		photoUploaderInit : function(that) {
			var pop = $(".pop-layer");
			var bg = pop.find('.bg')[0];
			var cbtn = pop.find(".close-btn")[0];
			var sbtn = pop.find(".btn")[0];
			var fileInput = pop.find("input")[0];
			fileInput.addEventListener("click",this.clearErrorMsg.bind(this));
			bg.addEventListener("click",this.closePhotoUpload.bind(this));
			cbtn.addEventListener("click",this.closePhotoUpload.bind(this));
			sbtn.addEventListener("click",this.submitPhotoImage.bind(that));
		},
		photoUpload : function() {
			var pop = $(".pop-layer");
			ADMIN.disable_scroll();
			ADMIN.popLayerRepositioning(pop);
			ADMIN.containerRepositioning(pop);
			pop.fadeIn();
		},
		clearErrorMsg : function(e) {
			var eMsg = e.target.parentElement.children[1];
			eMsg.innerText = "";
		},
		closePhotoUpload : function(e) {
			if (e)
			    e.preventDefault();
			ADMIN.enable_scroll();
			var pop = $(".pop-layer");
			pop.fadeOut();
		},
		
		preventDesfault : function(e) {
			e = e || window.event;
			if (e.preventDefault)
			    e.preventDefault();
			e.returnValue = false;  
		},
		submitPhotoImage : function(e) {
			e.preventDefault();
			var target = e.target;
			var form = ADMIN.findParentElement(target, 3);
			var data = new FormData(form);
			var fileInput = form.getElementsByTagName("input")[0];
			var hasFile = fileInput.files.length;
			var that = this;
			if(hasFile){
				 $.ajax({
			         url: form.action,
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
			        		 that.imgUploadComplete(result);
			        		 ADMIN.closePhotoUpload();
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
		popupItems : function(callback) {
			var popUrl = "/popup/item";	//팝업창에 출력될 페이지 URL
			var popOption = "width=600, height=500";
			ADMIN.popupCallback = callback;
			window.open(popUrl,"",popOption);
		},
		popupCallBack : function(data) {
			ADMIN.popupCallback(data);
		}
		
}