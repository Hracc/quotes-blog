export function displayPosts(posts) {
    const postsContainer = document.getElementById('posts-container');

    posts.forEach(post => {
        const postElement = document.createElement('div');
        postElement.className = 'post';
        postElement.setAttribute('data-id', `${post.idPost}`);
        let status;
        if(post.statusPost === "published"){
            status ="Опубликовано";
        }else{
            status ="Предложено";
        }

        postElement.innerHTML = `
            <div class="edit">
                <a id="edit" href="/postEdit" onclick="getEdit(this)">Редактировать</a>
                <button class="del" onclick="delAlert(this)">Удалить пост</button>
            </div>
            <div class="post-nick">
                <a href="/${post.idUser}">${post.username}</a>
            </div>
            <div>
            <span class="title">Статус:</span><span class="info"> ${status} </span>
            </div>
            <br>
            <div class="post-text">
                <span>${post.textPost}</span>
            </div>
            <div class="buttons">
                <button class="likes-button">Реакция</button>
            </div>
        `;

        postsContainer.appendChild(postElement);
    });
}