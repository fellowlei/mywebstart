package com.mark.service.domain;

public class StateStatistic {
    public String title;
    public int total;
    public int success;
    public int failed;

    public void add(StateStatistic stateStatistic){
        this.total += stateStatistic.total;
        this.success+= stateStatistic.success;
        this.failed += stateStatistic.failed;
    }

    @Override
    public String toString() {
        return String.format("title:%s##total:%s,success:%s,failed:%s",title,total,success,failed);
    }
}
