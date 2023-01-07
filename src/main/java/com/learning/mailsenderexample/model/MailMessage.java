package com.learning.mailsenderexample.model;

import java.util.List;
import lombok.Data;

@Data
public class MailMessage {
    private String from;
    private List<String> to;
    private String text;
    private String subject;
    private String attachment;
}
