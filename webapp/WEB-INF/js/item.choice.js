var ITEM = {

	aOptRow : [ "<div class='form-inline'> <input name='iom.optionBlocks[","].optionRow[","].description' class='form-control' type='text' placeholder='옵션 이름'><input name='iom.optionBlocks[","].optionRow[", "].price' class='form-control' type='text' placeholder='가격'><input type='button' class='btn btn btn-warning' value='삭제' onclick='ITEM.delOptRow(this)'></div>"],
	aOptBlock : ["<div class='form-group'><div class='form-inline'><input name='iom.optionBlocks[","].optionTitle' class='form-control' placeholder='항목 이름' type='text'><input type='button' class='btn btn btn-warning' value='항목삭제' onclick='ITEM.delItemOption(this)'></div><div class='form-inline'><input name='iom.optionBlocks[","].optionRow[0].description' class='form-control' type='text' placeholder='옵션 이름'><input name='iom.optionBlocks[","].optionRow[0].price' class='form-control' type='text' placeholder='가격'><input type='button' class='btn btn btn-warning' value='삭제' onclick='ITEM.delOptRow(this)'></div><input type='button' class='btn btn-primary' id='adddpo' value='옵션추가' onclick='ITEM.addOptRow(this)'></div>"],
	radioList : function(n, t) {
		var radios = t.children;
		var len = radios.length;
		for(var i =0; i<len; i++){
			radios[i].addEventListener('click',this.selectClick.bind(this));
		}
	},	
	selectClick : function (e) {
		var target = e.target;
		if(target.tagName === "INPUT"){
			var t = ADMIN.findParentElement(target,5);
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
				var p = ADMIN.findParentElement(t,5);
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
		
		ADMIN.photoUploaderInit(this);
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
		var oHtml = "<div class='form-inline'> <input id='spm.priceOpt3Des' name='spm.priceOpt3Des' class='form-control' type='text' ><input id='spm.priceOpt3Pri' name='spm.priceOpt3Pri' class='form-control' type='text' > <input type='button' class='btn btn btn-warning' value='삭제' onclick='ITEM.delDpo(this)'/></div> ";
		// Internet Explorer, Opera, Google Chrome and Safari
		if (t.insertAdjacentHTML) {        
			t.insertAdjacentHTML("beforeBegin", oHtml);
        }
		
	},
	delDpo : function() {
		var t = event.target.parentElement;
		t.remove(t);
	},
	
	addItemOptinInput : function() {
		var t = event.target;
		var oHtml = "<div class='form-inline'> <input id='itemOptionDes' name='itemOptionDes' class='form-control' type='text' ><input id='itemOptionPri' name='itemOptionPri' class='form-control' type='text' > <input type='button' class='btn btn btn-warning' value='삭제' onclick='ITEM.delDpo(this)'/></div> ";
		if (t.insertAdjacentHTML) {        
			t.insertAdjacentHTML("beforeBegin", oHtml);
        }
	},
	
	imgUploadComplete : function(result) {
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
		t.parentElement.remove();		
	},
	delOptRow : function() {
		var t = event.target.parentElement;
		t.remove();
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

ITEM.itemInit();