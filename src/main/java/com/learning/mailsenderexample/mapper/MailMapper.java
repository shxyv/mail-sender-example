package com.learning.mailsenderexample.mapper;

import com.learning.mailsenderexample.model.MailMessage;
import java.util.Base64;
import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.mail.javamail.MimeMessageHelper;

@Mapper(componentModel = "spring")
@Slf4j
public abstract class MailMapper {

    @Mapping(target = "to", source = "mailMessage.to")
    @Mapping(target = "from", source = "mailMessage.from")
    @Mapping(target = "text", source = "mailMessage.text")
    @Mapping(target = "subject", source = "mailMessage.subject")
    public abstract void mapMailMessageToMimeHelper(MailMessage mailMessage,
                                                    @MappingTarget MimeMessageHelper mimeMessageHelper) throws MessagingException;

    @AfterMapping
    void mapAttachment(MailMessage mailMessage, @MappingTarget MimeMessageHelper mimeMessageHelper) {
        byte[] image = Base64.getDecoder().decode(mailMessage.getAttachment());
        ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(image, "application/octet-stream");
        try {
            mimeMessageHelper.addAttachment("example", byteArrayDataSource);
        } catch (MessagingException exception) {
            log.error("Unable to send message!");
        }

    }
}
