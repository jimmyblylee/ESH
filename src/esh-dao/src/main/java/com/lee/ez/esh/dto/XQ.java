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

/**
 * Description: 需求DTO.<br>
 * Created by Jimmybly Lee on 2017/8/8.
 *
 * @author Jimmybly Lee
 */
public class XQ {

    /** 需求id. */
    private Integer id;
    /** 专业类别. */
    private ZYLB zylb;
    /** 人数. */
    private Integer rs;
    /** 库内现有人数. */
    private Integer knrs;
    /** 现有专家总数. */
    private Integer xyzs;
    /** 是否特定抽取. */
    private boolean isTdcq;
    /** 条件列表. */
    private List<TJ> tjList;
    /** 库外专家列表. */
    private List<ZJ> kwList;

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
     * Get the zylb.
     *
     * @return return the zylb
     */
    public ZYLB getZylb() {
        return zylb;
    }

    /**
     * Set zylb.
     *
     * @param zylb the zylb to set
     */
    public void setZylb(ZYLB zylb) {
        this.zylb = zylb;
    }

    /**
     * Get the zs.
     *
     * @return return the zs
     */
    public Integer getRs() {
        return rs;
    }

    /**
     * Set zs.
     *
     * @param rs the zs to set
     */
    public void setRs(Integer rs) {
        this.rs = rs;
    }

    /**
     * Get the knrs.
     *
     * @return return the knrs
     */
    public Integer getKnrs() {
        return knrs;
    }

    /**
     * Set knrs.
     *
     * @param knrs the knrs to set
     */
    public void setKnrs(Integer knrs) {
        this.knrs = knrs;
    }

    /**
     * Get the xyzs.
     *
     * @return return the xyzs
     */
    public Integer getXyzs() {
        return xyzs;
    }

    /**
     * Set xyzs.
     *
     * @param xyzs the xyzs to set
     */
    public void setXyzs(Integer xyzs) {
        this.xyzs = xyzs;
    }

    /**
     * Get the isTdcq.
     *
     * @return return the isTdcq
     */
    public boolean isIsTdcq() {
        return isTdcq;
    }

    /**
     * Set isTdcq.
     *
     * @param isTdcq the isTdcq to set
     */
    public void setIsTdcq(boolean isTdcq) {
        this.isTdcq = isTdcq;
    }

    /**
     * Get the tjList.
     *
     * @return return the tjList
     */
    public List<TJ> getTjList() {
        return tjList;
    }

    /**
     * Set tjList.
     *
     * @param tjList the tjList to set
     */
    public void setTjList(List<TJ> tjList) {
        this.tjList = tjList;
    }

    /**
     * Get the kwList.
     *
     * @return return the kwList
     */
    public List<ZJ> getKwList() {
        return kwList;
    }

    /**
     * Set kwList.
     *
     * @param kwList the kwList to set
     */
    public void setKwList(List<ZJ> kwList) {
        this.kwList = kwList;
    }
}
