
$('.menus-upload').click(function(e) {
	MENUS.itemChoiceClick(e);
})

$('.processbutton').click(function(e) {
	var t = e.target;
	var eForm = t.parentElement;
	var eTd = eForm.parentElement;
	var eTr = eTd.parentElement;
	var eShop = eTr.getElementsByClassName('shop-name')[0];
	var eSel = eTr.getElementsByClassName('sel-date')[0];
	var input = eTd.getElementsByClassName('input-date')[0];
	input.value = eSel.value; 
	if(confirm(eShop.innerHTML+'의 '+eSel.options[eSel.selectedIndex].text + '의 상태를 조리중으로 변경하시겠습니까?')){
		eForm.submit();
	}
})

$('.outbutton').click(function(e) {
	var t = e.target;
	var eForm = t.parentElement;
	var eTd = eForm.parentElement;
	var eTr = eTd.parentElement;
	var eSel = eTr.getElementsByClassName('sel-date')[0];
	var input = eTd.getElementsByClassName('input-date')[0];
	input.value = eSel.value; 
	eForm.submit();
})