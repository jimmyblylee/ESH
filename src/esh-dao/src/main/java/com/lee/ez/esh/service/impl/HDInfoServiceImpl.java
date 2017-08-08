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
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.esh.dto.*;
import com.lee.ez.esh.entity.*;
import com.lee.ez.esh.service.HDInfoService;
import com.lee.ez.sys.entity.SysDict;
import com.lee.ez.sys.entity.SysUser;
import com.lee.jwaf.token.Token;
import com.lee.util.BeanUtils;
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
@SuppressWarnings({"unused", "CheckStyle"})
public class HDInfoServiceImpl implements HDInfoService {

    // CSOFF: MemberName
    /**
     * Hibernate 数据库操作管理器.
     **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;

    @SuppressWarnings({"ResultOfMethodCallIgnored", "CheckStyle"})
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

        //noinspection unchecked,CheckStyle
        List<EshHD> result = query.getResultList();
        //noinspection CheckStyle
        for (EshHD hd : result) {
            for (EshHDXQ xq : hd.getXqList()) {
                for (EshHDXQTJ tj : xq.getTjList()) {
                    tj.getLx();
                    tj.getXq();
                }
                for (EshHDXQKW tj : xq.getKwList()) {
                    tj.getXq();
                    tj.getZj();
                }
            }
        }
        return result;
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
    public Integer create(Token token, EshHD entity) {
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
        final EshHD entity = em.find(EshHD.class, id);
        entity.setQy(isEnabled);
        entity.setGxsj(DateUtils.formatDateToYMD(new Date().getTime()));
    }

    @Override
    public void doRealRemove(Integer id) {
        em.remove(em.find(EshHD.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public HD getHDInfo(Integer id) {
        return getHD(id);
    }

    private HD getHD(Integer id) {
        final HD hd = new HD();
        // 处理基本信息
        EshHD hdEntity = em.find(EshHD.class, id);
        BeanUtils.copyProperties(hdEntity, hd, "djr", "shr", "xqList", "zjList", "sjList");
        hd.setShr(ObjectUtils.isEmpty(hdEntity.getShr()) ? "" : hdEntity.getShr().getName());
        hd.setDjr(hdEntity.getDjr().getName());

        // 处理需求列表
        hd.setXqList(new LinkedList<>());
        for (EshHDXQ xqEntity : hdEntity.getXqList()) {
            hd.getXqList().add(getXQ(xqEntity.getId()));
        }
        // 处理专家列表
        hd.setZjList(new LinkedList<>());
        for (EshHDZJ zjEntity : hdEntity.getZjList()) {
            hd.getZjList().add(getZJ(zjEntity.getZj().getId()));
        }

        // 处理随机记录列表
        hd.setSjList(new LinkedList<>());
        for (EshHDZJSJ sjEntity : hdEntity.getSjList()) {
            hd.getSjList().add(getSJ(sjEntity.getId()));
        }

        // 处理备选列表
        hd.setBxList(new LinkedList<>());
        for (XQ xq : hd.getXqList()) {
            for (ZJ zj : xq.getKwList()) {
                hd.getBxList().add(zj);
            }
        }

        return hd;
    }

    /**
     * 转化随机记录DTO.
     * @param id 随机记录ID
     * @return 随机记录DTO
     */
    private SJ getSJ(Integer id) {
        SJ sj = new SJ();
        EshHDZJSJ sjEntity = em.find(EshHDZJSJ.class, id);
        sj.setId(id);
        sj.setBz(sjEntity.getBz());
        sj.setCzr(sjEntity.getCzr().getName());
        sj.setCzsj(sjEntity.getCzsj());

        // 处室随机结果
        sj.setSjjgList(new LinkedList<>());
        for (EshHDZJSJJG sjjgEntity : sjEntity.getSjjgList()) {
            sj.getSjjgList().add(getSJJG(sjjgEntity.getId()));
        }
        return sj;
    }

    /**
     * 转化随机记录对应的结果DTO.
     * @param id 随机记录对应的结果ID
     * @return 随机记录对应的结果DTO
     */
    private SJJG getSJJG(Integer id) {
        SJJG sjjg = new SJJG();
        EshHDZJSJJG sjjgEntity = em.find(EshHDZJSJJG.class, id);
        sjjg.setId(id);
        sjjg.setZj(getZJ(sjjgEntity.getZj().getId()));
        sjjg.setSfcj(sjjgEntity.getSfcj());
        sjjg.setWcjyy(sjjgEntity.getWcjyy());
        return sjjg;
    }

    /**
     * 转化条件Dto.
     * @param id 条件id
     * @return 条件Dto
     */
    private TJ getTJ(Integer id) {
        TJ tj = new TJ();
        EshHDXQTJ tjEntity = em.find(EshHDXQTJ.class, id);
        tj.setId(id);
        tj.setLx(tjEntity.getLx().getCode());
        tj.setLxmc(tjEntity.getLx().getValue());
        tj.setZ(tjEntity.getZ());
        return tj;
    }

    /**
     * 转化需求DTO.
     * @param id 需求Id
     * @return 需求DTO
     */
    private XQ getXQ(Integer id) {
        EshHDXQ xqEntity = em.find(EshHDXQ.class, id);
        // 处理基本信息
        XQ xq = new XQ();
        BeanUtils.copyProperties(xqEntity, xq, "hd", "zylb", "tjList", "kwList");

        // 处理专业类别
        xq.setZylb(getZYLB(xqEntity.getZylb().getId()));

        // 处理条件列表
        xq.setTjList(new LinkedList<>());
        for (EshHDXQTJ tjEntity : xqEntity.getTjList()) {
            xq.getTjList().add(getTJ(tjEntity.getId()));
        }

        // 处理库外列表
        xq.setKwList(new LinkedList<>());
        for (EshHDXQKW kwEntity : xqEntity.getKwList()) {
            xq.getKwList().add(getZJ(kwEntity.getZj().getId()));
        }

        // 库内现有人数
        Number count = (Number) em.createQuery("select count(rel.zj) from EshZJZYLB rel where rel.zylb.code = :zylbCode")
            .setParameter("zylbCode", xq.getZylb().getCode()).getSingleResult();
        xq.setKnrs(count.intValue());

        return xq;
    }

    /**
     * 转化专家DTO.
     * @param id 专家ID
     * @return 专家DTO
     */
    private ZJ getZJ(Integer id) {
        ZJ zj = new ZJ();
        EshZJ zjEntity = em.find(EshZJ.class, id);
        zj.setId(id);
        zj.setGzdw(zjEntity.getGz_gzdw());
        zj.setIsKW(zjEntity.getXt_sfkw());
        zj.setLxfs(zjEntity.getJb_sj());
        zj.setName(zjEntity.getJb_xm());
        zj.setZysp(zjEntity.getZy_ywzc() + ";" + zjEntity.getZy_jszc());

        zj.setZylbList(new LinkedList<>());
        for (EshZJZYLB zylb : zjEntity.getZylbList()) {
            zj.getZylbList().add(getZYLB(zylb.getZylb().getId()));
        }
        return zj;
    }

    /**
     * 转化专业类别DTO.
     * @param id 专业类别字典ID.
     * @return 专业类别DTO
     */
    private ZYLB getZYLB(Integer id) {
        ZYLB zylb = new ZYLB();
        SysDict zylbEntity = em.find(SysDict.class, id);
        zylb.setId(id);
        zylb.setCode(zylbEntity.getCode());
        zylb.setName(zylbEntity.getValue());
        return zylb;
    }


    // CSON: MemberName
}
