import { displayUsers } from "./userScript.js";

fetch('/fetch/users/all')
    .then(response => {
        if (!response.ok) {
            throw new Error(`Ошибка HTTP: ${response.status}`);
        }
        return response.json();
    })
    .then(users => {
        displayUsers(users);
    })
    .catch(error => {
        console.error('Произошла ошибка:', error);
    });