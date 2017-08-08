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
 * Description: 专家工作经历表实体类.<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_ZJ_GZJL")
@SuppressWarnings("unused")
public class EshZJGZJL implements EshZJFZ {

    private static final long serialVersionUID = 3691842673260308888L;
    /** 工作经历ID.**/
    @Id
    @Column(name = "GZJL_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;
    /** 专家.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZJ_ID")
    private EshZJ zj;
    /** 工作开始时间.**/
    @OrderBy
    @Column(name = "GZJL_KS")
    private String ks;
    /** 工作终止时间.**/
    @Column(name = "GZJL_ZZ")
    private String zz;
    /** 所在单位.**/
    @Column(name = "GZJL_DW")
    private String dw;
    /** 职务（职级）.**/
    @Column(name = "GZJL_ZW")
    private String zw;

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
     * Get the ks.
     *
     * @return return the ks
     */
    public String getKs() {
        return ks;
    }

    /**
     * Set ks.
     *
     * @param ks the ks to set
     */
    public void setKs(String ks) {
        this.ks = ks;
    }

    /**
     * Get the zz.
     *
     * @return return the zz
     */
    public String getZz() {
        return zz;
    }

    /**
     * Set zz.
     *
     * @param zz the zz to set
     */
    public void setZz(String zz) {
        this.zz = zz;
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
}
