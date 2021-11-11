package de.bank;

public class Bank {

    private int konten[] = {30,50,100};

    public void kontostand() {
        for (int konto = 0; konto < konten.length; konto++) {
            System.out.println(String.format("Konto Nr. %s hat den Saldo %S.", konto, konten[konto]));
        }
        System.out.println("------------------------------------");
    }

    public synchronized void ueberweisen(int von , int nach , int betrag) {
        int zettel = konten[von];
        zettel -= betrag;
        try {
            Thread.sleep((long)(Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        konten[von] = zettel;
        konten[nach] += betrag;
        kontostand();
    }
}
