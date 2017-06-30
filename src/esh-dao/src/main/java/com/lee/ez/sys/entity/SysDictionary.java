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

/**
 * Description: 字典实体类 for 编辑.<br>
 * Created by Jimmybly Lee on 2017/6/20.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "SYS_DICT")
@SuppressWarnings("unused")
public final class SysDictionary {

    /** Id. */
    @Id
    @Column(name = "DICT_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

    /** 上级字典，往往直接是类型描述，除非是父子结构的. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DICT_PARENT_ID")
    private SysDictionary parent;

    /** 类型. */
    @Column(name = "DICT_NATURE")
    private String nature;

    /** 编码. */
    @Column(name = "DICT_CODE")
    private String code;

    /** 显示值. */
    @Column(name = "DICT_VALUE")
    private String value;

    /** 描述，只有类型描述的项需要填写这个. */
    @Column(name = "DICT_DESC")
    private String desc;

    /** 是否禁用. */
    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    // CSOFF: LineLength
    /** 是否有下级. */
    @Formula("(SELECT CASE WHEN (COUNT(1) > 0) THEN 1 ELSE 0 END FROM SYS_DICT DICT WHERE DICT.DICT_PARENT_ID = DICT_ID)")
    private Boolean hasChildren;
    // CSON: LineLength

    /** 是否是“类型”而非具体字典项. */
    @Formula("(CASE WHEN (DICT_NATURE = 'NATURE_TYPE') THEN 1 ELSE 0 END)")
    private Boolean isNature;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SysDictionary getParent() {
        return parent;
    }

    public void setParent(SysDictionary parent) {
        this.parent = parent;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Boolean getIsNature() {
        return isNature;
    }

    public void setIsNature(Boolean isNature) {
        this.isNature = isNature;
    }
}
