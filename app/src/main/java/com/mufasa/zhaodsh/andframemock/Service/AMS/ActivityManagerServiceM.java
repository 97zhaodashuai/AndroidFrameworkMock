package com.mufasa.zhaodsh.andframemock.Service.AMS;

import android.content.Intent;
import android.util.SparseArray;

import com.mufasa.zhaodsh.andframemock.App.IApplicationThreadM;
import com.mufasa.zhaodsh.andframemock.IntentM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;

public class ActivityManagerServiceM extends IActivityManagerServiceM {

    private  ActivityStackM  mMainStack;

    SparseArray<ProcessRecordM>  mPidsSelfLocked = new SparseArray<ProcessRecordM>();
    HashMap<String, ProcessRecordM> mProcessMap = new HashMap<>();

    HashMap<String, ServiceRecordM> mServices = new HashMap<>();
    HashMap<IBinderM, ReceiverListM> mRegisteredReceivers = new HashMap<>();
    IdentityHashMap<String, List<BroadcastFilterM>> mReceiverResolver = new IdentityHashMap<>();
    List<BroadcastFilterM> mParallelBroadcasts = new ArrayList<>();
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
        mMainStack.startActivity(resultTo, intent);
    }

    @Override
    public void startService(IApplicationThreadM applicationThreadM, IntentM intent){

        ServiceRecordM r = new ServiceRecordM();
        String servName = getComponentName(intent);
        mServices.put(servName, r);

        String processName = "";

        ProcessRecordM  pre = mProcessMap.get(processName);
        if(pre != null){
            applicationThreadM.scheduleCreateService(r);
        }else {
            startProcess(processName);
        }
    }


    @Override
    public void bindService(IApplicationThreadM applicationThreadM, IntentM intent, IServiceConnectionM connectionM){
        ServiceRecordM r = new ServiceRecordM();
        String servName = getComponentName(intent);
        mServices.put(servName, r);

        String processName = "";

        ProcessRecordM  pre = mProcessMap.get(processName);
        if(pre != null){
            applicationThreadM.scheduleBindService(r);
        }else {
            startProcess(processName);
        }

    }

    @Override
    public void publicService(IBinderM token, IBinderM binder) {
        ServiceRecordM r = null;
        //getconnection   call connected
    }


    @Override
    public void serviceDoneExecuting(IBinderM binder) {

    }

    @Override
    public void registerReceiver(IApplicationThreadM applicationThreadM, IBinderM receiver, String action) {

        ProcessRecordM  callerApp = getRecordForAppLocked(applicationThreadM);
        ReceiverListM  rl = mRegisteredReceivers.get(receiver);
        if(rl == null){
            rl = new ReceiverListM();
            rl.app = callerApp;
            rl.receiver = receiver;
            rl.app.receivers.add(rl);
            mRegisteredReceivers.put(receiver, rl);
        }

        BroadcastFilterM bf = new BroadcastFilterM(action, rl);
        mReceiverResolver.put(action, Collections.singletonList(bf));

    }

    @Override
    public void broadcastIntent(IApplicationThreadM applicationThreadM, String action) {

        List<BroadcastFilterM>  rls = mReceiverResolver.get(action);
        mParallelBroadcasts.addAll(rls);

    }

    private void scheduleBroadcastsLocked(){
        while (mParallelBroadcasts.size() > 0) {
            BroadcastFilterM r = mParallelBroadcasts.remove(0);
            r.rl.app.thread.scheduleRegisteredReceiver(r.rl.receiver);
        }
    }


    private void realStartService(ServiceRecordM r, ProcessRecordM app){
        r.app = app;
        app.thread.scheduleLaunchActivity(r);
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

    private String getComponentName(IntentM  intent){
        return "";
    }

    private ProcessRecordM  getRecordForAppLocked(IApplicationThreadM applicationThreadM){
        return null;
    }

    private long getCrashTime(ProcessRecordM proc){
        return 0;
    }

    public  void handleAppCrash(IApplicationThreadM applicationThreadM){
        ProcessRecordM proc = getRecordForAppLocked(applicationThreadM);
        long crashTime = getCrashTime(proc);
        if(crashTime  != 0 && crashTime + 1000 * 60 < System.currentTimeMillis()){
            //两次间隔小于60s
            if(!proc.persistent){
                mMainStack.handleAppCrash(proc);
                clearUPApplicationRecord();
                kill(proc.pid);
                mMainStack.resumeTopActivitiesLocked();
            }else{
                mMainStack.resumeTopActivitiesLocked();
            }
        }else{
            mMainStack.finishTopRunningActivity(proc);
        }

        showCrashDialog();

        kill(proc.pid);

    }

    private void showCrashDialog(){

    }

    public void handleAppDied(IApplicationThreadM applicationThreadM){
        ProcessRecordM proc = getRecordForAppLocked(applicationThreadM);
        clearUPApplicationRecord();
        mMainStack.handleAppCrash(proc);
        mMainStack.resumeTopActivitiesLocked();
    }


    public void clearUPApplicationRecord(){
        mServices.remove(null);
        mRegisteredReceivers.remove(null);
    }

    private  void kill(int pid){

    }








}
