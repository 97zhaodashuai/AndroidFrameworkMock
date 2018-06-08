package com.mufasa.zhaodsh.andframemock.Service.WMS;

import com.mufasa.zhaodsh.andframemock.App.IWindowSeesionM;
import com.mufasa.zhaodsh.andframemock.Service.AMS.ActivityRecordM;

import java.util.ArrayList;
import java.util.HashMap;

public class WindowManagerServiceM  extends IWindowManagerServiceM{


    private HashMap<ActivityRecordM.IApplicationToken, AppWindowToken> mTokenMap = new HashMap<>();

    private ArrayList<WindowStateM> mWindows = new ArrayList<WindowStateM>();

    public WindowManagerServiceM() {

    }

    public void addAppToken(ActivityRecordM.Token appToken) {
        AppWindowToken wtoken = new AppWindowToken();
        mTokenMap.put(appToken, wtoken);
    }

    public void addWindow(IWindowM client , ActivityRecordM.IApplicationToken  appToken){

        AppWindowToken  windowToken = mTokenMap.get(appToken);
        WindowStateM windowState = new WindowStateM(client, windowToken);
        mWindows.add(windowState);
    }

    @Override
    public IWindowSeesionM openSession() {
        return null;
    }
}
