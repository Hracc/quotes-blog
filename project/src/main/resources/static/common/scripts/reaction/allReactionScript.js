import {displayReactions } from "./reactionScript.js";

fetch('/fetch/reactions/all')
    .then(response => {
        if (!response.ok) {
            throw new Error(`Ошибка HTTP: ${response.status}`);
        }
        return response.json();
    })
    .then(reactions => {
        displayReactions(reactions);
    })
    .catch(error => {
        console.error('Произошла ошибка:', error);
    });