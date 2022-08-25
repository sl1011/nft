package com.shitouren.core.dto;

public class AccountInfo {
    public AccountInfo() {
    }

    public AccountInfo(String accountDID, String accountName, String accountRole, String leaderDID, String platformState, String operatorState, String field) {
        this.accountDID = accountDID;
        this.accountName = accountName;
        this.accountRole = accountRole;
        this.leaderDID = leaderDID;
        this.platformState = platformState;
        this.operatorState = operatorState;
        this.field = field;
    }

    private String accountDID;
    private String accountName;
    private String accountRole;
    private String leaderDID;
    private String platformState;
    private String operatorState;
    private String field;

    public String getAccountDID() {
        return accountDID;
    }

    public void setAccountDID(String accountDID) {
        this.accountDID = accountDID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(String accountRole) {
        this.accountRole = accountRole;
    }

    public String getLeaderDID() {
        return leaderDID;
    }

    public void setLeaderDID(String leaderDID) {
        this.leaderDID = leaderDID;
    }

    public String getPlatformState() {
        return platformState;
    }

    public void setPlatformState(String platformState) {
        this.platformState = platformState;
    }

    public String getOperatorState() {
        return operatorState;
    }

    public void setOperatorState(String operatorState) {
        this.operatorState = operatorState;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "accountDID='" + accountDID + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountRole='" + accountRole + '\'' +
                ", leaderDID='" + leaderDID + '\'' +
                ", platformState='" + platformState + '\'' +
                ", operatorState='" + operatorState + '\'' +
                ", field='" + field + '\'' +
                '}';
    }
}
