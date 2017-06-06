//startup.js file
var globalhttpheaders = {};
var appConfig = {
    appId: "ScreenshotFFI",
    appName: "ScreenshotFFI",
    appVersion: "1.0.0",
    platformVersion: null,
    serverIp: "10.10.34.155",
    serverPort: "80",
    secureServerPort: "443",
    isDebug: true,
    middlewareContext: "middleware",
    url: "http://10.10.34.155:80/middleware/MWServlet",
    secureurl: "https://10.10.34.155:443/middleware/MWServlet"
};
sessionID = "";

function appInit(params) {
    skinsInit();
    frmStartUpGlobals();
    setAppBehaviors();
};

function setAppBehaviors() {
    kony.application.setApplicationBehaviors({
        applyMarginPaddingInBCGMode: false,
        adherePercentageStrictly: true,
        retainSpaceOnHide: true,
        marginsIncludedInWidgetContainerWeight: true,
        APILevel: 6000
    })
};

function themeCallBack() {
    kony.application.setApplicationInitializationEvents({
        init: appInit,
        showstartupform: function() {
            frmStartUp.show();
        }
    });
};

function loadResources() {
    globalhttpheaders = {};
    kony.os.loadLibrary({
        "javaclassname": "com.konylabs.ffi.N_screenshotns"
    });
    kony.theme.setCurrentTheme("default", themeCallBack, themeCallBack);
};
kony.application.setApplicationMode(constants.APPLICATION_MODE_NATIVE);
//If default locale is specified. This is set even before any other app life cycle event is called.
loadResources();
// If you wish to debug Application Initialization events, now is the time to
// place breakpoints.
debugger;