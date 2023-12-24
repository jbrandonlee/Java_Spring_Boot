package sg.edu.nus.iss.lms.service;

import java.util.List;

import sg.edu.nus.iss.lms.model.Account;

public interface AccountService {
	public Account authenticate(String username, String password);
	public Account createAccount(Account account);
	public Account updateAccount(Account account);
	public List<Account> findAllAccounts();
	public Account findAccountById(String id);
}
