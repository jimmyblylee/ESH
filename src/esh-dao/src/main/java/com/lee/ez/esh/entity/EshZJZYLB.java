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
 * Description: 专家专业类别.<br>
 * Created by Jimmybly Lee on 2017/7/12.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_ZJ_ZYLB")
@SuppressWarnings("unused")
public class EshZJZYLB implements EshZJFZ {

    private static final long serialVersionUID = 751430676588807549L;
    /** 研究成果ID.**/
    @Id
    @Column(name = "ZYLB_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;
    /** 专家.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZJ_ID")
    private EshZJ zj;

    /** 专业类别：文化程度，关联字典项“ZYLB”.**/
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "ZYLB", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'ZYLB'")) })
    private SysDict zylb;

    /**
     * Get the id.
     *
     * @return return the id
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to set
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the zj.
     *
     * @return return the zj
     */
    @Override
    public EshZJ getZj() {
        return zj;
    }

    /**
     * Set zj.
     *
     * @param zj the zj to set
     */
    @Override
    public void setZj(EshZJ zj) {
        this.zj = zj;
    }

    /**
     * Get the zylb.
     *
     * @return return the zylb
     */
    public SysDict getZylb() {
        return zylb;
    }

    /**
     * Set zylb.
     *
     * @param zylb the zylb to set
     */
    public void setZylb(SysDict zylb) {
        this.zylb = zylb;
    }
}
