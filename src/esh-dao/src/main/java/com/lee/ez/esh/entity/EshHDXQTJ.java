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

package com.lee.ez.esh.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import com.lee.ez.sys.entity.SysDict;

// CSOFF: MethodCount
// CSOFF: ParameterName
// CSOFF: MethodName
// CSOFF: MemberName
/**
 * Description: 活动需求条件表实体类.<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_HD_XQ_TJ")
@SuppressWarnings("unused")
public class EshHDXQTJ implements Serializable {
    private static final long serialVersionUID = 3934981306797460095L;
    /** 条件ID.**/
    @Id
    @Column(name = "TJ_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

    /** 需求.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "XQ_ID")
    private EshHDXQ xq;

    // CSOFF: LineLength
    /** 条件类型.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "TJ_LX", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'TJLX'")) })
    private SysDict lx;
    // CSON: LineLength

    /** 条件值.**/
    @Column(name = "TJ_Z")
    private String z;

    /**
     * Get the id.
     *
     * @return return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the xq.
     *
     * @return return the xq
     */
    public EshHDXQ getXq() {
        return xq;
    }

    /**
     * Set xq.
     *
     * @param xq the xq to set
     */
    public void setXq(EshHDXQ xq) {
        this.xq = xq;
    }

    /**
     * Get the lx.
     *
     * @return return the lx
     */
    public SysDict getLx() {
        return lx;
    }

    /**
     * Set lx.
     *
     * @param lx the lx to set
     */
    public void setLx(SysDict lx) {
        this.lx = lx;
    }

    /**
     * Get the z.
     *
     * @return return the z
     */
    public String getZ() {
        return z;
    }

    /**
     * Set z.
     *
     * @param z the z to set
     */
    public void setZ(String z) {
        this.z = z;
    }
}
