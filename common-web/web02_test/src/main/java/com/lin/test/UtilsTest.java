package com.lin.test;

import com.lin.dao.UserDao;
import com.lin.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;


/**
 * @auther : lin
 * @date : 2019/6/10 11:41
 * @desc :
 */
public class UtilsTest {
    @Test
    public void test() {
        User user = new User();
        try {
            BeanUtils.setProperty(user, "hehe", "male");
            System.out.println(user);
            String gender = BeanUtils.getProperty(user, "hehe");
            System.out.println(gender);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setUsername("super");
        loginUser.setPassword("123");
        UserDao dao = new UserDao();
        User login = dao.login(loginUser);
        System.out.println(login);
    }
}
