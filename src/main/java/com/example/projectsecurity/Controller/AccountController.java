package com.example.projectsecurity.Controller;

import com.example.projectsecurity.Model.Account;
import com.example.projectsecurity.Model.User;
import com.example.projectsecurity.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @GetMapping("/get")
    public ResponseEntity findAllAccount(){
        return ResponseEntity.status(200).body(accountService.findAllAccount());
    }

    //Create a new bank account
    @PostMapping("/add")
    public ResponseEntity addAccountToCutsomer(@AuthenticationPrincipal User user, @Valid @RequestBody Account account){
        accountService.addAccountToUser(account, user.getId());
        return ResponseEntity.status(200).body("New Bank Account Added Successfully");}


    @PutMapping("/update/{account_id}")
    public ResponseEntity updateAccount(@AuthenticationPrincipal User user, @Valid @RequestBody Account account,Integer account_id){
        accountService.updateAccount(account_id,user.getId(), account);
        return ResponseEntity.status(200).body("Account Updated Successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user,@PathVariable Integer account_id){
        accountService.deleteAccount(account_id, user.getId());
        return ResponseEntity.status(200).body("Account Deleted Successfully");
    }

    //Active a bank account
    @PutMapping("/setActivationAccount/{account_id}")
    public ResponseEntity setActivationAccount(@AuthenticationPrincipal User user,@PathVariable Integer account_id){
        accountService.setActivationAccount(user.getId(), account_id);
        return ResponseEntity.status(200).body("Account Activated Successfully");
    }

    //View account details
    @GetMapping("/account-details/{account_id}")
    public ResponseEntity viewAccountDetails(@AuthenticationPrincipal User user ,@PathVariable Integer account_id) {
        return ResponseEntity.ok(accountService.viewDetails(user.getId(),account_id));}

    // List user's accounts
    @GetMapping("/get-my")
    public ResponseEntity getMyAccount(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(accountService.getMyAccount(user.getId()));
    }

    //Deposit money
    @PutMapping("/deposit-money/{account_id}/{amount}")
    public ResponseEntity depositMoney(@AuthenticationPrincipal User user, @PathVariable Integer account_id , @PathVariable double amount ){
        accountService.depositMoney(user.getId(),account_id,amount);
        return ResponseEntity.status(200).body("Deposit money successfully");
    }

    // withdraw money
    @PutMapping("/withdraw-money/{account_id}/{amount}")
    public ResponseEntity withdrawMoney(@AuthenticationPrincipal User user, @PathVariable Integer account_id , @PathVariable double amount ){
        accountService.withdrawMoney(user.getId(),account_id,amount);
        return ResponseEntity.status(200).body("Withdraw money successfully");
    }

    //Transfer funds between accounts
    @PutMapping("/transfer-Funds/{Account1_id}/{Account2_id}/{amount}")
    public ResponseEntity transferFunds(@AuthenticationPrincipal User user , @PathVariable Integer Account1_id, @PathVariable Integer Account2_id, @PathVariable double amount){
        accountService.transferFunds(user.getId(),Account1_id,Account2_id,amount);
        return ResponseEntity.status(200).body("Transfer funds successfully");
    }

    //Block bank account
    @PutMapping("/blockAccount/{account_id}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal User user,@PathVariable Integer account_id){
        accountService.blockAccount(user.getId(), account_id);
        return ResponseEntity.status(200).body("Account Activated Successfully");
    }


}
