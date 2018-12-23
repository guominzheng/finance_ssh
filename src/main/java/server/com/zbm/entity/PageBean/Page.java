package server.com.zbm.entity.PageBean;


public class Page {
    private Integer pagesize; //页面大小
    private Integer pageno; //当前页
    private Integer startrow; //起始行
    private Integer totalpage; //总页数
    private Integer totalcount; //总条数

    public Page() {
    }

    public Page(Integer pageSize, Integer pageNo, Integer totalCount) {
        this.pagesize = pageSize; //页面大小
        this.pageno = pageNo;  //当前页
        this.totalcount = totalCount; //计算总条数
        this.setStartrow(pageNo, pageSize); //计算起始行
        this.setTotalpage(totalCount, pageSize); //计算总页数
    }

    public Integer getPagesize() {
        return this.pagesize;
    }

    public void setPagesize(Integer pageSize) {
        this.pagesize = pageSize;
    }

    public Integer getPageno() {
        return this.pageno;
    }

    public void setPageno(Integer pageNo) {
        if (this.pageno != null && pageno > 0) {
            this.pageno = pageNo;
        } else {
            this.pageno = 1;
        }
    }

    public Integer getStartrow() {
        return (this.pageno - 1) * this.pagesize;
    }

    /**
     * 计算起始行
     *
     * @param pageNo
     * @param pageSize
     */
    public void setStartrow(Integer pageNo, Integer pageSize) {
        this.startrow = (pageNo - 1) * pageSize;
    }

    public Integer getTotalpage() {
        return this.totalpage;
    }

    /**
     * 计算总页数
     *
     * @param totalCount
     * @param pageSize
     */
    public void setTotalpage(Integer totalCount, Integer pageSize) {
        this.totalpage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        this.pagesize = pageSize;
    }

    public Integer getTotalcount() {
        return this.totalcount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalcount = totalCount;
    }

    @Override
    public String toString() {
        return "[{\"pageno\":" + pageno + ",\"pagesize\":" + pagesize
                + ",\"startrow\":" + startrow + ",\"totalcount\":" + totalcount
                + ",\"totalpage\":" + totalpage + "}]";
    }

}
