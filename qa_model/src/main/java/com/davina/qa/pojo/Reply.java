package com.davina.qa.pojo;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
* description 回复实体
* @author chenyingxin
* @date 2020/3/11 17:47
* @Param
* @return
**/
@ApiModel
@Table(name = "tb_reply")
public class Reply implements Serializable {

  @Id
  private String id;
  private String problemid;
  private String content;
  private Date createtime;
  private Date updatetime;
  private String userid;
  private String nickname;

  public Reply(String id, String problemid, String content, Date createtime, Date updatetime, String userid, String nickname) {
    this.id = id;
    this.problemid = problemid;
    this.content = content;
    this.createtime = createtime;
    this.updatetime = updatetime;
    this.userid = userid;
    this.nickname = nickname;
  }

  public Reply() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProblemid() {
    return problemid;
  }

  public void setProblemid(String problemid) {
    this.problemid = problemid;
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
}
