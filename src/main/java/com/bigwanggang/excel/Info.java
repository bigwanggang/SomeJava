package com.bigwanggang.excel;


public class Info {
    private String name;
    private String age;
    private String height;
    private String phone;
    private String sex;
    private String location;
    private String job;
    private String hometown;
    private String education;
    private String major;
    private String message;

    public Info(String name, String age, String height, String phone, String sex, String location,
                String job, String hometown, String education, String major, String message) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.phone = phone;
        this.sex = sex;
        this.location = location;
        this.job = job;
        this.hometown = hometown;
        this.education = education;
        this.major = major;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public String getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }

    public String getLocation() {
        return location;
    }

    public String getJob() {
        return job;
    }

    public String getHometown() {
        return hometown;
    }

    public String getEducation() {
        return education;
    }

    public String getMajor() {
        return major;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Info{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", height='" + height + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", location='" + location + '\'' +
                ", job='" + job + '\'' +
                ", hometown='" + hometown + '\'' +
                ", education='" + education + '\'' +
                ", major='" + major + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
