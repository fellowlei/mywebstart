package com.mark.service.domain;


public class StatisticResult {
    private boolean success;
    private String msg;
    private StateStatistic stateStatistic;

    public StatisticResult(boolean success, String msg, StateStatistic stateStatistic) {
        this.success = success;
        this.msg = msg;
        this.stateStatistic = stateStatistic;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public StateStatistic getStateStatistic() {
        return stateStatistic;
    }

    public void setStateStatistic(StateStatistic stateStatistic) {
        this.stateStatistic = stateStatistic;
    }

    @Override
    public String toString() {
        return "StatisticResult{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", stateStatistic=" + stateStatistic +
                '}';
    }
}
