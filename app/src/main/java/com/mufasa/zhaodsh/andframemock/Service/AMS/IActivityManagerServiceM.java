package com.mufasa.zhaodsh.andframemock.Service.AMS;

        import com.mufasa.zhaodsh.andframemock.App.IApplicationThreadM;
        import com.mufasa.zhaodsh.andframemock.IntentM;
        import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

public abstract  class IActivityManagerServiceM extends IBinderM {

    public abstract void attachApplication(IApplicationThreadM applicationThreadM, int pid);

    public abstract void startActivity(IBinderM resultTo, IntentM intent);

    public abstract void startService(IApplicationThreadM applicationThreadM, IntentM intent);

    public abstract void bindService(IApplicationThreadM applicationThreadM, IntentM intent, IServiceConnectionM connectionM);

    public abstract void publicService(IBinderM token,IBinderM binder);

    public abstract  void serviceDoneExecuting( IBinderM binder);

    public abstract  void registerReceiver(IApplicationThreadM applicationThreadM, IBinderM receiver, String action);


    public abstract void broadcastIntent(IApplicationThreadM applicationThreadM, String action);

}
