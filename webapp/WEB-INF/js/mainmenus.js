MENUS = {
	currentImgDiv : null,
	itemChoiceClick: function(e) {
		MENUS.currentImgDiv = ADMIN.findParentElement(e.target, 2).children[0];
		ADMIN.popupItems(this.popupResult1.bind(this));
	},
	
	popupResult1 : function(data) {
		var div = this.currentImgDiv;
		var eItemId = div.children[2];
		var eMediaId = div.children[3];
		var eImg = div.children[4]
		var eTitle = div.children[9];
		eItemId.defaultValue	= data.itemId;
		eMediaId.defaultValue  	= data.mediaId;
		eTitle.value 		= data.itemName;
		eImg.src 			= data.mediaPath;
	},
}

$('.menus-upload').click(function(e) {
	MENUS.itemChoiceClick(e);
})

$('#newbutton').click(function(e) {
	var form = $('.menus-row')[0];
	var source   = document.getElementById("menus-template").innerHTML;
	var template = Handlebars.compile(source);
	var html     = template();
	form.insertAdjacentHTML("beforeend",html);
})