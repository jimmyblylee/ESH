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

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import com.lee.ez.sys.entity.SysDict;

// CSOFF: MethodCount
// CSOFF: ParameterName
// CSOFF: MethodName
// CSOFF: MemberName
/**
 * Description: 活动需求表实体类.<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_HD_XQ")
@SuppressWarnings("unused")
public final class EshHDXQ {
    /** 活动需求ID.**/
    @Id
    @Column(name = "XQ_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    /** 活动.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HD_ID")
    private EshHD hd;
    // CSOFF: LineLength
    /** 专业类别.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "HD_XQ_ZYLB", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'ZYLB'")) })
    private SysDict zylb;
    // CSON: LineLength
    /** 需求人数.**/
    @Column(name = "XQ_RS")
    private Integer rs;
    /** 是否特定抽取.**/
    @Column(name = "XQ_TDCQ")
    private Boolean tdcq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EshHD getHd() {
        return hd;
    }

    public void setHd(EshHD hd) {
        this.hd = hd;
    }

    public SysDict getZylb() {
        return zylb;
    }

    public void setZylb(SysDict zylb) {
        this.zylb = zylb;
    }

    public Integer getRs() {
        return rs;
    }

    public void setRs(Integer rs) {
        this.rs = rs;
    }

    public Boolean getTdcq() {
        return tdcq;
    }

    public void setTdcq(Boolean tdcq) {
        this.tdcq = tdcq;
    }
}
