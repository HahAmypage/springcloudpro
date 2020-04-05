package com.davina.user.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_user")
public class User implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.id
     *
     * @mbggenerated
     */
    @Id
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.mobile
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.nickname
     *
     * @mbggenerated
     */
    private String nickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.sex
     *
     * @mbggenerated
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.birthday
     *
     * @mbggenerated
     */
    private Date birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.avatar
     *
     * @mbggenerated
     */
    private String avatar;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.regdate
     *
     * @mbggenerated
     */
    private Date regdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.updatedate
     *
     * @mbggenerated
     */
    private Date updatedate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.lastdate
     *
     * @mbggenerated
     */
    private Date lastdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.online
     *
     * @mbggenerated
     */
    private Long online;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.interest
     *
     * @mbggenerated
     */
    private String interest;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.personality
     *
     * @mbggenerated
     */
    private String personality;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.fanscount
     *
     * @mbggenerated
     */
    private Integer fanscount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.followcount
     *
     * @mbggenerated
     */
    private Integer followcount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.loginname
     *
     * @mbggenerated
     */
    private String loginname;

    public User() {
    }

    public User(String id, String mobile, String password, String nickname, String sex, Date birthday, String avatar, String email, Date regdate, Date updatedate, Date lastdate, Long online, String interest, String personality, Integer fanscount, Integer followcount, String loginname) {
        this.id = id;
        this.mobile = mobile;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.birthday = birthday;
        this.avatar = avatar;
        this.email = email;
        this.regdate = regdate;
        this.updatedate = updatedate;
        this.lastdate = lastdate;
        this.online = online;
        this.interest = interest;
        this.personality = personality;
        this.fanscount = fanscount;
        this.followcount = followcount;
        this.loginname = loginname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.id
     *
     * @return the value of tb_user.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.id
     *
     * @param id the value for tb_user.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.mobile
     *
     * @return the value of tb_user.mobile
     *
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.mobile
     *
     * @param mobile the value for tb_user.mobile
     *
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.password
     *
     * @return the value of tb_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.password
     *
     * @param password the value for tb_user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.nickname
     *
     * @return the value of tb_user.nickname
     *
     * @mbggenerated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.nickname
     *
     * @param nickname the value for tb_user.nickname
     *
     * @mbggenerated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.sex
     *
     * @return the value of tb_user.sex
     *
     * @mbggenerated
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.sex
     *
     * @param sex the value for tb_user.sex
     *
     * @mbggenerated
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.birthday
     *
     * @return the value of tb_user.birthday
     *
     * @mbggenerated
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.birthday
     *
     * @param birthday the value for tb_user.birthday
     *
     * @mbggenerated
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.avatar
     *
     * @return the value of tb_user.avatar
     *
     * @mbggenerated
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.avatar
     *
     * @param avatar the value for tb_user.avatar
     *
     * @mbggenerated
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.email
     *
     * @return the value of tb_user.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.email
     *
     * @param email the value for tb_user.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.regdate
     *
     * @return the value of tb_user.regdate
     *
     * @mbggenerated
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.regdate
     *
     * @param regdate the value for tb_user.regdate
     *
     * @mbggenerated
     */
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.updatedate
     *
     * @return the value of tb_user.updatedate
     *
     * @mbggenerated
     */
    public Date getUpdatedate() {
        return updatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.updatedate
     *
     * @param updatedate the value for tb_user.updatedate
     *
     * @mbggenerated
     */
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.lastdate
     *
     * @return the value of tb_user.lastdate
     *
     * @mbggenerated
     */
    public Date getLastdate() {
        return lastdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.lastdate
     *
     * @param lastdate the value for tb_user.lastdate
     *
     * @mbggenerated
     */
    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.online
     *
     * @return the value of tb_user.online
     *
     * @mbggenerated
     */
    public Long getOnline() {
        return online;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.online
     *
     * @param online the value for tb_user.online
     *
     * @mbggenerated
     */
    public void setOnline(Long online) {
        this.online = online;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.interest
     *
     * @return the value of tb_user.interest
     *
     * @mbggenerated
     */
    public String getInterest() {
        return interest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.interest
     *
     * @param interest the value for tb_user.interest
     *
     * @mbggenerated
     */
    public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.personality
     *
     * @return the value of tb_user.personality
     *
     * @mbggenerated
     */
    public String getPersonality() {
        return personality;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.personality
     *
     * @param personality the value for tb_user.personality
     *
     * @mbggenerated
     */
    public void setPersonality(String personality) {
        this.personality = personality == null ? null : personality.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.fanscount
     *
     * @return the value of tb_user.fanscount
     *
     * @mbggenerated
     */
    public Integer getFanscount() {
        return fanscount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.fanscount
     *
     * @param fanscount the value for tb_user.fanscount
     *
     * @mbggenerated
     */
    public void setFanscount(Integer fanscount) {
        this.fanscount = fanscount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.followcount
     *
     * @return the value of tb_user.followcount
     *
     * @mbggenerated
     */
    public Integer getFollowcount() {
        return followcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.followcount
     *
     * @param followcount the value for tb_user.followcount
     *
     * @mbggenerated
     */
    public void setFollowcount(Integer followcount) {
        this.followcount = followcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.loginname
     *
     * @return the value of tb_user.loginname
     *
     * @mbggenerated
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.loginname
     *
     * @param loginname the value for tb_user.loginname
     *
     * @mbggenerated
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }
}