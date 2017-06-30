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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ClassName : AppException <br>
 * Description : Application Exception <br>
 * Create Time : 2016-09-17 <br>
 * @author  jimmyblylee@126.com
 */
@SuppressWarnings("WeakerAccess")
public class AppException extends Exception {

    private static final long serialVersionUID = 4998100661136469807L;

    /** Default error code. */
    private final String CNS_DEFAULT_ERR_CODE = "ERR-UNKNOWN-001";
    /** Error code.*/
    //CSOFF: MutableException
    private String errCode = CNS_DEFAULT_ERR_CODE;
    //CSON: MutableException

    /**
     * Create a new instance of AppException. <br>
     * {@code errCode = "ERR-UNKNOWN-001"}
     *
     * @param message the message
     */
    public AppException(String message) {
        super(message);
    }

    /**
     * Create a new instance of AppException. <br>
     * {@code errCode = "ERR-UNKNOWN-001"}
     *
     * @param message the message
     * @param cause error cause
     */
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new instance of AppException. <br>
     *
     * @param code error code string which will be managed by message.properties
     * @param messages the message
     */
    public AppException(String code, String messages) {
        super(messages);
        this.errCode = code;
    }

    /**
     * Create a new instance of AppException.
     *
     * @param code error code string which will be managed by message.properties
     * @param message the message
     * @param cause error cause
     */
    public AppException(String code, String message, Throwable cause) {
        super(message, cause);
        this.errCode = code;
    }

    /**
     * @return the errorCode.
     */
    @SuppressWarnings("unused")
    public String getErrCode() {
        return errCode;
    }

    /**
     * @param errCode the errorCode to set.
     */
    @SuppressWarnings("unused")
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
     * Description : get strack string from exception trace <br>
     * Create Time: 2016-09-17 <br>
     * Create by : jimmyblylee@126.com <br>
     *
     * @return exception stack as one single string
     */
    @SuppressWarnings("unused")
    public String getStackString() {
        String exceptionStack = "";
        if (this.getCause() != null) {
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw);
            try {
                this.printStackTrace(pw);
                exceptionStack = sw.toString();
            } finally {
                try {
                    sw.close();
                    pw.close();
                } catch (IOException ex) {
                    // ignore
                }
            }
        }
        return exceptionStack;
    }
}
