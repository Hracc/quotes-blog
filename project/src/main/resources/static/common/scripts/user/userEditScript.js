let delAlertEdit = document.getElementById("deleteAccountModal");
function getEdit(a_href) {
    let postBlock = a_href.closest('.post');

    if (postBlock) {
        const idUser = postBlock.getAttribute('data-id');
        localStorage.setItem('idUser', idUser);
    } else {
        console.error('Post block not found');
    }

    delAlertEdit.style.display = "none";
}
function confEdit() {
    const idUser = localStorage.getItem('idUser');
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const roleUser =document.getElementById("role").value;

    let msgEl = document.getElementById('message');

    let data = {
        idUser: idUser,
        username: username,
        password: password,
        roleUser: roleUser
    };
    fetch(`/fetch/user/edit`, {
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
            window.location.href = '/userView';
            localStorage.removeItem('idUser');
            return response.text();
        })
        .catch(error => {
            msgEl.textContent = 'Ошибка: ' + error.message;
        
            if (error.message != 'Ошибка сети или сервера.') {
                msgEl.textContent = 'Ошибка: Данный ник занят!';
            }
            
            document.getElementById('errorContainer').style.display = 'block';
        });
}