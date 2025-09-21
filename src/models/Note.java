package models;

import java.time.LocalDateTime;

public class Note {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private Tag tag;

    public Note(String title, String content, Tag tag) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.createdDate = LocalDateTime.now();
    }

    public Note(int id, String title, String content, LocalDateTime createdDate, Tag tag) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Tag getTag() {
        return tag;
    }

    public Object getId() {
        return id;
    }
}
