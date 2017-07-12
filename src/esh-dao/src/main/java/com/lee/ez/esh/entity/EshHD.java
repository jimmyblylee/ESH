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
 * Description: 活动表实体类.<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_HD")
@SuppressWarnings("unused")
public class EshHD implements Serializable {
    private static final long serialVersionUID = 4747869199465270414L;
    /** 活动ID.**/
    @Id
    @Column(name = "HD_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Long id;
    /** 活动名称.**/
    @Column(name = "HD_MC")
    private String mc;
    /** 开始时间.**/
    @Column(name = "HD_KS")
    private String ks;
    /** 终止时间.**/
    @Column(name = "HD_ZZ")
    private String zz;
    /** 性质，0：评审类，1：非评审类.**/
    @Enumerated
    @Column(name = "HD_XZ")
    private HDXZ xz;
    /** 组织部门.**/
    @Column(name = "HD_BM")
    private String bm;
    /** 活动地址.**/
    @Column(name = "HD_DZ")
    private String dz;
    /** 系统信息：活动状态，0:待上报，1:待受理，2:待完成专家筛选，3:待补充专家，4:待启动，5:等待活动开始，6:活动中，7:待总结，8：活动结束.**/
    @Enumerated
    @Column(name = "HD_ZT")
    private HDZT zt;
    /** 登记时间.**/
    @Column(name = "HD_DJSJ")
    private String djsj;
    /** 更新时间.**/
    @Column(name = "HD_GXSJ")
    private String gxsj;

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
     * Get the xz.
     *
     * @return return the xz
     */
    public HDXZ getXz() {
        return xz;
    }

    /**
     * Set xz.
     *
     * @param xz the xz to set
     */
    public void setXz(HDXZ xz) {
        this.xz = xz;
    }

    /**
     * Get the bm.
     *
     * @return return the bm
     */
    public String getBm() {
        return bm;
    }

    /**
     * Set bm.
     *
     * @param bm the bm to set
     */
    public void setBm(String bm) {
        this.bm = bm;
    }

    /**
     * Get the dz.
     *
     * @return return the dz
     */
    public String getDz() {
        return dz;
    }

    /**
     * Set dz.
     *
     * @param dz the dz to set
     */
    public void setDz(String dz) {
        this.dz = dz;
    }

    /**
     * Get the zt.
     *
     * @return return the zt
     */
    public HDZT getZt() {
        return zt;
    }

    /**
     * Set zt.
     *
     * @param zt the zt to set
     */
    public void setZt(HDZT zt) {
        this.zt = zt;
    }

    /**
     * Get the djsj.
     *
     * @return return the djsj
     */
    public String getDjsj() {
        return djsj;
    }

    /**
     * Set djsj.
     *
     * @param djsj the djsj to set
     */
    public void setDjsj(String djsj) {
        this.djsj = djsj;
    }

    /**
     * Get the gxsj.
     *
     * @return return the gxsj
     */
    public String getGxsj() {
        return gxsj;
    }

    /**
     * Set gxsj.
     *
     * @param gxsj the gxsj to set
     */
    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
    }
}
