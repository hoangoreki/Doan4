package com.example.genk;

public class Docbao {
    public String title;
    public  String link;
    public  String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Docbao(String title, String link, String image) {
        this.title = title;
        this.link = link;
        this.image = image;
    }
}
