package Util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MybatisUtil {
   private static SqlSessionFactory sqlSessionFactory;
   static {
       //创建一个工厂对象
       try {
           Reader resourceAsReader = Resources.getResourceAsReader("MyBatis-Config.xml");
           sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);

       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   public static SqlSession getSession(){
       return sqlSessionFactory.openSession();
   }
}
