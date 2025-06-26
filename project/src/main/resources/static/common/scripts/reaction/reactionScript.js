export function displayReactions(reactions) {
    const postsContainer = document.getElementById('posts-container');

    reactions.forEach(reaction => {
        const postElement = document.createElement('div');
        postElement.className = 'post';
        postElement.setAttribute('data-id', `${reaction.idReaction}`);
        let status;
        if(reaction.statusPost === "published"){
            status ="Опубликовано";
        }else{
            status ="Предложено";
        }

        postElement.innerHTML = `
        <div class="edit">
            <a id="edit" href="/reactionEdit" onclick="getEdit(this)" >Редактировать</a>
            <button onclick="delAlert(this)" class="del">Удалить реакцию</button>
        </div>
        <br>
        <div>
            <span class="title">Реакция:</span><span class="info"> ${reaction.nameReaction}</span>
        </div>
        <div class="post-img">
        </div>
        `;

        postsContainer.appendChild(postElement);
    });
}
