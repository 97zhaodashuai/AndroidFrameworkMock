package com.mufasa.zhaodsh.andframemock.Service.AMS;

import com.mufasa.zhaodsh.andframemock.App.IApplicationThreadM;
import com.mufasa.zhaodsh.andframemock.IntentM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

public class ActivityManagerServiceM extends IActivityManagerServiceM {

    private  ActivityStackM  mMainStack;

    public ActivityManagerServiceM() {
        mMainStack = new ActivityStackM();
    }

    @Override
    public void attachApplication(IApplicationThreadM applicationThreadM) {

    }

    @Override
    public void startActivity(IApplicationThreadM callingPid, IBinderM resultTo, IntentM intent) {



    }


}
