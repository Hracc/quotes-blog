function submitForm() {
    let nameReaction = document.getElementById("nameReaction").value;

    let msgEl = document.getElementById('message');

    let data = {
        nameReaction: nameReaction,
    };

    console.log(data+ " ");

    fetch(`/fetch/reaction/create`, {
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
        msgEl.textContent = 'Реакция создана';
    })
    .catch(error => {
        msgEl.textContent = 'Ошибка: ' + error.message;
    
        if (error.message != 'Ошибка сети или сервера.') {
            msgEl.textContent = 'Ошибка: Данная реакция существует!';
        } else {
            msgEl.textContent += ' Ошибка сервера: ' + error;
        }
    });
}