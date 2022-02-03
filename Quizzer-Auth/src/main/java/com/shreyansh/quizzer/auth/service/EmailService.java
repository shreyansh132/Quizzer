package com.shreyansh.quizzer.auth.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shreyansh.quizzer.auth.model.EmailRequest;
import com.shreyansh.quizzer.auth.model.ResponseHandler;
import com.shreyansh.quizzer.auth.util.OtpGenerator;
import freemarker.core.ParseException;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration;

    public void initiateEmail(EmailRequest emailRequest) throws Exception {
        Map<String, Object> mailContent = new HashMap<>();
        mailContent.put("sender", emailRequest.getSenderName());
        mailContent.put("content", emailRequest.getEmailContent());
        mailContent.put("receiverName", emailRequest.getRecipientName());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED
                , StandardCharsets.UTF_8.name());
        helper.setTo(emailRequest.getRecipientsTo().split("[,;]+"));
        helper.setBcc(emailRequest.getRecipientsBcc().split("[,;]+"));
        helper.setCc(emailRequest.getRecipientsCc().split("[,;]+"));
        helper.setPriority(emailRequest.getEmailPriority());
        helper.setSubject(emailRequest.getEmailSubject());
        Template template = configuration.getTemplate("mail.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailContent);
        helper.setText(html, true);
        javaMailSender.send(mimeMessage);
    }

    public ResponseEntity<Object> initiateEmailWithAttachment(String emailModel, List<MultipartFile> attachments) {
        EmailRequest emailRequest = getEmailRequestObject(emailModel);
        System.out.println(emailRequest);
        return prepareAndSendEmail(emailRequest, attachments);
    }

    public ResponseEntity<Object> prepareAndSendEmail(EmailRequest emailRequest, List<MultipartFile> attachments) {
        Map<String, Object> mailContent = new HashMap<>();
        mailContent.put("senderName", emailRequest.getSenderName());
        mailContent.put("emailContent", emailRequest.getEmailContent());
        mailContent.put("recipientName", emailRequest.getRecipientName());

        ResponseHandler responseHandler = new ResponseHandler();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED
                    , StandardCharsets.UTF_8.name());

            if (emailRequest.getRecipientsTo() != null && "" != emailRequest.getRecipientsTo())
                helper.setTo(emailRequest.getRecipientsTo().split("[,;]+"));

            if (emailRequest.getRecipientsBcc() != null && "" != emailRequest.getRecipientsBcc())
                helper.setBcc(emailRequest.getRecipientsBcc().split("[,;]+"));

            if (emailRequest.getRecipientsCc() != null && "" != emailRequest.getRecipientsCc())
                helper.setCc(emailRequest.getRecipientsCc().split("[,;]+"));

            if (attachments != null) {
                for (int i = 0; i < attachments.size(); i++) {
                    helper.addAttachment(attachments.get(i).getOriginalFilename(), attachments.get(i));
                }
            }

            if (emailRequest.getEmailPriority() != null)
                helper.setPriority(emailRequest.getEmailPriority());

            if (emailRequest.getEmailSubject() != null && "" != emailRequest.getEmailSubject())
                helper.setSubject(emailRequest.getEmailSubject());

            Template template = configuration.getTemplate("registration_confirmation.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailContent);
            helper.setText(html, true);

        } catch (MessagingException e) {
            e.printStackTrace();
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        } catch (TemplateException e) {
            e.printStackTrace();
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        } catch (TemplateNotFoundException e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        } catch (ParseException e) {
            e.printStackTrace();
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        } catch (MalformedTemplateNameException e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        } catch (IOException e) {
            e.printStackTrace();
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

        javaMailSender.send(mimeMessage);
        return responseHandler.generateResponse("Email Sent Successfully", HttpStatus.OK, null);
    }

    public EmailRequest getEmailRequestObject(String emailModel) {
        EmailRequest emailRequest = new EmailRequest();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            emailRequest = objectMapper.readValue(emailModel, EmailRequest.class);
        } catch (IOException err) {
            System.out.printf("Error", err.toString());
        }
        return emailRequest;
    }

    public String sendOTPEmail(String emailTo, String otp) throws Exception {

        Map<String, Object> mailContent = new HashMap<>();
        mailContent.put("otp", otp);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED
                    , StandardCharsets.UTF_8.name());

            helper.setTo(emailTo);
            helper.setSubject("One time password to Login to Quizzer");
            Template template = configuration.getTemplate("otp_layout.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailContent);
            helper.setText(html, true);
            javaMailSender.send(mimeMessage);
            return "We have sent you One time password on your registered Email";
    }
}
