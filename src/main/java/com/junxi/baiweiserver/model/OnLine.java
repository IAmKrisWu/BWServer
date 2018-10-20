package com.junxi.baiweiserver.model;

public class OnLine {
    private boolean ison;
    private Hr user;

    public boolean isIson() {
        return ison;
    }

    public void setIson(boolean ison) {
        this.ison = ison;
    }

    public Hr getUser() {
        return user;
    }

    public void setUser(Hr user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OnLine{" +
                "ison=" + ison +
                ", user=" + user +
                '}';
    }
}
