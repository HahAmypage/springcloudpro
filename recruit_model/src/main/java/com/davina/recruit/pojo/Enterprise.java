package com.davina.recruit.pojo;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* description 企业实体类
* @author chenyingxin
* @date 2020/3/11 17:46
* @Param
* @return
**/
@Entity
@Table(name = "tb_enterprise")
@ApiModel
public class Enterprise {

  @Id
  private String id;
  private String name;
  private String summary;
  private String address;
  private String labels;
  private String coordinate;
  private String ishot;
  private String logo;
  private long jobcount;
  private String url;

  public Enterprise() {
  }

  public Enterprise(String id, String name, String summary, String address, String labels, String coordinate, String ishot, String logo, long jobcount, String url) {
    this.id = id;
    this.name = name;
    this.summary = summary;
    this.address = address;
    this.labels = labels;
    this.coordinate = coordinate;
    this.ishot = ishot;
    this.logo = logo;
    this.jobcount = jobcount;
    this.url = url;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getLabels() {
    return labels;
  }

  public void setLabels(String labels) {
    this.labels = labels;
  }


  public String getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(String coordinate) {
    this.coordinate = coordinate;
  }


  public String getIshot() {
    return ishot;
  }

  public void setIshot(String ishot) {
    this.ishot = ishot;
  }


  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }


  public long getJobcount() {
    return jobcount;
  }

  public void setJobcount(long jobcount) {
    this.jobcount = jobcount;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
