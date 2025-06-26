function submitForm() {
    let username = document.getElementById("username").value;
    let textPost = document.getElementById("textPost").value;
    let statusPost = document.getElementById("statusPost").value;

    let msgEl = document.getElementById('message');

    let data = {
        username: username,
        textPost: textPost,
        statusPost: statusPost,
    };

    fetch(`/fetch/post/create`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка сети или сервера');
            }
            return response.text();
        })
        .then(data => {
            msgEl.textContent = 'Пост создан';
        })
        .catch(error => {
            msgEl.textContent = 'Ошибка: ' + error.message;

            if (error.message != 'Ошибка сети или сервера.') {
                msgEl.textContent += '. Пользователь не найден ';
            }
        });
}
