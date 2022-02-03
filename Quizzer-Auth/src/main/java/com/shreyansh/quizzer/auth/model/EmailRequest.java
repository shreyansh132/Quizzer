package com.shreyansh.quizzer.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailRequest {
    String senderName;
    String recipientName;
    String recipientsCc;
    String recipientsBcc;
    String recipientsTo;
    String emailSubject;
    String emailContent;
    Integer emailPriority;
}
