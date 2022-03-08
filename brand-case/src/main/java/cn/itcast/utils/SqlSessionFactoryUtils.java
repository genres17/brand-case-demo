package cn.itcast.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
    private static final SqlSessionFactory SqlSessionFactory;

    static {
        String path = "mybatis-config.xml";
        InputStream in  = null;
        try {
            in = Resources.getResourceAsStream(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

    }

    public static SqlSessionFactory getSqlSessionFactory(){
        return SqlSessionFactory;
    }
}
