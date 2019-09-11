package com.icloudwhale.cloudpos.dialog;

import android.content.Context;

import com.iwhalecloud.common.dialog.base.BaseDialog;
import com.iwhalecloud.common.view.progresshud.ProgressHUD;

/**
 * @author xll
 * @date 2018/12/3
 */

public class DialogManager {
    private static DialogManager dialogManager;

    private ProgressHUD hud;
    private BaseDialog mDialog;

    private DialogManager() {

    }

    public static DialogManager getInstance() {
        if (null == dialogManager) {
            synchronized (DialogManager.class) {
                if (null == dialogManager) {
                    dialogManager = new DialogManager();
                }
            }
        }
        return dialogManager;
    }

    public void showProgressHUD(Context context) {
        dismissProgressHUD();
        hud = ProgressHUD.create(context)
                .setStyle(ProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();
    }

    public void dismissProgressHUD() {
        if (null != hud) {
            hud.dismiss();
            hud = null;
        }
    }

    public void dismissDialog() {
        if (null != mDialog) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

}
