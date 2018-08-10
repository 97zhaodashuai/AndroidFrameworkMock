package com.mufasa.zhaodsh.andframemock.App;

import android.os.Bundle;

import com.mufasa.zhaodsh.andframemock.IntentM;
import com.mufasa.zhaodsh.andframemock.Service.AMS.ActivityRecordM;
import com.mufasa.zhaodsh.andframemock.Service.AMS.IActivityManagerServiceM;
import com.mufasa.zhaodsh.andframemock.Service.AMS.ServiceRecordM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;
import com.mufasa.zhaodsh.andframemock.Service.ServiceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityThreadM {

    public HashMap<IBinderM, ActivityClientRecordM>  mActivities = new HashMap<IBinderM, ActivityClientRecordM>();
    public HashMap<IBinderM, ServiceM> mServices = new HashMap<>();
    public HashMap<IBinderM, ReceiverDispatcher> mReceivers = new HashMap<>();

    private List<ViewRootImplM> mViewRoots = new ArrayList<>();

    public IApplicationThreadM mAppThread = new ApplicationThread();

    public static void main(String[] args){
        ActivityThreadM thread = new ActivityThreadM();
        thread.attach();

    }

    class ApplicationThread extends  IApplicationThreadM{

        @Override
        public void scheduleLaunchActivity(IBinderM  binder ) {
            ActivityClientRecordM  r = new ActivityClientRecordM();
            r.token = binder;
            mActivities.put(binder, r);
            handleLaunchActivity(r);
        }

        @Override
        public void schedulePauseActivity(IBinderM binder) {
            ActivityClientRecordM r = mActivities.get(binder);

            if(isPreHoneycomb()){
                if(r.state == null){
                    r.state = new Bundle();
                }
                r.activty.onSaveInstanceState(r.state);
            }
            r.activty.onPause();
        }

        @Override
        public void scheduleStopActivity(IBinderM binder) {
            ActivityClientRecordM r = mActivities.get(binder);
            if(r.state == null){
                r.state = new Bundle();
            }
            r.activty.onSaveInstanceState(r.state);
            r.activty.onStop();
        }

        private boolean isPreHoneycomb(){
            return false;  // 3。0以下手机返回true， 后面返回false
        }



        @Override
        public void scheduleCreateService(IBinderM token) {
            ServiceM service = new ServiceM();
            service.attach();
            service.onCreate();
            mServices.put(token, service);
            getActivityManager().serviceDoneExecuting(token);
        }

        @Override
        public void scheduleBindService(IBinderM token) {
            ServiceM service = new ServiceM();
            service.attach();
            service.onCreate();
            mServices.put(token, service);

            IBinderM binder = service.onBind();

            getActivityManager().publicService(token, binder);

            getActivityManager().serviceDoneExecuting(token);
        }

        @Override
        public void scheduleRegisteredReceiver(IBinderM binder) {
            ReceiverDispatcher dispatcher = mReceivers.get(binder);
            dispatcher.performReceive();
        }
    }


    public void attach(){
        IActivityManagerServiceM mgr = (IActivityManagerServiceM)ServiceManager.getService("ActivityManagerService");
        mgr.attachApplication(mAppThread, getProcessID());
    }


    public void handleLaunchActivity(ActivityClientRecordM r){

        ActivityM  a = new ActivityM();
        r.activty = a;
        a.attach(r.token);
        a.onCreate(r.state);
        a.onStart();

        a.onRetoreInstanceState(r.state);

        handleResumeActivity(r.token);
    }

    public void handleResumeActivity(IBinderM  binder){
        ActivityClientRecordM r = mActivities.get(binder);

        r.activty.onResume();

        ViewRootImplM  viewRoot = new ViewRootImplM();
        mViewRoots.add(viewRoot);
        viewRoot.setView(r.activty.mWindow.mDecor);
    }


    public static final int getProcessID() {
        return  2;
    }


    public IActivityManagerServiceM  getActivityManager(){
        return  (IActivityManagerServiceM)ServiceManager.getService("ActivityManagerService");
    }



    public void RegisterReceiver(BroadcastReceiverM  receiverM, String action){
        ReceiverDispatcher dispatcher = new ReceiverDispatcher();
        dispatcher.receiverM = receiverM;
        dispatcher.action = action;
        mReceivers.put(dispatcher, dispatcher);
        getActivityManager().registerReceiver(mAppThread, dispatcher, action);

    }


}
