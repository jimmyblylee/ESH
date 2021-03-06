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
 * Description: 活动登记列表控制器.<br>
 * Created by Jimmybly Lee on 2017/7/25.
 * @author Jimmybly Lee
 */
angular.module("WebApp").controller("HDDJListCtrl", ["$rootScope", "$scope", "$ajaxCall", "$listService", function($rootScope, $scope, $ajaxCall, $listService){

    $.getJSON("packages/esh/views/com/cfg.json", function(data) {
        $scope.cfg = data;
    });

    $scope.condition = {qy: true, xz: "FPSL", zt: "DSB"};
    $listService.init($scope, {
        "controller": "HDController",
        "method": "query",
        callback: function (success) {
            $scope.list = success.data.result;
        }
    });

    /**
     * 刷新数据
     */
    $scope.load = function () {
        $scope.pageRequest.getResponse();
    };
    $scope.load();
    /**
     * 修改给定实体的状态
     * @param item 给定实体
     * @param isEnabled 新状态
     */
    $scope.changeStatus = function (item, isEnabled) {
        bootbox.dialog({
            title: "请确认",
            message: isEnabled ? "是否确认恢复该活动？" : "是否确认禁用该活动？",
            buttons: {
                main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                danger: {
                    label: isEnabled ? " 恢 复 ！ " : " 禁 用 ！",
                    className: isEnabled ? "fa fa-recycle green" : "fa fa-ban red",
                    callback: function () {
                        $ajaxCall.post({
                            data: {
                                controller: "HDController",
                                method: isEnabled ? "resume" : "remove",
                                id: item.id
                            },
                            success: function () {
                                $scope.load();
                            }
                        });
                    }
                }
            }
        });
    };

    /**
     * 准备添加实体
     */
    $scope.prepareToAdd = function () {
        var scope = $("#updateHDModalDiv").scope();
        scope.title = "添加活动信息";
        scope.method = "create";
        scope.entity = {
            xqList: []
        };
        scope.reset();

        scope.$on("submitted", function () {
            $scope.load();
        });
    };

    /**
     * 准备修改实体
     */
    $scope.prepareToUpdate = function (item) {
        var scope = $("#updateHDModalDiv").scope();
        scope.title = "修改活动信息";
        scope.method = "update";
        scope.entity = item;
        scope.reset();

        scope.$on("submitted", function () {
            $scope.load();
        });

        $('div[ng-controller="HDDJSelectZJCtrl"]').scope().loadSelected();
    };

    /**
     * 查看专家需求.
     * @param item
     */
    $scope.prepareToViewXQ = function (item) {
        var scope = $("#hdxqViewModalDiv").scope();
        scope.load(item.id);
    };

    /**
     * 上报
     * @param item 活动 entity
     */
    $scope.shangBao = function(item) {
        bootbox.dialog({
            title: "请确认",
            message: "是否上报？",
            buttons: {
                main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                danger: {
                    label: " 上 报 ！ ",
                    className: "fa fa-hand-o-up green",
                    callback: function () {
                        $ajaxCall.post({
                            data: {
                                controller: "HDController",
                                method: "shangBao",
                                id: item.id
                            },
                            success: function () {
                                $scope.load();
                            }
                        });
                    }
                }
            }
        });
    };

    /**
     * 启动
     * @param item 活动 entity
     */
    $scope.qiDong = function(item) {
        bootbox.dialog({
            title: "请确认",
            message: "是否启动？",
            buttons: {
                main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                danger: {
                    label: " 启 动 ！ ",
                    className: "fa fa-hand-o-up green",
                    callback: function () {
                        $ajaxCall.post({
                            data: {
                                controller: "HDController",
                                method: "qiDong",
                                id: item.id
                            },
                            success: function () {
                                $scope.load();
                            }
                        });
                    }
                }
            }
        });
    };

    /**
     * 启动
     * @param item 活动 entity
     */
    $scope.qiDong = function(item) {
        bootbox.dialog({
            title: "请确认",
            message: "是否开始？",
            buttons: {
                main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                danger: {
                    label: " 开 始 ！ ",
                    className: "fa fa-hand-o-up green",
                    callback: function () {
                        $ajaxCall.post({
                            data: {
                                controller: "HDController",
                                method: "ksiShi",
                                id: item.id
                            },
                            success: function () {
                                $scope.load();
                            }
                        });
                    }
                }
            }
        });
    };
}]);
