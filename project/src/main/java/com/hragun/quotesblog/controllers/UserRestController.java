package com.hragun.quotesblog.controllers;

import com.hragun.quotesblog.DTO.UserAccDTO;
import com.hragun.quotesblog.models.User;
import com.hragun.quotesblog.repository.UserRepository;
import com.hragun.quotesblog.services.UserAccService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserRestController {
    private final UserAccService userAccService;
    private final UserRepository userRepository;
    @Autowired
    private HttpSession session;

    @Autowired
    public UserRestController(UserAccService userAccService, UserRepository userRepository) {
        this.userAccService = userAccService;
        this.userRepository = userRepository;
    }

    @GetMapping("/fetch/users/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Вывод данных в профиле
    @GetMapping("/fetch/{idUser}/info")
    public UserAccDTO infoUser(@PathVariable Long idUser) {
        return userAccService.getAccInfo(idUser);
    }

    @PostMapping("/fetch/user/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userRepository.save(user);
            return ResponseEntity.ok("Пользователь успешно создан");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Такой ник занят");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании пользователя");
        }
    }

    @PostMapping("/fetch/user/find")
    public ResponseEntity<String> findUser(@RequestBody User user) {
        session.setAttribute("id", user.getIdUser());
        return ResponseEntity.ok("ID успешно сохранен в сессии");
    }

    @GetMapping("/fetch/user/load")
    public ResponseEntity<User> loadUser() {
        Long id = (Long) session.getAttribute("id");
        if (id != null) {
            User loadedReaction = userRepository.findUserById(id);
            return ResponseEntity.ok(loadedReaction);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/fetch/user/delete")
    public void delUser(@RequestBody User user) {
        userRepository.delUser(user.getIdUser());
    }

    @PostMapping("/fetch/user/edit")
    public ResponseEntity<String> editUser(@RequestBody User user) {
        try{
            userRepository.editUser(user.getIdUser(), user.getUsername(), user.getPassword(), user.getRoleUser());
            return ResponseEntity.ok("Пользователь успешно изменен");
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Такой ник занят");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при редактировании пользователя");
        }
    }
}
