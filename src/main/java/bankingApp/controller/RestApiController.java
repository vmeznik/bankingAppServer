package bankingApp.controller;


import bankingApp.model.RequestConfirmation;
import bankingApp.model.Transaction;
import bankingApp.model.User;
import bankingApp.repository.ITransactionsRepository;
import bankingApp.repository.IUserRepository;
import bankingApp.utility.AccountNumberGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class RestApiController {
    private final IUserRepository userRepository;
    private final ITransactionsRepository transactionsRepository;

    public RestApiController(IUserRepository userRepository, ITransactionsRepository transactionsRepository) {
        this.userRepository = userRepository;
        this.transactionsRepository = transactionsRepository;
    }

    @PostMapping("bankingApp/register")
    public RequestConfirmation registerUser(@RequestBody User user) {
        return registerUserConfirmation(user);
    }

    @PostMapping("bankingApp/login")
    public RequestConfirmation loginUser(@RequestBody User user) {
        return loginUserConfirmation(user);
    }


    @PostMapping("bankingApp/accountInfo")
    public User user(@RequestBody User user){
        return getUserInfo(user);
    }

    @PostMapping("bankingApp/sendMoney")
    public RequestConfirmation sendMoney(@RequestBody Transaction transaction){
        return sendMoneyConfirmation(transaction);
    }

    @PostMapping("bankingApp/getTransactions")
    public List<Transaction> getTransactions(@RequestBody User user){
       return getTransactionsFromDb(user);
    }


    private RequestConfirmation loginUserConfirmation(User user) {
        if (userRepository.loginUser(user.getName(), user.getPassword())== null){
            return new RequestConfirmation(false,"Wrong username or password");
        }else {
            return new RequestConfirmation(true,null);
        }
    }

    private RequestConfirmation registerUserConfirmation(User user) {
        try {
            if (!userRepository.nameExists(user.getName()).isEmpty()) {
                return new RequestConfirmation(false, "Username already exists");
            }
            if (!userRepository.emailExists(user.getEmail()).isEmpty()) {
                return new RequestConfirmation(false, "Email already exists");
            }
            userRepository.addUser(
                    user.getName(), user.getPassword(), user.getEmail(),
                    createAccNumber(),10000);
        } catch (Exception e) {
            e.printStackTrace();
            return new RequestConfirmation(false, "User was not registered");
        }
        return new RequestConfirmation(true, null);
    }

    private User getUserInfo(User user){
        User userInfo;
        try {
            userInfo = userRepository.loginUser(user.getName(),user.getPassword());
            return userInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private RequestConfirmation sendMoneyConfirmation(Transaction transaction){
        if (!userRepository.accNumberExists(transaction.getSenderAccNumber()).isEmpty() &&
        !userRepository.accNumberExists(transaction.getReceiverAccNumber()).isEmpty()){

            User sender = userRepository.getUserByAccountNumber(transaction.getSenderAccNumber());
            User receiver = userRepository.getUserByAccountNumber(transaction.getReceiverAccNumber());

            userRepository.changeBalance((sender.getBalance() - transaction.getAmount()),transaction.getSenderAccNumber());
            userRepository.changeBalance((receiver.getBalance() + transaction.getAmount()),transaction.getReceiverAccNumber());

            transactionsRepository.save(transaction);

            return new RequestConfirmation(true,null);
        }
        return new RequestConfirmation(false,"Transaction failed");
    }

    private List<Transaction> getTransactionsFromDb(User user){
        List<Transaction> transactions = new ArrayList<>();
        try {
            transactions = transactionsRepository.getTransactions(user.getAccount_number());
            return transactions;
        }catch (Exception e){
            e.printStackTrace();
        }
        return transactions;
    }

    private int createAccNumber(){
        int accNumber = AccountNumberGenerator.generateNumber();
        while (!userRepository.accNumberExists(accNumber).isEmpty()){
            accNumber = AccountNumberGenerator.generateNumber();
        }
        return accNumber;
    }
}
