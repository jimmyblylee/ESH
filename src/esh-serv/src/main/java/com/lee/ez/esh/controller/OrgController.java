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

package com.lee.ez.esh.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lee.ez.sys.entity.SysOrg;
import com.lee.ez.sys.service.OrgService;
import com.lee.jwaf.action.AbstractControllerSupport;

/**
 * Description: 处室管理.<br>
 * Created by Jimmybly Lee on 2017/7/3.
 *
 * @author Jimmybly Lee
 */
@Controller("OrgController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class OrgController extends AbstractControllerSupport {

    /** 处室服务. */
    @Resource
    private OrgService service;

    /**
     * 查询处室.
     */
    public void query() {
        final SysOrg condition = workDTO.convertJsonToBeanByKey("condition", SysOrg.class);
        workDTO.setResult(service.query(condition, workDTO.getStart(), workDTO.getLimit()));
        workDTO.setTotle(service.count(condition));
    }

    /**
     * 创建处室.
     */
    public void create() {
        final SysOrg entity = workDTO.convertJsonToBeanByKey("entity", SysOrg.class);
        service.create(entity);
    }

    /**
     * 修改处室.
     */
    public void update() {
        final SysOrg entity = workDTO.convertJsonToBeanByKey("entity", SysOrg.class);
        service.update(entity);
    }

    /**
     * 删除处室.
     */
    public void remove() {
        service.changeStatus(workDTO.getInteger("id"), false);
    }

    /**
     * 恢复删除处室.
     */
    public void resume() {
        service.changeStatus(workDTO.getInteger("id"), true);
    }
}
