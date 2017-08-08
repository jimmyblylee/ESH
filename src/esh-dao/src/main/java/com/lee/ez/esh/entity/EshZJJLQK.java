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
 * Description: 专家奖励情况表实体类.<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_ZJ_JLQK")
@SuppressWarnings("unused")
public class EshZJJLQK implements EshZJFZ {

    private static final long serialVersionUID = 3764725942215371600L;
    /** 奖励情况ID.**/
    @Id
    @Column(name = "JLQK_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;
    /** 专家.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZJ_ID")
    private EshZJ zj;
    /** 名称（奖励或荣誉）.**/
    @Column(name = "JLQK_MC")
    private String mc;
    /** 单位（授予单位）.**/
    @Column(name = "JLQK_DW")
    private String dw;
    /** 日期（奖励日期）.**/
    @OrderBy
    @Column(name = "JLQK_RQ")
    private String rq;

    /**
     * Get the id.
     *
     * @return return the id
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to set
     */
    @Override
    public void setId(Integer id) {
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
     * Get the mc.
     *
     * @return return the mc
     */
    public String getMc() {
        return mc;
    }

    /**
     * Set mc.
     *
     * @param mc the mc to set
     */
    public void setMc(String mc) {
        this.mc = mc;
    }

    /**
     * Get the dw.
     *
     * @return return the dw
     */
    public String getDw() {
        return dw;
    }

    /**
     * Set dw.
     *
     * @param dw the dw to set
     */
    public void setDw(String dw) {
        this.dw = dw;
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
