package com.project.splitwise.SplitWise.Application.Algo;

import java.util.*;

public class SplitwiseSimplification {

    // Function to calculate and simplify debts
    public static void simplifyDebts(List<Transaction> transactions) {
        // Step 1: Calculate net balances for each person
        Map<String, Integer> balances = new HashMap<>();

        // For each transaction, update the payer's and payee's balance
        for (Transaction t : transactions) {
            balances.put(t.payer, balances.getOrDefault(t.payer, 0) - t.amount);
            balances.put(t.payee, balances.getOrDefault(t.payee, 0) + t.amount);
        }

        // Step 2: Create two lists: one for people who owe (negative balance)
        // and one for people who are owed (positive balance)
        List<Map.Entry<String, Integer>> oweList = new ArrayList<>();
        List<Map.Entry<String, Integer>> owedList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : balances.entrySet()) {
            if (entry.getValue() < 0) {
                oweList.add(entry);
            } else if (entry.getValue() > 0) {
                owedList.add(entry);
            }
        }

        // Step 3: Settle debts
        int i = 0, j = 0;

        while (i < oweList.size() && j < owedList.size()) {
            Map.Entry<String, Integer> owe = oweList.get(i);
            Map.Entry<String, Integer> owed = owedList.get(j);

            int settleAmount = Math.min(-owe.getValue(), owed.getValue());

            System.out.println(owe.getKey() + " pays " + settleAmount + " to " + owed.getKey());

            // Update the balances after settling this amount
            owe.setValue(owe.getValue() + settleAmount); // Reduce owed amount
            owed.setValue(owed.getValue() - settleAmount); // Reduce received amount

            // If this person has settled their debt, move to the next person
            if (owe.getValue() == 0) {
                i++;
            }

            if (owed.getValue() == 0) {
                j++;
            }
        }
    }

    // Transaction class to represent each transaction
    static class Transaction {
        String payer;
        String payee;
        int amount;

        public Transaction(String payer, String payee, int amount) {
            this.payer = payer;
            this.payee = payee;
            this.amount = amount;
        }
    }

    public static void main(String[] args) {
        // Sample transactions
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("A", "B", 100));
        transactions.add(new Transaction("B", "C", 140));
        transactions.add(new Transaction("A", "C", 100));

        // Simplify debts
        simplifyDebts(transactions);
    }
}
