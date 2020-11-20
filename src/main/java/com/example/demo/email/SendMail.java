package com.example.demo.email;

/**
 * @ClassName SendmailApplicationTest
 * @Description
 * @PackageName com.example.demo.email.SendMail
 * @Author fate
 * @Date 2020/10/17 13:18
 **/
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class SendMail {

    @Bean
    @Lazy
    public MailService mailService(){
        return new MailServiceImpl();
    }

//    MailService mailService = mailService();

    // 发件人要跟yml配置文件里填写的邮箱一致
//    String mailFrom = "13198251538@163.com";
    String mailFrom = "2018077362@qq.com";
    // 收件人
//    String mailTo = "13198251538@163.com";
    String mailTo = "2018077362@qq.com";
    // 抄送
    String cc = "";



    /**
     * 1、测试普通邮件发送
     */
    public void testSendSimpleMail() {

        String result = "发送邮件成功";
        try {
            mailService().sendSimpleMail(mailFrom, "一个大帅哥", mailTo, cc, "TestMail", "Hello World !");
        } catch (Exception e) {
            result = "发送邮件失败！";
            System.out.println(result);
            System.out.println(e);
        }
        System.out.println(result);
    }

    /**
     * 2、测试带附件的方法
     */
    public void testSendAttachment() {
        File imgFile = new File("src\\main\\java\\com\\ztt\\controller\\f1bdd00e8c.jpg");
        File txtFile = new File("src\\main\\java\\com\\ztt\\controller\\hello.txt");
        List<File> fileList = new ArrayList<>();
        fileList.add(imgFile);
        fileList.add(txtFile);


        String result = "发送邮件成功";
        try {
            mailService().sendMailWithAttachments(mailFrom, "一个大帅哥", mailTo, cc, "TestMail", "Hello World !", fileList);
        } catch (Exception e) {
            result = "发送邮件失败！";
            System.out.println(result);
            System.out.println(e);
        }
        System.out.println(result);
    }


    /**
     * 3、正文带图片
     */
    public void testSendMailWithImage() {
        // 图片路径
        String image01Path = "E:\\personal\\gittest\\学习项目库\\learning_project_library\\SpringBoot_mail\\src\\main\\java\\com\\ztt\\controller\\2ed0c0d5a2.jpg";
        String image02Path = "E:\\personal\\gittest\\学习项目库\\learning_project_library\\SpringBoot_mail\\src\\main\\java\\com\\ztt\\controller\\3bcd0b6866.jpg";
        String[] imageArr = new String[]{image01Path, image02Path};
        String[] imageIdArr = new String[]{"image01", "image02"};

        String result = "发送邮件成功";
        try {
            String contentHtml = "这是图片1:<div><img src='cid:image01'/></div>" +
                    "这是图片2:<div><img src='cid:image02'/></div>";
            mailService().sendMailWithImage(mailFrom, "一个大帅哥", mailTo, cc, "TestMail", contentHtml, imageArr, imageIdArr);
        } catch (Exception e) {
            result = "发送邮件失败！";
            System.out.println(result);
            System.out.println(e);
        }
        System.out.println(result);
    }

    /**
     * 4、使用ThymeLeaf
     */
    // 注入TemplateEngine
    @Bean
    public TemplateEngine templateEngine(){
        return new TemplateEngine();
    }

    public void testSendHtmlMailThymeLeaf() {

        // 注意导入的包是org.thymeleaf.context
        Context context = new Context();
        context.setVariable("username", "比尔盖茨");
        context.setVariable("age", "18");
        String content = templateEngine().process("mailTemplate01.html", context);

        mailService().sendHtmlMailThymeLeaf(mailFrom, "一个大帅哥", mailTo, cc, "TestMail", content);

        System.out.println("邮件发送成功");
    }

}
