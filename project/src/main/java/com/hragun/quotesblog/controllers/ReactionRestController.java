package com.hragun.quotesblog.controllers;

import com.hragun.quotesblog.models.Reaction;
import com.hragun.quotesblog.repository.ReactionRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReactionRestController {
    public final ReactionRepository reactionRepository;
    @Autowired
    private HttpSession session;

    @Autowired
    public ReactionRestController(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    @GetMapping("/fetch/reactions/all")
    public Iterable<Reaction> getAllReactions() {
        return reactionRepository.findAll();
    }

    @PostMapping("/fetch/reaction/create")
    public ResponseEntity<String> createReaction(@RequestBody Reaction reaction) {
        try {
            reactionRepository.save(reaction);
            return ResponseEntity.ok("Реакция успешно создана");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Такая реакция существует");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании поста");
        }
    }

    @PostMapping("/fetch/reaction/find")
    public ResponseEntity<String> findReaction(@RequestBody Reaction reaction) {
        session.setAttribute("id", reaction.getIdReaction());
        return ResponseEntity.ok("ID успешно сохранен в сессии");
    }

    @GetMapping("/fetch/reaction/load")
    public ResponseEntity<Reaction> loadReaction() {
        Long id = (Long) session.getAttribute("id");
        if (id != null) {
            Reaction loadedReaction = reactionRepository.findReactionById(id);
            return ResponseEntity.ok(loadedReaction);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/fetch/reaction/delete")
    public void delReaction(@RequestBody Reaction reaction) {
        reactionRepository.delReactn(reaction.getIdReaction());
    }

    @PostMapping("/fetch/reaction/edit")
    public ResponseEntity<String> editReaction(@RequestBody Reaction reaction) {
        try {
            reactionRepository.editReactn(reaction.getIdReaction(), reaction.getNameReaction());
            return ResponseEntity.ok("Реакция успешно изменена");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при редактировании реакции");
        }
    }
}
