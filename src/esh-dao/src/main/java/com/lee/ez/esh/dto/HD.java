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

package com.lee.ez.esh.dto;

import java.util.List;

import com.lee.ez.esh.entity.HDXZ;
import com.lee.ez.esh.entity.HDZT;

/**
 * Description: 活动DTO.<br>
 * Created by Jimmybly Lee on 2017/8/8.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings("CheckStyle")
public class HD {

    /** 活动Id.*/
    private Integer id;
    /** 活动名称.**/
    private String mc;
    /** 开始时间.**/
    private String ks;
    /** 终止时间.**/
    private String zz;
    /** 性质，0：评审类，1：非评审类.**/
    private HDXZ xz;
    /** 组织部门.**/
    private String bm;
    /** 活动地址.**/
    private String dz;
    /** 系统信息：活动状态，0:待上报，1:待受理，2:待完成专家筛选，3:待补充专家，4:待启动，5:等待活动开始，6:活动中，7:待总结，8：活动结束.**/
    private HDZT zt;
    /** 登记人名称.*/
    private String djr;
    /** 审核人名称. */
    private String shr;
    /** 状态备注.*/
    private String bz;
    /** 登记时间.**/
    private String djsj;
    /** 更新时间.**/
    private String gxsj;
    /** 活动描述.*/
    private String ms;
    /** 活动是否启用.*/
    private Boolean qy;
    /** 需求专家总数.*/
    private Integer xq;

    /** 需求列表.*/
    private List<XQ> xqList;
    /** 专家列表. */
    private List<ZJ> zjList;
    /** 随机列表. */
    private List<SJ> sjList;
    /** 备选列表. */
    private List<ZJ> bxList;

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
    public String getDjr() {
        return djr;
    }

    /**
     * Set djr.
     *
     * @param djr the djr to set
     */
    public void setDjr(String djr) {
        this.djr = djr;
    }

    /**
     * Get the shr.
     *
     * @return return the shr
     */
    public String getShr() {
        return shr;
    }

    /**
     * Set shr.
     *
     * @param shr the shr to set
     */
    public void setShr(String shr) {
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
    public List<XQ> getXqList() {
        return xqList;
    }

    /**
     * Set xqList.
     *
     * @param xqList the xqList to set
     */
    public void setXqList(List<XQ> xqList) {
        this.xqList = xqList;
    }

    /**
     * Get the zjList.
     *
     * @return return the zjList
     */
    public List<ZJ> getZjList() {
        return zjList;
    }

    /**
     * Set zjList.
     *
     * @param zjList the zjList to set
     */
    public void setZjList(List<ZJ> zjList) {
        this.zjList = zjList;
    }

    /**
     * Get the sjList.
     *
     * @return return the sjList
     */
    public List<SJ> getSjList() {
        return sjList;
    }

    /**
     * Set sjList.
     *
     * @param sjList the sjList to set
     */
    public void setSjList(List<SJ> sjList) {
        this.sjList = sjList;
    }

    /**
     * Get the bxList.
     *
     * @return return the bxList
     */
    public List<ZJ> getBxList() {
        return bxList;
    }

    /**
     * Set bxList.
     *
     * @param bxList the bxList to set
     */
    public void setBxList(List<ZJ> bxList) {
        this.bxList = bxList;
    }
}
