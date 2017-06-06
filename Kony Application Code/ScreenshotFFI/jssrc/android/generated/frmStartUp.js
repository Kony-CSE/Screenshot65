//Form JS File
function frmStartUp_button11739591661574_onClick_seq0(eventobject) {
    onBtnTakeScreenshotFFiClick.call(this);
};

function addWidgetsfrmStartUp() {
    var button11739591661574 = new kony.ui.Button({
        "id": "button11739591661574",
        "top": "144dp",
        "left": "91dp",
        "width": "260dp",
        "height": "50dp",
        "zIndex": 1,
        "isVisible": true,
        "text": "Take Screenshot",
        "skin": "btnNormal",
        "focusSkin": "btnFocus",
        "onClick": frmStartUp_button11739591661574_onClick_seq0
    }, {
        "padding": [0, 0, 0, 0],
        "contentAlignment": constants.CONTENT_ALIGN_CENTER,
        "displayText": true,
        "marginInPixel": false,
        "paddingInPixel": false,
        "containerWeight": 11
    }, {});
    frmStartUp.add(
    button11739591661574);
};

function frmStartUpGlobals() {
    var MenuId = [];
    frmStartUp = new kony.ui.Form2({
        "id": "frmStartUp",
        "enableScrolling": true,
        "bounces": true,
        "allowHorizontalBounce": true,
        "allowVerticalBounce": true,
        "pagingEnabled": false,
        "needAppMenu": true,
        "title": null,
        "enabledForIdleTimeout": false,
        "skin": "frm",
        "layoutType": kony.flex.FREE_FORM,
        "addWidgets": addWidgetsfrmStartUp
    }, {
        "padding": [0, 0, 0, 0],
        "displayOrientation": constants.FORM_DISPLAY_ORIENTATION_PORTRAIT,
        "paddingInPixel": false
    }, {
        "retainScrollPosition": false,
        "windowSoftInputMode": constants.FORM_ADJUST_RESIZE,
        "titleBar": true,
        "footerOverlap": false,
        "headerOverlap": false,
        "inTransitionConfig": {
            "formAnimation": 0
        },
        "outTransitionConfig": {
            "formAnimation": 0
        },
        "menuPosition": constants.FORM_MENU_POSITION_AFTER_APPMENU
    });
    frmStartUp.setDefaultUnit(kony.flex.DP);
};