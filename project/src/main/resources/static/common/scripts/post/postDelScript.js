
let delAlertBtn = document.getElementById("deleteAccountModal");
let idPost;

function delAlert(button) {
    let postBlock = button.closest('.post');

    if (postBlock) {
        idPost = postBlock.getAttribute('data-id');
        delAlertBtn.style.display = "block";
    } else {
        console.error('Post block not found');
    }

    delAlertBtn.style.display = "block";
}
function deleteAccount() {
    let data = {
        idPost: idPost,
    };
    fetch(`/fetch/post/delete`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    location.reload ()
}
function delCancel() {
    delAlertBtn.style.display = "none";
}