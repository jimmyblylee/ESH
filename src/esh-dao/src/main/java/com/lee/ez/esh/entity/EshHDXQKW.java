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

import java.io.Serializable;

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
public class EshHDXQKW implements Serializable {
    private static final long serialVersionUID = -8938828638926987900L;
    /** 库外ID.**/
    @Id
    @Column(name = "KW_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;
    /** 需求.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "XQ_ID")
    private EshHDXQ xq;
    /** 库外专家.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZJ_ID")
    private EshZJ zj;

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
     * Get the xq.
     *
     * @return return the xq
     */
    public EshHDXQ getXq() {
        return xq;
    }

    /**
     * Set xq.
     *
     * @param xq the xq to set
     */
    public void setXq(EshHDXQ xq) {
        this.xq = xq;
    }

    /**
     * Get the zj.
     *
     * @return return the zj
     */
    public EshZJ getZj() {
        return zj;
    }

    /**
     * Set zj.
     *
     * @param zj the zj to set
     */
    public void setZj(EshZJ zj) {
        this.zj = zj;
    }
}
