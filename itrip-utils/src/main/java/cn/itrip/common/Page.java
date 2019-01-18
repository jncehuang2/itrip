
package cn.itrip.common;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * <p>分页处理类 .</p>
 *
 * @version v1.0
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class Page<T> {
    private Integer curPage;
    /**
     * 总记录数 .
     */
    private Integer total;
    /**
     * 每页行数 .
     */
    private Integer pageSize;
    /**
     * 页面的总数  .
     */
    private Integer pageCount;
    /**
     * 结果集中数据的起始位置  .
     */
    private Integer beginPos;
    /**
     * List 集合.
     */
    private List<T> rows;
    /**
     * 当前页面 .
     * 页面的大小 .
     * @param curpage .
     * @param pagesize .
     */
    public Page(int curpage, Integer pagesize) {
        this.curPage = curpage;
        this.pageSize = pagesize;
    }
    /**
     * @param curpage .
     * @param total .
     * @param pagesize .
     */
    public Page(int curpage,Integer pagesize,Integer total) {
        super();
        this.curPage = curpage;//当前页码
        this.total = total;//总记录数
        this.pageSize = pagesize;//页码容量
        //总页数=总记录数total/pageSize（+1）
        this.pageCount = (total + this.pageSize - 1) /this.pageSize;
        //下标起始位置：(curPage-1)*pageSize
        this.beginPos = (curPage-1)*pageSize;
    }
}
