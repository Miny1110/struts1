package com.util;

/*
dataCount : 전체 데이터의 개수(34개)
numPerPage : 한 화면에 표시할 데이터의 개수(3개)
currentPage : 현재 표시할 페이지(9페이지)
totalPage : 전체 페이지 수(12페이지)
listUrl : 링크 설정할 url -> list.jsp가 default값임
numPerBlock :  1◀이전 6 7 8 9 10 다음▶12 (6~10까지 표시되는 페이지의 개수: 5)
currentPageSetup : 이전 버튼을 클릭하면 나오는 페이지
page : page index 숫자
*/

public class MyPage {

	//전체 페이지의 개수
	public int getPageCount(int numPerPage, int dataCount) {

		int pageCount = 0;

		pageCount = dataCount / numPerPage; //	34/3
		//전체 페이지 개수 = 전체 데이터 개수 / 한 페이지에 나오게 하려는 데이터 개수
		//딱 떨어지지 않고 나머지 값이 생기면 1개의 페이지가 더 필요하다.
		
		//한 페이지에 3개의 게시글을 보이게 할건데 전체 게시글이 34개라면, 총 페이지는 12개가 되어야 한다. 
		//그렇게 때문에 34/3에 나머지가 남는다면 페이지 개수를 하나 늘린다.
		if(dataCount % numPerPage != 0) {
			pageCount++;
		}

		return pageCount;

	}


	//페이징 처리 메소드
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
/*	currentPage : 현재페이지
	totalPage : 전체페이지(getPageCount 메소드를 통해 구해진다.)
	listUrl : 만든 페이징을 뿌릴 값	*/
		
		int numPerBlock = 2; // ◀이전 6 7 8 9 10 다음▶ 에서 가운데 숫자의 개수
		int currentPageSetup; // 이전페이지 버튼을 누르면 나오는 값
		int page; //for문의 i와 똑같은 역할. 시작하는 변수가 설정되어 있어야 for문을 돌릴 수 있다. page가 이와 같은 역할을 한다.

		StringBuffer sb = new StringBuffer();
		//StringBuffer : 쓰레기값을 방지하고 String보다 처리속도가 빠름

		if(currentPage==0||totalPage==0) {
			return "";
		}
		//현재페이지가 0이고, 전체페이지가 0이면 데이터가 잘못 입력됐다는 의미.
		//그러면 리턴(멈춘다)
		

		//돌아가는 주소 만들기
		//listUrl에는 기본적으로 list.jsp가 들어온다.
		//만약 검색을 했다면 [list.jsp?searchKey=선택값&searchValue=입력값]이 주소가 된다.
		if(listUrl.indexOf("?")!=-1) {
			listUrl = listUrl + "&";
		}else { // ?가 없으면
			listUrl = listUrl + "?";
		}
/*		주소에 ?가 없지않으면(=있으면) 주소 끝에 &를 붙인다.(뒤에 페이징 조건을 걸기 위해서)
		주소에 ?가 없으면 ?를 붙여준다.list.jsp? 처럼	*/
		
		
		currentPageSetup = (currentPage/numPerBlock) * numPerBlock; 
/*		◀이전 6 7 8 9 10 다음▶ 에서 currentPageSetup 는 이전을 눌렀을 때 뜨는 페이지에 해당한다.

		이전버튼페이지 = (현재페이지/페이지선택개수) * 페이지선택개수
		(현재페이지/페이지선택개수)는 변수가 int 타입이기 때문에 몫이 결과값이 된다.
		(페이지선택개수 : 여기서는 5)
		
		이전버튼페이지가 0보다 작으면 ◀이전 이 출력될 필요가 없다. (1 2 3 4 5 다음▶)
		이 경우에는 아래의 ◀이전 구하기 코드를 수행하지 않고 건너뛴다.
		 */
		
		if(currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;
		}
/*		현재페이지를 페이지선택개수로 나눈 나머지가 0이면
		이전버튼페이지는 이전페이지에서 페이지선택개수(5)를 뺀 값이 된다.
		이 조건을 주지 않으면, 나머지가 0일때 이전버튼페이지는 현재페이지가 된다. 본인에게 돌아온다.
		(현재페이지가 10일때 이전버튼페이지는 10이 된다. -> 2(10/5의 몫)*5=10)	*/


		//◀이전 구하기
		if(totalPage>numPerBlock && currentPageSetup>0) {

			sb.append("<a href=\"" + listUrl + "pageNum=" + currentPageSetup + "\">◀이전</a>&nbsp;");
			//<a href="list.jsp?pageNum=5">◀이전</a>&nbsp; 의 코딩

		}
/*		전체페이지가 페이지선택개수보다 크고, 이전버튼페이지가 0보다 크면 (=페이지선택목록이 1 2 3 4 5가 아니면)
		◀이전 을 눌렀을 때 위에서 설정한 이전버튼페이지 로 넘어간다. (하이퍼링크를 걸었다.)	*/
		
		
		//1 2 3 4 5 다음▶
		//◀이전 6 7 8 9 10 다음▶ 를 구하는 공식
		//바로가기 페이지. 시작값을 정해야 한다.
		page = currentPageSetup + 1;
/*		이전버튼페이지는 0이 될 수도 있는데 (현재페이지가 1~4일 경우) 페이지는 0값이 나오면 안되니까 +1을 해주어야 한다.
 		여기에서는 page가 1, 6, 11 셋 중 하나이다.	*/

		while(page<=totalPage && page<=(currentPageSetup+numPerBlock)) {

			//page와 지금 내가 보고있는 페이지가 동일하다면 링크를 걸어줄 필요가 없다. 페이지 이동을 할 필요가 없다.
			if(page==currentPage) {
				sb.append("<font color=\"Fuchsia\">" + page + "</font>&nbsp;");
				//<font color="Fuchsia">페이지번호</font>&nbsp;
			}else {
				sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				//<a href="listUrl.jsp?pageNum=페이지번호">페이지번호</a>&nbsp;
			}

			page++;

		}
/*		시작값이 전체페이지보다 작거나 같고, 시작값이 이전버튼페이지+페이지선택개수보다 작거나 같으면 반복한다.
 		다음▶ 을 출력하기 위한 조건
 		여기서는 1~10페이지까지 해당된다.	*/	
		
		
		
		//다음▶ 을 만드는 코드
		//◀이전 11 12
		if(totalPage-currentPageSetup>numPerBlock) {
		//totalPage=12, currentPageSetup=5 이면 두 수의 차가 numPerBlock=5보다 크므로 다음을 보여준다.
		//totalPage=12, currentPageSetup=10 이면 두 수의 차가 numPerBlock=5보다 작으므로 다음을 보여주지 않는다.
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">다음▶</a>&nbsp;");
			//<a href="list.jsp?pageNum=11">다음▶</a>&nbsp;
			
		}
/*		전체페이지에서 이전버튼페이지를 뺀것이 페이지선택개수보다 크면.... ? 
 		
 	*/
		
		return sb.toString();
			
	}	



}








