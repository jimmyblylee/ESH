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
import com.lee.ez.sys.entity.SysUser;
import com.lee.ez.sys.entity.SysUserAccount;
import com.lee.ez.sys.entity.SysUserPhoto;
import com.lee.ez.sys.service.UserService;
import com.lee.jwaf.exception.WarnException;
import com.lee.util.ObjectUtils;
import com.lee.util.PasswordUtils;
import com.lee.util.StringUtils;

// CSOFF: RegexpSinglelineJava

/**
 * Description: 用户服务.<br>
 * Created by Jimmybly Lee on 2017/6/27.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

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
    public SysUser get(Integer id) {
        return em.find(SysUser.class, id);
    }

    /**
     * 根据ID获得用户，并把照片一并返回.
     * @param id 用户ID
     * @return 用户实体，拥有照片
     */
    @Transactional(readOnly = true)
    public SysUserPhoto getUserPhoto(Integer id) {
        return em.find(SysUserPhoto.class, id);
    }

    /**
     * 根据id获得用户帐号.
     * @param id 用户id
     * @return 用户帐号
     */
    @Transactional(readOnly = true)
    public SysUserAccount getUserAccount(Integer id) {
        return em.find(SysUserAccount.class, id);
    }

    /**
     * 根据条件返回实体列表.
     * @param condition 可能参数，包括用户名，是否启用，处室名，处室ID
     * @param start 分页开始
     * @param limit 分页长度
     * @return 实体列表
     */
    @Transactional(readOnly = true)
    public List<SysUser> query(SysUser condition, Integer start, Integer limit) {
        String hql = "  from SysUser as u";
        hql += " left join fetch u.org";
        hql += " left join fetch u.account";
        hql += " left join fetch u.photo";
        hql += " where u.isEnabled = :isEnabled";
        if (!StringUtils.isEmpty(condition.getName())) {
            hql += " and u.name like :userName";
        }
        if (!ObjectUtils.isEmpty(condition.getOrg()) && !ObjectUtils.isEmpty(condition.getOrg().getId())) {
            hql += " and u.org.id = :orgId";
        }
        if (!ObjectUtils.isEmpty(condition.getOrg()) && !StringUtils.isEmpty(condition.getOrg().getName())) {
            hql += " and u.org.name like :orgName";
        }

        final Query query = em.createQuery(hql);
        query.setFirstResult(start).setMaxResults(limit);

        query.setParameter("isEnabled", condition.getIsEnabled());
        if (!StringUtils.isEmpty(condition.getName())) {
            query.setParameter("userName", "%" + condition.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(condition.getOrg()) && !ObjectUtils.isEmpty(condition.getOrg().getId())) {
            query.setParameter("orgId", condition.getOrg().getId());
        }
        if (!ObjectUtils.isEmpty(condition.getOrg()) && !StringUtils.isEmpty(condition.getOrg().getName())) {
            query.setParameter("orgName", "%" + condition.getOrg().getName() + "%");
        }

        //noinspection unchecked
        return query.getResultList();
    }

    /**
     * 根据条件返回用户个数.
     * @param condition 可能参数，包括用户名，处室名，处室ID
     * @return 用户个数
     */
    @Transactional(readOnly = true)
    public int count(SysUser condition) {
        String hql = "  select count(u) from SysUser as u";
        hql += " where u.isEnabled = :isEnabled";
        if (!StringUtils.isEmpty(condition.getName())) {
            hql += " and u.name like :userName";
        }
        if (!ObjectUtils.isEmpty(condition.getOrg()) && !ObjectUtils.isEmpty(condition.getOrg().getId())) {
            hql += " and u.org.id = :orgId";
        }
        if (!ObjectUtils.isEmpty(condition.getOrg()) && !StringUtils.isEmpty(condition.getOrg().getName())) {
            hql += " and u.org.name like :orgName";
        }

        final Query query = em.createQuery(hql);

        query.setParameter("isEnabled", condition.getIsEnabled());
        if (!StringUtils.isEmpty(condition.getName())) {
            query.setParameter("userName", "%" + condition.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(condition.getOrg()) && !ObjectUtils.isEmpty(condition.getOrg().getId())) {
            query.setParameter("orgId", condition.getOrg().getId());
        }
        if (!ObjectUtils.isEmpty(condition.getOrg()) && !StringUtils.isEmpty(condition.getOrg().getName())) {
            query.setParameter("orgName", "%" + condition.getOrg().getName() + "%");
        }

        return ((Number) query.getSingleResult()).intValue();
    }

    /**
     * 创建用户.
     * @param entity 用户游离实体
     * @return 持久实体
     * @throws WarnException 账号没校验通过
     */
    public SysUser create(SysUser entity) throws WarnException {
        final SysUserAccount account = entity.getAccount();
        entity.setAccount(null);
        final SysUserPhoto photo = entity.getPhoto();
        entity.setPhoto(null);
        if (checkUserAccount(account)) {
            throw new WarnException("有重复的账号，不能使用这个账号！");
        }

        // 先处理一对一关联的末端：account
        account.setPwd(PasswordUtils.encryptByMD5(account.getPwd()));
        em.persist(account);
        em.flush();

        em.find(SysUserPhoto.class, account.getId()).setData(photo.getData());

        final SysUser entityInDB = em.find(SysUser.class, account.getId());

        entityInDB.setOrg(em.find(SysOrg.class, entity.getOrg().getId()));
        entityInDB.setName(entity.getName());
        entityInDB.setMail(entity.getMail());
        entityInDB.setTel(entity.getTel());
        entityInDB.setIsEnabled(true);

        return entityInDB;
    }

    /**
     * 更新用户实体.只更新用户名、电话、邮箱、头像
     * @param entity 实体
     */
    public void update(SysUser entity) {
        final SysUser entityInDB = em.find(SysUser.class, entity.getId());
        entityInDB.setName(entity.getName());
        entityInDB.setTel(entity.getTel());
        entityInDB.setMail(entity.getMail());

        entityInDB.getPhoto().setData(entity.getPhoto().getData());

        if (!checkUserAccount(entity.getAccount())) {
            entityInDB.getAccount().setAccount(entity.getAccount().getAccount());
        }
    }

    /**
     * 校验用户帐号.
     * @param user 用户实体，校验其中的用户id以及账号
     * @return true for 校验失败，已经有重复账号的用户了.
     */
    @SuppressWarnings("WeakerAccess")
    @Transactional(readOnly = true)
    public boolean checkUserAccount(SysUserAccount user) {
        String hql = "  select count(u) from SysUserAccount as u";
        hql += " where u.account = :account";
        if (!ObjectUtils.isEmpty(user.getId())) {
            hql += " and u.id <> :userId";
        }
        final Query query = em.createQuery(hql);
        if (!ObjectUtils.isEmpty(user.getId())) {
            query.setParameter("userId", user.getId());
        }
        query.setParameter("account", user.getAccount());
        return ((Number) query.getSingleResult()).intValue() > 0;
    }

    /**
     * 更新用户密码.
     * @param entity 用户实体
     * @throws WarnException 账号没校验通过
     */
    public void updateAccount(SysUserAccount entity) throws WarnException {
        if (checkUserAccount(entity)) {
            throw new WarnException("有重复的账号，不能使用这个账号！");
        }
        final SysUserAccount entityInDB = getUserAccount(entity.getId());
        entityInDB.setAccount(entity.getAccount());
        entityInDB.setPwd(PasswordUtils.encryptByMD5(entity.getPwd()));
    }

    /**
     * 更新头像.
     * @param photo 头像实体
     */
    public void updatePhoto(SysUserPhoto photo) {
        final SysUserPhoto photoInDB = getUserPhoto(photo.getId());
        photoInDB.setData(photo.getData());
    }

    /**
     * 修改实体是否启用状态.
     * @param id 实体ID
     * @param isEnabled 是否启用
     */
    public void changeStatus(Integer id, Boolean isEnabled) {
        em.find(SysUser.class, id).setIsEnabled(isEnabled);
    }
}
