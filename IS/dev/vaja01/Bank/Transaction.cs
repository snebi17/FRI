using System;

namespace Bank {
    public class Transaction {
        public decimal Amount;
        public DateTime Date;
        public string Notes;
        public Transaction(decimal amount, DateTime Date, string notes) {
            this.Amount = amount;
            this.Date = Date;
            this.Notes = notes;
        }
    }
}