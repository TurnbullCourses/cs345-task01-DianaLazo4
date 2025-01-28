package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            if (isAmountValid(startingBalance)==true){
                this.balance = startingBalance;
            }
            else{
                throw new IllegalArgumentException("Starting Balance Invalid Amount");
            }
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    public void deposit(double amount){
        if (amount < 0){
            throw new IllegalArgumentException("Not valid input");
        }
        if (false == isAmountValid(amount)){
            throw new IllegalArgumentException("Not valid input");
        }
        double balancee = balance + amount;
        balance = balancee;
    }

    public void transfer(BankAccount targetAccount, double amount) throws InsufficientFundsException {
        if (false == isAmountValid(amount)){
            throw new IllegalArgumentException("Not valid input");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for the transfer.");
        }
        double balancee  = balance - amount;
        balance = balancee;       
        targetAccount.deposit(amount); 
    }
    
    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        //added this if statement, ammount withdrawn must be positive
        if (amount < 0){
            throw new IllegalArgumentException("Not valid input");
        }
        if (false == isAmountValid(amount)){
            throw new IllegalArgumentException("Not valid input");
        }
        if (amount <= balance){
            balance -= amount;
            
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    /**
     * @post removes balance from account
     */
    public void withdrawOscar (double amount) throws InsufficientFundsException{
        if (amount < 0){
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance){ 
            throw new InsufficientFundsException("Amount should be equal to or less than balance");
        }
        balance -= amount; 
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }

        for (char c : email.toCharArray()){
            if (!Character.isLetterOrDigit(c) && c != '@' && c != '.' && c != '-' && c != '_'){
                return false;
            }
        }
        
        String[] split = email.split("@");
        
        if (split.length != 2 || split[0].length() < 1 || split[1].length() < 3 || split[1].indexOf('.') == -1){
            return false;
        }

        return true;

    }

    public static boolean isAmountValid(double amount){

        if (amount < 0) {
            return false;
        }
    
        // Amount to string
        String amountStr = String.valueOf(amount);
    
        // Find index of decimal point
        int decimalIndex = amountStr.indexOf('.');
    
        // If there is a decimal, it loops and counts how many digits are after the decimal
        if (decimalIndex != -1) {
            int count = 0;
            for (int i = decimalIndex + 1; i < amountStr.length(); i++) {
                count++;
            }
    
            if (count > 2) {
                return false;
            }
        }
    
        return true;
    }

}