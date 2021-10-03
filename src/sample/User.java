/*===================================================================================================
Class that defines all the properties on an user.
===================================================================================================*/

package sample;

public class User {
    private String name;
    private String accountNumber;
    private String password;
    private double balance;

    public User(String name, String accountNumber, String password, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
