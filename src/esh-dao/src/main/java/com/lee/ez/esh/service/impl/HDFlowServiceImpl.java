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

package com.lee.ez.esh.service.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.esh.entity.EshHD;
import com.lee.ez.esh.entity.HDZT;
import com.lee.ez.esh.service.HDFlowService;
import com.lee.ez.sys.entity.SysUser;
import com.lee.jwaf.token.User;
import com.lee.util.DateUtils;

/**
 * Description: 活动流程服务.<br>
 * Created by Jimmybly Lee on 2017/6/28.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class HDFlowServiceImpl implements HDFlowService {

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;

    @Override
    public void shangBao(User user, Integer id) {
        final EshHD hd = em.find(EshHD.class, id);
        hd.setZt(HDZT.DSL);
        hd.setGxsj(DateUtils.formatDateToYMD2(new Date().getTime()));
        hd.setShr(em.find(SysUser.class, user.getId()));
    }

    @Override
    public void shouLi(User user, Integer id) {
        final EshHD hd = em.find(EshHD.class, id);
        hd.setZt(HDZT.DSX);
        hd.setGxsj(DateUtils.formatDateToYMD2(new Date().getTime()));
        hd.setShr(em.find(SysUser.class, user.getId()));
    }

    @Override
    public void xuYaoBuChong(User user, Integer id) {
        final EshHD hd = em.find(EshHD.class, id);
        hd.setZt(HDZT.DBC);
        hd.setGxsj(DateUtils.formatDateToYMD2(new Date().getTime()));
        hd.setShr(em.find(SysUser.class, user.getId()));
    }

    @Override
    public void wanChengBuChong(User user, Integer id) {
        final EshHD hd = em.find(EshHD.class, id);
        hd.setZt(HDZT.DSX);
        hd.setGxsj(DateUtils.formatDateToYMD2(new Date().getTime()));
        hd.setShr(em.find(SysUser.class, user.getId()));
    }

    @Override
    public void wanChengShaiXuan(User user, Integer id) {
        final EshHD hd = em.find(EshHD.class, id);
        hd.setZt(HDZT.DQD);
        hd.setGxsj(DateUtils.formatDateToYMD2(new Date().getTime()));
        hd.setShr(em.find(SysUser.class, user.getId()));
    }

    @Override
    public void qiDong(User user, Integer id) {
        final EshHD hd = em.find(EshHD.class, id);
        hd.setZt(HDZT.DKS);
        hd.setGxsj(DateUtils.formatDateToYMD2(new Date().getTime()));
        hd.setShr(em.find(SysUser.class, user.getId()));
    }

    @Override
    public void kaiShi(User user, Integer id) {
        final EshHD hd = em.find(EshHD.class, id);
        hd.setZt(HDZT.DZJ);
        hd.setGxsj(DateUtils.formatDateToYMD2(new Date().getTime()));
        hd.setShr(em.find(SysUser.class, user.getId()));
    }

    @Override
    public void zongJie(User user, Integer id) {
        final EshHD hd = em.find(EshHD.class, id);
        hd.setZt(HDZT.JS);
        hd.setGxsj(DateUtils.formatDateToYMD2(new Date().getTime()));
        hd.setShr(em.find(SysUser.class, user.getId()));
    }
    // CSON: MemberName
}
