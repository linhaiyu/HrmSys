package com.hr.entity;

import java.util.Date;

public class Train {
    private Integer id;
    private String title;
    private String remark;
    private Date createDate;
    private Employee employee;

    public Train() {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String toString() {
        return "Train [id=" + id + ", title=" + title + ", remark=" + remark
                + ", createDate=" + createDate + ", employee=" + employee + "]";
    }
}
