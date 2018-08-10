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
        ActivityRecordM sourceRecord = mHistory.get(indexOfToken(resultTo));
        findOrCreateTaskRecord(sourceRecord, r, intent);

        mWindowManager.addAppToken(r.appToken);
        sourceRecord.app.thread.schedulePauseActivity(resultTo);

        String processName = "";

        ProcessRecordM  pre = mActivityManager.mProcessMap.get(processName);
        if(pre != null){
            pre.thread.scheduleLaunchActivity(r.appToken);
        }else {
            mActivityManager.startProcess(processName);
        }

    }

    private void findOrCreateTaskRecord(ActivityRecordM sourceRecord, ActivityRecordM r, IntentM intent){
        int launchFlags = intent.getFlags();
        if(sourceRecord == null || sourceRecord.launchMode == ActivityRecordM.LAUNCH_SINGLE_INSTANCE){
            launchFlags = launchFlags | IntentM.FLAG_ACTIVITY_NEW_TASK;
        }

        if(r.launchMode == ActivityRecordM.LAUNCH_SINGLE_TASK || r.launchMode == ActivityRecordM.LAUNCH_SINGLE_INSTANCE){
            launchFlags = launchFlags | IntentM.FLAG_ACTIVITY_NEW_TASK;
        }
        TaskRecordM reuseTask = null;
        if((launchFlags&IntentM.FLAG_ACTIVITY_NEW_TASK) != 0){
            reuseTask = findTaskOrActivity(r);
            if(reuseTask != null){
                if((launchFlags&IntentM.FLAG_ACTIVITY_CLEAR_TOP) != 0){
                    performClearTopActivity();
                }
                r.setTask(reuseTask);
            }else{
                r.setTask(new TaskRecordM());
            }
        }
        r.setTask(sourceRecord.task);
    }


    private void performClearTopActivity(){

    }

    private TaskRecordM findTaskOrActivity(ActivityRecordM r){
        return null;
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


    public void handleAppCrash(ProcessRecordM app){
        for(ActivityRecordM  r :mHistory){
            if(r.app  == app){
                if(!r.haveState){
                    mHistory.remove(r);
                }
            }
        }
    }

    public void resumeTopActivitiesLocked(){

    }

    public void finishTopRunningActivity(ProcessRecordM app){
        ActivityRecordM  r = topRunningActivity();

        if(r.app == app){
            int activityNdx = mHistory.indexOf(r);
            finishActivity(r);
            --activityNdx;
            if(activityNdx >=0){
                r = mHistory.get(activityNdx);
                finishActivity(r);
            }
        }
    }

    public void finishActivity(ActivityRecordM r){

    }









}
