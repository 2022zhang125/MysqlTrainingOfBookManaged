package com.believesun.mysqltrain.pojo;

public class Reader extends User{
    private Integer id;
    private Integer rTypeId;
    private String no;
    private String name;
    private String password;
    private String sex;
    private String dept;
    private String type;
    private Integer nowBorrowNum;
    private Integer historyBorrowNum;

    public Reader() {
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", rTypeId=" + rTypeId +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", dept='" + dept + '\'' +
                ", type='" + type + '\'' +
                ", nowBorrowNum=" + nowBorrowNum +
                ", historyBorrowNum=" + historyBorrowNum +
                '}';
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getrTypeId() {
        return rTypeId;
    }

    public void setrTypeId(Integer rTypeId) {
        this.rTypeId = rTypeId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNowBorrowNum() {
        return nowBorrowNum;
    }

    public void setNowBorrowNum(Integer nowBorrowNum) {
        this.nowBorrowNum = nowBorrowNum;
    }

    public Integer getHistoryBorrowNum() {
        return historyBorrowNum;
    }

    public void setHistoryBorrowNum(Integer historyBorrowNum) {
        this.historyBorrowNum = historyBorrowNum;
    }

    public Reader(Integer id, Integer rTypeId, String no, String name, String password, String sex, String dept, String type, Integer nowBorrowNum, Integer historyBorrowNum) {
        this.id = id;
        this.rTypeId = rTypeId;
        this.no = no;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.dept = dept;
        this.type = type;
        this.nowBorrowNum = nowBorrowNum;
        this.historyBorrowNum = historyBorrowNum;
    }

    public Reader(Integer id, String username, String password) {
        super(id, username, password);
    }
}
