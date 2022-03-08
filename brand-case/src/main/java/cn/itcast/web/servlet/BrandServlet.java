package cn.itcast.web.servlet;

import cn.itcast.pojo.Brand;
import cn.itcast.pojo.PageInfo;
import cn.itcast.service.BrandService;

import cn.itcast.service.impl.BrandServiceImp;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImp();

    public void selectAllBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> ls = brandService.selectAll();
        String result = JSON.toJSONString(ls);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().println(result);

    }

    public void insertBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getReader().readLine();
//        System.out.println("insertBrand param is: " + param);
        Brand brand = JSON.parseObject(param, Brand.class);
        /*TODO: 传入的参数不能为空判断*/
        boolean flag = brandService.insertBrand(brand);
        PrintWriter pw = response.getWriter();
        if (flag) {
            pw.write("succeed");
        } else {
            pw.write("error");
        }
    }

    public void deleteBrands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getReader().readLine();
        int[] ids = JSON.parseObject(param, int[].class);
//        System.out.println(ids);
        boolean flag = brandService.deleteByIds(ids);
        PrintWriter pw = response.getWriter();
        if (flag) {
            pw.write("succeed");
        } else {
            pw.write("error");
        }
    }

    public void selectByPageBrands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 1. 获取post提交的查询条件信息
        String param = request.getReader().readLine();
        // 2. 获取get提交的分页信息
        Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
        Integer currentPageSize = Integer.parseInt(request.getParameter("currentPageSize"));

        Integer currentStart = (currentPage-1)*currentPageSize;
        Brand brand = JSON.parseObject(param,Brand.class);
        System.out.println("传入参数："+ currentPage+" "+currentPageSize+" "+brand);
        PageInfo<Brand> pageInfo = brandService.selectByPage(currentStart,currentPageSize,brand);
        String result = JSON.toJSONString(pageInfo);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().println(result);
    }

    public void updateBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String param = request.getReader().readLine();
        Brand brand = JSON.parseObject(param,Brand.class);
        boolean flag = brandService.updateBrand(brand);
        PrintWriter pw = response.getWriter();
        if (flag) {
            pw.println("success");
        } else {
            pw.println("error");
        }

    }
}
