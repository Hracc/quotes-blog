import { displayPosts } from "../post/postScript.js";

const currentUrl = window.location.href;
const url = new URL(currentUrl);
const userId = url.pathname.split('/').pop();
const apiUrl = `/fetch/user/${userId}`;

fetch(apiUrl)
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