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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.esh.entity.EshZJ;
import com.lee.ez.esh.entity.ZJZT;
import com.lee.jwaf.token.Token;

/**
 * Description: 登记专家流程服务.<br>
 * Created by Jimmybly Lee on 2017/6/28.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class ZJFlowService {

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;
    // CSON: MemberName

    /**
     * 提交.
     * @param userToken 操作人
     * @param id 专家ID
     */
    public void tiJiao(Token userToken, Long id) {
        final EshZJ entity = em.find(EshZJ.class, id);
        entity.setXt_zt(ZJZT.DSL);
    }

    /**
     * 受理.
     * @param userToken 操作人
     * @param id 专家ID
     */
    public void shouLi(Token userToken, Long id) {
        final EshZJ entity = em.find(EshZJ.class, id);
        entity.setXt_zt(ZJZT.DSH);
    }

    /**
     * 通过.
     * @param userToken 操作人
     * @param id 专家ID
     */
    public void tongGuo(Token userToken, Long id) {
        final EshZJ entity = em.find(EshZJ.class, id);
        entity.setXt_zt(ZJZT.SHTG);
    }

    /**
     * 驳回.
     * @param userToken 操作人
     * @param id 专家ID
     * @param note 驳回意见
     */
    public void boHui(Token userToken, Long id, String note) {
        final EshZJ entity = em.find(EshZJ.class, id);
        entity.setXt_zt(ZJZT.BBH);
    }
}
