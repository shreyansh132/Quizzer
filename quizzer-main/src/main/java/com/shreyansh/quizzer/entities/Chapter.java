package com.shreyansh.quizzer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shreyansh.quizzer.utility.UniqueIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chapter {

    @Id
    @GeneratedValue(generator = UniqueIdGenerator.generatorName, strategy = GenerationType.AUTO)
    @GenericGenerator(name = UniqueIdGenerator.generatorName, strategy = "com.shreyansh.quizzer.utility.UniqueIdGenerator")
    @Column(length = 42, unique = true)
    private String chapterId;
    @Column(length = 100, unique = true)
    private String chapterName;
    private String description;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    Course course;

    @OneToMany(mappedBy = "chapter")
    @JsonIgnoreProperties("chapter")
    List<Question> questions= new ArrayList<>();
}