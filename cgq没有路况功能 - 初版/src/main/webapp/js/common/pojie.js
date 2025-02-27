//var WinAlerts = window.alert;
function pojie(){
	window.alert = function (e) {
	    if (e != null && typeof e==="string" && e.indexOf("试用到期")>-1) {
	        //和谐了
	    }else {
	        WinAlerts (e);
	    }
	};
}
pojie();
setInterval("pojie()",1);

