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

package com.lee.ez.sys.dto;

import java.io.Serializable;

import com.lee.jwaf.token.Dict;
import com.lee.jwaf.token.Org;
import com.lee.jwaf.token.User;

/**
 * Description: 用户数据传输类型.<br>
 * Created by Jimmybly Lee on 2017/6/28.
 *
 * @author Jimmybly Lee
 */
public final class EshUser implements User, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 8097971841099343594L;
    // CSOFF: JavadocVariable
    private Integer id;
    private Org org;
    private String name;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Org getOrg() {
        return org;
    }

    @Override
    public void setOrg(Org org) {
        this.org = org;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAccount() {
        return null;
    }

    @Override
    public void setAccount(String account) {

    }

    @Override
    public Dict getType() {
        return null;
    }

    @Override
    public void setType(Dict type) {

    }
}
