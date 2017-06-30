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

import com.lee.ez.sys.entity.SysUser;

// CSOFF: MethodCount
// CSOFF: ParameterName
// CSOFF: MethodName
// CSOFF: MemberName
/**
 * Description: 活动专家随机记录..<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_HD_ZJ_SJ")
@SuppressWarnings("unused")
public final class EshHDZJSJ {
    /** 活动ID.**/
    @Id
    @Column(name = "SJ_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    /** 活动.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HD_ID")
    private EshHD hd;

    /** 备注.**/
    @Column(name = "SJ_BZ")
    private String bz;

    /** 操作人.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SJ_CZR")
    private SysUser czr;

    /** 操作时间.**/
    @Column(name = "SJ_CZSJ")
    private String czsj;

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

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public SysUser getCzr() {
        return czr;
    }

    public void setCzr(SysUser czr) {
        this.czr = czr;
    }

    public String getCzsj() {
        return czsj;
    }

    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }
}
