package com.doctorconference.managment;

public class GetSetData {
    private String mLastName;
    private String mEmail;
    private String mPwD;
    private String mFirstName;
    private String mAdmin;
    private String mRecodeID;
    private String mTopicD;
    private String mTopicTitle;
    private String mTopicDetails;


    public String getmTopicD() {
        return mTopicD;
    }

    public void setmTopicD(String mTopicD) {
        this.mTopicD = mTopicD;
    }

    public String getmTopicTitle() {
        return mTopicTitle;
    }

    public void setmTopicTitle(String mTopicTitle) {
        this.mTopicTitle = mTopicTitle;
    }

    public String getmTopicDetails() {
        return mTopicDetails;
    }

    public void setmTopicDetails(String mTopicDetails) {
        this.mTopicDetails = mTopicDetails;
    }

    public GetSetData(String mTopicD, String mTopicTitle, String mTopicDetails) {
        this.mTopicD = mTopicD;
        this.mTopicTitle = mTopicTitle;
        this.mTopicDetails = mTopicDetails;
    }

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
