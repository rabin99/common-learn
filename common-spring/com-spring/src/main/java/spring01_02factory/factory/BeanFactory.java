package spring01_02factory.factory;



import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @auther : lin
 * @date : 2019/5/20 11:09
 * @desc : 工厂类，用于解耦，通过配置文件来耦合硬编码。从此调用者，要使用底层，只需要从工厂方法中去取对应的对象;
 * 这里有个坑:使用foreach执行时，出现npe，因为foreach是transformation，用到时才触发，导致初始化的时候并没有将所有对象进行newInstance，
 * 同时告诫下，如果是初始化要创建出对象，尽量避开使用Consumer接口
 */
public class BeanFactory {
    private static Properties props;
    private static Map<String, Object> beans;

    static {
        props = new Properties();
        try (InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");) {
            props.load(is);
            beans = new HashMap<>();
//            props.forEach((key, value) -> {
//                try {
//                    System.out.println("foreach执行");
//                    Object o = Class.forName((String) value).newInstance();
//                    beans.put(key.toString(),o);
//                } catch (Exception e) {
//                   throw  new ExceptionInInitializerError("初始化失败");
//                }
//            });
            Enumeration<Object> keys = props.keys();
            while (keys.hasMoreElements()){
                String key = keys.nextElement().toString();
                Object value = Class.forName(props.getProperty(key)).newInstance();
                beans.put(key,value);
            }
        }catch (Exception e){
           e.printStackTrace();
        }
        }
        public static Object getBean (String beanName){
            return beans.get(beanName);
        }
    }
