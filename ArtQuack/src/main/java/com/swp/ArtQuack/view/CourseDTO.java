package com.swp.ArtQuack.view;

import com.swp.ArtQuack.Enum.CourseStatus;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class CourseDTO{
        private int courseID;
        private String name;
        private String description;
        private Date uploadDate;
        private int viewer;
        private int rate;
        private boolean status;
        private CourseStatus courseStatus;
        private String avatar;
        private float price;
        private List<ChapterDTO> chaptersList;
}
