/**
 * Created by maks on 12/22/15.
 */

define(['./common.module'], function () {
    "use strict";

    configRoutes.$inject = ['$stateProvider', '$urlRouterProvider'];

    return angular.module('twidder.common')
        .config(configRoutes);

    function configRoutes($stateProvider, $urlRouterProvider) {
        //$urlRouterProvider
        //    .when('/','/a/b');

        $stateProvider
            .state('common', {
                url: '/',
                controller: 'CommonCtrl',
                templateUrl: 'app/common/common.view.html'
            })

    }

});