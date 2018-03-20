package com.alphaz.application.mail.service.impl;

import com.alphaz.core.localization.LocalizationService;
import com.alphaz.application.mail.service.MailDomainService;
import com.alphaz.core.shared.service.DomainServiceImpl;
import com.alphaz.infrastructure.domain.model.ErrorInfo;
import com.alphaz.infrastructure.domain.model.ResponseModel;
import com.alphaz.infrastructure.util.string.CaptchaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: Authur
 * @Date: 2018/1/8 13:20
 */
@Service
public class MailDomainServiceImpl extends DomainServiceImpl implements MailDomainService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private LocalizationService localizationService;

    @Value("${spring.mail.username}")
    private String from;

    public MailDomainServiceImpl() {
    }

    @Override
    public ResponseModel sendmail(String email, String subject) {
        String str = CaptchaUtil.getRandomString(4);
        String content = "您的验证码：" + str + "，有效期为1分钟，请尽快验证。";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
            return new ResponseModel();
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
            return new ResponseModel(new ErrorInfo(localizationService.getMessage("sendfail")));
        }
    }

    @Override
    public ResponseModel sendHtmlMail(String email, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            logger.info("html邮件发送成功");
            return new ResponseModel();
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
            return new ResponseModel(new ErrorInfo(localizationService.getMessage("sendfail")));
        }
    }

    @Override
    public ResponseModel sendAttachmentsMail(String email, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            /*添加多个附件可以使用多条 helper.addAttachment(fileName, file)*/

            mailSender.send(message);
            logger.info("带附件的邮件已经发送。");
            return new ResponseModel();
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常！", e);
            return new ResponseModel(new ErrorInfo(localizationService.getMessage("sendfail")));
        }
    }

}
