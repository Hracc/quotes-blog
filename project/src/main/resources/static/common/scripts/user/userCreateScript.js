function submitForm() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let roleUser = document.getElementById("role").value;

    let msgEl = document.getElementById('message');

    let data = {
        username: username,
        password: password,
        roleUser: roleUser
    };

    fetch(`/fetch/user/create`, {
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
        msgEl.textContent = 'Пользователь создан';
    })
    .catch(error => {
        msgEl.textContent = 'Ошибка: ' + error.message;
    
        if (error.message != 'Ошибка сети или сервера.') {
            msgEl.textContent = 'Ошибка: Данный ник занят!';
        } else {
            msgEl.textContent += ' Ошибка сервера: ' + error;
        }
        
        document.getElementById('errorContainer').style.display = 'block';
    });
}
