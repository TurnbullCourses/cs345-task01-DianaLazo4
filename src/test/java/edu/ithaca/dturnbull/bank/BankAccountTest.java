package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InvalidClassException;

import org.junit.jupiter.api.Test;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        //Valid Bank Acount, Positive Class
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);

        //Valid Bank Acount 0 balance Class
        BankAccount bankAccount2 = new BankAccount("a3@b.com", 0);

        assertEquals(0, bankAccount2.getBalance(), 0.001);

        //Valid negative balance Class:
        BankAccount bankAccount22 = new BankAccount("a6@b.com", -5);

        assertEquals(-5, bankAccount22.getBalance(), 0.001);


    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        // Valid Inputs and enough money
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);

        // Insufficient Funds Class
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

        //  Edge Cases Equivalence Class
        bankAccount.withdraw(0);
        assertEquals(100, bankAccount.getBalance(), 0.001);

        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance(), 0.001);

        // Illegal Input Class
        BankAccount bankAccount2 = new BankAccount("a2@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> bankAccount2.withdraw(-10));

        

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
    void isEmailValidTestfixed(){
        //made some more tests
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string

        //prefix tests(equivalence class)
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid( "abc-d@mail.com")); 
        assertTrue(BankAccount.isEmailValid( "abc_def@mail.com")); 
        assertTrue(BankAccount.isEmailValid( "abc05@mail.com")); 

        assertFalse(BankAccount.isEmailValid( "ab3c..def@mail.com")); 
        assertFalse(BankAccount.isEmailValid( ".abc@mail.com"));
        assertFalse(BankAccount.isEmailValid( "abc-@mail.com"));
        assertFalse(BankAccount.isEmailValid( "ab2c#def@mail.com"));

        // domain tests(equivalence class)
        assertTrue(BankAccount.isEmailValid( "abc.def@mail-archive.cc")); 
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.org")); 
        assertTrue(BankAccount.isEmailValid( "a2bc.def@mail.com")); 


        assertFalse(BankAccount.isEmailValid( "abc.def@mail"));
        assertFalse(BankAccount.isEmailValid( "abc.def@mail#archive.com")); 
        assertFalse(BankAccount.isEmailValid( "abc.def@ma..il.com")); 

        assertFalse(BankAccount.isEmailValid( "abc.def@mail#archive..com")); 

        //TDL tests(equivalence class)
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.cc")); 

        assertFalse(BankAccount.isEmailValid( "abc.def@mail.c")); 
        assertFalse(BankAccount.isEmailValid( "abc.def@mail"));


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