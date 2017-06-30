/**
 * Created by Jimmybly Lee on 2017/6/30.
 */
/* Setup App Main Controller */
MetronicApp.controller('AppController', ['$scope', '$rootScope', "$http", function($scope, $rootScope, $http) {
    $scope.$on('$viewContentLoaded', function() {
        App.initComponents(); // init core components
        //Layout.init(); //  Init entire layout(header, footer, sidebar, etc) on page load if the partials included in server side instead of loading with ng-include directive
    });
}]);
