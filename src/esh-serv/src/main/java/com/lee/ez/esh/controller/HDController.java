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

import com.lee.ez.esh.entity.EshHDZJ;
import com.lee.ez.esh.entity.EshZJFZ;
import com.lee.ez.esh.service.HDZJService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lee.ez.esh.entity.EshHD;
import com.lee.ez.esh.service.HDFlowService;
import com.lee.ez.esh.service.HDInfoService;
import com.lee.jwaf.action.AbstractControllerSupport;

import java.util.List;

/**
 * Description: 活动控制器.<br>
 * Created by Jimmybly Lee on 2017/7/25.
 *
 * @author Jimmybly Lee
 */
@Controller("HDController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class HDController extends AbstractControllerSupport {

    /** 活动服务. */
    @Resource
    private HDInfoService infoService;
    /** 活动流程服务. */
    @Resource
    private HDFlowService flowService;
    /** 活动专家服务. */
    @Resource
    private HDZJService hdzjService;

    /**
     * 查询.
     */
    public void query() {
        final EshHD condition = workDTO.convertJsonToBeanByKey("condition", EshHD.class);
        workDTO.setResult(infoService.query(condition, workDTO.getStart(), workDTO.getLimit()));
        workDTO.setTotle(infoService.count(condition));
    }

    /**
     * 创建.
     */
    public void create() {
        final Long id = infoService.create(sessionDTO.currentToken(), workDTO.convertJsonToBeanByKey("entity", EshHD.class));
        workDTO.setResult(id);
    }

    /**
     * 更新.
     */
    public void update() {
        infoService.update(sessionDTO.currentToken(), workDTO.convertJsonToBeanByKey("entity", EshHD.class));
    }

    /**
     * 删除.
     */
    public void remove() {
        infoService.setStatus(sessionDTO.currentToken(), workDTO.getInteger("id"), false);
    }

    /**
     * 恢复.
     */
    public void resume() {
        infoService.setStatus(sessionDTO.currentToken(), workDTO.getInteger("id"), true);
    }

    /**
     * 物理删除.
     */
    public void doRealRemove() {
        infoService.doRealRemove(workDTO.getLong("id"));
    }

    /**
     * 指定专家.
     */
    public void assignZJ() {
        hdzjService.assignZJ(workDTO.getLong("zjId"), workDTO.getLong("hdId"));
    }

    /**
     * 在活动中删除指定专家.
     */
    public void removeZJ() {
        hdzjService.removeZJ(workDTO.getLong("hdzjId"));
    }

    /**
     * 根据活动获得已经选择的专家.
     */
    public void queryHDZJ() {
        final List<EshHDZJ> result = hdzjService.queryAssignedZJ(workDTO.getLong("hdId"));
        for (EshHDZJ zj : result) {
            zj.setHd(null);
            zj.getZj().setGzjlList(null);
            zj.getZj().setJlqkList(null);
            zj.getZj().setJybjList(null);
            zj.getZj().setJzList(null);
            zj.getZj().setYjcgList(null);
            zj.getZj().setZylbList(null);
        }
        workDTO.setResult(result);
    }

}
