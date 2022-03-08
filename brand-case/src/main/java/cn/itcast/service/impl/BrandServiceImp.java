package cn.itcast.service.impl;

import cn.itcast.dao.BrandMapper;
import cn.itcast.pojo.Brand;
import cn.itcast.pojo.PageInfo;
import cn.itcast.service.BrandService;
import cn.itcast.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImp implements BrandService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Brand> selectAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> ls = brandMapper.selectAll();
        sqlSession.close();
        return ls;
    }

    public boolean insertBrand(Brand brand){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        boolean flag = brandMapper.insertBrand(brand);
        sqlSession.commit();
        sqlSession.close();
        return flag;
    }

    @Override
    public boolean deleteByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        boolean flag = brandMapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
        return flag;
    }

    @Override
    public PageInfo<Brand> selectByPage(int currentStart, int currentPageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        String brandName = brand.getBrandName();
        // 模糊查询需要修改字段内容
        brand.setBrandName("%"+brandName+"%");
        String companyName = brand.getCompanyName();
        brand.setCompanyName("%"+companyName+"%");
        Integer counts = brandMapper.countBrands(brand);
        List<Brand> ls = brandMapper.selectByPage(currentStart,currentPageSize,brand);
        PageInfo<Brand> pageInfo = new PageInfo<>();
        System.out.println("查询的结果是："+ls);
        pageInfo.setCounts(counts);
        pageInfo.setItems(ls);
        sqlSession.close();
        return pageInfo;
    }

    @Override
    public boolean updateBrand(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        boolean flag = brandMapper.updateBrand(brand);
        sqlSession.commit();
        sqlSession.close();
        return flag;
    }
}
