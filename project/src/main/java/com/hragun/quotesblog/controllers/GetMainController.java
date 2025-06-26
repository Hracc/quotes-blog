package com.hragun.quotesblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GetMainController {
    //GetMapping
    //user
    @GetMapping("/userCreate")
    public String getUserCreate() {
        return "user/userCreate";
    }
    @GetMapping("/userPage")
    public String getUserPage() {
        return "user/userPage";
    }

    @GetMapping("/userEdit")
    public String getUserEdit() {
        return "user/userEdit";
    }

    @GetMapping("/userView")
    public String getUserView() {
        return "user/userView";
    }

    @GetMapping("/{idUser}")
    public String getAcc(@PathVariable Long idUser, Model model) {
        model.addAttribute("idUser", idUser);
        return "user/userPage";
    }

    //post
    @GetMapping("/postCreate")
    public String getCreateQuote() {
        return "post/postCreate";
    }

    @GetMapping("/")
    public String getQuoteFeed() {
        return "post/postMain";
    }

    @GetMapping("/postView")
    public String getPostView() {
        return "post/postView";
    }

    @GetMapping("/postEdit")
    public String getPostEdit() {
        return "post/postEdit";
    }
    //reaction
    @GetMapping("/reactnCreate")
    public String getReactnCreate() {
        return "reaction/reactnCreate";
    }
    @GetMapping("/reactnView")
    public String getReactnView() {
        return "reaction/reactnView";
    }
    @GetMapping("/reactionEdit")
    public String getReactionEdit() {
        return "reaction/reactnEdit";
    }
}
