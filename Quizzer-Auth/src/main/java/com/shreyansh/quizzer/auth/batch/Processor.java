package com.shreyansh.quizzer.auth.batch;

import com.shreyansh.quizzer.auth.entity.AuditRecord;
import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.repository.UserRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class Processor implements ItemProcessor<UserBatchVo, User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public User process(UserBatchVo userVo) throws Exception {
       User admin = userRepository.findByEmail("admin@gmail.com");
       AuditRecord auditRecord = auditRecord = AuditRecord.builder()
                .createdBy(admin.getFullName())
                .createdById(admin.getUserId())
                .lastModifiedBy(admin.getUserId())
                .lastModifiedById(admin.getUserId())
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        User user = new User();
        user.setFirstName(userVo.getFirstName());
        user.setLastName(userVo.getLastName());
        user.setMobile(userVo.getMobile());
        user.setPassword(userVo.getPassword());
        user.setEmail(userVo.getEmail());
        user.setActive(true);
        user.setIsLocked(false);
        user.setAuditRecord(auditRecord);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("[MM/dd/yyyy][yyyy/MM/dd][MM-dd-yyyy][yyyy-MM-dd]");
        LocalDate date = LocalDate.parse(userVo.getDateOfBirth(), formatter);
        user.setDateOfBirth(LocalDateTime.of(date, LocalTime.of(0,0)));
        return user;
    }
}
