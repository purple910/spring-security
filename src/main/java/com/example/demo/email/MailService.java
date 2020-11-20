package com.example.demo.email;

/**
 * @ClassName MailService
 * @Description 发送简单文本邮件、带附件邮件、带图片的邮件、使用Themeleaf构建邮件模板的
 * @PackageName com.example.demo.email.MailService
 * @Author fate
 * @Date 2020/10/17 13:15
 **/
import java.io.File;
import java.util.List;

public interface MailService {


    void sendSimpleMail(String mailFrom, String mailFromNick, String mailTo, String cc, String subject, String content);

    void sendMailWithAttachments(String mailFrom, String mailFromNick, String mailTo, String cc, String subject, String content,
                                 List<File> files);

    void sendMailWithImage(String mailFrom, String mailFromNick, String mailTo, String cc, String subject, String content,
                           String[] imagePaths, String[] imageId);

    void sendHtmlMailThymeLeaf(String mailFrom, String mailFromNick, String mailTo, String cc, String subject, String content);


}