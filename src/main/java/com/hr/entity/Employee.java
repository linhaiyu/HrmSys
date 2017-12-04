package com.hr.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Employee {
    private Integer id;
    private Dept dept;
    private Job job;
    private String name;
    private String cardId;
    private String phone;
    private String address;
    private String email;
    private String qq;
    private Integer sex;

    /**
     *  使用@ModelAttribute接收参数时
     *  form表单中有日期,Spring不知道该如何转换,
     *  要在实体类的日期属性上加@DateTimeFormat(pattern="yyyy-MM-dd")注解
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private String education;
    private String remark;

    public Employee() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return "Employee [id=" + id + ", dept=" + dept + ", job=" + job
                + ", name=" + name + ", cardId=" + cardId + ", address="
                + address + ", phone=" + phone + ", qq=" + qq + ", email=" + email
                + ", sex=" + sex + ", birthday="
                + birthday + ", education=" + education
                + ", remark=" + remark + "]";
    }

}
