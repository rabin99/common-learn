package com.lin.controller;

import com.lin.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sun.text.normalizer.UBiDiProps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @auther : lin
 * @date : 2019/6/20 17:41
 * @desc :
 */
/*
1) 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html，配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
2) 如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
    如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 */
@Controller   // RestController就不用使用ResponseBody注解了,也不会返回jsp视图，RestController只会返回return中的结果;
// 如果需要使用到视图，则需要使用Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"msg"},types = {String.class})
//  把msg=美美存入到session域对中,. 作用：用于控制器方法间的参数共享
//  (比如此类中testSessionAttributes，getSessionAttributes，delSessionAttributes三个方法中session数据共享)
public class AnnoController {
    @RequestMapping("/testRequestParam")
    public String testRequestParm(@RequestParam(name = "name") String username) {
        System.out.println("执行了...");
        System.out.println(username);
        return "success";
    }

    /*
     获取到请求体的内容
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println("执行了...");
        System.out.println(body);
        return "success";
    }

    /*
       PathVariable注解 ，直接放在url:  /anno/testPathVariable/10
        */
    @RequestMapping("/testPathVariable/{sid}")
    public String testPathVariable(@PathVariable(name = "sid") String id) {
        System.out.println("执行了...");
        System.out.println(id);
        return "success";
    }

    /**
     * 获取请求头的值
     * RequestHeader 用于获取请求头中内容，一般不怎么用
     *
     * @param header
     */
    @RequestMapping(value = "/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String header, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("执行了...");
        System.out.println(header);
        // return "success";
        // response.sendRedirect(request.getContextPath()+"/anno/testCookieValue");
        return "redirect:/param.jsp";
    }

    /**
     * CookieValue，用于获取指定cookie的名称的值，用于把指定 cookie 名称的值传入控制器方法参数。
     *
     * @return
     */
    @RequestMapping(value = "/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue) {
        System.out.println("执行了...");
        System.out.println(cookieValue);
        return "success";
    }



    //--------------------------------
    /*
    ModelAttribute注解
1. 作用
 - 出现在方法上：表示当前方法会在控制器方法执行前线执行。*(每个方法执行之前都会先执行ModelAttribute注解在类上的方法！！！！)
 (比如这里出现在showUser上，那么任何访问该类的请求执行之前，先执行showUser方法，然后再执行对应controller，这里同时配置map使用，就相当先把User存入到map中了)
 - 出现在参数上：获取指定的数据给参数赋值。

2. 应用场景
当提交表单数据不是完整的实体数据时，保证没有提交的字段使用数据库原来的数据
     */
//-----------------------------------
    @ModelAttribute
    public void showUser(String uname, Map<String, User> map) {
        System.out.println("showUser执行了...");
        // 这里直接new一个对象，来模拟，因为先于controller执行，那么可以做相应参数处理，比如常用对参数补全
        User user = new User();
        user.setUsername(uname);
        user.setAge(20);
        user.setDate(new Date());
        map.put("abc", user);
    }

    /**
     * ModelAttribute注解
     * 这里注解出现在参数上，意思是从showUser中存入的map中获取abc对应的user。
     * @return
     */
    @RequestMapping(value = "/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("abc") User user) {
        System.out.println("testModelAttribute执行了...");
        System.out.println(user);
        return "success";
    }

    /**
     * 该方法会先执行
     @ModelAttribute
     public User showUser(String uname){
     System.out.println("showUser执行了...");
     // 通过用户查询数据库（模拟）
     User user = new User();
     user.setUname(uname);
     user.setAge(20);
     user.setDate(new Date());
     return user;
     }
     */

    /**
     * SessionAttributes的注解
     * Model 是 spring 提供的一个接口，该接口有一个实现类 ExtendedModelMap
     * 该类继承了 ModelMap，而 ModelMap 就是 LinkedHashMap 子类
     *
     * @return
     */
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Model model) {
        System.out.println("testSessionAttributes...");
        // 底层会存储到request域对象中
        model.addAttribute("msg", "美美");
        // 在跳转之前，会将数据msg保存在session中，因为类上有注解
        return "success";
    }

    /**
     * 获取值
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/getSessionAttributes")
    public String getSessionAttributes(ModelMap modelMap) {
        System.out.println("getSessionAttributes...");
        String msg = (String) modelMap.get("msg");
        System.out.println(msg);
        return "success";
    }

    /**
     * 清除session中内容
     *
     * @param status
     * @return
     */
    @RequestMapping("/delSessionAttributes")
    public String delSessionAttributes(SessionStatus status) {
        System.out.println("getSessionAttributes...");
        status.setComplete();
        return "success";
    }
    /**
     * 默认用户url提交的都是字符串，sparingmvc没有能力对字符串直接转Date类型，需要注册 Converter来实现
     * @param date
     * @return
     */
    @RequestMapping("/testConversion")
    public String testConversion(@RequestParam Date date){
        System.out.println(date);
        return "success";
    }

    @RequestMapping("/testBoolean")
    public String testBoolean(Boolean bool){
        System.out.println(bool);
        return "success";
    }
}
