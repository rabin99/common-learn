package com.lin.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

/**
 * @auther : lin
 * @date : 2019/6/26 16:35
 * @desc : 文件上传，跨服务器上传
 */
@Controller
@RequestMapping("/user")
public class FileUploadController {
    /*
    普通文件上传
    这里注意，需要把springmvc中的上传文件解析器给注释掉，否则这里fileItems得到的长度始终为1
     */
    @RequestMapping("/fileupload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        System.out.println("普通文件上传");
        // 使用fileupload组件完成文件上传，指定上传位置
        // 得到项目路径，XXXX/com-springmvc01路径
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        // 判断路径是否存在，不存在创建
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        // 使用fileupload组件的实现上传
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 解析request获取文件
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem item : fileItems) {
            // 判断对象是否是上传文件项
            if(item.isFormField()){
                // 普通表单。。。其他处理
            }else{
                // 说明上传文件项，获取上传文件的名称
                String filename = item.getName();
                // 把文件的名称设置唯一值，uuid
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid+"_"+filename;
                // 完成文件上传
//                item.write(new File(path,filename));
                item.write(new File("C:\\Users\\Public\\Pictures\\Sample Pictures",filename));
                // 删除临时文件
                item.delete();;
            }
        }
        return "success_upload";
    }
    /*
    SpringMVC上传 ,使用该方式，前端input输入框的name必须和参数同名，比如这里变量是upload，input name也应该是upload，否则会报空指针
     */
    @RequestMapping("/fileupload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("springmvc文件上传...");

        // 使用fileupload组件完成文件上传，指定上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        // 判断路径是否存在，不存在创建
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        // 说明上传文件项
        // 获取上传文件的名称
        String filename = upload.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;
        // 完成文件上传
//        upload.transferTo(new File(path,filename));
        upload.transferTo(new File("C:\\Users\\Public\\Pictures\\Sample Pictures",filename));
        return "success_upload";
    }
    /*
   跨服务器文件上传,（直接在tomcat/webapps中创建2个目录，注意！！！
   需要修改tomcat的web.xml 在默认org.apache.catalina.servlets.DefaultServlet，修改readonly为false，而且注意全部小写！！！）
   说明：这里跨域，但是不会有跨域问题，因为跨域是ajax请求的安全限制，这里没ajax
    */
    @RequestMapping("/fileupload3")
    public String fileUpload3(MultipartFile upload) throws Exception {
        System.out.println("跨服务器文件上传...");
     
        // 定义上传文件服务器路径,需要指定服务器上readonly设置为false，允许写入
        String path = "http://localhost:8090/images-server/upload/";
        // 说明上传文件项
        // 获取上传文件的名称
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;

        // 创建客户端对象
        Client client = Client.create();
        // 和图片服务器进行连接
        WebResource webResource = client.resource(path + filename);
        // 上传文件，调用put方法，webResource也有post、delete等
        webResource.put(upload.getBytes());
        return "success_upload";


    }

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File("aaaaa"));
        printWriter.print("sdfsdfsdfsdf");
        printWriter.flush();
    }
}
