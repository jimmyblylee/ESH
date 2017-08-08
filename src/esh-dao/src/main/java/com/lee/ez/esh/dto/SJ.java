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
 * Description: 随机记录.<br>
 * Created by Jimmybly Lee on 2017/8/8.
 *
 * @author Jimmybly Lee
 */
public class SJ {

    /** 随机记录id. */
    private Integer id;
    /** 备注. */
    private String bz;
    /** 操作人名称. */
    private String czr;
    /** 操作时间. */
    private String czsj;
    /** 随机记录对应的结果. */
    private List<SJJG> sjjgList;

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
    public String getCzr() {
        return czr;
    }

    /**
     * Set czr.
     *
     * @param czr the czr to set
     */
    public void setCzr(String czr) {
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
    public List<SJJG> getSjjgList() {
        return sjjgList;
    }

    /**
     * Set sjjgList.
     *
     * @param sjjgList the sjjgList to set
     */
    public void setSjjgList(List<SJJG> sjjgList) {
        this.sjjgList = sjjgList;
    }
}
