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
import java.util.Set;

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
public class EshHDZJSJ implements Serializable {
    private static final long serialVersionUID = -3075221563787064114L;
    /** 活动ID.**/
    @Id
    @Column(name = "SJ_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

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

    /** 随机记录结果. */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sj", cascade = {CascadeType.ALL})
    private Set<EshHDZJSJJG> sjjgList;

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
     * Get the bz.
     *
     * @return return the bz
     */
    public String getBz() {
        return bz;
    }

    /**
     * Set bz.
     *
     * @param bz the bz to set
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * Get the czr.
     *
     * @return return the czr
     */
    public SysUser getCzr() {
        return czr;
    }

    /**
     * Set czr.
     *
     * @param czr the czr to set
     */
    public void setCzr(SysUser czr) {
        this.czr = czr;
    }

    /**
     * Get the czsj.
     *
     * @return return the czsj
     */
    public String getCzsj() {
        return czsj;
    }

    /**
     * Set czsj.
     *
     * @param czsj the czsj to set
     */
    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }

    /**
     * Get the sjjgList.
     *
     * @return return the sjjgList
     */
    public Set<EshHDZJSJJG> getSjjgList() {
        return sjjgList;
    }

    /**
     * Set sjjgList.
     *
     * @param sjjgList the sjjgList to set
     */
    public void setSjjgList(Set<EshHDZJSJJG> sjjgList) {
        this.sjjgList = sjjgList;
    }
}
