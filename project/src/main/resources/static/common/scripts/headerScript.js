document.addEventListener("DOMContentLoaded", function () {
    var newElement = document.createElement('div');
    var xhr = new XMLHttpRequest();

    xhr.open('GET', 'header.html', true);

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            newElement.innerHTML = xhr.responseText;
            document.body.appendChild(newElement);
        } else {
            console.error('Ошибка загрузки: ' + xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Ошибка сети');
    };

    xhr.send();
    console.log("yo");
});
