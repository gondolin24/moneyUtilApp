package com.example.gondolin.moneyutilapp.stats;


import com.example.gondolin.moneyutilapp.models.Account;

public class DisplayStats {


    public double getDailyAmount(Account account) {

        return account.getBalance();

    }

}
