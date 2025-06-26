

function getData() {
    let idReaction = localStorage.getItem('idReaction');
    let data = {
        idReaction: idReaction,
    };
    return data;
}

async function fetchData() {
    try {
        // Выполняем POST-запрос для сохранения idReaction
        await fetch(`/fetch/reaction/find`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(getData())
        });

        // Выполняем GET-запрос для загрузки реакции
        const response = await fetch('/fetch/reaction/load');
        if (!response.ok) {
            throw new Error(`Ошибка HTTP: ${response.status}`);
        }

        // Получаем данные реакции
        const reaction = await response.json();

        // Устанавливаем значение в HTML-элемент
        document.getElementById("nameReaction").value = reaction.nameReaction;
    } catch (error) {
        console.error('Произошла ошибка:', error);
    }
}

// Вызываем функцию
fetchData();

//document.getElementById("nameReaction").value="yo";
