package com.crio.qcontest.services;

 import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.crio.qcontest.constants.UserOrder;
import com.crio.qcontest.entities.User;
import com.crio.qcontest.repositories.IUserRepository;

public class UserService{

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name) {
        User u = new User(name);
        userRepository.save(u);
        return userRepository.findByName(name).get();
    //  return null;
    }

    public List<User> getUsers(UserOrder userOrder) {
        if(userOrder.equals(UserOrder.SCORE_ASC)) return userRepository.findAll().stream().sorted(Comparator.comparingInt(a -> a.getTotalScore())).collect(Collectors.toList());
        return userRepository.findAll().stream().sorted(Comparator.comparingInt(a -> -a.getTotalScore())).collect(Collectors.toList());
    //  return Collections.emptyList();
    } 
}
