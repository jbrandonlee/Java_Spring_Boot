package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.repository.AccountRepository;


@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accRepo;
	
	@Override
	public Account authenticate(String username, String password) {
		return accRepo.findAccountByUsernamePassword(username, password);
	}
	
	@Override
	public List<Account> findAllAccounts() {
		return accRepo.findAll();
	}
	
	@Override
	public Account findAccountById(String id) {
		return accRepo.findById(id).orElse(null);
	}
}
