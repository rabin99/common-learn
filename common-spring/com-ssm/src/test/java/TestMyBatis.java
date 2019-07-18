import com.lin.dao.IAccountDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @auther : lin
 * @date : 2019/6/28 17:42
 * @desc :
 */
public class TestMyBatis {
    @Test
    public void testRun(){
        try (InputStream resourceAsStream = Resources.getResourceAsStream("SqlConfigForTest.xml");) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = factory.openSession();
            IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
            accountDao.findAll().forEach(System.out::println);
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
