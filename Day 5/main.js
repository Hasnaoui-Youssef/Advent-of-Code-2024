"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var fs = require("fs");
fs.readFile('input.txt', 'utf8', function (err, data) {
    if (err) {
        console.log(err);
        return;
    }
    var a = data.trim().split('\r\n').filter(function (line) { return line.trim().length !== 0; }).map(function (line) { return line.split('|'); });
    var _a = splitArray(a), x = _a[0], y = _a[1];
    var hm = new Map();
    x.forEach(function (e) {
        var _a;
        var k = e[0], v = e[1];
        if (hm.has(Number(k))) {
            (_a = hm.get(Number(k))) === null || _a === void 0 ? void 0 : _a.add(Number(v));
        }
        else {
            hm.set(Number(k), new Set([Number(v)]));
        }
    });
    var compEL = y.map(function (element) {
        return element[0].split(',');
    });
    var total = compEL.filter(function (line) {
        var varFlag = true;
        line.forEach(function (char, index) {
            if (!varFlag || index === 0) {
                return;
            }
            if (hm.has(Number(char))) {
                var set = hm.get(Number(char));
                for (var i = index - 1; i >= 0; i--) {
                    if (set === null || set === void 0 ? void 0 : set.has(Number(line[i]))) {
                        //console.log(`Found violation at ${char} and ${line[i]}`);
                        varFlag = false;
                        break;
                    }
                }
            }
        });
        return !varFlag;
    }).map(function (line) {
        var _a;
        var n = line.length;
        var s = true;
        while (s) {
            s = false;
            for (var i = 1; i < n; i++) {
                for (var j = i - 1; j >= 0; j--) {
                    if (hm.has(Number(line[i]))) {
                        if ((_a = hm.get(Number(line[i]))) === null || _a === void 0 ? void 0 : _a.has(Number(line[j]))) {
                            var aux = line[i];
                            line[i] = line[j];
                            line[j] = aux;
                            s = true;
                        }
                    }
                }
            }
        }
        return line;
    })
        .map(function (line) {
        console.log(line);
        return Number(line[Math.floor(line.length / 2)]);
    }).reduce(function (acc, curr) {
        return acc + curr;
    }, 0);
    console.log(total);
    //console.log(hm);
});
function splitArray(arr) {
    var fh = arr.filter(function (e) { return e.length === 2; });
    var sh = arr.filter(function (e) { return e.length === 1; });
    return [fh, sh];
}
