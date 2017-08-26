package com.bigwanggang.Jmockit;


public class Demo2ServiceImpl implements Demo2Service {
    private MorganMemberService morganMemberService;

    @Override
    public String sayName() {
        if (isTp())
            return "TP Member";
        return "Free Member";
    }

    public boolean isTp() {
        return true;
    }

    public String sayNameIsValid(String str) {
        if (isValid(str))
            return "valid";
        return "inValid";
    }

    private boolean isValid(String str) {
        return true;
    }

    public void setMorganMemberService(MorganMemberService morganMemberService) {
        this.morganMemberService = morganMemberService;
    }
}
