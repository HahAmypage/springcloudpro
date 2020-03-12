package com.davina.qa.pojo;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
* description 问题实体类
* @author chenyingxin
* @date 2020/3/11 17:47
* @Param
* @return
**/
@ApiModel
@Entity
@Table(name = "tb_problem")
public class Problem implements Serializable {

  @Id
  private String id;
  private String title;
  private String content;
  private Date createtime;
  private Date updatetime;
  private String userid;
  private String nickname;
  private long visits;
  private long thumbup;
  private long reply;
  private String solve;
  private String replyname;
  private Date replytime;

  public Problem(String id, String title, String content, Date createtime, Date updatetime, String userid, String nickname, long visits, long thumbup, long reply, String solve, String replyname, Date replytime) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.createtime = createtime;
    this.updatetime = updatetime;
    this.userid = userid;
    this.nickname = nickname;
    this.visits = visits;
    this.thumbup = thumbup;
    this.reply = reply;
    this.solve = solve;
    this.replyname = replyname;
    this.replytime = replytime;
  }

  public Problem() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public Date getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(Date updatetime) {
    this.updatetime = updatetime;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public long getVisits() {
    return visits;
  }

  public void setVisits(long visits) {
    this.visits = visits;
  }

  public long getThumbup() {
    return thumbup;
  }

  public void setThumbup(long thumbup) {
    this.thumbup = thumbup;
  }

  public long getReply() {
    return reply;
  }

  public void setReply(long reply) {
    this.reply = reply;
  }

  public String getSolve() {
    return solve;
  }

  public void setSolve(String solve) {
    this.solve = solve;
  }

  public String getReplyname() {
    return replyname;
  }

  public void setReplyname(String replyname) {
    this.replyname = replyname;
  }

  public Date getReplytime() {
    return replytime;
  }

  public void setReplytime(Date replytime) {
    this.replytime = replytime;
  }
}
