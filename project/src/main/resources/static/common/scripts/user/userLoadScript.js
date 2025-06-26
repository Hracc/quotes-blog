function getData() {
    let idUser = localStorage.getItem('idUser');
    let data = {
        idUser: idUser,
    };
    return data;
}

async function fetchData() {
    try {
        await fetch(`/fetch/user/find`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(getData())
        });
        const response = await fetch('/fetch/user/load');
        if (!response.ok) {
            throw new Error(`Ошибка HTTP: ${response.status}`);
        }
        const user = await response.json();
        document.getElementById("username").value = user.username;
        document.getElementById("password").value = user.password;
        document.getElementById("role").value = user.roleUser;
    } catch (error) {
        console.error('Произошла ошибка:', error);
    }
}
fetchData();