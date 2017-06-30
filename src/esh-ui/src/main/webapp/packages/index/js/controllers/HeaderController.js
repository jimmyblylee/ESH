/**
 * Created by Jimmybly Lee on 2017/6/30.
 */
/* Setup Layout Part - Header */
MetronicApp.controller('HeaderController', ['$rootScope', '$scope', '$ajaxCall', function($rootScope, $scope, $ajaxCall) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initHeader(); // init header
    });
    $scope.login = function(user) {
        $ajaxCall.post({
            data: {
                "controller": "LoginController",
                "method": "login",
                "account": user.account,
                "pwd": user.pwd
            },
            success: function(req) {
                if (req.result) {
                    $rootScope.reloadToken();
                    $("#loginModal").modal('hide')
                } else {
                    bootbox.dialog({
                        title: "提示",
                        message: "用户名或密码错误，请检查用户名和密码！",
                        buttons: {
                            success: {
                                label: "确定",
                                className: "green"
                            }
                        }
                    })
                }
            }
        });
    };
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
