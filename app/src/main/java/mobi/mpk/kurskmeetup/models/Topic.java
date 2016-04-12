package mobi.mpk.kurskmeetup.models;

import java.util.List;

public class Topic {
    private String auname;
    private String title;
    private List<TopicContent> content;

    public String getAuname() {
        return auname;
    }

    public void setAuname(String auname) {
        this.auname = auname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TopicContent> getContent() {
        return content;
    }

    public void setContent(List<TopicContent> content) {
        this.content = content;
    }

}
