package com.alphaz.application.mail.service;


import com.alphaz.core.shared.service.DomainService;
import com.alphaz.infrastructure.domain.model.ResponseModel;

/**
 * @Author: Authur
 * @Date: 2018/1/8 13:19
 */
public interface MailDomainService extends DomainService {

    ResponseModel sendmail(String email, String subject);

    ResponseModel sendHtmlMail(String email, String subject, String content);

    ResponseModel sendAttachmentsMail(String email, String subject, String content, String filePath);
}
