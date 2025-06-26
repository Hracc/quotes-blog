let delAlertBtn = document.getElementById("deleteAccountModal");
let idReaction;

function delAlert(button) {
    let postBlock = button.closest('.post');

    if (postBlock) {
        idReaction = postBlock.getAttribute('data-id');
        delAlertEdit.style.display = "block";
    } else {
        console.error('Post block not found');
    }

    delAlertEdit.style.display = "block";
}
function deleteAccount() {
    let data = {
        idReaction: idReaction,
    };
    fetch(`/fetch/reaction/delete`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    location.reload ()
}
function delCancel() {
    delAlertEdit.style.display = "none";
}