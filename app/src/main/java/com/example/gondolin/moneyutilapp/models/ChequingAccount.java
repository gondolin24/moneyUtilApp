package com.example.gondolin.moneyutilapp.models;


public class ChequingAccount extends Account implements SimpleBankActions {


    @Override
    public void decreaseBalance(double amount) {
        setBalance(getBalance() - amount);
    }

    @Override
    public void increaseBalance(double amount) {
        setBalance(getBalance() + amount);
    }
}
