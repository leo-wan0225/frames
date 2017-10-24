package leo.wan.entity;
/**
 * 封装了对应bootstrap分页的数据
 * @author Administrator
 *
 */
public class PageBean {
	//页码
	private int pageNumber;
	//单页显示的数量
	private int pageSize;
	//开始数据索引
	private int start;
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStart() {
		return (pageNumber - 1) * pageSize;
	}
	public void setStart(int start) {
		this.start = start;
	}

}
