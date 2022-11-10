using Bank;
var account = new BankAccount("Nejc", 1000);

System.Console.WriteLine($"Account {account.Number} was created for {account.Owner} with {account.Balance}€ initial balance.");
System.Console.WriteLine("1 to make a deposit, 2 to make a withdrawal and 3 to view account history.");
bool flag = true;

while (flag) {
    string type = System.Console.ReadLine();
    switch (type) {
        case "1": {
            System.Console.WriteLine("How much do you want to deposit?");
            decimal amount = Decimal.Parse(System.Console.ReadLine());
            System.Console.WriteLine("What is this deposit about?");
            var notes = System.Console.ReadLine();
            account.MakeDeposit(amount, DateTime.Now, notes);
            break;
        }
        case "2": {
            System.Console.WriteLine("How much do you want to withdraw?");
            decimal amount = Decimal.Parse(System.Console.ReadLine());
            System.Console.WriteLine("What is this withdrawal about?");
            var notes = System.Console.ReadLine();
            account.MakeWithdrawal(amount, DateTime.Now, notes);
            break;
        }
        case "3": {
            System.Console.WriteLine(account.GetAccountHistory());
            break;
        }
        case "0": {
            flag = false;
            break;
        }
        default: break;
    }
}