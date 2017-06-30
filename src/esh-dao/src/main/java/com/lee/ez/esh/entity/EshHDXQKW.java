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

// CSOFF: MethodCount
// CSOFF: ParameterName
// CSOFF: MethodName
// CSOFF: MemberName
/**
 * Description: 活动需求库外备选专家表实体类.<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_HD_XQ_KW")
@SuppressWarnings("unused")
public final class EshHDXQKW {
    /** 库外ID.**/
    @Id
    @Column(name = "KW_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Long id;
    /** 需求.**/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "XQ_ID")
    private EshHDXQ xq;
    /** 库外专家.**/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ZJ_ID")
    private EshZJ zj;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EshHDXQ getXq() {
        return xq;
    }

    public void setXq(EshHDXQ xq) {
        this.xq = xq;
    }

    public EshZJ getZj() {
        return zj;
    }

    public void setZj(EshZJ zj) {
        this.zj = zj;
    }
}