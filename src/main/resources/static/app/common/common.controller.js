/**
 * Created by maks-pain on 12/17/15
 */

define(['./common.module', './common.states', './ws.service', '../user/user.service'], function () {
    "use strict";

    console.log('[COMMON.CONTROLLER] Load', new Date().toISOString());

    CommonCtrl.$inject = ['$log', '$scope', '$q', 'wsService', 'userService'];

    return angular
        .module('twidder.common')
        .controller('CommonCtrl', CommonCtrl);


    function CommonCtrl($log, $scope, $q, wsService, userService) {
        $log = $log.getInstance('CommonCtrl');
        $log.info('[INIT]');

        $scope.title = 'Twidder application!';
        $scope.currentUser = 'unknown';

        $scope.newUser = {};
        $scope.newUserName = null;
        $scope.newUserPass = null;

        $scope.allUsers = [];
        $scope.userSubscriptions = [];

        $scope.createUser = createUser;

        /// INIT ///
        userService.getCurrentUser().then(function (user) {
            $scope.currentUser = user;
            //TODO: get user's subscriptions
            manageUsers();
            $log.debug('Current user', $scope.currentUser)
        });

        function manageUsers() {
            userService
                .getAllUsers()
                .then(function (msg) {
                    updateAllUsers(msg);
                    userService.subscribeToUpdates('/topic/user/all', updateAllUsers)
                });

            function updateAllUsers(msg) {
                $log.debug('Getting all users from msg', msg);
                $log.debug('Current users', $scope.allUsers);
                $scope.allUsers.length = 0;
                msg.map(function (u) {
                    $scope.allUsers.push(u)
                });
                $log.debug('Updated users list', $scope.allUsers);
            }
        }

        function createUser() {
            if ($scope.newUserName) {
                userService
                    .createUser({username: $scope.newUserName, passwordHash: $scope.newUserPass})
                    .then(function (u) {
                        $log.debug('New user created', u);
                        $scope.newUser.id = u.id;
                        $scope.newUser.username = u.username;
                    })
            } else {
                $log.error('User name is not defined!')
            }
        }


    }

})