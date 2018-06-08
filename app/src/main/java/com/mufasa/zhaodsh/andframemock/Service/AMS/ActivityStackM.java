package com.mufasa.zhaodsh.andframemock.Service.AMS;

import com.mufasa.zhaodsh.andframemock.Service.WMS.WindowManagerServiceM;

import java.util.ArrayList;
import java.util.List;

public class ActivityStackM {

    WindowManagerServiceM mWindowManager;

    private List<ActivityRecordM> mHistory  = new ArrayList<>();

    public void startActivity(){

        ActivityRecordM  r = new ActivityRecordM();
        mWindowManager.addAppToken(r.appToken);

    }

}
