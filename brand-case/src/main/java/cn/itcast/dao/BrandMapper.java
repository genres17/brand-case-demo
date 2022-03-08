package cn.itcast.dao;

import cn.itcast.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
    // 查询所有信息
    List<Brand> selectAll();

    // 插入brand信息
    boolean insertBrand(Brand brand);

    // 批量删除
    boolean deleteByIds(@Param("ids") int[] ids);

    // 分页条件查询
    List<Brand> selectByPage(@Param("start") int currentStart, @Param("size") int currentPageSize, @Param("brand") Brand brand);

    // 显示总记录数
    Integer countBrands(@Param("brand") Brand brand);

    // 更新记录
    boolean updateBrand(Brand brand);

}
