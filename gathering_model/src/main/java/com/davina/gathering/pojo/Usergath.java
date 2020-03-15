package com.davina.gathering.pojo;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_usergath")
public class Usergath extends UsergathKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_usergath.exetime
     *
     * @mbggenerated
     */
    private Date exetime;

    public Usergath(Date exetime) {
        this.exetime = exetime;
    }

    public Usergath() {
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_usergath.exetime
     *
     * @return the value of tb_usergath.exetime
     *
     * @mbggenerated
     */

    public Date getExetime() {
        return exetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_usergath.exetime
     *
     * @param exetime the value for tb_usergath.exetime
     *
     * @mbggenerated
     */
    public void setExetime(Date exetime) {
        this.exetime = exetime;
    }
}