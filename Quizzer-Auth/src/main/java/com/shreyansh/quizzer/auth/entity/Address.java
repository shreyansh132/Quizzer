package com.shreyansh.quizzer.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shreyansh.quizzer.auth.util.UniqueIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(generator = UniqueIdGenerator.generatorName, strategy = GenerationType.AUTO)
    @GenericGenerator(name = UniqueIdGenerator.generatorName, strategy = "com.shreyansh.quizzer.auth.util.UniqueIdGenerator")
    @Column(name = "address_id", length = 42, unique = true)
    private String addressId;

    @Column(name = "house_building", nullable = false)
    private String houseNoBuildingName;

    @Column(name = "rode_area_colony", nullable = false)
    private String roadNameAreaColony;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    @Embedded
    private AuditRecord auditRecord;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("addresses")
    @JoinColumn(
            name = "user_id",referencedColumnName = "userId")
    private User user;
}