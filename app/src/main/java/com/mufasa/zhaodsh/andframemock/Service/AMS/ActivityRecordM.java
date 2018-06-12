package com.mufasa.zhaodsh.andframemock.Service.AMS;

import com.mufasa.zhaodsh.andframemock.App.ActivityM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

public class ActivityRecordM {

    public Token appToken;

    public ProcessRecordM app;

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

    public class IApplicationToken extends IBinderM {

    }


}
