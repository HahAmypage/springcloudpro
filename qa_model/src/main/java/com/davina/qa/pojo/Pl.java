package com.davina.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
* description 问题和标签关系实体
* @author chenyingxin
* @date 2020/3/11 17:47
* @Param
* @return
**/

@Table(name = "tb_pl")
public class Pl implements Serializable {

  @Id
  private String problemid;
  @Id
  private String labelid;

  public Pl(String problemid, String labelid) {
    this.problemid = problemid;
    this.labelid = labelid;
  }

  public Pl() {
  }

  public String getProblemid() {
    return problemid;
  }

  public void setProblemid(String problemid) {
    this.problemid = problemid;
  }


  public String getLabelid() {
    return labelid;
  }

  public void setLabelid(String labelid) {
    this.labelid = labelid;
  }

}
