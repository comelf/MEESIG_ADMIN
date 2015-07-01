RECOMMEND = {
	currentImgDiv : null,
	itemChoiceClick: function(e) {
		RECOMMEND.currentImgDiv = ADMIN.findParentElement(e.target, 2).children[0];
		ADMIN.popupItems(this.popupResult1.bind(this));
	},
	itemChangeClick: function(e) {
		RECOMMEND.currentImgDiv = ADMIN.findParentElement(e.target, 1);
		ADMIN.popupItems(this.popupResult2.bind(this));
	},
	popupResult1 : function(data) {
		var div = this.currentImgDiv;
		var pDiv = ADMIN.findParentElement(div, 1);
		var eItemId = div.children[2];
		var eMediaId = div.children[3];
		var eImg = div.children[4]
		var eTitle = pDiv.children[1].children[3];
		var eDes = pDiv.children[1].children[5];
		eItemId.defaultValue	= data.itemId;
		eMediaId.defaultValue  	= data.mediaId;
		eTitle.value 		= data.itemName;
		eDes.value 			= data.itemDes;
		eImg.src 			= data.mediaPath;
	},
	popupResult2 : function(data) {
		var div = this.currentImgDiv;
		var eImg     = div.children[0];
		var eItemId  = div.children[3];
		var eMediaId = div.children[4];
		eImg.src = data.mediaPath;
		eItemId.value = data.itemId;
		eMediaId.value = data.mediaId;
	}
}

$('.recommend-upload').click(function(e) {
	RECOMMEND.itemChoiceClick(e);
})

$('.recommend-change').click(function(e) {
	RECOMMEND.itemChangeClick(e);
})