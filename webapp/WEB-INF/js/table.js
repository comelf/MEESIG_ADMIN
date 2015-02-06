var ADMIN = {

	clickTable : function(e) {
		var input = e.target.parentElement.children[0].children[0];
		var row = e.target.parentNode;
		row.style.cssText ="background-color:#bbb;";
		if(typeof input.value != "undefined"){
			var path = "/user/detail/" + input.value
			window.location.pathname = path; 
		}
	},
	adminInit : function() {
		console.log("GOOD");
		var tbody = $('.table').find('tbody')[0];
		console.log(tbody[0]);
		tbody.addEventListener("click", this.clickTable.bind(this));
	}
}

ADMIN.adminInit();