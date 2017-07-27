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

import java.util.List;
import java.util.Set;

import com.lee.ez.esh.entity.EshHDXQKW;
import com.lee.ez.esh.entity.EshHDXQTJ;
import com.lee.ez.esh.entity.EshZJ;
import com.lee.ez.sys.entity.SysDict;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.esh.entity.EshHDXQ;
import com.lee.ez.esh.service.HDXQService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Description: 活动需求服务实现.<br>
 * Created by Jimmybly Lee on 2017/7/27.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class HDXQServiceImpl implements HDXQService {

    // CSOFF: MemberName
    /**
     * Hibernate 数据库操作管理器.
     **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;

    @Override
    public List<EshHDXQ> queryHDXQ(Long hdId) {
        final String hql = "from EshHDXQ as d where d.hd.id = :id";
        //noinspection unchecked
        return em.createQuery(hql).setParameter("id", hdId).getResultList();
    }

    @Override
    public void assignHDXQ(EshHDXQ entity) {
        final Set<EshHDXQTJ> tjList = entity.getTjList();
        final Set<EshHDXQKW> kwList = entity.getKwList();
        // 1、保存需求
        entity.setZylb(em.find(SysDict.class, entity.getZylb().getId()));
        em.persist(entity);
        // 2、保存条件
        for (EshHDXQTJ tj : tjList) {
            tj.setXq(entity);
            tj.setLx(em.find(SysDict.class, tj.getLx().getId()));
            em.persist(tj);
        }
        // 3、保存库外
        for (EshHDXQKW kw : kwList) {
            kw.setXq(entity);
            kw.setZj(em.find(EshZJ.class, kw.getZj().getId()));
            em.persist(kw);
        }
    }

    @Override
    public void removeHDXQ(Long hdxqId) {
        em.remove(em.find(EshHDXQ.class, hdxqId));
    }
}
