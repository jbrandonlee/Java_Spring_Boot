package sg.nus.iss.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.javaproject.model.Account;

public interface AccountRepository extends JpaRepository<Account,String> {
}

