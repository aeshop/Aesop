package teamSemiProject2.edu.kh.semi.category.model.vo;


/**페이지네이션 객체
 * @author nyr
 * 화면에 보여질 페이지의 개수와, 하단부분 페이지 이동 버튼에 쓰인다.
 */
public class Pagination {

	//계산에 영향미치는 변수
	private int currentPage; //현재 페이지
	private int productCount;//전체 제품수
	private int limit=8;//한번에 화면에서 보여줄 제품 개수
	private int pageSize=5; //한번에 화면에서 보여줄 페이지 개수
	
	
	//계산되어지는 변수
	private int maxPage;
	private int startPage;
	private int endPage;
	private int prevPage;
	private int nextPage;
	
	
	public Pagination(int productCount,int currentPage ) {

		
		this.productCount = productCount;
		this.currentPage = currentPage;
		makePagination();
	
	}


	private void makePagination() {
			
		maxPage = (int)Math.ceil((double) productCount / limit);
		//시작페이지 1~5:1  6~10: 6
		startPage = ((currentPage-1)/pageSize)*pageSize +1;
		endPage = startPage +pageSize -1<=maxPage ? startPage +pageSize -1 : maxPage;
		prevPage = startPage -1 >= 1 ? startPage -1 : 1;
		nextPage = endPage +1 <=maxPage? endPage+1:maxPage;
		
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		makePagination();
	}


	public int getProductCount() {
		return productCount;
	}


	public void setProductCount(int productCount) {
		this.productCount = productCount;
		makePagination();

	}


	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
		makePagination();

	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		makePagination();

	}


	public int getMaxPage() {
		return maxPage;
	}


	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
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


	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", productCount=" + productCount + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}
	
	
	
	
	
}
