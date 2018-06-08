package com.mufasa.zhaodsh.andframemock.App;

import android.os.IBinder;

import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityThreadM {

    public HashMap<IBinder, ActivityClientRecordM>  mActivities = new HashMap<IBinder, ActivityClientRecordM>();

    private List<ViewRootImplM> mViewRoots = new ArrayList<>();

    public void handleLaunchActivity(IBinderM  binder){
        ActivityClientRecordM r = mActivities.get(binder);
        r.activty.onCreate();
    }

    public void handleResumeActivity(IBinderM  binder){
        ActivityClientRecordM r = mActivities.get(binder);

        r.activty.onResume();

        ViewRootImplM  viewRoot = new ViewRootImplM();
        mViewRoots.add(viewRoot);
        viewRoot.setView(r.activty.mWindow.mDecor);
    }




}
