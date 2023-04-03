let deleteButtons = document.querySelectorAll('.deleteButton');

// todo: implement warning when deleting user

addEventListeners()

function addEventListeners(){
    for (const deleteButton of deleteButtons){
        deleteButton.addEventListener('click', deleteUser)
    }
}

function deleteUser(event){
    const row = event.target.parentNode.parentNode;
    const email = row.querySelector('.emailUser');
    const userId = row.getAttribute('data-user-id');
    fetch(`/api/users/${userId}`, {
        method: 'DELETE'
    })
        .then(handleDeletionResponse)
}

function handleDeletionResponse(response){
    if (response.status === 204) {
        const userId = +response.url.substring(response.url.lastIndexOf('/') + 1);
        const tbodys = document.querySelectorAll('.tbodyUsers');
        // console.log(children);
        // console.log(userId);
        for (const tbody of tbodys){
            let children = tbody.children;
            for (const child of children){
                // console.log(child.getAttribute('data-user-id') )
                if(Number(child.getAttribute('data-user-id')) === userId){
                    child.innerHTML=''
                }
            }
        }
    } else {
        alert("serverside error")
    }
}