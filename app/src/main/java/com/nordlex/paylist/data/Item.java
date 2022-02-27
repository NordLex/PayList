package com.nordlex.paylist.data;

import androidx.annotation.NonNull;

public class Item {
    private final int numCode;
    private final int dollars;
    private final int cents;

    public Item (int numCode, int dollars, int cents) {
        this.numCode = numCode;
        this.dollars = dollars;
        this.cents = cents;
    }

    public int getNumCode () {
        return numCode;
    }

    public int getDollars () {
        return dollars;
    }

    public int getCents () {
        return cents;
    }

    @NonNull
    public String toString () {
        if (cents<10) {
            return dollars+".0"+cents;
        } else {
            return dollars+"."+cents;
        }
    }
}
