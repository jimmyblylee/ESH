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

/**
 * Description: 随机结果DTO.<br>
 * Created by Jimmybly Lee on 2017/8/8.
 *
 * @author Jimmybly Lee
 */
public class SJJG {

    /** 随机结果ID. */
    private Integer id;
    /** 专家ID. */
    private ZJ zj;
    /** 是否参加. */
    private Boolean sfcj;
    /** 未参加原因. */
    private String wcjyy;

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
     * Get the zj.
     *
     * @return return the zj
     */
    public ZJ getZj() {
        return zj;
    }

    /**
     * Set zj.
     *
     * @param zj the zj to set
     */
    public void setZj(ZJ zj) {
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
