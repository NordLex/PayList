package com.nordlex.paylist.data;

public class Month {
    private static final String[] MONTH = new String[12];
    static {
        MONTH[0] ="Январь";
        MONTH[1] ="Февраль";
        MONTH[2] ="Март";
        MONTH[3] ="Апрель";
        MONTH[4] ="Май";
        MONTH[5] ="Июнь";
        MONTH[6] ="Июль";
        MONTH[7] ="Август";
        MONTH[8] ="Сентябрь";
        MONTH[9] ="Октябрь";
        MONTH[10] ="Ноябрь";
        MONTH[11] ="Декабрь";
    }

    public static String byNumber(int num) {
        if (num<12 & num>0){
            return MONTH[num-1];
        } else {
            return "Месяц введён не корректно";
        }
    }
}
