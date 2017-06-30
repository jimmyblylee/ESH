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

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.ez.sys.dto.EshFunc;
import com.lee.ez.sys.dto.EshToken;
import com.lee.ez.sys.entity.SysFunc;
import com.lee.ez.sys.entity.SysUser;
import com.lee.ez.sys.service.AuthService;
import com.lee.jwaf.token.Func;
import com.lee.jwaf.token.FuncTree;
import com.lee.jwaf.token.Token;
import com.lee.util.BeanUtils;
import com.lee.util.PasswordUtils;

/**
 * Description: 权限服务.<br>
 * Created by Jimmybly Lee on 2017/6/27.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class AuthServiceImpl implements AuthService {
    /** SerialVersionUID. */
    private static final long serialVersionUID = 6718746124995812410L;

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "esh_mgmt")
    private EntityManager em;
    // CSON: MemberName

    /**
     * 校验账号和密码.
     * @param account 账号
     * @param pwd 密码
     * @return user id 获得 空（如果找不到）
     */
    public Integer checkAccountAndPwd(String account, String pwd) {
        String hql = "from SysUser as u";
        hql += " where u.account = :account";
        hql += " and u.pwd = :pwd";
        hql += " and u.isEnabled = true";
        final Query query = em.createQuery(hql);
        query.setParameter("account", account);
        query.setParameter("pwd", PasswordUtils.encryptByMD5(pwd));
        //noinspection unchecked
        final List<SysUser> result = query.getResultList();
        return result.size() > 0 ? result.get(0).getId() : null;
    }

    /**
     * 给用户指定对应的功能.
     * @param userId 用户id
     * @param funcId 功能id
     * @param assigned 是否关联
     */
    public void assignFuncToUser(Integer userId, Integer funcId, Boolean assigned) {
        // CSOFF: LineLength
        if (assigned) {
            Query query = em.createNativeQuery("SELECT COUNT(1) FROM SYS_USER_FUNC WHERE USER_ID = :userId AND FUNC_ID = :funcId");
            final Number count = (Number) query.setParameter("userId", userId).setParameter("funcId", funcId).getSingleResult();
            if (count.intValue() == 0) {
                query = em.createNativeQuery("INSERT INTO SYS_USER_FUNC(REL_ID, USER_ID, FUNC_ID) VALUES(SEQ_ESH.NEXTVAL, :userId, :funcId)");
                query.setParameter("userId", userId).setParameter("funcId", funcId).executeUpdate();
            }
        } else {
            final Query query = em.createNativeQuery("DELETE FROM SYS_USER_FUNC WHERE USER_ID = :userId AND FUNC_ID = :funcId");
            query.setParameter("userId", userId).setParameter("funcId", funcId).executeUpdate();
        }
        // CSON: LineLength
    }

    /**
     * 根据给定的用户ID返回用户令牌.
     * @param userId 用户id
     * @return 用户令牌
     */
    public Token getTokenByUserId(Integer userId) {
        final EshToken token = new EshToken();
        final SysUser user = em.find(SysUser.class, userId);

        // org
        token.org().setId(user.getOrg().getId());
        token.org().setName(user.getOrg().getName());
        // user
        token.user().setId(user.getId());
        token.user().setName(user.getName());
        token.user().setOrg(token.org());
        token.user().setAccount(user.getAccount());

        //noinspection unchecked
        final List<Number> ids = em.createNativeQuery("SELECT FUNC_ID FROM SYS_USER_FUNC WHERE USER_ID = :userId")
            .setParameter("userId", user.getId()).getResultList();
        final List<Integer> intIds = new LinkedList<>();
        for (Number n : ids) {
            intIds.add(n.intValue());
        }
        final SysFunc root = em.find(SysFunc.class, -1000000);
        // function tree
        //noinspection ConstantConditions
        BeanUtils.copyProperties(fetchChildren(root, intIds), token.funcTree());
        // function list
        token.funcs().addAll(getFuncList(token.funcTree()));
        return token;
    }

    /**
     * 从根菜单开始获取数据传输对象.
     * @param node 当前节点
     * @param ids 权限列表
     * @return 权限过滤后的跟菜单数据传输对象
     */
    private EshFunc fetchChildren(SysFunc node, List<Integer> ids) {
        if (node.getIsLeaf() && !ids.contains(node.getId())) {
            // 如果是最终功能，而且，不在权限列表中，那么返回空，等待“不”加到列表中
            return null;
        }
        final EshFunc func = new EshFunc();
        func.setId(node.getId());
        func.setCode(node.getCode());
        func.setName(node.getName());
        func.setSeq(node.getSeq());
        func.setIcon(node.getIcon());

        func.setParentId(node.getParent().getId());
        func.setChildren(new LinkedList<>());
        func.setIsRoot(node.getIsRoot());
        func.setIsLeaf(node.getIsLeaf());

        if (!node.getIsLeaf()) {
            for (SysFunc child : node.getChildren()) {
                final EshFunc item = fetchChildren(child, ids);
                if (item != null) {
                    func.getChildren().add(item);
                }
            }
        }
        // 如果作为分菜单没有任何子菜单，则返回空，等待“不”加到列表中
        return !func.getIsLeaf() && func.getChildren().size() == 0 && !func.getIsRoot() ? null : func;
    }

    /**
     * 根据树结构菜单获得菜单列表.
     * @param func 树节点
     * @return 菜单列表
     */
    private List<Func> getFuncList(FuncTree func) {
        final List<Func> result = new LinkedList<>();
        result.add(func);
        if (func.getChildren().size() > 0) {
            for (FuncTree child : func.getChildren()) {
                result.addAll(getFuncList(child));
            }
        }
        return result;
    }

}
