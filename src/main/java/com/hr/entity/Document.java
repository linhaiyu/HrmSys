package com.hr.entity;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class Document {
    private Integer id;
    private String title;
    private String fileName;
    private MultipartFile file;
    private Date createDate;
    private String remark;
    private User user;

    public Document() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return "Document [id=" + id + ", title=" + title + ", fileName="
                + fileName + ", file=" + file + ", remark=" + remark
                + ", createDate=" + createDate + ", user=" + user + "]";
    }
}
