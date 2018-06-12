package com.mufasa.zhaodsh.andframemock.Service.AMS;

        import com.mufasa.zhaodsh.andframemock.App.IApplicationThreadM;
        import com.mufasa.zhaodsh.andframemock.IntentM;
        import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

public abstract  class IActivityManagerServiceM extends IBinderM {

    public abstract void attachApplication(IApplicationThreadM applicationThreadM, int pid);

    public abstract void startActivity(IBinderM resultTo, IntentM intent);


}
