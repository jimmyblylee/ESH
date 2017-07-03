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
 * Description: 更新字典控制器.<br>
 * Created by Jimmybly Lee on 2017/7/3.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('DictUpdateCtrl', ['$scope', "$ajaxCall", function ($scope, $ajaxCall) {

    /**
     * 提交表单
     */
    $scope.submit = function () {
        $ajaxCall.post({
            data: {
                controller: "DictController",
                method: $scope.method,
                entity: JSON.stringify($scope.entity)
            },
            success: function () {
                $scope.$emit("submitted");
            }
        });
    };
    /**
     * 初始化下拉菜单
     */
    $scope.$parent.$on('$includeContentLoaded', function (event, data) {
        $(".update-dict-parent-select-ajax").select2({
            width: "off",
            ajax: {
                url: "mvc/dispatch",
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    return {
                        controller: "DictController", method: "query", start: 0, limit: 1000,
                        condition: JSON.stringify({nature : params.term, isNature: true, isEnabled: true}) // search term
                    };
                },
                processResults: function (data) {
                    // parse the results into the format expected by Select2.
                    // since we are using custom formatting functions we do not need to
                    // alter the remote JSON data
                    return {
                        results: data.result
                    };
                },
                cache: true
            },
            escapeMarkup: function (markup) {
                return markup;
            }, // let our custom formatter work
            minimumInputLength: 3,
            templateResult: function (repo) {
                if (repo.loading) return repo.text;

                var markup = "<div class='select2-result-repository clearfix'>" +
                    "<div class='select2-result-repository__avatar'><img src='" + repo.owner.avatar_url + "' /></div>" +
                    "<div class='select2-result-repository__meta'>" +
                    "<div class='select2-result-repository__title'>" + repo.full_name + "</div>";

                if (repo.description) {
                    markup += "<div class='select2-result-repository__description'>" + repo.description + "</div>";
                }

                markup += "<div class='select2-result-repository__statistics'>" +
                    "<div class='select2-result-repository__forks'><span class='glyphicon glyphicon-flash'></span> " + repo.forks_count + " Forks</div>" +
                    "<div class='select2-result-repository__stargazers'><span class='glyphicon glyphicon-star'></span> " + repo.stargazers_count + " Stars</div>" +
                    "<div class='select2-result-repository__watchers'><span class='glyphicon glyphicon-eye-open'></span> " + repo.watchers_count + " Watchers</div>" +
                    "</div>" +
                    "</div></div>";

                return markup;
            },
            templateSelection: function (repo) {
                return repo.full_name || repo.text;
            }
        });
    });

}]);
