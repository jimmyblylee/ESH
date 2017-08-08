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
 * Description: 专家DTO.<br>
 * Created by Jimmybly Lee on 2017/8/8.
 *
 * @author Jimmybly Lee
 */
public class ZJ {

    /** 专家id .*/
    private Integer id;
    /** 名称. */
    private String name;
    /** 专业水平. */
    private String zysp;
    /** 工作单位. */
    private String gzdw;
    /** 联系方式. */
    private String lxfs;
    /** 专业类别列表. */
    private List<ZYLB> zylbList;
    /** 是否库外. */
    private boolean isKW;

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
     * Get the name.
     *
     * @return return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the zysp.
     *
     * @return return the zysp
     */
    public String getZysp() {
        return zysp;
    }

    /**
     * Set zysp.
     *
     * @param zysp the zysp to set
     */
    public void setZysp(String zysp) {
        this.zysp = zysp;
    }

    /**
     * Get the gzdw.
     *
     * @return return the gzdw
     */
    public String getGzdw() {
        return gzdw;
    }

    /**
     * Set gzdw.
     *
     * @param gzdw the gzdw to set
     */
    public void setGzdw(String gzdw) {
        this.gzdw = gzdw;
    }

    /**
     * Get the lxfs.
     *
     * @return return the lxfs
     */
    public String getLxfs() {
        return lxfs;
    }

    /**
     * Set lxfs.
     *
     * @param lxfs the lxfs to set
     */
    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    /**
     * Get the zylbList.
     *
     * @return return the zylbList
     */
    public List<ZYLB> getZylbList() {
        return zylbList;
    }

    /**
     * Set zylbList.
     *
     * @param zylbList the zylbList to set
     */
    public void setZylbList(List<ZYLB> zylbList) {
        this.zylbList = zylbList;
    }

    /**
     * Get the isKW.
     *
     * @return return the isKW
     */
    public boolean isIsKW() {
        return isKW;
    }

    /**
     * Set isKW.
     *
     * @param isKW the isKW to set
     */
    public void setIsKW(boolean isKW) {
        this.isKW = isKW;
    }
}
