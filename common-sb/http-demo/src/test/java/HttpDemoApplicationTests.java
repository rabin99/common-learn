import com.lin.HttpDemoApplication;
import com.lin.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/7/5 14:44
 * @desc :
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HttpDemoApplication.class)
public class HttpDemoApplicationTests {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void httpGet(){
        List forObject = restTemplate.getForObject("http://localhost/user/commonSelectByExample", List.class);
        System.out.println(forObject);
    }
}
