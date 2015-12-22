/**
 * Created by maks-pain on 12/22/15.
 */

define(['./user.module'], function () {
    "use strict";

    console.log('[USER.SERVICE] Load');

    userService.$inject = ['$log', '$q']

    return angular.module('twidder.user')
        .factory('userService', userService);


    function userService($log, $q) {
        $log = $log.getInstance('userService');
        $log.info('[INIT]')


        return {
            name: 'userService'
        }

    }


});