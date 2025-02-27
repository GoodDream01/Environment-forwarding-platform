var pathName = window.document.location.pathname;
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

function findYc() {
	var yc = null;
    $.ajax({
        url: projectName + "/cgq/dpzs/findYc.do",
        dataType: 'json',
        async: false,
        success: function (data) {
        	yc = data;
        },
    });
    return yc;
}

function findBzx() {
	var bzx = null;
    $.ajax({
        url: projectName + "/cgq/dpzs/findBzx.do",
        dataType: 'json',
        async: false,
        success: function (data) {
        	bzx = data;
        },
    });
    return bzx;
}

function findFx() {
	var fx = null;
    $.ajax({
        url: projectName + "/cgq/dpzs/findFx.do",
        dataType: 'json',
        async: false,
        success: function (data) {
        	fx = data;
        },
    });
    return fx;
}

function findFs() {
	var fs = null;
    $.ajax({
        url: projectName + "/cgq/dpzs/findFs.do",
        dataType: 'json',
        async: false,
        success: function (data) {
        	fs = data;
        },
    });
    return fs;
}

var anquanchangtongData_0 = {
    shigushu: {
        nameArray: findYc().names,
        zongValueArray: findYc().value1s
    },
    zhenggailv: {
        nameArray: findBzx().names,
        valueArray: findBzx().value1s
    },
    yongduzhishu: {
        nameArray: findFx().names,
        dqdValueArray: findFx().value1s,
        pjdValueArray: findFx().value2s
    },
    wangrenshigu: {
    	 nameArray: findFs().names,
         dqzValueArray: findFs().value1s,
         pjzValueArray: findFs().value2s
    }
}