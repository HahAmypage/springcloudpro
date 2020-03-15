package com.davina.article.pojo;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@ApiModel
@Entity
@Table(name = "tb_column")
public class Column {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_column.id
     *
     * @mbggenerated
     */
    @Id
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_column.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_column.summary
     *
     * @mbggenerated
     */
    private String summary;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_column.userid
     *
     * @mbggenerated
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_column.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_column.checktime
     *
     * @mbggenerated
     */
    private Date checktime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_column.state
     *
     * @mbggenerated
     */
    private String state;

    public Column() {
    }

    public Column(String id, String name, String summary, String userid, Date createtime, Date checktime, String state) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.userid = userid;
        this.createtime = createtime;
        this.checktime = checktime;
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_column.id
     *
     * @return the value of tb_column.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_column.id
     *
     * @param id the value for tb_column.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_column.name
     *
     * @return the value of tb_column.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_column.name
     *
     * @param name the value for tb_column.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_column.summary
     *
     * @return the value of tb_column.summary
     *
     * @mbggenerated
     */
    public String getSummary() {
        return summary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_column.summary
     *
     * @param summary the value for tb_column.summary
     *
     * @mbggenerated
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_column.userid
     *
     * @return the value of tb_column.userid
     *
     * @mbggenerated
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_column.userid
     *
     * @param userid the value for tb_column.userid
     *
     * @mbggenerated
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_column.createtime
     *
     * @return the value of tb_column.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_column.createtime
     *
     * @param createtime the value for tb_column.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_column.checktime
     *
     * @return the value of tb_column.checktime
     *
     * @mbggenerated
     */
    public Date getChecktime() {
        return checktime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_column.checktime
     *
     * @param checktime the value for tb_column.checktime
     *
     * @mbggenerated
     */
    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_column.state
     *
     * @return the value of tb_column.state
     *
     * @mbggenerated
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_column.state
     *
     * @param state the value for tb_column.state
     *
     * @mbggenerated
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}