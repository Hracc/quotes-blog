export function displayUsers(users) {
    const postsContainer = document.getElementById('posts-container');

    users.forEach(user => {
        const postElement = document.createElement('div');
        postElement.className = 'post';
        postElement.setAttribute('data-id', `${user.idUser}`);
        let role;
        if (user.roleUser === "admin") {
            role = "Админ";
        } else {
            role = "Пользователь";
        }

        postElement.innerHTML = `
            <div class="edit">
            <a id="edit" href="/userEdit" onclick="getEdit(this)">Редактировать</a>
            <button class="del" onclick="delAlert(this)">Удалить пользователя</button>
            </div>
            <div>
                <span class="title">Ник:</span><span class="info"> ${user.username}</span>
            </div>
            <div >
                <span class="title">Пароль:</span><span class="info"> ${user.password}</span>
            </div>
            <div>
                <span class="title">Роль:</span><span class="info"> ${role}</span>
            </div>
            <div class="post-img">
            </div>
        `;

        postsContainer.appendChild(postElement);
    });
}
