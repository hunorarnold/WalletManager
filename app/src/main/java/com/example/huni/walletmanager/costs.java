package com.example.huni.walletmanager;

public class Costs {
        public String salary;
        public String remaining;
        public String general;
        public String housing;
        public String finance;
        public String transport;
        public String drinks;
        public String food;
        public String entertainment;

        public Costs(){

        }

        public Costs(String salary, String remaining, String general, String housing, String finance,
                     String transport, String drinks, String food, String entertainment) {

            this.salary = salary;
            this.remaining = remaining;
            this.general = general;
            this.housing = housing;
            this.finance = finance;
            this.transport = transport;
            this.drinks = drinks;
            this.food = food;
            this.entertainment = entertainment;
        }
}
