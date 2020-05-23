package leo.wan.common;

public class Page {
    //每页允许的数量，分页的前提下默认单元显示20条
    private int limit=20;
    //偏移量，下标
    private int offset;
    //当前页码
    private int pageNo;

    public Page(int pageNo, int pageSize) {
        this.limit = pageSize;
        this.pageNo=pageNo;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return  pageNo > 0 ? (pageNo - 1) * this.limit : 0;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
