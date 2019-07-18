import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @auther : lin
 * @date : 2019/6/12 11:15
 * @desc :
 */
public class DateTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String beforeEncoderDate = dateTimeFormatter.format(now);
        // 将时间编码后存入到cookie
        System.out.println("编码前："+beforeEncoderDate);
        String afterEncoderDate = URLEncoder.encode(beforeEncoderDate, "utf-8");
        System.out.println("编码后："+afterEncoderDate);

    }
}
