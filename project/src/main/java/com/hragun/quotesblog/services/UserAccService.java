package com.hragun.quotesblog.services;

import com.hragun.quotesblog.DTO.UserAccDTO;
import com.hragun.quotesblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccService {

    @Autowired
    private UserRepository usrAccRepstry;


    public UserAccDTO getAccInfo(Long idUser){
        UserAccDTO userAccDTO=new UserAccDTO();
        userAccDTO.setNickUser(usrAccRepstry.findNick(idUser));
        userAccDTO.setPublicPosts(usrAccRepstry.findPublishedPosts(idUser));
        userAccDTO.setReactionPosts(usrAccRepstry.findReactionPosts(idUser));
        userAccDTO.setOfferedPosts(usrAccRepstry.findOfferedPosts(idUser));
        return userAccDTO;
    }

}
