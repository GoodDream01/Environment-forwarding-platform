;(function (window, $, undefined) {
    if (typeof window.ScreenCommon != 'undefined') {
        return;
    }
    var screenCommon = {
        /**
         * 创建Echarts对象
         */
        initEchartWithId: function (id) {
            var dom = document.getElementById(id);
            var eObj = null;
            if (dom != null) {
                eObj = echarts.getInstanceByDom(dom);
                if (typeof eObj == 'undefined') {
                    eObj = echarts.init(dom);
                } else {
                    echarts.dispose(dom);
                    eObj = echarts.init(dom);
                }
            }

            return eObj;
        },
        /**
         * 多统计resize
         */
        resizeCharts: function (ids) {
            var echartsObj = null;
            var dom = null;
            for (var i = 0, len = ids.length; i < len; i++) {
                dom = document.getElementById(ids[i])
                if (dom != null) {
                    echartsObj = echarts.getInstanceByDom(dom);
                    if (typeof echartsObj != 'undefined') {
                        echartsObj.resize();
                    }
                }
            }
        },
        initArrWithKey: function (list, key) {
            var arr = [];
            for (var i = 0, len = list.length; i < len; i++) {
                var obj = list[i];
                arr.push(obj[key]);
            }
            return arr;
        },
        // 该方法需要animate.css支持https://daneden.github.io/animate.css/ (协议类型:MIT)
        doAnimate: function ($dom, animationName) {
            var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
            //var animationName = animate;
            $dom.addClass('animated ' + animationName).one(animationEnd, function() {
                $dom.removeClass('animated ' + animationName);
            });
        },
        /**
         * 刷新页面上所有统计
         */
        resizeAllCharts: function () {
            var echartsObj = null;
            var $echarts = $(".echart");
            $echarts.each(function () {
                echartsObj = echarts.getInstanceByDom(this);
                if (typeof echartsObj != 'undefined') {
                    echartsObj.resize();
                }
            });
        }
    };
    window.ScreenCommon = screenCommon;
})(window, jQuery);

/**
 * 大屏公共方法
 */
// 信息滚动
var MsgMove = function (className) {
    var _this = this;
    this.box = $("." + className);
    this.parentBox = this.box.parents(".msgMoveBox");
    this.boxH = this.parentBox.height();
    this.tBody = this.box.find("tbody");
    this.trArr = this.tBody.find("tr");
    if (this.box.siblings(".btn").length) {
        this.upBtn = this.box.siblings(".upBtn");//向上切换按钮
        this.downBtn = this.box.siblings(".downBtn");//向下切换按钮
        this.hideBtn = function () {
            _this.upBtn.addClass("hide");
            _this.downBtn.addClass("hide");
        };
        this.showBtn = function () {
            _this.upBtn.removeClass("hide");
            _this.downBtn.removeClass("hide");
        };
    }
    this.speed = 50;
    this.currentTop = 0;
    this.parentBox.unbind("mouseenter");
    this.parentBox.unbind("mouseleave");
    this.parentBox.mouseenter(function () {
        _this.showBtn();
    });
    this.parentBox.mouseleave(function () {
        _this.hideBtn();
    });
    this.html = this.tBody.html();
    this.height = this.tBody.height();
    this.trH = this.tBody.find("tr").height();//行高
    if (this.boxH < this.height) {
        this.doubleHtml = _this.html + _this.html;
        this.tBody.empty().html(_this.doubleHtml);
        this.moveFn = function (stepLen, direction) {//参数表示一次递减的值,和递减的方向
            if (-_this.currentTop >= _this.height) {
                _this.currentTop = 0;
            }
            else {
                if (arguments.length == 2) {
                    if (direction == "up") {
                        _this.currentTop = _this.currentTop - stepLen;
                        if (-_this.currentTop >= _this.height - _this.trH) {
                            _this.currentTop = -_this.height;
                        }
                        if (-_this.currentTop > _this.height) {
                            _this.currentTop = 0
                        }
                    }
                    else {
                        _this.currentTop = _this.currentTop + stepLen;
                        if (-_this.currentTop <= 0) {
                            _this.currentTop = 0;
                        }
                    }
                }
                else {
                    _this.currentTop = _this.currentTop - stepLen;
                }
            }
            _this.box.css("top", _this.currentTop + 'px');
        };
        this.timer = setInterval(function () {
            _this.moveFn(1);
        }, _this.speed);
        this.tBody.unbind("mouseenter");
        this.tBody.unbind("mouseleave");
        this.tBody.mouseenter(function () {
            clearInterval(_this.timer)

        });
        this.tBody.mouseleave(function () {
            clearInterval(_this.timer)
            _this.timer = setInterval(function () {
                _this.moveFn(1)
            }, _this.speed);

        });//鼠标进入一开滚动关闭和开启
        this.upBtn.unbind("mouseenter");
        this.upBtn.mouseenter(function () {
            clearInterval(_this.timer);
            event.stopPropagation();
        });
        this.upBtn.unbind("mouseleave");
        this.upBtn.mouseleave(function () {
            clearInterval(_this.timer);
            _this.timer = setInterval(function () {
                _this.moveFn(1)
            }, _this.speed);
            //event.stopPropagation();
        });//鼠标进入一开滚动关闭和开启
        this.downBtn.unbind("mouseenter");
        this.downBtn.mouseenter(function () {
            clearInterval(_this.timer);
            event.stopPropagation();
        });
        this.downBtn.unbind("mouseleave");
        this.downBtn.mouseleave(function () {
            clearInterval(_this.timer);
            clearInterval(_this.timer)
            _this.timer = setInterval(function () {
                _this.moveFn(1)
            }, _this.speed);
            //event.stopPropagation();
        });//鼠标进入一开滚动关闭和开启
        this.upBtn.click(function () {
            clearInterval(_this.timer);
            _this.moveFn(_this.trH, "up")
        });
        this.downBtn.click(function () {
            clearInterval(_this.timer);
            _this.moveFn(_this.trH, "down")
        });
    }
}
// 隔行换色
var ChangeLineBack = function (className) {
    this.box = $("." + className);
    this.trArr = this.box.find("tr");
    this.trArr.each(function (index) {
        if (index % 2 == 0) {
            $(this).addClass("ghhs");
        }
    })
}