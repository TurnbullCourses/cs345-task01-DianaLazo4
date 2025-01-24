package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string

        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com")); // valid email address/period in local part
        assertTrue(BankAccount.isEmailValid( "abc-d@mail.com")); // valid email address/hyphen in local part
        assertTrue(BankAccount.isEmailValid( "abc_def@mail.com")); // valid email address/underscore in local part
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.cc")); // valid email address/two letter domain--border case

        assertFalse(BankAccount.isEmailValid( "abc#def@mail.com")); // invalid email address/special character in local part
        assertFalse(BankAccount.isEmailValid( "abc.def@mail#archive.com")); // invalid email address/special character in domain
        assertFalse(BankAccount.isEmailValid( "abc.def@mail")); // invalid email address/no domain--border case
        assertFalse(BankAccount.isEmailValid( "abc..def@mail.com	")); // invalid email address/double special in local part--border case
        
        // No equivalence class seems to be missing
        // Could add a border case for a valid email with a subdomain (help.ithaca.edu)
        // Border case of a valid email with missing @
        // Border case of consecutive dot in domain

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}