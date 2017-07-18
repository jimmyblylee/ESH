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
angular.module('WebApp').controller('ZJDJUpdateCtrl', ['$scope', "$ajaxCall", function ($scope, $ajaxCall) {

    /*
     * 开始 与 ZJGLUpdateCtrl 一致.
     */
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
        $ajaxCall.post({
            data: {
                controller: "ZJInfoController",
                method: $scope.method,
                entity: JSON.stringify($scope.entity)
            },
            success: function () {
                $scope.$emit("submitted");
            }
        });
    };

    /**
     * 准备设置图片
     */
    $scope.prepare2SetPhoto = function () {
        var uploadModalScope = $("#uploadPhoto").scope();
        uploadModalScope.init();
        uploadModalScope.$on("submit", function (event, data) {
            $scope.entity.jb_zp = data;
        });
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
    $scope.remove = function (idx, key, id, type) {
        $scope.entity[key].splice(idx, 1);
        if (id) {
            $ajaxCall.post({
                data: {
                    controller: "ZJInfoController",
                    method: "removeFz",
                    id: id,
                    type: type
                }
            })
        }
    };
    /*
     * 结束 与 ZJGLUpdateCtrl 一致.
     */
}]);
