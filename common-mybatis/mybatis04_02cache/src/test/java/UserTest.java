import com.lin0402.dao.IUserDao;
import com.lin0402.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/28 15:37
 * @desc :
 */
public class UserTest {
    private InputStream in;
    private SqlSession sqlSession;
    private SqlSessionFactory factory;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws Exception {
        sqlSession.close();
        in.close();
    }

    /*
    测试一级缓存
     */
    @Test
    public void testFirstLevelCache() {
        User user1 = userDao.findById(41);
        System.out.println(user1);
//        一级缓存无法通过配置操作，只能关闭session再打开一个新的，或者调用clearCache方法强制清除
        sqlSession.close();
        sqlSession = factory.openSession();
        // 这里测试一级注意关闭掉二级缓存
        // 如果不关闭二级缓存，那么这里拿的还是缓存，不过是二级缓存！！！
        userDao = sqlSession.getMapper(IUserDao.class);
//        sqlSession.clearCache();
        User user2 = userDao.findById(41);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }
    /*
    测试缓存的同步
     */
    @Test
    public void testClearCache(){
        //1.根据id查询用户
        User user1 = userDao.findById(41);
        System.out.println(user1);

        //2.更新用户信息
        user1.setUsername("update user clear cache");
        user1.setAddress("北京市海淀区");
        userDao.updateUser(user1);

        //3.再次查询id为41的用户
        User user2 = userDao.findById(41);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }
}
