package com.shreyansh.quizzer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shreyansh.quizzer.utility.UniqueIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter @Setter @Builder
public class Course {
    @Id
    @GeneratedValue(generator = UniqueIdGenerator.generatorName, strategy = GenerationType.AUTO)
    @GenericGenerator(name = UniqueIdGenerator.generatorName, strategy = "com.shreyansh.quizzer.utility.UniqueIdGenerator")
    @Column(length = 42, unique = true)
    private String courseId;
    @Column(length = 100, unique = true)
    private String courseName;
    private String description;
    private String imageUrl;

    @OneToMany(mappedBy = "course")
    @JsonIgnoreProperties("course")
    private List<Chapter> chapters = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "categoryId")
    @JsonIgnoreProperties("courses")
    private Category category;
}
