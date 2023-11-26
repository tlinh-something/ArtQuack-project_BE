package com.swp.ArtQuack.view;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class ChapterDTO {
    private int chapterID;
    private String chapterName;
    private boolean status;
    private boolean seevideo;
    private List<ItemDTO> itemsList;
}
