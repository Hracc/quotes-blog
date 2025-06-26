import { displayPosts } from "./postScript.js";

fetch('/fetch/posts/published')
    .then(response => {
        if (!response.ok) {
            throw new Error(`Ошибка HTTP: ${response.status}`);
        }
        return response.json();
    })
    .then(posts => {
        displayPosts(posts);
    })
    .catch(error => {
        console.error('Произошла ошибка:', error);
    });