using System;
using System.Collections;

namespace Bank {
    public class BankAccount {
        private static int accountNumberSeed = 1234567890;
        public string Number { get; set; }
        public string Owner { get; set; }
        public decimal Balance { get { 
            decimal balance = 0;
            foreach (var item in allTransactions) {
                balance += item.Amount;
            }
            return balance;
        } }   
        private List<Transaction> allTransactions = new List<Transaction>();

        public BankAccount (string name, decimal initialBalance) {
            Number = accountNumberSeed.ToString();
            accountNumberSeed++;
            
            Owner = name;
            MakeDeposit(initialBalance, DateTime.Now, "Initial deposit");
        }
        public void MakeDeposit (decimal amount, DateTime date, string note) {
            if (amount <= 0) {
                throw new ArgumentOutOfRangeException(nameof(amount), "Amount of deposit must be positive!");
            }
            var deposit = new Transaction(amount, date, note);
            allTransactions.Add(deposit);
            System.Console.WriteLine($"Deposit of {amount} was made. New account balance for {Owner} is {Balance}.");
        }
        public void MakeWithdrawal (decimal amount, DateTime date, string note) {
            if (amount <= 0) {
                throw new ArgumentOutOfRangeException(nameof(amount), "Amount of withdrawal must be positive!");
            }
            if (Balance - amount < 0) {
                throw new InvalidOperationException("Not sufficient funds for this withdrawal.");
            }
            var withdrawal = new Transaction(-amount, date, note);
            allTransactions.Add(withdrawal);
            System.Console.WriteLine($"Withdrawal of {amount} was made. New account balance for {Owner} is {Balance}.");
        }

        public string GetAccountHistory() {
            var report = new System.Text.StringBuilder();

            decimal balance = 0;
            report.AppendLine("Date\t\tAmount\tBalance\tNote");
            foreach (var item in allTransactions) {
                balance += item.Amount;
                report.AppendLine($"{item.Date.ToShortDateString()}\t{item.Amount}\t{balance}\t{item.Notes}");
            }

            return report.ToString();
        }
    }
}