package com.projects2024.flightreservation2.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    @Value("${email.set.body}")
    private String EMAIL_BODY;
    @Value("${email.set.subject}")
    private String EMAIL_SUBJECT;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
    @Autowired
    private JavaMailSender sender;

    public EmailUtil() {
    }

    public void sendItinerary(String toAddress, String filePath) {
        LOGGER.info("Inside sendItinerary()");
        MimeMessage message = this.sender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(this.EMAIL_SUBJECT);
            messageHelper.setText(this.EMAIL_BODY);
            messageHelper.addAttachment("Itinearary", new File(filePath));
            this.sender.send(message);
        } catch (MessagingException var5) {
            LOGGER.error("Exception inside sendItinerary" + var5);
        }

    }
}
