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
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.esh.entity.*;
import com.lee.ez.esh.service.ZJInfoService;
import com.lee.ez.sys.service.DictService;
import com.lee.ez.sys.service.UserService;
import com.lee.jwaf.exception.ServiceException;
import com.lee.jwaf.token.Token;
import com.lee.util.Assert;
import com.lee.util.DateUtils;
import com.lee.util.ObjectUtils;
import com.lee.util.StringUtils;

// CSOFF: RegexpSinglelineJava
// CSOFF: ParameterName

/**
 * Description: 专家基本信息服务实现类.<br>
 * Created by Jimmybly Lee on 2017/7/10.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class ZJInfoServiceImpl implements ZJInfoService {

    // CSOFF: MemberName
    /**
     * Hibernate 数据库操作管理器.
     **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;
    // CSON: MemberName

    /**
     * 字典服务.
     */
    @Resource
    private DictService dictService;
    /**
     * 用户服务.
     */
    @Resource
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public EshZJ get(Long id) {
        return em.find(EshZJ.class, id);
    }

    // CSOFF: NPathComplexity
    // CSOFF: CyclomaticComplexity

    @SuppressWarnings("ConstantConditions")
    @Override
    @Transactional(readOnly = true)
    public List<EshZJ> query(EshZJ condition, Integer start, Integer limit) {
        String hql = "  from EshZJ as zj";
        hql += " left join fetch zj.jb_zzmm";
        hql += " left join fetch zj.gz_gzdw_dz_sheng";
        hql += " left join fetch zj.jy_whcd";
        hql += " left join fetch zj.gzjlList";
        hql += " left join fetch zj.jlqkList";
        hql += " left join fetch zj.jybjList";
        hql += " left join fetch zj.jzList";
        hql += " left join fetch zj.yjcgList";
        hql += " left join fetch zj.zylbList";
        // 是否启用
        hql += " where zj.xt_qy = :xt_qy";
        // 是否库外
        hql += "  and zj.xt_sfkw = :xt_sfkw";
        // 姓名
        if (!StringUtils.isEmpty(condition.getJb_xm())) {
            hql += "  and zj.jb_xm like :jb_xm";
        }
        // 性别
        if (!StringUtils.isEmpty(condition.getJb_xb())) {
            hql += "  and zj.jb_xb = :jb_xb";
        }
        // 民族
        if (!StringUtils.isEmpty(condition.getJb_mz())) {
            hql += "  and zj.jb_mz = :jb_mz";
        }
        // 所在单位
        if (!StringUtils.isEmpty(condition.getGz_gzdw())) {
            hql += "  and zj.gz_gzdw like :gz_gzdw";
        }
        // 是否体制内
        if (!ObjectUtils.isEmpty(condition.getGz_gaxt())) {
            hql += "  and zj.gz_gaxt = :gz_gaxt";
        }
        // 推荐单位
        if (!StringUtils.isEmpty(condition.getYj_tjdw_mc())) {
            hql += "  and zj.yj_tjdw_mc like :yj_tjdw_mc";
        }
        // 审核状态
        if (!ObjectUtils.isEmpty(condition.getXt_zt())) {
            hql += "  and zj.xt_zt = :xt_zt";
        }
        // 登记人所在单位
        if (!ObjectUtils.isEmpty(condition.getXt_djr()) && !ObjectUtils.isEmpty(condition.getXt_djr().getOrg())
            && !ObjectUtils.isEmpty(condition.getXt_djr().getOrg().getId())) {
            hql += "  and zj.xt_djr.org.id = :orgId";
        }

        final Query query = em.createQuery(hql);
        query.setFirstResult(start).setMaxResults(limit);

        // 是否启用
        query.setParameter("xt_qy", condition.getXt_qy());
        // 是否库外
        query.setParameter("xt_sfkw", condition.getXt_sfkw());
        // 姓名
        if (!StringUtils.isEmpty(condition.getJb_xm())) {
            query.setParameter("jb_xm", "%" + condition.getJb_xm() + "%");
        }
        // 性别
        if (!StringUtils.isEmpty(condition.getJb_xb())) {
            query.setParameter("jb_xb", condition.getJb_xb());
        }
        // 民族
        if (!StringUtils.isEmpty(condition.getJb_mz())) {
            query.setParameter("jb_mz", condition.getJb_mz());
        }
        // 所在单位
        if (!StringUtils.isEmpty(condition.getGz_gzdw())) {
            query.setParameter("gz_gzdw", "%" + condition.getGz_gzdw() + "%");
        }
        // 是否体制内
        if (!ObjectUtils.isEmpty(condition.getGz_gaxt())) {
            query.setParameter("gz_gaxt", condition.getGz_gaxt());
        }
        // 推荐单位
        if (!StringUtils.isEmpty(condition.getYj_tjdw_mc())) {
            query.setParameter("yj_tjdw_mc", "%" + condition.getYj_tjdw_mc() + "%");
        }
        // 审核状态
        if (!ObjectUtils.isEmpty(condition.getXt_zt())) {
            query.setParameter("xt_zt", condition.getXt_zt());
        }
        // 登记人所在单位
        if (!ObjectUtils.isEmpty(condition.getXt_djr()) && !ObjectUtils.isEmpty(condition.getXt_djr().getOrg())
            && !ObjectUtils.isEmpty(condition.getXt_djr().getOrg().getId())) {
            query.setParameter("orgId", condition.getXt_djr().getOrg().getId());
        }

        //noinspection unchecked
        return query.getResultList();
    }
    // CSON: CyclomaticComplexity
    // CSON: NPathComplexity

    // CSOFF: CyclomaticComplexity
    // CSOFF: NPathComplexity

    @SuppressWarnings("ConstantConditions")
    @Override
    @Transactional(readOnly = true)
    public Integer count(EshZJ condition) {
        String hql = "  select count(zj) from EshZJ as zj";
        // 是否启用
        hql += " where zj.xt_qy = :xt_qy";
        // 是否库外
        hql += "  and zj.xt_sfkw = :xt_sfkw";
        // 姓名
        if (!StringUtils.isEmpty(condition.getJb_xm())) {
            hql += "  and zj.jb_xm like :jb_xm";
        }
        // 性别
        if (!StringUtils.isEmpty(condition.getJb_xb())) {
            hql += "  and zj.jb_xb = :jb_xb";
        }
        // 民族
        if (!StringUtils.isEmpty(condition.getJb_mz())) {
            hql += "  and zj.jb_mz = :jb_mz";
        }
        // 所在单位
        if (!StringUtils.isEmpty(condition.getGz_gzdw())) {
            hql += "  and zj.gz_gzdw like :gz_gzdw";
        }
        // 是否体制内
        if (!ObjectUtils.isEmpty(condition.getGz_gaxt())) {
            hql += "  and zj.gz_gaxt = :gz_gaxt";
        }
        // 推荐单位
        if (!StringUtils.isEmpty(condition.getYj_tjdw_mc())) {
            hql += "  and zj.yj_tjdw_mc like :yj_tjdw_mc";
        }
        // 审核状态
        if (!ObjectUtils.isEmpty(condition.getXt_zt())) {
            hql += "  and zj.xt_zt = :xt_zt";
        }
        // 登记人所在单位
        if (!ObjectUtils.isEmpty(condition.getXt_djr()) && !ObjectUtils.isEmpty(condition.getXt_djr().getOrg())
            && !ObjectUtils.isEmpty(condition.getXt_djr().getOrg().getId())) {
            hql += "  and zj.xt_djr.org.id = :orgId";
        }

        final Query query = em.createQuery(hql);

        // 是否启用
        query.setParameter("xt_qy", condition.getXt_qy());
        // 是否库外
        query.setParameter("xt_sfkw", condition.getXt_sfkw());
        // 姓名
        if (!StringUtils.isEmpty(condition.getJb_xm())) {
            query.setParameter("jb_xm", "%" + condition.getJb_xm() + "%");
        }
        // 性别
        if (!StringUtils.isEmpty(condition.getJb_xb())) {
            query.setParameter("jb_xb", condition.getJb_xb());
        }
        // 民族
        if (!StringUtils.isEmpty(condition.getJb_mz())) {
            query.setParameter("jb_mz", condition.getJb_mz());
        }
        // 所在单位
        if (!StringUtils.isEmpty(condition.getGz_gzdw())) {
            query.setParameter("gz_gzdw", "%" + condition.getGz_gzdw() + "%");
        }
        // 是否体制内
        if (!ObjectUtils.isEmpty(condition.getGz_gaxt())) {
            query.setParameter("gz_gaxt", condition.getGz_gaxt());
        }
        // 推荐单位
        if (!StringUtils.isEmpty(condition.getYj_tjdw_mc())) {
            query.setParameter("yj_tjdw_mc", "%" + condition.getYj_tjdw_mc() + "%");
        }
        // 审核状态
        if (!ObjectUtils.isEmpty(condition.getXt_zt())) {
            query.setParameter("xt_zt", condition.getXt_zt());
        }
        // 登记人所在单位
        if (!ObjectUtils.isEmpty(condition.getXt_djr()) && !ObjectUtils.isEmpty(condition.getXt_djr().getOrg())
            && !ObjectUtils.isEmpty(condition.getXt_djr().getOrg().getId())) {
            query.setParameter("orgId", condition.getXt_djr().getOrg().getId());
        }

        return ((Number) query.getSingleResult()).intValue();
    }
    // CSON: CyclomaticComplexity
    // CSON: NPathComplexity

    @Override
    public EshZJ create(Token userToken, EshZJ entity) throws ServiceException {
        validate(entity);

        setZJ(entity.getGzjlList(), entity);
        setZJ(entity.getJlqkList(), entity);
        setZJ(entity.getJybjList(), entity);
        setZJ(entity.getJzList(), entity);
        setZJ(entity.getYjcgList(), entity);
        setZJ(entity.getZylbList(), entity);

        entity.setJb_zzmm(dictService.get(entity.getJb_zzmm().getId()));
        entity.setGz_gzdw_dz_sheng(dictService.get(entity.getGz_gzdw_dz_sheng().getId()));
        entity.setJy_whcd(dictService.get(entity.getJy_whcd().getId()));
        entity.setXt_zt(ZJZT.DSB);
        entity.setXt_qy(true);
        entity.setXt_djr(userService.get(userToken.user().getId()));
        entity.setXt_djsj(DateUtils.formatDateToYMD(new Date().getTime()));
        em.persist(entity);
        return entity;
    }

    /**
     * 反向设置级联保存或者更新的内容.
     *
     * @param set 一对多的多的部分集合
     * @param zj  专家
     */
    private void setZJ(Set<? extends EshZJFZ> set, EshZJ zj) {
        for (EshZJFZ item : set) {
            item.setZj(zj);
        }
    }

    /**
     * 校验.
     *
     * @param entity 实体
     * @throws ServiceException 校验不过
     */
    private void validate(EshZJ entity) throws ServiceException {
        try {
            Assert.notNull(entity.getJb_zzmm(), "政治面貌不能为空！");
            Assert.notNull(entity.getGz_gzdw_dz_sheng(), "单位所在地不能为空！");
            Assert.notNull(entity.getJy_whcd(), "文化程度不能为空！");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void update(Token userToken, EshZJ entity) throws ServiceException {

        validate(entity);

        setZJ(entity.getGzjlList(), entity);
        setZJ(entity.getJlqkList(), entity);
        setZJ(entity.getJybjList(), entity);
        setZJ(entity.getJzList(), entity);
        setZJ(entity.getYjcgList(), entity);
        setZJ(entity.getZylbList(), entity);

        entity.setJb_zzmm(dictService.get(entity.getJb_zzmm().getId()));
        entity.setGz_gzdw_dz_sheng(dictService.get(entity.getGz_gzdw_dz_sheng().getId()));
        entity.setJy_whcd(dictService.get(entity.getJy_whcd().getId()));
        entity.setXt_zt(ZJZT.DSB);
        entity.setXt_djr(userService.get(userToken.user().getId()));
        entity.setXt_djsj(DateUtils.formatDateToYMD(new Date().getTime()));
        entity.setXt_gxsj(DateUtils.formatDateToYMD(new Date().getTime()));

        em.merge(entity);
    }

    @Override
    public void setStatus(Token userToken, Integer id, Boolean isEnabled) {
        final EshZJ entity = em.find(EshZJ.class, id.longValue());
        entity.setXt_qy(isEnabled);
        entity.setXt_gxsj(DateUtils.formatDateToYMD(new Date().getTime()));
    }

    @Override
    public void removeFz(Long id, Class<? extends EshZJFZ> type) {
        em.remove(em.find(type, id));
    }
}
