
package org.afpa.DAL;

import com.google.gson.annotations.SerializedName;

public class Post {

    private Long id;
    private Long userId;
    private String title;
    @SerializedName("body")
    private String text;

    public Post(int id, int userId, String title, String text) {
        this.id =(long) id;
        this.userId =(long) userId;
        this.title = title;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
