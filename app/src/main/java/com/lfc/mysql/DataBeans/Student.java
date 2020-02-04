package com.lfc.mysql.DataBeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by LFC
 * on 2020/2/3.
 */
@Entity
public class Student {
    @Id(autoincrement = true) // id自增长
    private Long strUId;// 学院id
    @Index(unique = true) // 唯一性
    private String stuNo;// 学员编号
    private String stuName;// 学员姓名
    private String stuSex;// 学员性别
    private int stuScore;// 学员成绩

    private String stuads;// 学员地址

@Generated(hash = 1332954201)
    public Student(Long strUId, String stuNo, String stuName, String stuSex,
            int stuScore, String stuads) {
        this.strUId = strUId;
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuScore = stuScore;
        this.stuads = stuads;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getStrUId() {
        return this.strUId;
    }

    public void setStrUId(Long strUId) {
        this.strUId = strUId;
    }

    public String getStuNo() {
        return this.stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return this.stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return this.stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public int getStuScore() {
        return this.stuScore;
    }

    public void setStuScore(int stuScore) {
        this.stuScore = stuScore;
    }

    public String getStuads() {
        return this.stuads;
    }

    public void setStuads(String stuads) {
        this.stuads = stuads;
    }

}
