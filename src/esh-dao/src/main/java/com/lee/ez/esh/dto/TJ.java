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
 * Description: 条件DTO.<br>
 * Created by Jimmybly Lee on 2017/8/8.
 *
 * @author Jimmybly Lee
 */
public class TJ {

    /** 条件id. */
    private Integer id;
    /** 条件类型. */
    private String lx;
    /** 类型名称. */
    private String lxmc;
    /** 条件值. */
    private String z;

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
     * Get the lx.
     *
     * @return return the lx
     */
    public String getLx() {
        return lx;
    }

    /**
     * Set lx.
     *
     * @param lx the lx to set
     */
    public void setLx(String lx) {
        this.lx = lx;
    }

    /**
     * Get the z.
     *
     * @return return the z
     */
    public String getZ() {
        return z;
    }

    /**
     * Set z.
     *
     * @param z the z to set
     */
    public void setZ(String z) {
        this.z = z;
    }

    /**
     * Get the lxMC.
     *
     * @return return the lxMC
     */
    public String getLxmc() {
        return lxmc;
    }

    /**
     * Set lxMC.
     *
     * @param lxmc the lxMC to set
     */
    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }
}
