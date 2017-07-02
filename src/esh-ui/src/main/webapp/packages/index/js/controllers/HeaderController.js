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
 * Description: HeaderController.<br>
 * Created by Jimmybly Lee on 2017/6/30.
 * @author Jimmybly Lee
 */
/* Setup Layout Part - Header */
angular.module('WebApp').controller('HeaderController', ['$rootScope', '$scope', '$ajaxCall', function($rootScope, $scope, $ajaxCall) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initHeader(); // init header
    });
    $scope.logout = function() {
        bootbox.dialog({
            title: "请确认",
            message: "是否确认退出?",
            buttons: {
                main: {
                    label: " 取 消 ",
                    className: "dark icon-ban btn-outline"
                },
                danger: {
                    label: " 退 出！ ",
                    className: "red icon-logout",
                    callback: function() {
                        $ajaxCall.post({
                            data: {
                                "controller": "LoginController",
                                "method": "logout"
                            },
                            success: function() {
                                $rootScope.reloadToken();
                            }
                        });
                    }
                }
            }
        });
    }
}]);
