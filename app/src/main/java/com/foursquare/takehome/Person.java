package com.foursquare.takehome;

final public class Person implements Comparable<Person>{
    private int id;
    private String name;
    private long arriveTime;
    private long leaveTime;

    public Person(int id,String n, long aTime, long lTime) {
        this.id = id;
        name = n;
        arriveTime = aTime;
        leaveTime = lTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Override
    public int compareTo(Person p) {
        long arrTime = p.getArriveTime();
        long leaveTime = p.getLeaveTime();
        if (this.arriveTime < arrTime) {
            return 1;
        } else if (this.arriveTime == arrTime) {
                if(this.leaveTime < leaveTime)
                    return 1;
                else if (this.leaveTime == leaveTime)
                    return 0;
                else
                    return -1;
        } else {
            return -1;
        }
    }
}
