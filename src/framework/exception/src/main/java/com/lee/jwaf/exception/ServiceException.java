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

package com.lee.jwaf.exception;

/**
 * ClassName : ServiceException <br>
 * Description : Service Layer Exception <br>
 * Create Time : 2016-09-17 <br>
 * @author jimmyblylee@126.com
 */
@SuppressWarnings("WeakerAccess")
public class ServiceException extends AppException {

    private static final long serialVersionUID = 5850704921792051901L;

    /**
     * Create a new instance of ServiceException. <br>
     * {@code errCode = "ERR-UNKNOWN-001"}
     *
     * @param message the message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Create a new instance of ServiceException. <br>
     * {@code errCode = "ERR-UNKNOWN-001"}
     *
     * @param message the message
     * @param cause error cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new instance of ServiceException. <br>
     *
     * @param code error code string which will be managed by message.properties
     * @param message the message
     */
    public ServiceException(String code, String message) {
        super(code, message);
    }

    /**
     * Create a new instance of ServiceException.
     *
     * @param code error code string which will be managed by message.properties
     * @param message the message
     * @param cause error cause
     */
    public ServiceException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
