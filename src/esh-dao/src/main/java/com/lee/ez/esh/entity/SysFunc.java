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

package com.lee.ez.esh.entity;

import javax.persistence.*;

/**
 * Description: 功能菜单实体类.<br>
 * Created by Jimmybly Lee on 2017/6/20.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "SYS_FUNC")
@SuppressWarnings("unused")
public final class SysFunc {

    /** Id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FUNC_ID")
    private Integer id;

    /** 上级菜单. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNC_PARENT_ID")
    private SysFunc parent;

    /** 菜单编码，前台需要解析. */
    @Column(name = "FUNC_CODE")
    private String code;

    /** 菜单需要显示的名称. */
    @Column(name = "FUNC_NAME")
    private String name;

    /** 菜单在同级排序的序号. */
    @Column(name = "FUNC_SEQ")
    private Integer seq;

    /** 菜单在显示时，同时需要显示的图标的css样式. */
    @Column(name = "FUNC_ICON")
    private String icon;

    /** 菜单是否被启用. */
    @Column(name = "FUNC_IS_ENABLED")
    private Boolean isEnabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SysFunc getParent() {
        return parent;
    }

    public void setParent(SysFunc parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }
}
