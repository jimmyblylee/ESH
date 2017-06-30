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
 * Description: 系统用户.<br>
 * Created by Jimmybly Lee on 2017/6/20.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "SYS_USER")
@SuppressWarnings("unused")
public final class SysUser {

    /** Id. */
    @Id
    @Column(name = "USER_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

    /** 用户所属处室. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ORG_ID")
    private SysOrg org;

    /** 用户名称. */
    @Column(name = "USER_NAME")
    private String name;

    /** 用户帐号. */
    @Column(name = "USER_ACCOUNT")
    private String account;

    /** 用户密码. */
    @Column(name = "USER_PWD")
    private String pwd;

    /** 联系电话. */
    @Column(name = "USER_TEL")
    private String tel;

    /** 邮箱. */
    @Column(name = "USER_EMAIL")
    private String mail;

    /** 用户头像. */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "USER_PHOTO", columnDefinition = "CLOB")
    private String photo;

    /** 是否可用. */
    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SysOrg getOrg() {
        return org;
    }

    public void setOrg(SysOrg org) {
        this.org = org;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
