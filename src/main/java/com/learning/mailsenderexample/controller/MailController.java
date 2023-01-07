package com.learning.mailsenderexample.controller;

import com.learning.mailsenderexample.model.MailMessage;
import com.learning.mailsenderexample.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping
    public void sendMail(@RequestBody SimpleMailMessage simpleMailMessage) {
        mailService.sendMail(simpleMailMessage);
    }

    @PostMapping(value = "/attachment")
    public void sendAttachedMail(@RequestBody MailMessage mailMessage) {
        mailService.sendAttachedMail(mailMessage);
    }

}
