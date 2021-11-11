package de.bank;

public class BankTest {
    public static void main(String[] args) {
        Bank bank = new Bank();
        System.out.println("Vorher:");
        bank.kontostand();

        Angestellter angestellte[] = {
                new Angestellter(bank, 0,1,20),
                new Angestellter(bank, 1,2,20),
                new Angestellter(bank, 2,0,20)
        };

        for(int i = 0; i < angestellte.length; i ++) {
            angestellte[i].start();
        }
    }
}
