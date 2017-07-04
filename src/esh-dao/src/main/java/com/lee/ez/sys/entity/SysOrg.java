/* ***************************************************************************
 * EZ.JWAF/EZ.JCWAP: Easy series Production.
 * Including JWAF(Java-based Web Application Framework)
 * and JCWAP(Java-based Customized Web Application Platform).
 * Copyright (C) 2016-2017 the original author or authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of MIT License as published by
 * the Free Software Foundation;
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the MIT License for more details.
 *
 * You should have received a copy of the MIT License along
 * with this library; if not, write to the Free Software Foundation.
 * ***************************************************************************/

package com.lee.ez.sys.entity;

import javax.persistence.*;

/**
 * Description: 处室（部门）实体类.<br>
 * Created by Jimmybly Lee on 2017/6/20.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "SYS_ORG")
@SuppressWarnings("unused")
public class SysOrg {

    /** ID.*/
    @Id
    @Column(name = "ORG_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

    /** 处室名称. */
    @Column(name = "ORG_NAME")
    private String name;

    /** 联系电话. */
    @Column(name = "ORG_TEL")
    private String tel;

    /** 是否可用. */
    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    /**
     * Get the id.
     *
     * @return return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the name.
     *
     * @return return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the tel.
     *
     * @return return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * Set tel.
     *
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Get the isEnabled.
     *
     * @return return the isEnabled
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * Set isEnabled.
     *
     * @param isEnabled the isEnabled to set
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
