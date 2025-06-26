let delAlertBtn = document.getElementById("deleteAccountModal");
let id;
function alert(that,id) {
    let postBlock = that.closest('.post');

    if (postBlock) {
        id = postBlock.getAttribute('data-id');
        delAlertBtn.style.display = "block";
    } else {
        console.error('Post block not found');
    }
    console.log(""+id);
    delAlertBtn.style.display = "block";
}
