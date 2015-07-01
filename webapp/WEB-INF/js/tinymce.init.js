var ADMIN = {
	cAreaName : "textarea",
	cNodes : [],
	addEvent : function(a) {
		console.log(a);
	},
	
	putTinymceDataToTextArea : function(e,n) {
		var iframe = e.parentNode.getElementsByTagName("iframe")[0];
		var text = iframe.contentDocument["body"].innerHTML;
		e.innerText = text;
	},
	
	saveButtonClick : function(e) {
		if(!this.cNodes.length){
			return;
		}
		
		var nodes = this.cNodes;
		nodes.map(this.putTinymceDataToTextArea.bind(this))
		
		var f = document.getElementById("form");
		f.submit();
		
	},
	
	init : function() {
		var textareaList = document.querySelectorAll("textarea");
		this.cNodes = Array.prototype.slice.call(textareaList,0);
		var saveButton = document.getElementById("savebutton");
		saveButton.addEventListener('click',this.saveButtonClick.bind(this));
	}
}

tinymce.init({
		language : 'ko_KR',
		selector: ADMIN.cAreaName,
		theme: "modern",
		relative_urls: false,
		convert_urls: false,
		plugins: ["advlist autolink link lists charmap preview hr anchor pagebreak spellchecker",
			       "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime nonbreaking",
			       "save table contextmenu directionality emoticons paste textcolor uploadimage"
					],
		toolbar: " undo redo | styleselect | bold italic | forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link uploadimage | preview code media fullpage ", 
});

ADMIN.init();
