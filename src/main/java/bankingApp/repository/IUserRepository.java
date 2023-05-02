package bankingApp.repository;

import bankingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users (name,password,email,account_number,balance)" +
            " VALUES (:nameReq,:passwordReq,:emailReq,:accNumberReq,:balanceReq)", nativeQuery = true)
    void addUser(@Param("nameReq") String name,
                 @Param("passwordReq") String password,
                 @Param("emailReq") String email,
                 @Param("accNumberReq") String accNumber,
                 @Param("balanceReq") double balance);


    @Query(value = "SELECT * FROM users WHERE name = ?1", nativeQuery = true)
    ArrayList<User> nameExists(String name);

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    ArrayList<User> emailExists(String email);

    @Query(value = "SELECT * FROM users WHERE account_number = ?1", nativeQuery = true)
    ArrayList<User> accNumberExists(String accountNumber);

    @Query(value = "SELECT * FROM users WHERE name = ?1 AND password = ?2", nativeQuery = true)
    User loginUser(String name, String password);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET balance = ?1 WHERE account_number = ?2" , nativeQuery = true)
    void changeBalance(double amount, String accountNumber);

    @Query(value = "SELECT * FROM users WHERE account_number = ?1" , nativeQuery = true)
    User getUserByAccountNumber(String accountNumber);
}
