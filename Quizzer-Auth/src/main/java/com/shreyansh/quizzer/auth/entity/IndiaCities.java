package com.shreyansh.quizzer.auth.entity;

import com.shreyansh.quizzer.auth.util.UniqueIdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class IndiaCities {

        @Id
        @GeneratedValue(generator = UniqueIdGenerator.generatorName, strategy = GenerationType.AUTO)
        @GenericGenerator(name = UniqueIdGenerator.generatorName, strategy = "com.shreyansh.quizzer.auth.util.UniqueIdGenerator")
        @Column(name = "recId", length = 42, unique = true)
        private String recId;
        private String postOfficeName;
        private String pinCode;
        private String city;
        private String district;
        private String state;

        @Embedded
        private AuditRecord auditRecord;
}

