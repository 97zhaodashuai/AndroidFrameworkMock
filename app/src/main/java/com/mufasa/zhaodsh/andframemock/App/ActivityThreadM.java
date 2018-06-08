package com.mufasa.zhaodsh.andframemock.App;

import com.mufasa.zhaodsh.andframemock.Service.AMS.IActivityManagerServiceM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;
import com.mufasa.zhaodsh.andframemock.Service.ServiceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityThreadM {

    public HashMap<IBinderM, ActivityClientRecordM>  mActivities = new HashMap<IBinderM, ActivityClientRecordM>();

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
    }


    public void attach(){
        IActivityManagerServiceM mgr = (IActivityManagerServiceM)ServiceManager.getService("ActivityManagerService");
        mgr.attachApplication(mAppThread);
    }


    public void handleLaunchActivity(ActivityClientRecordM r){
        r.activty.onCreate();
        handleResumeActivity(r.token);
    }

    public void handleResumeActivity(IBinderM  binder){
        ActivityClientRecordM r = mActivities.get(binder);

        r.activty.onResume();

        ViewRootImplM  viewRoot = new ViewRootImplM();
        mViewRoots.add(viewRoot);
        viewRoot.setView(r.activty.mWindow.mDecor);
    }




}
