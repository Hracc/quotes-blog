let delAlertEdit = document.getElementById("deleteAccountModal");
function getEdit(a_href) {
    let postBlock = a_href.closest('.post');

    if (postBlock) {
        const idReaction = postBlock.getAttribute('data-id');
        localStorage.setItem('idReaction', idReaction);
    } else {
        console.error('Post block not found');
    }

    delAlertEdit.style.display = "none";
}
function confEdit() {
    const idReaction = localStorage.getItem('idReaction');
    const nameReaction = document.getElementById("nameReaction").value

    let msgEl = document.getElementById('message');

    let data = {
        idReaction: idReaction,
        nameReaction: nameReaction
    };
    fetch(`/fetch/reaction/edit`, {
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
            window.location.href = '/reactnView';
            localStorage.removeItem('idReaction');
            return response.text();
        })
        .catch(error => {
            msgEl.textContent = 'Ошибка: ' + error.message;

            if (error.message != 'Ошибка сети или сервера.') {
                msgEl.textContent = 'Ошибка: Данная реакция существует!';
            }
        });
}