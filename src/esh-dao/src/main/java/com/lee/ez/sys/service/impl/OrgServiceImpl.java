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

package com.lee.ez.sys.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.sys.entity.SysOrg;
import com.lee.ez.sys.service.OrgService;
import com.lee.util.StringUtils;

/**
 * Description: 处室服务.<br>
 * Created by Jimmybly Lee on 2017/6/27.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class OrgServiceImpl implements OrgService {

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;
    // CSON: MemberName

    @Override
    @Transactional(readOnly = true)
    public SysOrg get(Integer id) {
        return em.find(SysOrg.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysOrg> query(SysOrg condition, Integer start, Integer limit) {
        String hql = "  from SysOrg as o";

        hql += " where o.isEnabled = :isEnabled";
        if (!StringUtils.isEmpty(condition.getName())) {
            hql += " and o.name like :orgName";
        }
        if (!StringUtils.isEmpty(condition.getTel())) {
            hql += " and o.tel like :orgTel";
        }

        final Query query = em.createQuery(hql);
        query.setFirstResult(start).setMaxResults(limit);

        query.setParameter("isEnabled", condition.getIsEnabled());
        if (!StringUtils.isEmpty(condition.getName())) {
            query.setParameter("orgName", "%" + condition.getName() + "%");
        }
        if (!StringUtils.isEmpty(condition.getTel())) {
            query.setParameter("orgTel", "%" + condition.getTel() + "%");
        }

        //noinspection unchecked
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count(SysOrg condition) {
        String hql = "  select count(o) from SysOrg as o";

        hql += " where o.isEnabled = :isEnabled";
        if (!StringUtils.isEmpty(condition.getName())) {
            hql += " and o.name like :orgName";
        }
        if (!StringUtils.isEmpty(condition.getTel())) {
            hql += " and o.tel like :orgTel";
        }

        final Query query = em.createQuery(hql);

        query.setParameter("isEnabled", condition.getIsEnabled());
        if (!StringUtils.isEmpty(condition.getName())) {
            query.setParameter("orgName", "%" + condition.getName() + "%");
        }
        if (!StringUtils.isEmpty(condition.getTel())) {
            query.setParameter("orgTel", "%" + condition.getTel() + "%");
        }

        return ((Number) query.getSingleResult()).intValue();
    }

    @Override
    public SysOrg create(SysOrg entity) {
        entity.setIsEnabled(true);
        em.persist(entity);
        return entity;
    }

    @Override
    public void update(SysOrg entity) {
        final SysOrg entityInDB = em.find(SysOrg.class, entity.getId());
        entityInDB.setName(entity.getName());
        entityInDB.setTel(entity.getTel());
    }

    @Override
    public void changeStatus(Integer id, Boolean isEnabled) {
        em.find(SysOrg.class, id).setIsEnabled(isEnabled);
    }
}
