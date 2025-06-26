function getData() {
    let idPost = localStorage.getItem('idPost');
    let data = {
        idPost: idPost,
    };
    return data;
}

async function fetchData() {
    try {
        await fetch(`/fetch/post/find`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(getData())
        });
        const response = await fetch('/fetch/post/load');
        if (!response.ok) {
            throw new Error(`Ошибка HTTP: ${response.status}`);
        }
        const post = await response.json();

        document.getElementById("username").value = post.username;
        document.getElementById("textPost").value = post.textPost;
        document.getElementById("statusPost").value = post.statusPost;
    } catch (error) {
        console.error('Произошла ошибка:', error);
    }
}

fetchData();