/**
 * 게시판에서 사용되는 js함수
 */

// 제목을 눌렀을 때 실행되는 함수 - 해당하는 게시글로 이동
function selectPost(post_ID) {
	var frm = $('#selectPost');
	frm.attr('method', 'GET');
	frm.append("<input type='hidden' name='idx' value='" + post_ID + "'>")
	// alert("id = " + this.id);
	frm.submit();
};

// 페이지 이동 버튼을 눌렀을 때 실행되는 함수 - 페이지 이동(게시판의 페이지)
function selectPage() {
	var url = "";
	// alert("id = " + this.id);
	// --------------------------------------------페이지 번호
	// 붙임--------------------------------------------------------
	if (this.id == "prev") {
		url = '/ex/Board/getBoardList?setPage=' + $
		{
			page.curPage - page.range
		}
		;
	} else if (this.id == "next") {
		url = '/ex/Board/getBoardList?setPage=' + $
		{
			page.curPage + page.range
		}
		;
	} else {
		url = '/ex/Board/getBoardList?setPage=' + this.value;
	}

	// --------------------------------------------검색 쿼리

	if (query == null) {
		alert("검색내용 없음");
	} else {
		url += "&searchContent=" + query;
	}
	location.href = url;
};