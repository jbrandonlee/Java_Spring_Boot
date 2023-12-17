package sg.edu.nus.iss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.iss.lms.model.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

}
