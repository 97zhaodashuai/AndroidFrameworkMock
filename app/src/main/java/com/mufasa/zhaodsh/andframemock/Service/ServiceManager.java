package com.mufasa.zhaodsh.andframemock.Service;

import java.util.HashMap;

public class ServiceManager {
    static  HashMap<String, IBinderM> mServices = new HashMap<>();
    public static  IBinderM getService(String serviceName){
        return mServices.get(serviceName);
    }
}
