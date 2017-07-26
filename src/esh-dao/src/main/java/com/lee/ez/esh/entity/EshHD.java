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
    /** 登记人. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HD_DJR")
    private SysUser djr;
    /** 审核人. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HD_SHR")
    private SysUser shr;
    /** 状态备注.*/
    @Column(name = "HD_ZT_BZ")
    private String bz;
    /** 登记时间.**/
    @Column(name = "HD_DJSJ")
    private String djsj;
    /** 更新时间.**/
    @Column(name = "HD_GXSJ")
    private String gxsj;
    /** 活动描述.*/
    @Column(name = "HD_MS")
    private String ms;
    /** 活动是否启用.*/
    @Column(name = "HD_QY")
    private Boolean qy;
    /** 需求专家总数.*/
    @Column(name = "HD_XQ")
    private Integer xq;

    /** 需求列表. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hd")
    private Set<EshHDXQ> xqList;
    /** 专家列表. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hd")
    private Set<EshHDZJ> zjList;
    /** 随机列录列表. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hd")
    private Set<EshHDZJSJ> sjList;

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
     * Get the djr.
     *
     * @return return the djr
     */
    public SysUser getDjr() {
        return djr;
    }

    /**
     * Set djr.
     *
     * @param djr the djr to set
     */
    public void setDjr(SysUser djr) {
        this.djr = djr;
    }

    /**
     * Get the shr.
     *
     * @return return the shr
     */
    public SysUser getShr() {
        return shr;
    }

    /**
     * Set shr.
     *
     * @param shr the shr to set
     */
    public void setShr(SysUser shr) {
        this.shr = shr;
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

    /**
     * Get the ms.
     *
     * @return return the ms
     */
    public String getMs() {
        return ms;
    }

    /**
     * Set ms.
     *
     * @param ms the ms to set
     */
    public void setMs(String ms) {
        this.ms = ms;
    }

    /**
     * Get the qy.
     *
     * @return return the qy
     */
    public Boolean getQy() {
        return qy;
    }

    /**
     * Set qy.
     *
     * @param qy the qy to set
     */
    public void setQy(Boolean qy) {
        this.qy = qy;
    }

    /**
     * Get the xq.
     *
     * @return return the xq
     */
    public Integer getXq() {
        return xq;
    }

    /**
     * Set xq.
     *
     * @param xq the xq to set
     */
    public void setXq(Integer xq) {
        this.xq = xq;
    }

    /**
     * Get the xqList.
     *
     * @return return the xqList
     */
    public Set<EshHDXQ> getXqList() {
        return xqList;
    }

    /**
     * Set xqList.
     *
     * @param xqList the xqList to set
     */
    public void setXqList(Set<EshHDXQ> xqList) {
        this.xqList = xqList;
    }

    /**
     * Get the zjList.
     *
     * @return return the zjList
     */
    public Set<EshHDZJ> getZjList() {
        return zjList;
    }

    /**
     * Set zjList.
     *
     * @param zjList the zjList to set
     */
    public void setZjList(Set<EshHDZJ> zjList) {
        this.zjList = zjList;
    }

    /**
     * Get the sjList.
     *
     * @return return the sjList
     */
    public Set<EshHDZJSJ> getSjList() {
        return sjList;
    }

    /**
     * Set sjList.
     *
     * @param sjList the sjList to set
     */
    public void setSjList(Set<EshHDZJSJ> sjList) {
        this.sjList = sjList;
    }
}
