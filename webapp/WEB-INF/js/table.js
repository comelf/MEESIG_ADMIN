var ADMIN = {

	clickTable : function(e) {
		var input = e.target.parentElement.children[0].children[0];
		var row = e.target.parentNode;
		row.style.cssText ="background-color:#bbb;";
		if(typeof input.value != "undefined"){
			var path = "/" + this.getBasePath() + "/detail/" + input.value
			window.location.pathname = path; 
		}
	},
	adminInit : function() {
		var tbody = $('.table').find('tbody')[0];
		tbody.addEventListener("click", this.clickTable.bind(this));
	},
	getBasePath : function() {
		var path = window.location.pathname;
		var pathArr = path.split("/");
		return pathArr[1];
	}
}

ADMIN.adminInit();