const currentUrl = window.location.href;
const url = new URL(currentUrl);
const userId = url.pathname.split('/').pop();
const apiUrl = `/fetch/${userId}/info`;

fetch(apiUrl)
    .then(response => {
        if (!response.ok) {
            throw new Error(`Ошибка HTTP: ${response.status}`);
        }
        return response.json();
    })
    .then(info => {
        const postsPublic = document.getElementById('status-public');
        const postsLiked = document.getElementById('status-liked');
        const postsOffered = document.getElementById('status-offered');
        const nickUser=document.getElementById('nick');
        nickUser.innerHTML=`<h1>${info.nickUser}</h1>`;
        postsPublic.innerHTML = `<p>${info.publicPosts}</p>`;
        postsLiked.innerHTML = `<p>${info.reactionPosts}</p>`;
        postsOffered.innerHTML = `<p>${info.offeredPosts}</p>`;
    })
    .catch(error => {
        console.error('Произошла ошибка:', error);
    });