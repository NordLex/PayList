package com.nordlex.paylist.data;

import java.util.ArrayList;

public class Total {
    private final String strClear;
    private final String strIncome;
    private final String strOutcome;

    public Total (ArrayList<Item> incomeList, ArrayList<Item> outcomeList) {
        int[] sumIn = addition(incomeList);
        strIncome = formatting(sumIn);
        int[] sumOut = addition(outcomeList);
        strOutcome = formatting(sumOut);
        int[] subClear = subtraction(sumIn, sumOut);
        strClear = formatting(subClear);
    }

    private int[] addition (ArrayList<Item> list) {
        int[] sum = new int[2];
        int intDollars = 0;
        int intCents = 0;
        for (Item item:list) {
            intDollars += item.getDollars();
            intCents += item.getCents();
        }
        int remainder = intCents / 100;
        sum[1] = intCents - (remainder*100);
        sum[0] = intDollars + remainder;
        return sum;
    }

    private int[] subtraction (int[] income, int[] outcome) {
        int[] clear = new int[2];

        if(income[1]<outcome[1]) {
            income[0] -= 1;
            income[1] += 100;
        }

        clear[0] = income[0]-outcome[0];
        clear[1] = income[1]-outcome[1];

        return clear;
    }

    private String formatting (int[] cash) {
        if (cash[1]<10) {
            return cash[0]+".0"+cash[1];
        } else {
            return cash[0]+"."+cash[1];
        }
    }

    public String getClear() {
        return strClear;
    }

    public String getIncome () {
        return strIncome;
    }

    public String getOutcome () {
        return strOutcome;
    }
}
