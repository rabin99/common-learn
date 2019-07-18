import com.lin.dao.IUserDao0102;
import com.lin.domain.User;
import com.lin.mybatis.io.Resources;
import com.lin.mybatis.sqlsession.SqlSession;
import com.lin.mybatis.sqlsession.SqlSessionFactory;
import com.lin.mybatis.sqlsession.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/24 11:08
 * @desc : 自定义mybatis (手写mybatis)测试
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        try (InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig0102.xml");) {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(inputStream);
            SqlSession session = factory.openSession();
            // session调用getMapper，实际返回的是被代理后的对象
            IUserDao0102 userDao = session.getMapper(IUserDao0102.class);
            /* 这里执行，已经不再是原来userDao中的方法，而是代理对象提供的方法
             代理对象调用：new Executor().selectList(mapper,conn)，executor对象中将返回值给封装
             */
            List<User> users = userDao.findAll();
            users.stream().forEach(System.out::println);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
