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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Description: 系统用户.<br>
 * Created by Jimmybly Lee on 2017/6/20.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "SYS_USER")
@SuppressWarnings("unused")
public class SysUser {

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

    /** 联系电话. */
    @Column(name = "USER_TEL")
    private String tel;

    /** 邮箱. */
    @Column(name = "USER_EMAIL")
    private String mail;

    /** 是否可用. */
    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    /** 账号. */
    @OneToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private SysUserAccount account;

    /** 图片. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private SysUserPhoto photo;

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
     * Get the org.
     *
     * @return return the org
     */
    public SysOrg getOrg() {
        return org;
    }

    /**
     * Set org.
     *
     * @param org the org to set
     */
    public void setOrg(SysOrg org) {
        this.org = org;
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
     * Get the mail.
     *
     * @return return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Set mail.
     *
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
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

    /**
     * Get the account.
     *
     * @return return the account
     */
    public SysUserAccount getAccount() {
        return account;
    }

    /**
     * Set account.
     *
     * @param account the account to set
     */
    public void setAccount(@SuppressWarnings("SameParameterValue") SysUserAccount account) {
        this.account = account;
    }

    /**
     * Get the photo.
     *
     * @return return the photo
     */
    public SysUserPhoto getPhoto() {
        return photo;
    }

    /**
     * Set photo.
     *
     * @param photo the photo to set
     */
    public void setPhoto(@SuppressWarnings("SameParameterValue") SysUserPhoto photo) {
        this.photo = photo;
    }
}
