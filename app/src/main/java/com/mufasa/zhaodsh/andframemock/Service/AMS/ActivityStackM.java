package com.mufasa.zhaodsh.andframemock.Service.AMS;

import com.mufasa.zhaodsh.andframemock.IntentM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;
import com.mufasa.zhaodsh.andframemock.Service.WMS.WindowManagerServiceM;

import java.util.ArrayList;
import java.util.List;

public class ActivityStackM {

    WindowManagerServiceM mWindowManager;
    ActivityManagerServiceM mActivityManager;

    private List<ActivityRecordM> mHistory  = new ArrayList<>();

    public void startActivity(IBinderM resultTo, IntentM intent){

        ActivityRecordM  r = new ActivityRecordM();
        mWindowManager.addAppToken(r.appToken);
        ActivityRecordM sourceRecord = mHistory.get(indexOfToken(resultTo));
        sourceRecord.app.thread.schedulePauseActivity(resultTo);

        String processName = "";

        ProcessRecordM  pre = mActivityManager.mProcessMap.get(processName);
        if(pre != null){
            pre.thread.scheduleLaunchActivity(r.appToken);
        }else {
            mActivityManager.startProcess(processName);
        }

    }

    public int indexOfToken(IBinderM  token){
        for(int i = 0; i < mHistory.size(); i++){
            if(token.equals(mHistory.get(i).appToken)){
                return i;
            }
        }
        return -1;
    }

    public void realStartActivity(ProcessRecordM app, ActivityRecordM hr){
        hr.app = app;
        hr.app.thread.scheduleLaunchActivity(hr.appToken);
    }

    public ActivityRecordM topRunningActivity(){
        ActivityRecordM  r =  mHistory.get(mHistory.size()-1);
        return r;
    }







}
