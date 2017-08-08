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

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.esh.entity.*;
import com.lee.ez.esh.service.HDXQService;
import com.lee.ez.sys.entity.SysDict;
import com.lee.ez.sys.entity.SysUser;
import com.lee.jwaf.token.Token;
import com.lee.util.DateUtils;
import com.lee.util.StringUtils;

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
    public List<EshHDXQ> queryXQ(Integer hdId) {
        //noinspection unchecked
        return em.createQuery("from EshHDXQ where hd.id = :id").setParameter("id", hdId).getResultList();
    }

    @Override
    public EshHDXQ createXQ(EshHDXQ entity) {
        entity.setHd(em.find(EshHD.class, entity.getHd().getId()));
        entity.setZylb(em.find(SysDict.class, entity.getZylb().getId()));
        entity.setKwList(Collections.emptySet());
        entity.setTjList(Collections.emptySet());
        em.persist(entity);
        return entity;
    }

    @Override
    public void updateXQ(EshHDXQ entity) {
        final EshHDXQ entityInDB = em.find(EshHDXQ.class, entity.getId());
        entityInDB.setZylb(em.find(SysDict.class, entity.getZylb().getId()));
        entityInDB.setRs(entity.getRs());
        entityInDB.setTdcq(entity.getTdcq());
    }

    @Override
    public void removeXQ(Integer id) {
        em.createQuery("delete EshHDXQ as xq where xq.id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public List<EshHDXQTJ> queryXQTJ(Integer id) {
        //noinspection unchecked
        return em.createQuery("from EshHDXQTJ where xq.id = :id").setParameter("id", id).getResultList();
    }

    @Override
    public EshHDXQTJ createXQTJ(EshHDXQTJ entity) {
        entity.setLx(em.find(SysDict.class, entity.getLx().getId()));
        entity.setXq(em.find(EshHDXQ.class, entity.getXq().getId()));
        if (StringUtils.isEmpty(entity.getZ())) {
            entity.setZ("1");
        }
        em.persist(entity);
        return entity;
    }

    @Override
    public void updateXQTJ(EshHDXQTJ entity) {
        entity.setLx(em.find(SysDict.class, entity.getLx().getId()));
        entity.setXq(em.find(EshHDXQ.class, entity.getXq().getId()));
        em.merge(entity);
    }

    @Override
    public void removeXQTJ(Integer id) {
        em.remove(em.find(EshHDXQTJ.class, id));
    }

    @Override
    public List<EshHDXQKW> queryXQKW(Integer id) {
        //noinspection unchecked
        return em.createQuery("from EshHDXQKW where xq.id = :id").setParameter("id", id).getResultList();
    }

    @Override
    public EshHDXQKW createXQKW(EshHDXQKW entity) {
        entity.setXq(em.find(EshHDXQ.class, entity.getXq().getId()));
        // 随便找一个库外人员.
        entity.setZj((EshZJ) em.createQuery("from EshZJ where xt_sfkw = true").setMaxResults(1).getResultList().get(0));
        em.persist(entity);
        return entity;
    }

    @Override
    public void updateXQKW(EshHDXQKW entity) {
        entity.setXq(em.find(EshHDXQ.class, entity.getXq().getId()));
        entity.setZj(em.find(EshZJ.class, entity.getZj().getId()));
        em.merge(entity);

    }

    @Override
    public void removeXQKW(Integer id) {
        em.remove(em.find(EshHDXQKW.class, id));
    }

    @SuppressWarnings("CheckStyle")
    @Override
    public void suiJi(Token userToken, Integer id) {
        // 1、获取所有需求
        EshHD entity = em.find(EshHD.class, id);
        Set<EshHDXQ> xqList = entity.getXqList();
        for (EshHDXQ xq : xqList) {
            // 组织待抽取选专家列表，暂时称之为“备选”与业务上的“备选专家”有所区别，
            // 这是指随机前的库中可随机列表，而非随机后需要沟通是否参加的备选列表
            final List<EshZJ> beiXuanZJ = new LinkedList<>();
            if (xq.getTdcq()) { // 特定抽取
                for (EshHDXQKW kw : xq.getKwList()) {
                    beiXuanZJ.add(kw.getZj());
                }
            } else {
                // 非特定抽取
                String hql = "select d.zj from EshZJZYLB as d where d.zylb.id = :zylbId and d.zj.xt_qy = true and d.zj.xt_sfkw = false";
                //noinspection unchecked
                List<EshZJ> zjList = em.createQuery(hql).setParameter("zylbId", xq.getZylb().getId()).getResultList();
                beiXuanZJ.addAll(zjList);
            }

            // 随机抽取
            final int[] indexArray = random(0, beiXuanZJ.size() - 1, xq.getRs());
            //noinspection ConstantConditions
            for (int idx : indexArray) {
                EshHDZJSJ sj = new EshHDZJSJ();
                sj.setHd(entity);
                sj.setCzr(em.find(SysUser.class, userToken.user().getId()));
                sj.setCzsj(DateUtils.formatDateToYMD2(new Date().getTime()));
                sj.setBz("第一次随机");
                em.persist(sj);

                EshHDZJSJJG jg = new EshHDZJSJJG();
                jg.setSj(sj);
                jg.setZj(beiXuanZJ.get(idx));
                jg.setSfcj(true);
                jg.setWcjyy("未联系");
                em.persist(jg);
            }
        }
    }

    @Override
    public void queRenCanJia(Token userToken, Integer sjjgId) {
        EshHDZJSJJG sjjg = em.find(EshHDZJSJJG.class, sjjgId);

        sjjg.setWcjyy("确认参加");

        EshHDZJ hdzj = new EshHDZJ();
        hdzj.setHd(sjjg.getSj().getHd());
        hdzj.setZj(sjjg.getZj());
        em.persist(hdzj);

    }

    @Override
    public void queRenBuCanJia(Token userToken, Integer sjjgId, String liYou) {
        EshHDZJSJJG sjjg = em.find(EshHDZJSJJG.class, sjjgId);
        sjjg.setSfcj(false);
        sjjg.setWcjyy(liYou);
    }

    @Override
    public void buChong(Integer xqId, Integer zjId) {
        final EshHDXQKW entity = new EshHDXQKW();
        entity.setXq(em.find(EshHDXQ.class, xqId));
        entity.setZj(em.find(EshZJ.class, zjId));
        em.persist(entity);
    }

    /**
     * 在给定的范围内，获得 numCount 个随机数.
     * @param min 最小
     * @param max 最大
     * @param numCount 个数
     * @return 数组
     */
    private int[] random(@SuppressWarnings("SameParameterValue") int min, int max, int numCount) {
        if (numCount > (max - min + 1) || max < min) {
            return null;
        }
        final int[] result = new int[numCount];
        int count = 0;
        while (count < numCount) {
            final int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < numCount; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }
}
