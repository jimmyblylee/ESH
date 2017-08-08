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

/**
 * Description: 活动基本信息登记控制器.<br>
 * Created by Jimmybly Lee on 2017/7/25.
 * @author Jimmybly Lee
 */
angular.module("WebApp").controller("HDDJUpdateCtrl", ["$rootScope", "$scope", "$ajaxCall", "$listService", function($rootScope, $scope, $ajaxCall){

    /**
     * 提交表单
     */
    $scope.submit = function() {
        $ajaxCall.post({
            data : {
                controller: "HDController",
                method: $scope.method,
                entity : JSON.stringify($scope.entity)
            },
            success: function(res) {
                if ($scope.isCreate) {
                    $scope.entity.id = res.result;
                }
            }
        });
    };


    /**
     * 校验当前实体，一些必须为非空的东西需要有所提示
     */
    $scope.validate = function() {
        var message = "";
        var nullCheck = function(key, msg, parent) {
            var dom = $("*[ng-model='entity." + key + "'").parents(parent ? "." + parent : ".form-group");
            dom.removeClass("has-error");
            if ($scope.entity[key] === undefined || $scope.entity[key].length === 0) {
                message += "<span class='margin-right-10'><b>" + msg + "</b>不能为空;</span>";
                dom.addClass("has-error");
            }
        };

        nullCheck("mc", "主题");
        nullCheck("ks", "开始时间");
        nullCheck("zz", "终止时间");
        nullCheck("xz", "性质");
        nullCheck("bm", "组织部门");
        nullCheck("dz", "活动地址");
        nullCheck("xq", "专家人数");
        nullCheck("ms", "活动描述");

        if (message.length > 0) {
            App.alert({
                container: $("#updateHDErrorMsgDiv"),
                place: 'append', // append or prepent in container
                type: 'warning', // alert's type
                message: message, // alert's message
                close: true, // make alert closable
                icon: 'fa fa-warning' // put icon class before the message
            });
            return false;
        } else {
            $('.custom-alerts').remove();
            return true;
        }
    };

    /**
     * 放弃.
     */
    $scope.discard = function() {
        if ($scope.isCreate && $scope.entity.id) {
            // 如果是新建的时候放弃，需要在数据库中删除已经创建的活动数据
            $ajaxCall.post({
                data : {
                    controller: "HDController",
                    method: "doRealRemove",
                    id: $scope.entity.id
                },
                success: function() {
                }
            });
        }
        $("#updateHDModalDiv").modal('hide');
        $scope.$emit("submitted");
    };

    /**
     * 重置向导状态
     */
    $scope.reset = function() {
        $scope.isCreate = $scope.method === "create";
        /* 非评审类 开始 */
        $("#hd_fpsl_jbxx").removeClass("active").addClass("active");
        $("#hd_fpsl_xzzj").removeClass("active");
        $("#hd_fpsl_qrxx").removeClass("active");

        $("#hd_fpsl_nav_jbxx").removeClass("active").removeClass("done").addClass("active");
        $("#hd_fpsl_nav_xzzj").removeClass("active").removeClass("done");
        $("#hd_fpsl_nav_qrxx").removeClass("active").removeClass("done");
        /* 非评审类 结束 */

        /* 评审类 开始 */
        $("#hd_psl_jbxx").removeClass("active").addClass("active");
        $("#hd_psl_xq").removeClass("active");
        $("#hd_psl_qrxx").removeClass("active");

        $("#hd_psl_nav_jbxx").removeClass("active").removeClass("done").addClass("active");
        $("#hd_psl_nav_xq").removeClass("active").removeClass("done");
        $("#hd_psl_nav_qrxx").removeClass("active").removeClass("done");
        /* 评审类 结束 */
    };

    /* 非评审类 开始 */
    /**
     * 基本信息，下一步
     */
    $scope.fpslJbxxNext = function() {
        if ($scope.validate()) {
            if ($scope.isCreate && $scope.entity.id) {
                // 是创建行为，而且已经创建过了活动，需要变为更新
                $scope.method = "update";
            }
            $scope.submit();
            $("#hd_fpsl_nav_jbxx").removeClass("active").addClass("done");
            $("#hd_fpsl_nav_xzzj").addClass("active");

            $("#hd_fpsl_jbxx").removeClass("active");
            $("#hd_fpsl_xzzj").addClass("active");
        }
    };

    /**
     * 选择专家，上一步
     */
    $scope.fpslXzzjLast = function() {
        $("#hd_fpsl_nav_jbxx").removeClass("done").addClass("active");
        $("#hd_fpsl_nav_xzzj").removeClass("active");

        $("#hd_fpsl_xzzj").removeClass("active");
        $("#hd_fpsl_jbxx").addClass("active");
    };

    /**
     * 选择专家，下一步
     */
    $scope.fpslXzzjNext = function() {
        $("#hd_fpsl_nav_qrxx").addClass("active");
        $("#hd_fpsl_nav_xzzj").removeClass("active").addClass("done");

        $("#hd_fpsl_xzzj").removeClass("active");
        $("#hd_fpsl_qrxx").addClass("active");
    };

    /**
     * 确认信息，上一步
     */
    $scope.fpslQrxxLast = function() {
        $("#hd_fpsl_nav_qrxx").removeClass("active");
        $("#hd_fpsl_nav_xzzj").addClass("active").removeClass("done");

        $("#hd_fpsl_xzzj").addClass("active");
        $("#hd_fpsl_qrxx").removeClass("active");
    };
    /* 非评审类 结束 */

    /* 评审类 开始 */
    /**
     * 基本信息，下一步
     */
    $scope.pslJbxxNext = function() {
        if ($scope.validate()) {
            if ($scope.isCreate && $scope.entity.id) {
                // 是创建行为，而且已经创建过了活动，需要变为更新
                $scope.method = "update";
            }
            $scope.submit();
            $("#hd_psl_nav_jbxx").removeClass("active").addClass("done");
            $("#hd_psl_nav_xq").addClass("active");

            $("#hd_psl_jbxx").removeClass("active");
            $("#hd_psl_xq").addClass("active");
        }
    };

    /**
     * 需求，上一步
     */
    $scope.pslXqLast = function() {
        $("#hd_psl_nav_jbxx").removeClass("done").addClass("active");
        $("#hd_psl_nav_xq").removeClass("active");

        $("#hd_psl_xq").removeClass("active");
        $("#hd_psl_jbxx").addClass("active");
    };

    /**
     * 需求，下一步
     */
    $scope.pslXqNext = function() {
        $("#hd_psl_nav_qrxx").addClass("active");
        $("#hd_psl_nav_xq").removeClass("active").addClass("done");

        $("#hd_psl_xq").removeClass("active");
        $("#hd_psl_qrxx").addClass("active");
    };

    /**
     * 确认信息，上一步
     */
    $scope.pslQrxxLast = function() {
        $("#hd_psl_nav_qrxx").removeClass("active");
        $("#hd_psl_nav_xq").addClass("active").removeClass("done");

        $("#hd_psl_xq").addClass("active");
        $("#hd_psl_qrxx").removeClass("active");
    };
    /* 评审类 结束 */
}]);
