package com.mufasa.zhaodsh.andframemock.Service;

import com.mufasa.zhaodsh.andframemock.Service.AMS.ActivityManagerServiceM;
import com.mufasa.zhaodsh.andframemock.Service.WMS.WindowManagerServiceM;

import java.util.HashMap;

public class ServiceManager {
    static  HashMap<String, IBinderM> mServices = new HashMap<>();
    public static  IBinderM getService(String serviceName){
        return mServices.get(serviceName);
    }

    public void initSystemService(){
        mServices.put("ActivityManagerService", new ActivityManagerServiceM());
        mServices.put("WindowManagerService", new WindowManagerServiceM());
    }
}
