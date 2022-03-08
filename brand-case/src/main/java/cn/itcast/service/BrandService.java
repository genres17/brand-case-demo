package cn.itcast.service;

import cn.itcast.pojo.Brand;
import cn.itcast.pojo.PageInfo;

import java.util.List;

public interface BrandService {
    /* 查询所有 */
    List<Brand> selectAll();

    /* 插入一条记录 */
    boolean insertBrand(Brand brand);

    /* 批量删除*/
    boolean deleteByIds(int[] ids);

    /* 分页条件查询 */
    PageInfo<Brand> selectByPage(int currentStart, int currentPageSize, Brand brand);

    /* 修改一条记录 */
    boolean updateBrand(Brand brand);


}
