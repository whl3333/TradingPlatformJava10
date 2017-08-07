package service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Trade;
import repository.TradeRepository;

/** 
* @author Curry
* @date Apr 2, 2017
* @version 0.1
*/
@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;
    
    /**
     * user login
     * @param username
     * @param password
     * @return login user or null
     */
//    public User login(String username, String password){
//        return userRepository.findByUsernameAndPassword(username, password);
//    }

    /**
     * get user by id
     * @param id
     * @return user or null
     */
//    public User findOne(int id){
//        return userRepository.findOne(id);
//    }
//    
//    /**
//     * get user by name
//     * @param name
//     * @return user or null
//     */
//    public User findOne(String name){
//        return userRepository.findByName(name);
//    }
    
    /**
     * get all user
     * @return user list or null
     */
    public List<Trade> find(){
        return tradeRepository.findAll();
    }
    
    /**
     * sort by name
     * @return user list
     */
//    public List<User> sortByName(){
//        return userRepository.findAllOrderByName();
//    }
//    
    /**
     * create a user
     * @param user
     * @return the user be created or null
     */
    public Trade add(Trade trade){
        return tradeRepository.save(trade);
    }
    
    /**
     * update a user
     * @param user
     * @return the user be updated or null
     */
    public Trade update(Trade trade){
        return tradeRepository.save(trade);
    }
    
    /**
     * delete user by id
     * @param id
     */
    public void delete(int id){
        tradeRepository.delete(id);
    }
}
