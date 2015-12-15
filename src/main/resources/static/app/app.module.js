/**
 * Created by maks-pain on 12/15/15.
 */

define([], function () {
    'use strict';

    console.log("[APP.MODULE] Init");
    return angular.module('twidder', [
        'ui.bootstrap'
    ]).config(function ($logProvider, $provide) {

        $logProvider.debugEnabled(true);

        var isTraceEnabled = true;


        $provide.decorator('$log', ['$delegate', function ($delegate) {

            var _$log = (function ($delegate) {
                return {
                    log: $delegate.log,
                    info: $delegate.info,
                    warn: $delegate.warn,
                    debug: $delegate.debug,
                    trace: $delegate.debug,
                    error: $delegate.error
                }
            })($delegate);

            $delegate.log = decorate($delegate.log);
            $delegate.info = decorate($delegate.info);
            $delegate.warn = decorate($delegate.warn);
            $delegate.debug = decorate($delegate.debug);
            $delegate.trace = decorate($delegate.trace);
            $delegate.error = decorate($delegate.error);

            $delegate.getInstance = function (className) {
                return {
                    isTrace: isTraceEnabled,
                    log: decorate(_$log.log, className),
                    info: decorate(_$log.info, className),
                    warn: decorate(_$log.warn, className),
                    debug: decorate(_$log.debug, className),
                    error: decorate(_$log.error, className),
                    trace: (function () {
                        if (isTraceEnabled) {
                            return decorate(_$log.trace, className)
                        } else {
                            return function () {
                            }
                        }
                    })()
                }
            }

            return $delegate;

            function decorate(fn, className) {
                return function () {
                    var args = [].slice.call(arguments),
                        now = new Date();
                    var millis = now.getMilliseconds(),
                        secs = now.getSeconds(),
                        mins = now.getMinutes(),
                        hours = now.getHours();
                    now = hours +
                    ':' + (mins < 10 ? '0' + mins : mins) +
                    ':' + (secs < 10 ? '0' + secs : secs) +
                    '.' + (millis < 100 ? '0' + millis : millis);
                    args.unshift(
                        '[%c' + now + (className ? ':%c:' + className : '') + '%c]',
                        "color: green; font-size: x-small",
                        (className ? "color: red; font-size: x-small" : "color: black"),
                        (className ? "color: black" : "")
                    );
                    fn.apply(null, args)
                }
            }

        }])
    })
});