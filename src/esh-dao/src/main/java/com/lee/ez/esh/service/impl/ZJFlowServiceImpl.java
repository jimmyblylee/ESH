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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.esh.entity.EshZJ;
import com.lee.ez.esh.entity.ZJZT;
import com.lee.ez.esh.service.ZJFlowService;
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
public class ZJFlowServiceImpl implements ZJFlowService {

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;
    // CSON: MemberName

    @Override
    public void tiJiao(Token userToken, Integer id) {
        final EshZJ entity = em.find(EshZJ.class, id);
        entity.setXt_zt(ZJZT.DSL);
    }

    @Override
    public void shouLi(Token userToken, Integer id) {
        final EshZJ entity = em.find(EshZJ.class, id);
        entity.setXt_zt(ZJZT.DSH);
    }

    @Override
    public void tongGuo(Token userToken, Integer id) {
        final EshZJ entity = em.find(EshZJ.class, id);
        entity.setXt_zt(ZJZT.SHTG);
    }

    @Override
    public void boHui(Token userToken, Integer id, String note) {
        final EshZJ entity = em.find(EshZJ.class, id);
        entity.setXt_zt(ZJZT.BBH);
    }
}
