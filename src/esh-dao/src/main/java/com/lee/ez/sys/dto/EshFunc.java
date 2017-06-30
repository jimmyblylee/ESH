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

package com.lee.ez.sys.dto;

import java.io.Serializable;
import java.util.List;

import com.lee.jwaf.token.*;

/**
 * Description: 功能（菜单）数据传输类型.<br>
 * Created by Jimmybly Lee on 2017/6/28.
 *
 * @author Jimmybly Lee
 */
public final class EshFunc implements FuncTree, Serializable {

    /** SerialVersionUID. */
    private static final long serialVersionUID = 3353231166287810537L;

    // CSOFF: JavadocVariable
    private Integer id;
    private String code;
    private String name;
    private Integer seq;
    private String icon;

    private Integer parentId;
    private List<FuncTree> children;
    private Boolean isRoot;
    private Boolean isLeaf;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getSeq() {
        return seq;
    }

    @Override
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public Integer getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public List<FuncTree> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<FuncTree> children) {
        this.children = children;
    }

    public Boolean getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    @Override
    public Dict getType() {
        return null;
    }

    @Override
    public void setType(Dict type) {

    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public void setUrl(String url) {

    }

    @Override
    public Boolean getIsVisable() {
        return false;
    }

    @Override
    public void setIsVisable(Boolean isVisable) {

    }
}
