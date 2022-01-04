/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankstuff2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
class Bank {

    private String name;
    private double balance;

    public Bank() {
    }

    public Bank(String name, double balance) throws FileNotFoundException {
        this.name = name;
        this.balance = balance;
        File file = new File(name + ".txt");
        try {
            boolean value = file.createNewFile();
            if (value) {
                System.out.println("New account created");
            } else {
                System.out.println("Account found");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        try (java.io.PrintWriter out = new java.io.PrintWriter(file)) {
            out.println(this.name);
            out.println(this.balance);
        }
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void updateFile() throws FileNotFoundException {
        File file = new File(name + ".txt");
        try (java.io.PrintWriter out = new java.io.PrintWriter(file)) {
            out.println(this.name);
            out.println(this.balance);
        }
    }

    @Override
    public String toString() {
        return "\nName: " + this.name
                + "\nBalance: " + this.balance;
    }

    public void deposit(double amount) throws FileNotFoundException {
        this.balance += amount;
        System.out.println(amount + " deposited to account");
        this.updateFile();
    }

    public void withdraw(double amount) throws FileNotFoundException {
        if (this.balance - amount < 0) {
            System.out.println("ERROR: Withdraw amount larger than balance");
            return;
        }
        this.balance -= amount;
        System.out.println(amount + " withdrawn from account");
        this.updateFile();
    }
}

public class BankStuff2 {

    public static Bank getAccount() throws FileNotFoundException{
        Scanner in = new Scanner(System.in);
        boolean accNotFound = true;
        do {
            System.out.println("\nPick an action number\n1.Add new account\n2.Login\n3.Delete account\n4.Exit\nInput: ");
            switch (in.nextInt()) {
                case 1: {
                    System.out.print("Input first name: ");
                    String name = in.next();
                    System.out.print("Input balance: ");
                    double balance = in.nextDouble();
                    Bank acc = new Bank(name, balance);
                    return acc;
                }
                case 2: {
                    System.out.print("Input first name: ");
                    String name = in.next();
                    File file = new File(name + ".txt");
                    if (file.exists()) {
                        System.out.println("\nAccount found");
                        Scanner fin = new Scanner(file);
                        Bank acc = new Bank();
                        while (fin.hasNext()) {
                            acc.setName(fin.nextLine());
                            acc.setBalance(fin.nextDouble());
                        }
                        return acc;
                    } else {
                        System.out.println("\nAccount not found");
                        return null;
                    }
                }
                case 3:{
                    System.out.print("Input first name: ");
                    String name = in.next();
                    File file = new File(name + ".txt");
                    if (file.exists()) {
                        System.out.println("\nAccount found.\nDeleting account.");
                        file.delete();
                    }
                    else{
                        System.out.println("\nAccount not found.");
                    }
                    break;
                }
                case 4:{
                    System.exit(0);
                }
            }
        } while (accNotFound);
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        Bank acc = getAccount();
        int input = 0;
        do {
            System.out.print("\nPick an action number\n1.View Account\n2.Deposit\n3.Withdraw\n4.Exit\nInput: ");
            input = in.nextInt();
            switch (input) {
                case 1: {
                    System.out.println(acc.toString());
                    break;
                }
                case 2: {
                    System.out.print("\nInput amount to deposit: ");
                    acc.deposit(in.nextDouble());
                    break;
                }
                case 3: {
                    System.out.print("\nInput amount to withdraw: ");
                    acc.withdraw(in.nextDouble());
                    break;
                }
                case 4:{
                    break;
                }
                default: {
                    System.out.println("\nInvalid input.");
                }
            }
        } while (input != 4);
    }
}
