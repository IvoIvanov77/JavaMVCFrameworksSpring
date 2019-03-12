package org.softuini.exodia.domain.models.view;

public class DocumentListViewModel {
    private String id;

    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length() <= 12){
            this.title = title;
        }else{
            this.title = title.substring(0,12) + "...";
        }

    }
}
