package com.mufasa.zhaodsh.andframemock.Service.AMS;

import android.util.SparseArray;

import com.mufasa.zhaodsh.andframemock.App.IApplicationThreadM;
import com.mufasa.zhaodsh.andframemock.IntentM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

import java.util.HashMap;

public class ActivityManagerServiceM extends IActivityManagerServiceM {

    private  ActivityStackM  mMainStack;

    SparseArray<ProcessRecordM>  mPidsSelfLocked = new SparseArray<ProcessRecordM>();
    HashMap<String, ProcessRecordM> mProcessMap = new HashMap<>();
    public ActivityManagerServiceM() {
        mMainStack = new ActivityStackM();
    }

    @Override
    public void attachApplication(IApplicationThreadM applicationThreadM, int pid) {
        ProcessRecordM  app = mPidsSelfLocked.get(pid);
        app.thread = applicationThreadM;
        ActivityRecordM hr = mMainStack.topRunningActivity();
        if(hr.app == null){
            mMainStack.realStartActivity(app, hr);
        }

    }
    @Override
    public void startActivity( IBinderM resultTo, IntentM intent) {

    }


    public void startProcess(String ProcessName){
        ProcessRecordM  app = new ProcessRecordM();
        app.ProcessName = ProcessName;
        ProcessM.start("android.app.ActivityThread", app.ProcessName);

    }


    static  class ProcessM {
        public static void  start(String mainClass, String ProcessName){

        }
    }






}
