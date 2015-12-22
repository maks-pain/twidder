/**
 * Created by maks-pain on 12/17/15
 */

define(['./common.module', './common.states', './ws.service', '../user/user.service'], function () {
    "use strict";

    console.log('[COMMON.CONTROLLER] Load');

    CommonCtrl.$inject = ['$log', '$scope', '$q', 'wsService', 'userService'];

    return angular.module('twidder.common')
        .controller('CommonCtrl', CommonCtrl);


    function CommonCtrl($log, $scope, $q, wsService, userService) {
        $log = $log.getInstance('CommonCtrl');
        $log.info('[INIT]');

        console.log('$log > ', $log);

        $scope.title = 'Twidder application!'


    }

})