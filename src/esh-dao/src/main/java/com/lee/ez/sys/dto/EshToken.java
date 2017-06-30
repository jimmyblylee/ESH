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
import java.util.LinkedList;
import java.util.List;

import com.lee.jwaf.token.*;

/**
 * Description: 用户令牌.<br>
 * Created by Jimmybly Lee on 2017/6/28.
 *
 * @author Jimmybly Lee
 */
public final class EshToken implements Token, Serializable {

    /** SerialVersionUID. */
    private static final long serialVersionUID = -7411492595784741520L;
    // CSOFF: JavadocVariable
    private User user = new EshUser();
    private Org org = new EshOrg();
    private FuncTree funcTree = new EshFunc();
    private List<Func> funcs = new LinkedList<>();

    @Override
    public User user() {
        return user;
    }

    @Override
    public Org org() {
        return org;
    }

    @Override
    public List<Func> funcs() {
        return funcs;
    }

    @Override
    public FuncTree funcTree() {
        return funcTree;
    }

    @Override
    public List<Role> roles() {
        return null;
    }

    @Override
    public List<Group> groups() {
        return null;
    }

}
