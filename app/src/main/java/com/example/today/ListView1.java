package com.example.today;

public class ListView1 {
    int hclock;
    int mclock;
    String memo;

    public ListView1(int hclock, int mclock, String memo) {
        this.hclock = hclock;
        this.mclock = mclock;
        this.memo = memo;
    }

    public int getHclock() {
        return hclock;
    }

    public void setHclock(int hclock) {
        this.hclock = hclock;
    }

    public int getMclock() {
        return mclock;
    }

    public void setMclock(int mclock) {
        this.mclock = mclock;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
