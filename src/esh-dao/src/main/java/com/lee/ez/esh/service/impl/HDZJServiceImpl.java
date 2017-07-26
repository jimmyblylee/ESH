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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.esh.entity.EshHD;
import com.lee.ez.esh.entity.EshHDZJ;
import com.lee.ez.esh.entity.EshZJ;
import com.lee.ez.esh.service.HDZJService;

/**
 * Description: 活动专家服务.<br>
 * Created by Jimmybly Lee on 2017/7/27.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class HDZJServiceImpl implements HDZJService {

    // CSOFF: MemberName
    /**
     * Hibernate 数据库操作管理器.
     **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;

    @Override
    public void assignZJ(Long zjId, Long hdId) {
        final EshHDZJ entity = new EshHDZJ();
        entity.setHd(em.find(EshHD.class, hdId));
        entity.setZj(em.find(EshZJ.class, zjId));
        em.persist(entity);
    }

    @Override
    public List<EshHDZJ> queryAssignedZJ(Long hdId) {
        final String hql = "from EshHDZJ as d left join fetch d.zj where d.hd.id = :id";
        //noinspection unchecked
        return em.createQuery(hql).setParameter("id", hdId).getResultList();
    }

    @Override
    public void removeZJ(Long hdzjId) {
        em.remove(em.find(EshHDZJ.class, hdzjId));
    }
}
