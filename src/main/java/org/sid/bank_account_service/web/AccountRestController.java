package org.sid.bank_account_service.web;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    //@Autowired not the best, a constructor is a better option
    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Bank account with id %s not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount) {
        if(bankAccount.getId()==null) bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account=bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Bank account with id %s not found", id)));
        if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if(bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteBankAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}
