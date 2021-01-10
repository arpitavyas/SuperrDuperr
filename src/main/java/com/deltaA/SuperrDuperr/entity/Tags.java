package com.deltaA.SuperrDuperr.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TBL_TAGS")
public class Tags {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="tag_id")
    private int tagId;

    @Column(name="tag_name")
    private String tagName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tags")
    private Set<ToDoListItem> tags = new HashSet<>();

    public Tags() {
    }

    public Tags(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}
