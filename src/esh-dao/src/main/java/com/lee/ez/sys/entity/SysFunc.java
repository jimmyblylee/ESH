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
public class SysFunc {

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

    /** 是否已经被赋予. */
    @Transient
    private Boolean isAssigned = false;

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
     * Get the parent.
     *
     * @return return the parent
     */
    public SysFunc getParent() {
        return parent;
    }

    /**
     * Set parent.
     *
     * @param parent the parent to set
     */
    public void setParent(SysFunc parent) {
        this.parent = parent;
    }

    /**
     * Get the code.
     *
     * @return return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set code.
     *
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
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
     * Get the seq.
     *
     * @return return the seq
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * Set seq.
     *
     * @param seq the seq to set
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * Get the icon.
     *
     * @return return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Set icon.
     *
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
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
     * Get the children.
     *
     * @return return the children
     */
    public List<SysFunc> getChildren() {
        return children;
    }

    /**
     * Set children.
     *
     * @param children the children to set
     */
    public void setChildren(List<SysFunc> children) {
        this.children = children;
    }

    /**
     * Get the isRoot.
     *
     * @return return the isRoot
     */
    public Boolean getIsRoot() {
        return isRoot;
    }

    /**
     * Set isRoot.
     *
     * @param isRoot the isRoot to set
     */
    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }

    /**
     * Get the isLeaf.
     *
     * @return return the isLeaf
     */
    public Boolean getIsLeaf() {
        return isLeaf;
    }

    /**
     * Set isLeaf.
     *
     * @param isLeaf the isLeaf to set
     */
    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * Get the isAssigned.
     *
     * @return return the isAssigned
     */
    public Boolean getIsAssigned() {
        return isAssigned;
    }

    /**
     * Set isAssigned.
     *
     * @param isAssigned the isAssigned to set
     */
    public void setIsAssigned(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }
}
