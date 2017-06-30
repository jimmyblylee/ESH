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

package com.lee.ez.esh.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.esh.entity.EshZJFZ;
import com.lee.util.ObjectUtils;

/**
 * Description: 专家辅助信息服务，服务于教育背景、工作经历、奖励情况、研究成果、学术组织.<br>
 * Created by Jimmybly Lee on 2017/6/28.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class ZJFZService {

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;
    // CSON: MemberName

    /**
     * 根据专家id返回辅助信息表,时间顺序.
     * @param id 专家ID
     * @param type 实体类型
     * @return 辅助信息表,时间顺序.
     */
    public List<EshZJFZ> queryJYBJ(Long id, Class type) {
        //noinspection unchecked,JpaQlInspection
        return em.createQuery("from " + type + " where zj.id = :id").setParameter("id", id).getResultList();
    }

    /**
     * 将给定的专家辅助信息存储.
     * @param list 辅助信息列表
     */
    public void update(List<EshZJFZ> list) {
        for (EshZJFZ entity : list) {
            if (!ObjectUtils.isEmpty(entity)) {
                em.merge(entity);
            } else {
                em.persist(entity);
            }
        }
    }

    /**
     * 删除实体.
     * @param entity 实体
     * @param type 实体类型
     */
    public void remove(EshZJFZ entity, Class type) {
        //noinspection unchecked
        em.remove(em.find(type, entity.getId()));
    }
}
