package com.learning.mailsenderexample.service;


import com.learning.mailsenderexample.mapper.MailMapper;
import com.learning.mailsenderexample.model.MailMessage;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;

    private final MailMapper mailMapper;

    public void sendMail(SimpleMailMessage simpleMailMessage) {
        javaMailSender.send(simpleMailMessage);
    }

    public void sendAttachedMail(MailMessage mailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mailMapper.mapMailMessageToMimeHelper(mailMessage, mimeMessageHelper);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Unable to send message!");
        }
    }

}
