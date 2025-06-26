let delAlertEdit = document.getElementById("deleteAccountModal");
function getEdit(a_href) {
    let postBlock = a_href.closest('.post');

    if (postBlock) {
        const idPost = postBlock.getAttribute('data-id');
        localStorage.setItem('idPost', idPost);
    } else {
        console.error('Post block not found');
    }

    delAlertEdit.style.display = "none";
}
function confEdit() {
    const idPost = localStorage.getItem('idPost');
    let msgEl = document.getElementById('message');

    let data = {
        idPost: idPost,
        username: document.getElementById("username").value,
        textPost: document.getElementById("textPost").value,
        statusPost: document.getElementById("statusPost").value
    };
    fetch(`/fetch/post/edit`, {
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
            window.location.href = '/postView';
            localStorage.removeItem('idPost');
            return response.text();
        })
        .catch(error => {
            msgEl.textContent = 'Ошибка: ' + error.message;

            if (error.message != 'Ошибка сети или сервера.') {
                msgEl.textContent = 'Ошибка: Такого пользователя не существует!';
            }

            document.getElementById('errorContainer').style.display = 'block';
        });
}