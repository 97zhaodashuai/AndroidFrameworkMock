package com.mufasa.zhaodsh.andframemock.Service.AMS;

import com.mufasa.zhaodsh.andframemock.App.ActivityM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

public class ActivityRecordM {

    public Token appToken;

    public ProcessRecordM app;

    public int launchMode;

    public TaskRecordM task;

    public boolean haveState = false;

    public static  int LAUNCH_STANDARD = 0;
    public static  int LAUNCH_SINGLE_TOP = 1;
    public static  int LAUNCH_SINGLE_TASK = 2;
    public static  int LAUNCH_SINGLE_INSTANCE = 3;

    public ActivityRecordM() {
        appToken  = new Token(this);
    }

    public class Token extends IApplicationToken {

        public Token(ActivityRecordM activityRecordM) {
            super();
        }

        public void Token(ActivityRecordM ac){

        }
    }

    public void setTask(TaskRecordM task){
        this.task = task;
    }

    public class IApplicationToken extends IBinderM {

    }


}
