package leo.wan.common;

public class Page {
    //每页显示的数量
    private int pageSize;
    //偏移量，下标
    private int offset;
    //当前页码
    private int pageNo;

    public Page(int pageNo, int pageSize) {
        this.pageSize = pageSize;
        this.offset = pageNo > 0 ? (pageNo - 1) * this.pageSize : 0;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
