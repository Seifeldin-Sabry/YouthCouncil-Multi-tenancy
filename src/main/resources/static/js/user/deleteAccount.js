const deleteAccountBtn = document.querySelector('#delete-account');
deleteAccountBtn.addEventListener('click', () => {
    fetch('/api/users/{id}', {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to delete account');
            }
            // do something after account is successfully deleted
        })
        .catch(error => {
            console.error(error);
            // handle error
        });
});