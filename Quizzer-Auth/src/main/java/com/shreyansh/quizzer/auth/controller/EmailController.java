package com.shreyansh.quizzer.auth.controller;

import com.shreyansh.quizzer.auth.model.EmailRequest;
import com.shreyansh.quizzer.auth.model.ResponseHandler;
import com.shreyansh.quizzer.auth.service.EmailService;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/send", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })

    public ResponseEntity<?> initiateEmail(
                       @RequestPart("emailRequest") String emailRequest,
                       @RequestPart(value = "attachments",required = false) List<MultipartFile> attachments) {
       return emailService.initiateEmailWithAttachment(emailRequest, attachments);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
        ResponseHandler responseHandler = new ResponseHandler();
        try {
            emailService.initiateEmail(emailRequest);
            return responseHandler.generateResponse("Email Sent Successfully", HttpStatus.OK, null);
        } catch (MessagingException e) {
            e.printStackTrace();
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.OK, null);
        } catch (TemplateException e) {
            e.printStackTrace();
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.OK, null);
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.OK, null);
        } catch (ParseException e) {
            e.printStackTrace();
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.OK, null);
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.OK, null);
        } catch (IOException e) {
            e.printStackTrace();
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.OK, null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.OK, null);
        }
    }
}
