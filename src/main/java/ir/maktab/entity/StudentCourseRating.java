package ir.maktab.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@IdClass(StudentCourseRatingId.class)
@Getter
@Setter
public class StudentCourseRating {
    @Id
    @ManyToOne
    Student student;
    @Id
    @OneToOne
    Course course;
    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    double rating;
    String comment;
}
