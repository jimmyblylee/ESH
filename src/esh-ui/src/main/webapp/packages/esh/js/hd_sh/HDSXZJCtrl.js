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
 * Description: TODO.<br>
 * Created by Jimmybly Lee on 2017/8/8.
 * @author Jimmybly Lee
 */

angular.module("WebApp").controller("HDSXZJCtrl", ["$rootScope", "$scope", "$ajaxCall", function($rootScope, $scope, $ajaxCall){
    $scope.load = function(hdId) {
        $ajaxCall.post({
            data: {
                controller: "HDController",
                method: "getHDInfo",
                id: hdId
            },
            success: function (res) {
                $scope.hd = res.result;
            }
        });
    };

    /**
     * 获得所有轮随机专家总数
     */
    $scope.getSJZJZS = function() {
        var count = 0;
        $.each($scope.hd.sjList, function(idx, sj) {
            count += sj.sjjgList.length;
        });
        return count;
    };

    /**
     * 随机抽取，抽取后更新数据
     */
    $scope.takeSJ = function() {
        $ajaxCall.post({
            data: {
                controller: "HDController",
                method: "suiJi",
                id: $scope.hd.id
            },
            success: function () {
                $scope.load($scope.hd.id);
            }
        });
    };

    /**
     * 确认随机结果的专家参加活动.
     * @param sjjg 随机结果
     */
    $scope.queRenCanJia = function(sjjg) {
        bootbox.dialog({
            title: "请确认",
            message: "是否确定" + sjjg.zj.name + "不参加此次活动？",
            buttons: {
                main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                danger: {
                    label: " 确 定 ！ ",
                    className: "fa fa-hand-o-up green",
                    callback: function () {
                        $ajaxCall.post({
                            data: {
                                controller: "HDController",
                                method: "queRenCanJia",
                                id: sjjg.id
                            },
                            success: function () {
                                $scope.load($scope.hd.id);
                            }
                        });
                    }
                }
            }
        });
    };

    /**
     * 确认随机结果的专家参加活动.
     * @param sjjg 随机结果
     */
    $scope.queRenBuCanJia = function(sjjg) {
        bootbox.prompt({
            title: "是否确定" + sjjg.zj.name + "不参加此次活动？",
            callback: function (message) {
                if (message && message !== null && message !== "") {
                    $ajaxCall.post({
                        data: {
                            controller: "HDController",
                            method: "queRenBuCanJia",
                            id: sjjg.id,
                            liYou: message
                        },
                        success: function () {
                            $scope.load($scope.hd.id);
                        }
                    });
                } else {
                    alert("请填写不参加的理由");
                }
            }
        });
    };

    $scope.wanChengShaiXuan = function() {
        bootbox.dialog({
            title: "请确认",
            message: "是否确定完成筛选？",
            buttons: {
                main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                danger: {
                    label: " 确 定 ！ ",
                    className: "fa fa-hand-o-up green",
                    callback: function () {
                        $ajaxCall.post({
                            data: {
                                controller: "HDController",
                                method: "wanChengShaiXuan",
                                id: $scope.hd.id
                            }
                        });
                    }
                }
            }
        });
    }

}]);
