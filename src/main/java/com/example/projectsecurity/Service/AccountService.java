package com.example.projectsecurity.Service;

import com.example.projectsecurity.API.APIException;
import com.example.projectsecurity.Model.Account;
import com.example.projectsecurity.Model.Customer;
import com.example.projectsecurity.Model.User;
import com.example.projectsecurity.Repository.AccountRepository;
import com.example.projectsecurity.Repository.AuthRepository;
import com.example.projectsecurity.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public List<Account> findAllAccount() {
        return accountRepository.findAll();}


   //Create a new bank account
   public void addAccountToUser(Account account, Integer customer_id) {
       Customer c = customerRepository.findCustomerById(customer_id);
       if (c==null){
           throw new APIException("User not found");}
       account.setAccountNumber(account.getAccountNumber());
       account.setBalance(account.getBalance());
       account.setCustomer(c);
       accountRepository.save(account);
   }
   //Active a bank account
   public void setActivationAccount(Integer account_id, Integer customer_id) {
        Account a = accountRepository.findAccountById(account_id);
        Customer c = customerRepository.findCustomerById(customer_id);
        if (a==null){
            throw new APIException("Account not found");}
        if(c==null){
            throw new APIException("Customer not found");}
        else if(a.getCustomer().getId()!= customer_id){
            throw new APIException("This account is not related to this customer");}
        a.setIsActive(true);
        accountRepository.save(a);}


   public void updateAccount(Integer account_id, Integer customer_id,Account account) {
        Account a = accountRepository.findAccountById(account_id);
        Customer c = customerRepository.findCustomerById(customer_id);
     if(a==null){
         throw new APIException("Account not found");
     }if(c==null){
         throw new APIException("Customer not found");}
     else if (a.getCustomer().getId()!= customer_id) {
         throw new APIException("This account is not related to this customer");}
     a.setBalance(account.getBalance());
     a.setCustomer(c);
     accountRepository.save(a);
   }

   public void deleteAccount(Integer account_id, Integer customer_id) {
        Account a = accountRepository.findAccountById(account_id);
        Customer c = customerRepository.findCustomerById(customer_id);
        if(a==null){
            throw new APIException("Account not found");
        }if(c==null){
            throw new APIException("Customer not found");}
        else if (a.getCustomer().getId()!= customer_id) {
            throw new APIException("This account is not related to this customer");}
        accountRepository.delete(a);}

    //View account details
    public Account viewDetails(Integer customer_id,Integer account_id) {
        Account account = accountRepository.findAccountById(account_id);
        Customer customer=customerRepository.findCustomerById(customer_id);
        if (account == null) {
            throw new APIException("Account not found");}
        if (customer == null) {
            throw new APIException("customer not found");}
        if(account.getCustomer().getId()!=customer_id){
            throw new APIException("Sorry you don't have authority");}
        return account;}


    // List user's accounts
    public List<Account> getMyAccount(Integer customer_id) {
        Customer c = customerRepository.findCustomerById(customer_id);
        return accountRepository.getAllMyAccountsByCustomerId(c);
    }

    //Deposit money
    public Account depositMoney(Integer account_id,Integer customer_id,double amount) {
        Account account = accountRepository.findAccountById(account_id);
        Customer customer=customerRepository.findCustomerById(customer_id);
        if (account == null) {
            throw new APIException("Account not found");}
        if (customer == null) {
            throw new APIException("Customer not found");
        }else if(account.getCustomer().getId()!= customer_id){
            throw new APIException("Doesn't have authority");}
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);}


   //Withdraw money
    public Account withdrawMoney(Integer account_id, Integer customer_id,double amount) {
        Account account = accountRepository.findAccountById(account_id);
        Customer customer=customerRepository.findCustomerById(customer_id);
        if (account == null) {
            throw new APIException("Account not found");
        }
        if (customer == null) {
            throw new APIException("customer not found");
        }else if(account.getCustomer().getId()!= customer_id){
            throw new APIException("Doesn't have authority");}
        if (account.getBalance() < amount) {
            throw new APIException("Balance less than amount");}
            account.setBalance(account.getBalance() - amount);
            return accountRepository.save(account);}


    //Transfer
    public void transferFunds(Integer Account1_id, Integer Account2_id, Integer customer_id,double amount) {
        Account a = accountRepository.findAccountById(Account1_id);
        Account a1 = accountRepository.findAccountById(Account2_id);
        Customer customer=customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new APIException("Customer not found");}
        if (a == null || a1== null) {
            throw new APIException("Account not found");
        }else if(a.getCustomer().getId()!= customer_id || a1.getCustomer().getId()!=customer_id){
            throw new APIException("Doesn't have authority");}
        if (a.getBalance() < amount) {
            throw new APIException("Balance less than amount");}
        a.setBalance(a.getBalance() - amount);
        a1.setBalance(a1.getBalance() + amount);
        accountRepository.save(a);
        accountRepository.save(a1);}


    //Block bank account
    public void blockAccount(Integer account_id, Integer customer_id) {
        Account a = accountRepository.findAccountById(account_id);
        Customer c = customerRepository.findCustomerById(customer_id);
        if (a==null){
            throw new APIException("Account not found");}
        if(c==null){
            throw new APIException("Customer not found");}
        else if(a.getCustomer().getId()!= customer_id){
            throw new APIException("This account is not related to this customer");}
        a.setIsActive(false);
        accountRepository.save(a);}









}
