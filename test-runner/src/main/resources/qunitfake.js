//@ sourceURL=test-runner/src/main/resources/qunitfake.js


var QUnit = {
    test : function ( testName, expected, callback, async ) {
        // todo check number of params
        tests[testName] = expected;
    }
};

var currentMessage = '';
ok = function(passed, message) { currentMessage = message; }

var tests = {};

getTestNames = function() { return Java.to(Object.keys(tests)); }

runTest = function(testName) {
    tests[testName]();
    return currentMessage;
}

var currentInspectObject = null;

inspect = function(obj) {
    var value = obj.value
    print(value)
    currentInspectObject = obj;
}

getCurrentInspectObject = function() { print('get: ', currentInspectObject); return currentInspectObject; }
