package com.bits.epm.data.report;


public interface IEmployeeReport {

    String getGender();
    Long getCount();

    default void print() {
        System.out.println("Gender: "+getGender()+" Count: "+getCount());
    }

}
