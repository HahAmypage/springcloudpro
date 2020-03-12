package com.davina.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/** 
* description 用户和回复关系实体
* @author chenyingxin
* @date 2020/3/11 17:48
* @Param 
* @return 
**/        
@Entity
@Table(name = "tb_ul")
public class Ul implements Serializable {

  @Id
  private String uid;
  @Id
  private String lid;

  public Ul(String uid, String lid) {
    this.uid = uid;
    this.lid = lid;
  }

  public Ul() {
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }


  public String getLid() {
    return lid;
  }

  public void setLid(String lid) {
    this.lid = lid;
  }

}
