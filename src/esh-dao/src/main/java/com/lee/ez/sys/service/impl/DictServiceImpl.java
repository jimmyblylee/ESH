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

import com.lee.ez.sys.entity.SysDict;
import com.lee.ez.sys.entity.SysDictionary;
import com.lee.ez.sys.service.DictService;
import com.lee.jwaf.exception.ServiceException;
import com.lee.util.ObjectUtils;
import com.lee.util.StringUtils;

// CSOFF: RegexpSinglelineJava

/**
 * Description: 字典服务.<br>
 * Created by Jimmybly Lee on 2017/6/27.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class DictServiceImpl implements DictService {

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;
    // CSON: MemberName

    /**
     * 根据ID获得实体.
     * @param id 实体ID
     * @return 实体
     */
    @Transactional(readOnly = true)
    public SysDict get(Integer id) {
        return em.find(SysDict.class, id);
    }

    /**
     * 根据条件获得实体列表.
     * @param condition 条件，类型，编码，值，是否启用
     * @param start 分页开始
     * @param limit 分页长度
     * @return 实体列表
     */
    @Transactional(readOnly = true)
    public List<SysDictionary> query(SysDictionary condition, Integer start, Integer limit) {
        String hql = " from SysDictionary as d";
        hql += " where d.isEnabled = :isEnabled";

        if (!StringUtils.isEmpty(condition.getNature())) {
            hql += " and d.nature like :dictNature";
        }
        if (!StringUtils.isEmpty(condition.getCode())) {
            hql += " and d.code like :dictCode";
        }
        if (!StringUtils.isEmpty(condition.getValue())) {
            hql += " and d.value like :dictValue";
        }

        final Query query = em.createQuery(hql);
        query.setFirstResult(start).setMaxResults(limit);

        query.setParameter("isEnabled", condition.getIsEnabled());

        if (!StringUtils.isEmpty(condition.getNature())) {
            query.setParameter("dictNature", "%" + condition.getNature() + "%");
        }
        if (!StringUtils.isEmpty(condition.getCode())) {
            query.setParameter("dictCode", "%" + condition.getCode() + "%");
        }
        if (!StringUtils.isEmpty(condition.getValue())) {
            query.setParameter("dictValue", "%" + condition.getValue() + "%");
        }

        //noinspection unchecked
        return query.getResultList();
    }

    /**
     * 根据条件获得实体数量.
     * @param condition 条件，类型，编码，值，是否启用
     * @return 实体数量
     */
    @Transactional(readOnly = true)
    public Integer count(SysDictionary condition) {
        String hql = " select count(d) from SysDictionary as d";
        hql += " where d.isEnabled = :isEnabled";

        if (!StringUtils.isEmpty(condition.getNature())) {
            hql += " and d.nature like :dictNature";
        }
        if (!StringUtils.isEmpty(condition.getCode())) {
            hql += " and d.code like :dictCode";
        }
        if (!StringUtils.isEmpty(condition.getValue())) {
            hql += " and d.value like :dictValue";
        }

        final Query query = em.createQuery(hql);

        query.setParameter("isEnabled", condition.getIsEnabled());

        if (!StringUtils.isEmpty(condition.getNature())) {
            query.setParameter("dictNature", "%" + condition.getNature() + "%");
        }
        if (!StringUtils.isEmpty(condition.getCode())) {
            query.setParameter("dictCode", "%" + condition.getCode() + "%");
        }
        if (!StringUtils.isEmpty(condition.getValue())) {
            query.setParameter("dictValue", "%" + condition.getValue() + "%");
        }

        return ((Number) query.getSingleResult()).intValue();
    }

    /**
     * 创建实体.
     * @param entity 游离状态实体
     * @return 持久化实体
     * @throws ServiceException 有已经存在重复的类型和编码
     */
    public SysDictionary create(SysDictionary entity) throws ServiceException {
        if (checkNatureAndCode(entity)) {
            throw new ServiceException("有已经存在重复的类型和编码！");
        }
        entity.setParent(em.find(SysDictionary.class, entity.getParent().getId()));
        em.persist(entity);
        return entity;
    }

    /**
     * 更新实体.只更新编码，显示值，描述
     * @param entity 游离状态实体.
     * @throws ServiceException 有已经存在重复的类型和编码
     */
    public void update(SysDictionary entity) throws ServiceException {
        if (checkNatureAndCode(entity)) {
            throw new ServiceException("有已经存在重复的类型和编码！");
        }
        final SysDictionary entityInDB = em.find(SysDictionary.class, entity.getId());
        entityInDB.setCode(entity.getCode());
        entityInDB.setValue(entity.getValue());
        entityInDB.setDesc(entity.getDesc());
    }

    /**
     * 校验类型和编码.
     * @param entity 实体
     * @return 类型和编码校验失败，有重复的，不能这样变更或添加
     */
    @SuppressWarnings("WeakerAccess")
    @Transactional(readOnly = true)
    public boolean checkNatureAndCode(SysDictionary entity) {
        String hql = " select count(d) from SysDictionary as d";
        hql += "where where d.nature = :nature and d.code = :code";
        if (!ObjectUtils.isEmpty(entity)) {
            hql += " and d.id = :id";
        }
        final Query query = em.createQuery(hql);
        query.setParameter("nature", entity.getNature());
        query.setParameter("code", entity.getCode());
        if (!ObjectUtils.isEmpty(entity)) {
            query.setParameter("id", entity.getId());
        }
        return ((Number) query.getSingleResult()).intValue() > 0;
    }

    /**
     * 修改实体是否启用状态.
     * @param id 实体ID
     * @param isEnabled 是否启用
     */
    public void changeStatus(Integer id, Boolean isEnabled) {
        em.find(SysDictionary.class, id).setIsEnabled(isEnabled);
    }

}