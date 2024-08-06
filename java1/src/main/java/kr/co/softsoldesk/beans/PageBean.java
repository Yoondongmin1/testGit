package kr.co.softsoldesk.beans;

public class PageBean {

	private int min; //최소페이지 번호
	private int max; //최대페이지 번호
	private int prevPage; //이전버튼의 페이지 번호
	private int nextPage; //다음버튼의 페이지번호
	private int pageCnt; //전체 페이지 번호
	private int currentPage; //현재 페이지 번호
	
	public PageBean(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {
		/*
		 contentCnt : 전체글 개수
		 currentPage : 현재 페이지 번호
		 contentPageCnt : 페이지당 글의 개수(10)
		 paginationCnt : 한번에 표시할 페이지 버튼의 개수(예컨대 게시글이 21개면, 3개의 링크 버튼) 
		 
		 */
		
		this.currentPage = currentPage;
		
		pageCnt = contentCnt/contentPageCnt; //21나누기 10해서 pagcont에 2라는 값을 넣는다
		//게시글 수에 따른 페이지 수
		//게시글이 1개 : 0페이지 + 1 필요
		//게시글이 10개 : 1페이지
		//게시글이 11개 : 1페이지 + 1 필요
		//게시글이 20개 : 2페이지
		
		if(contentCnt % contentPageCnt > 0) { //pageCnt가 10의 단위가 아니면
			 // +1 해주겠다.
			pageCnt++;
		}
		
		min = ((currentPage-1)/contentPageCnt) * contentPageCnt + 1; //최소 페이지
		//1페이지: ((0/10) + 1 => 1
		//2페이지: ((2-1/10) + 1 => 1
		// (10-1) 하면 9에 / 10하면 0에다가 +1 해버리니까 1이뜬다
		//위의 공식하면 1~10페이지까지는 무조껀 1뜬다
		//11페이지 : ((11-1)/10) + 10 => 2
		// 11~19 페이지의 최소 페이지는 11
		
		max = min + paginationCnt -1; //최대페이지
		//1페이지 : 1+10 - 1 = > [1]이 보이는 화면에서는 [10]이 최대페이지
		//11페이지 : 11+10 - 1 => 20 [11]이 보이는 화면에서는 [20]이 최대페이지
		
		if(max > pageCnt) {
		//만약에 최대페이지의 갯수가
		
			max = pageCnt;
			
		}
		//전체 페이지가 3페이지까지 있으면 MAX 페이지는  3페이지로 제한 ([1] [2] [3])
		//전체 페이지가 11페이지까지 있으면 MAXX 페이지는 11페이지로 제한([11])
	
		
		prevPage = min - 1;
		//이전 페이지는 최소페이지에서 -1 / <이전 [11] =>  [10]
		nextPage = max + 1; //맥스는 최대로
		//다음 페이지는 최대페이지에서 +1/ [10] 다음> => [11]
		if(nextPage > pageCnt) { 
			nextPage = pageCnt; //전제페이지가 15면 넥스트페이지가 16으로 넘어가지않게 고정시켜준다 
		}
		
		//전체페이작 15(pageCnt)페이지일 떄, nextPage는 16페이지어야 하지만 현재 페이지가 15페이지면 nextPage를 15로 조정
	}
		
	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	
	
	
	
	
}
