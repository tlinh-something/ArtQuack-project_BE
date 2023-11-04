package com.swp.ArtQuack.view.response;

import lombok.Data;

import java.util.List;

@Data
public class ChapterResponse {
    int chapterID;
    String chapterName;
    List<ItemResponse> items;
}
