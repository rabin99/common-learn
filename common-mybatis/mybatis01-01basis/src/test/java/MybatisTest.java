import com.lin.dao.IUserAnnotationDao;
import com.lin.dao.IUserDao;
import com.lin.dao.impl.UserDaoImpl;
import com.lin.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/23 16:25
 * @desc :
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        // 使用xml配置
//      userXml();
        userAnnotation();

        // 使用自己实现的dao层
//        userImplDao();
    }

    /*
    使用自己实现的dao层
     */
    public static void userImplDao() throws IOException {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        IUserDao userDao = new UserDaoImpl(factory);
        List<User> users = userDao.findAll();
        users.stream().forEach(System.out::println);
        in.close();
    }

    /*
    使用注解形式
     */
    public static void userAnnotation() throws IOException {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // boolean参数 标识是否开启自动提交
        SqlSession session = factory.openSession();
        IUserAnnotationDao userDao = session.getMapper(IUserAnnotationDao.class);
        List<User> users = userDao.findAll();
        users.stream().forEach(System.out::println);
        session.close();
        in.close();

    }

    public static void userXml() throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }
}
