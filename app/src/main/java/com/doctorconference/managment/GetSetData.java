package com.doctorconference.managment;

class GetSetData {
    private String mLastName;
    private String mEmail;
    private String mPwD;
    private String mFirstName;
    private String mAdmin;
    private String mRecodeID;

    public GetSetData() {
    }

    public GetSetData(String mFirstName, String mLastName, String mEmail,
                      String mpwd,String madmin) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mPwD = mpwd;
        this.mAdmin = madmin;
    }

    public void setmRecodeID(String mRecodeID) {
        this.mRecodeID = mRecodeID;
    }

    public String getmRecodeID() {
        return mRecodeID;
    }

    public String getmAdmin() {
        return mAdmin;
    }

    public void setmAdmin(String madmin) {
        this.mAdmin = madmin;
    }

    public void setmPwd(String mpwD) {
        this.mPwD = mpwD;
    }

    public String getmPwD() {
        return mPwD;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmLastName() {
        return mLastName;
    }


}
