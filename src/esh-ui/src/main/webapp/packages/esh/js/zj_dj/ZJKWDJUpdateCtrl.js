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
 * Description: 更新专家控制器.<br>
 * Created by Jimmybly Lee on 2017/7/3.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('ZJKWDJUpdateCtrl', ['$scope', "$ajaxCall", function ($scope, $ajaxCall) {

    /* 与专家管理一致 开始. */

    // 政治面貌字典列表
    $ajaxCall.getDictList($scope, "ZZMM", 'zzmmList');
    // 省字典列表
    $ajaxCall.getDictList($scope, "SHENG", 'shengList');
    // 文化程度典列表
    $ajaxCall.getDictList($scope, "WHCD", 'whcdList');
    // 专业类别典列表
    $ajaxCall.getDictList($scope, "ZYLB", 'zylbList');

    /**
     * 提交表单
     */
    $scope.submit = function () {
        if ($scope.validate()) {
            $ajaxCall.post({
                data : {
                    controller: "ZJInfoController",
                    method: $scope.method,
                    entity : JSON.stringify($scope.entity)
                },
                success: function(res) {
                    if ($scope.method === "create") {
                        // 如果是创建，则获取生成的id后自动通过.
                        $ajaxCall.post({
                            data : {
                                controller: "ZJInfoController",
                                method: "tongGuo",
                                id: res.result
                            },
                            success: function() {
                                $scope.$emit("submitted");
                            }
                        });
                    } else {
                        // 如果是更新，则获取id后自动通过.
                        $ajaxCall.post({
                            data : {
                                controller: "ZJInfoController",
                                method: "tongGuo",
                                id: $scope.entity.id
                            },
                            success: function() {
                                $scope.$emit("submitted");
                            }
                        });
                    }
                }
            });
            $(".modal").modal('hide');
        }
    };

    /**
     * 当专业类别被选中时，添加到专业类别列表中
     * @param item
     */
    $scope.onZYLBSelected = function (item) {
        if ($.inArray(item, $scope.entity.zylbList) < 0) {
            $scope.entity.zylbList.push({zylb: item});
        }
    };

    /**
     * 删除辅助信息
     * @param idx 序号
     * @param key entity中的名称
     * @param id 辅助信息的id
     * @param type 辅助信息的java类
     */
    $scope.remove = function(idx, key, id, type) {
        $scope.entity[key].splice(idx,1);
        if (id) {
            $ajaxCall.post({
                data : {
                    controller: "ZJInfoController",
                    method: "removeFz",
                    id : id,
                    type: type
                }
            })
        }
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


        nullCheck("jb_xm", "姓名");
        nullCheck("jb_dh", "电话");
        nullCheck("jb_sj", "手机");
        nullCheck("gz_gzdw", "工作单位");
        nullCheck("gz_gzdw_dz_sheng", "所在省份");
        nullCheck("gz_gzdw_zw", "职务");
        nullCheck("jb_zzmm", "政治面貌");
        nullCheck("jy_whcd", "文化程度");

        if ($scope.entity.zylbList.length === 0) {
            message += "至少选择一个专业类别;";
        }

        if (message.length > 0) {
            App.alert({
                container: $("#updateZJKWErrorMsgDiv"),
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
    /* 与专家管理一致 结束. */
}]);
