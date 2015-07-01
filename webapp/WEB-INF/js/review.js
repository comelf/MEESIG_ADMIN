REVIEW = {
	currentImgDiv : null,
	init : function() {
		ADMIN.photoUploaderInit(this);
	},
	imgUploadComplete : function(result) {
		debugger;
		var div = this.currentImgDiv;
		var eMediaId = div.children[2];
		var eImg = div.children[3];
		eMediaId.value  	= result.img_id;
		eImg.src 			= result.image;
	},
	reviewUploadClick: function(e) {
		REVIEW.currentImgDiv = ADMIN.findParentElement(e.target, 2).children[0];
		ADMIN.photoUpload();
	},
	itemChoiceClick: function(e) {
		REVIEW.currentImgDiv = ADMIN.findParentElement(e.target, 1);
		ADMIN.popupItems(this.popupResult.bind(this));
	},
	popupResult : function(data) {
		debugger;
		var div = this.currentImgDiv;
		var eItemId = div.children[7];
		eItemId.defaultValue	= data.itemId;
	},
}

$('.review-upload').click(function(e) {
	REVIEW.reviewUploadClick(e);
})
$('#newbutton').click(function(e) {
	var form = $('.review-row')[0];
	var source   = document.getElementById("review-template").innerHTML;
	var template = Handlebars.compile(source);
	var html     = template();
	form.insertAdjacentHTML("beforeend",html);
})
$('.review-item-choice').click(function(e) {
	REVIEW.itemChoiceClick(e);
})
REVIEW.init();