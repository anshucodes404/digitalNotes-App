package models;

public class Tag {
    private Integer tag_id;
    private String tag_name;

    public Tag(String tag_name) {
        this.tag_name = tag_name;
    }

    public Tag(Integer tag_id, String tag_name) {
        this.tag_id = tag_id;
        this.tag_name = tag_name;
    }

    public String getName() {
        return tag_name;
    }

    public Integer getId() {
        return tag_id;
    }

    public void setId(Integer tag_id) {
        this.tag_id = tag_id;
    }

    @Override
    public String toString() {
        return tag_name;
    }
}
