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

import com.lee.ez.esh.entity.EshZJFZ;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lee.ez.esh.entity.EshZJ;
import com.lee.ez.esh.service.ZJInfoService;
import com.lee.jwaf.action.AbstractControllerSupport;

import java.util.List;

/**
 * Description: 专家管理控制器.<br>
 * Created by Jimmybly Lee on 2017/7/10.
 *
 * @author Jimmybly Lee
 */
@Controller("ZJInfoController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class ZJInfoController extends AbstractControllerSupport {

    /** 专家辅助. */
    @Resource
    private ZJInfoService service;

    /**
     * 查询.
     */
    public void query() {
        final EshZJ condition = workDTO.convertJsonToBeanByKey("condition", EshZJ.class);
        List<EshZJ> result = service.query(condition, workDTO.getStart(), workDTO.getLimit());
        for (EshZJ zj : result) {
            for (EshZJFZ item : zj.getGzjlList()) {
                item.setZj(null);
            }
            for (EshZJFZ item : zj.getJlqkList()) {
                item.setZj(null);
            }
            for (EshZJFZ item : zj.getJybjList()) {
                item.setZj(null);
            }
            for (EshZJFZ item : zj.getJzList()) {
                item.setZj(null);
            }
            for (EshZJFZ item : zj.getYjcgList()) {
                item.setZj(null);
            }
            for (EshZJFZ item : zj.getZylbList()) {
                item.setZj(null);
            }
        }
        workDTO.setResult(result);
        workDTO.setTotle(service.count(condition));
    }

    /**
     * 创建.
     */
    public void create() {
        final EshZJ entity = workDTO.convertJsonToBeanByKey("entity", EshZJ.class);
        service.create(sessionDTO.currentToken(), entity);
    }

    /**
     * 修改.
     */
    public void update() {
        final EshZJ entity = workDTO.convertJsonToBeanByKey("entity", EshZJ.class);
        service.update(sessionDTO.currentToken(), entity);
    }

    /**
     * 删除.
     */
    public void remove() {
        service.setStatus(sessionDTO.currentToken(), workDTO.getInteger("id"), false);
    }

    /**
     * 恢复删除.
     */
    public void resume() {
        service.setStatus(sessionDTO.currentToken(), workDTO.getInteger("id"), true);
    }
}