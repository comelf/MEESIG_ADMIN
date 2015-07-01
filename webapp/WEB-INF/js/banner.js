BANNER = {
	currentImgDiv : null,
	init : function() {
		ADMIN.photoUploaderInit(this);
	},
	imgUploadComplete : function(result) {
		var div = this.currentImgDiv;
		var eType = div.children[1];
		var eSrc = div.children[2];
		var eMediaId = div.children[3];
		var eImg = div.children[4];
		eMediaId.value  	= result.img_id;
		eType.value 		= $('#banner-type')[0].value;
		eSrc.value 			= result.image;
		eImg.src 			= result.image;
	},
	bannerUploadClick: function(e) {
		BANNER.currentImgDiv = ADMIN.findParentElement(e.target, 2).children[0];
		ADMIN.photoUpload();
	},
}

$('.banner-upload').click(function(e) {
	BANNER.bannerUploadClick(e);
})
$('#newbutton').click(function(e) {
	var form = $('.banner-row')[0];
	var source   = document.getElementById("banner-template").innerHTML;
	var template = Handlebars.compile(source);
	var html     = template();
	form.insertAdjacentHTML("beforeend",html);
})
BANNER.init();