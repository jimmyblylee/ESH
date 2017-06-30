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

import org.hibernate.annotations.Formula;

import java.util.List;

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
    @Column(name = "FUNC_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
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
    @OrderBy
    private Integer seq;

    /** 菜单在显示时，同时需要显示的图标的css样式. */
    @Column(name = "FUNC_ICON")
    private String icon;

    /** 菜单是否被启用. */
    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    /** 下级菜单. */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<SysFunc> children;

    /** 是否为根节点. */
    @Formula("(CASE WHEN (FUNC_PARENT_ID = 0) THEN 1 ELSE 0 END)")
    private Boolean isRoot;

    // CSOFF: LineLength
    /** 是否为叶子节点. */
    @Formula("(SELECT CASE WHEN (COUNT(1) > 0) THEN 0 ELSE 1 END FROM SYS_FUNC FUNC WHERE FUNC.FUNC_PARENT_ID = FUNC_ID)")
    private Boolean isLeaf;
    // CSON: LineLength

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

    public List<SysFunc> getChildren() {
        return children;
    }

    public void setChildren(List<SysFunc> children) {
        this.children = children;
    }

    public Boolean getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}
