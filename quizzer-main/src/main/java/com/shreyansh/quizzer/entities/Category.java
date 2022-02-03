package com.shreyansh.quizzer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shreyansh.quizzer.utility.UniqueIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Shreyansh Jain
 * Quiz Category: Outer Most topic EG: Aptitude/Reasoning
 * **/

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"categoryId","categoryName"})})
public class Category {

    @Id
    @GeneratedValue(generator = UniqueIdGenerator.generatorName, strategy = GenerationType.AUTO)
    @GenericGenerator(name = UniqueIdGenerator.generatorName, strategy = "com.shreyansh.quizzer.utility.UniqueIdGenerator")
    @Column(length = 42, unique = true)
    private String categoryId;
    @Column(length = 100, unique = true)
    private String categoryName;
    private String description;
    private String imageUrl;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    List<Course> courses = new ArrayList<>();

}
