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
 * Description: 专家教育背景表实体类.<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_ZJ_JYBJ")
@SuppressWarnings("unused")
public class EshZJJYBJ implements EshZJFZ {

    /** 教育背景ID.**/
    @Id
    @Column(name = "JYBJ_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Long id;
    /** 专家.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZJ_ID")
    private EshZJ zj;

    /** 开始时间.**/
    @OrderBy
    @Column(name = "JYBJ_KS")
    private String ks;
    /** 终止时间.**/
    @Column(name = "JYBJ_ZZ")
    private String zz;
    /** 毕业院校.**/
    @Column(name = "JYBJ_YX")
    private String yx;
    /** 所学专业.**/
    @Column(name = "JYBJ_ZY")
    private String zy;

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
     * Get the yx.
     *
     * @return return the yx
     */
    public String getYx() {
        return yx;
    }

    /**
     * Set yx.
     *
     * @param yx the yx to set
     */
    public void setYx(String yx) {
        this.yx = yx;
    }

    /**
     * Get the zy.
     *
     * @return return the zy
     */
    public String getZy() {
        return zy;
    }

    /**
     * Set zy.
     *
     * @param zy the zy to set
     */
    public void setZy(String zy) {
        this.zy = zy;
    }
}
