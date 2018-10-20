package com.junxi.baiweiserver.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MailSend {
    private String name;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Override
    public String toString() {
        return "MailSend{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
