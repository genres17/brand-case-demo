package cn.itcast.pojo;

import java.util.List;

public class PageInfo<T> {
    // 因为在传送数据过程中，分页的信息是包裹在一个json中传输的，所以要单独看成一个实体
    // 为了泛用性，因为分页的内容不一定是brand实体，所以采用泛型的通配符
    Integer counts;
    List<T> items;

    public PageInfo() {
    }

    public PageInfo(Integer counts, List<T> items) {
        this.counts = counts;
        this.items = items;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "counts=" + counts +
                ", items=" + items +
                '}';
    }
}
