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
 * Description: 活动专家表实体类.<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_HD_ZJ")
@SuppressWarnings("unused")
public class EshHDZJ implements Serializable {
    private static final long serialVersionUID = -7570424340449338466L;
    /** 活动ID.**/
    @Id
    @Column(name = "HDZJ_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

    /** 活动.**/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HD_ID")
    private EshHD hd;

    /** 专家.**/
    @ManyToOne(fetch = FetchType.EAGER)
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
     * Get the hd.
     *
     * @return return the hd
     */
    public EshHD getHd() {
        return hd;
    }

    /**
     * Set hd.
     *
     * @param hd the hd to set
     */
    public void setHd(EshHD hd) {
        this.hd = hd;
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
