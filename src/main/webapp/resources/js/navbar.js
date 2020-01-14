/**
 * 헤더 네비게이션 바에서 사용하는 js 함수
 */
// 지역별 검색
function byLocation(loca) {
	location.href = "/ex/Board/getPostListByLocation?location="
			+ encodeURI(loca);
}
// 카테고리별 검색
function byLocation(cate) {
	location.href = "/ex/Board/getPostListByCategory?category="
			+ encodeURI(cate);
}
