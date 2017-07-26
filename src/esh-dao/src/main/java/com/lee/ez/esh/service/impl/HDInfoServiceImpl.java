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
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.esh.entity.EshHD;
import com.lee.ez.esh.entity.HDZT;
import com.lee.ez.esh.service.HDInfoService;
import com.lee.ez.sys.entity.SysUser;
import com.lee.jwaf.token.Token;
import com.lee.util.DateUtils;
import com.lee.util.ObjectUtils;
import com.lee.util.StringUtils;

/**
 * Description: 活动服务信息维护服务.<br>
 * Created by Jimmybly Lee on 2017/6/28.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class HDInfoServiceImpl implements HDInfoService {

    // CSOFF: MemberName
    /**
     * Hibernate 数据库操作管理器.
     **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<EshHD> query(EshHD condition, Integer start, Integer limit) {
        String hql = "";
        hql += "  from EshHD as h";
        hql += "  left join fetch h.djr";
        hql += " where h.zt = :zt";
        hql += "   and h.xz = :xz";
        hql += "   and h.qy = :qy";
        // 主题
        if (!StringUtils.isEmpty(condition.getMc())) {
            hql += " and h.mc like :mc";
        }
        // 开始时间， 大于
        if (!StringUtils.isEmpty(condition.getKs())) {
            hql += " and h.ks > :ks";
        }
        // 结束时间， 大于
        if (!StringUtils.isEmpty(condition.getZz())) {
            hql += " and h.zz > :zz";
        }
        // 所属部门
        if (!ObjectUtils.isEmpty(condition.getDjr()) && !ObjectUtils.isEmpty(condition.getDjr().getOrg())) {
            hql += " and h.djr.org.id = :orgId";
        }

        final Query query = em.createQuery(hql);
        query.setFirstResult(start).setMaxResults(limit);

        query.setParameter("zt", condition.getZt());
        query.setParameter("xz", condition.getXz());
        query.setParameter("qy", condition.getQy());
        // 主题
        if (!StringUtils.isEmpty(condition.getMc())) {
            query.setParameter("mc", condition.getMc());
        }
        // 开始时间， 大于
        if (!StringUtils.isEmpty(condition.getKs())) {
            query.setParameter("ks", condition.getKs());
        }
        // 结束时间， 大于
        if (!StringUtils.isEmpty(condition.getZz())) {
            query.setParameter("zz", condition.getZz());
        }
        // 所属部门
        if (!ObjectUtils.isEmpty(condition.getDjr()) && !ObjectUtils.isEmpty(condition.getDjr().getOrg())) {
            query.setParameter("orgId", condition.getDjr().getOrg().getId());
        }

        //noinspection unchecked
        return query.getResultList();
    }

    @Override
    public Integer count(EshHD condition) {
        String hql = "";
        hql += "select count(h)  from EshHD as h";
        hql += " where h.zt = :zt";
        hql += "   and h.xz = :xz";
        hql += "   and h.qy = :qy";
        // 主题
        if (!StringUtils.isEmpty(condition.getMc())) {
            hql += " and h.mc like :mc";
        }
        // 开始时间， 大于
        if (!StringUtils.isEmpty(condition.getKs())) {
            hql += " and h.ks > :ks";
        }
        // 结束时间， 大于
        if (!StringUtils.isEmpty(condition.getZz())) {
            hql += " and h.zz > :zz";
        }
        // 所属部门
        if (!ObjectUtils.isEmpty(condition.getDjr()) && !ObjectUtils.isEmpty(condition.getDjr().getOrg())) {
            hql += " and h.djr.org.id = :orgId";
        }

        final Query query = em.createQuery(hql);

        query.setParameter("zt", condition.getZt());
        query.setParameter("xz", condition.getXz());
        query.setParameter("qy", condition.getQy());
        // 主题
        if (!StringUtils.isEmpty(condition.getMc())) {
            query.setParameter("mc", condition.getMc());
        }
        // 开始时间， 大于
        if (!StringUtils.isEmpty(condition.getKs())) {
            query.setParameter("ks", condition.getKs());
        }
        // 结束时间， 大于
        if (!StringUtils.isEmpty(condition.getZz())) {
            query.setParameter("zz", condition.getZz());
        }
        // 所属部门
        if (!ObjectUtils.isEmpty(condition.getDjr()) && !ObjectUtils.isEmpty(condition.getDjr().getOrg())) {
            query.setParameter("orgId", condition.getDjr().getOrg().getId());
        }

        return ((Number) query.getSingleResult()).intValue();
    }

    @Override
    public Long create(Token token, EshHD entity) {
        entity.setQy(true);
        entity.setZt(HDZT.DSB);
        entity.setDjr(em.find(SysUser.class, token.user().getId()));
        entity.setDjsj(DateUtils.formatDateToYMD(new Date().getTime()));
        em.persist(entity);
        return entity.getId();
    }

    @Override
    public void update(Token token, EshHD entity) {
        entity.setDjr(em.find(SysUser.class, entity.getDjr().getId()));
        entity.setGxsj(DateUtils.formatDateToYMD(new Date().getTime()));
        em.merge(entity);
    }

    @Override
    public void setStatus(Token userToken, Integer id, Boolean isEnabled) {
        final EshHD entity = em.find(EshHD.class, id.longValue());
        entity.setQy(isEnabled);
        entity.setGxsj(DateUtils.formatDateToYMD(new Date().getTime()));
    }

    @Override
    public void doRealRemove(Long id) {
        em.remove(em.find(EshHD.class, id));
    }

    // CSON: MemberName
}
