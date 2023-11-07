package com.swp.ArtQuack.view.response;

import lombok.Data;

import java.util.List;

@Data
public class ChapterResponse {
    int chapterID;
    String chapterName;
    List<ItemResponse> items;
	boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getChapterID() {
		return chapterID;
	}
	public void setChapterID(int chapterID) {
		this.chapterID = chapterID;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public List<ItemResponse> getItems() {
		return items;
	}
	public void setItems(List<ItemResponse> items) {
		this.items = items;
	}
    
    
}
