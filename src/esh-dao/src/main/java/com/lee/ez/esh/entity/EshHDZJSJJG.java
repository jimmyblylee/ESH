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
 * Description: 活动专家随机结果表实体类.<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_HD_ZJ_SJ_JG")
@SuppressWarnings("unused")
public class EshHDZJSJJG {
    /** 随机结果ID.**/
    @Id
    @Column(name = "SJJG_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    /** 专家.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZJ_ID")
    private EshZJ zj;

    /** 是否参加.**/
    @Column(name = "SJJG_SFCJ")
    private Boolean sfcj;
    /** 未参加原因.**/
    @Column(name = "SJJG_WCJYY")
    private String wcjyy;

    /**
     * Get the id.
     *
     * @return return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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

    /**
     * Get the sfcj.
     *
     * @return return the sfcj
     */
    public Boolean getSfcj() {
        return sfcj;
    }

    /**
     * Set sfcj.
     *
     * @param sfcj the sfcj to set
     */
    public void setSfcj(Boolean sfcj) {
        this.sfcj = sfcj;
    }

    /**
     * Get the wcjyy.
     *
     * @return return the wcjyy
     */
    public String getWcjyy() {
        return wcjyy;
    }

    /**
     * Set wcjyy.
     *
     * @param wcjyy the wcjyy to set
     */
    public void setWcjyy(String wcjyy) {
        this.wcjyy = wcjyy;
    }
}
