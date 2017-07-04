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
 * Description: 专家兼职学术组织、行业协会表实体类.<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_ZJ_JZ")
@SuppressWarnings("unused")
public class EshZJJZ implements EshZJFZ {

    /** 兼职ID.**/
    @Id
    @Column(name = "JZ_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Long id;
    /** 专家.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZJ_ID")
    private EshZJ zj;

    /** 组织名称.**/
    @Column(name = "JZ_ZZMC")
    private String zzmc;
    /** 担任职务.**/
    @Column(name = "JZ_ZW")
    private String zw;
    /** 参加日期.**/
    @OrderBy
    @Column(name = "JZ_RQ")
    private String rq;

    /**
     * Get the id.
     *
     * @return return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to set
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the zj.
     *
     * @return return the zj
     */
    @Override
    public EshZJ getZj() {
        return zj;
    }

    /**
     * Set zj.
     *
     * @param zj the zj to set
     */
    @Override
    public void setZj(EshZJ zj) {
        this.zj = zj;
    }

    /**
     * Get the zzmc.
     *
     * @return return the zzmc
     */
    public String getZzmc() {
        return zzmc;
    }

    /**
     * Set zzmc.
     *
     * @param zzmc the zzmc to set
     */
    public void setZzmc(String zzmc) {
        this.zzmc = zzmc;
    }

    /**
     * Get the zw.
     *
     * @return return the zw
     */
    public String getZw() {
        return zw;
    }

    /**
     * Set zw.
     *
     * @param zw the zw to set
     */
    public void setZw(String zw) {
        this.zw = zw;
    }

    /**
     * Get the rq.
     *
     * @return return the rq
     */
    public String getRq() {
        return rq;
    }

    /**
     * Set rq.
     *
     * @param rq the rq to set
     */
    public void setRq(String rq) {
        this.rq = rq;
    }
}
