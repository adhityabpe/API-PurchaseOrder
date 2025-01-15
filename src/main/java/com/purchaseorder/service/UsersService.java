package com.purchaseorder.service;

import com.purchaseorder.model.Users;
import com.purchaseorder.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findById(int id) {
        return usersRepository.findById(id);
    }

    public Users save(Users user) {
        return usersRepository.save(user);
    }

    public Users update(int id, Users user) {
        Users existingUser = usersRepository.findById(id);
        if (existingUser == null) {
            return null;
        }
        user.setId(id);
        return usersRepository.update(id, user);
    }

    public void delete(int id) {
        Users existingUser = usersRepository.findById(id);
        if (existingUser != null) {
            usersRepository.delete(id);
        }
    }
}
