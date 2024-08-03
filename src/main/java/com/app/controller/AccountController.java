package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AccountDto;
import com.app.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	public AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	//Add Account REST
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
		
	}
	
	//Get Account By Id
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto); 
	}	
	
	//Deposit to account
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
										@RequestBody Map<String, Double> request){
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//Withdraw to account
		@PutMapping("/{id}/withdraw")
		public ResponseEntity<AccountDto> withDraw(@PathVariable Long id,
											@RequestBody Map<String, Double> request){
			Double amount = request.get("amount");
			AccountDto accountDto = accountService.withDraw(id, amount);
			return ResponseEntity.ok(accountDto);
		}
		
		//Get All Accounts List 
		@GetMapping
		public ResponseEntity<List<AccountDto>> getAllAccount(){
			List<AccountDto> accounts = accountService.getAllAccounts();
			return ResponseEntity.ok(accounts); 
		}
		
		//Delete Account By Id
		@DeleteMapping("/{id}")
		public ResponseEntity<String> deleteAccount(@PathVariable Long id){
			accountService.deleteAccount(id);;
			return ResponseEntity.ok("Account Deleted Successfully!!"); 
		}

}
