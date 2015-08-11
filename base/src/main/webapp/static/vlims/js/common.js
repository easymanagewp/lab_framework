/**
 * Created by aaron on 15/7/23.
 */
$(function() {
    searchShow();
    listTableEvent();
});

/*高级查询显示隐藏*/
function searchShow() {
    $('.searchBar .morestj').click(function() {
        $('.normalsearchbar,.highsearchbar').toggle();
    });
}

function listTableEvent() {
    $('.listTable tbody tr').each(function(i) {
        if ( i % 2 != 0 ) {
            $(this).addClass('even');
        }
    });
}

function submit() {
	$('form').submit();
}

function del(action) {
	if(confirm('确定删除当前记录吗')){
		 next(action);
		 return false;
	}
}

function next(action) {
	window.location.href = action;
}

function back() {
	window.history.back(); return false;
}
