//import { createEventListeners } from 'user-management-max.js';

const adminTable = document.getElementById('dtVerticalScroll1');
const moderatorTable = document.getElementById('dtVerticalScroll2');

const createAdminButton = document.querySelector('.createAdminButton');
const createModeratorButton = document.querySelector('.createModeratorButton');
const userForm = document.querySelector('.userForm');

const firstName = document.getElementById('firstName');
const surName = document.getElementById('surname');
const username = document.getElementById('username');
const postcode = document.getElementById('postcode');
const email = document.getElementById('email');
const password = document.getElementById('password');
const confirmpassword = document.getElementById('confirmPassword');


createAdminButton.addEventListener('click', submitSuperUser);

createModeratorButton.addEventListener('click', submitSuperUser);

function submitSuperUser(event) {
    const formIsValid = true;
    //    userForm.checkValidity(); not working for some reaosn idk
    let typeOfButton;
    if (event.target.classList.contains('createAdminButton')) {
        typeOfButton = 'admin';
    } else {
        typeOfButton = 'moderator';
    }
    //console.log(typeOfButton)
    userForm.classList.add('was-validated');
    //console.log('is this even working?')
    if (formIsValid && password.value === confirmpassword.value) {
        fetch(`/api/users/${typeOfButton}`, {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "first_name": firstName.value,
                "surname": surName.value,
                "username": username.value,
                "postcode": postcode.value,
                "email": email.value,
                "password": password.value,
            })
        }).then(response => {
            if (response.status === 201) {
                userForm.reset();
                userForm.classList.remove('was-validated');
                response.json()
                    .then(handleAddedUser)
            } else {
                alert("Error in form found client-side")
            }
        });
    }
}

function handleAddedUser(response) {
    console.log(response.role);
    if (response.role === 'YOUTH_COUNCIL_ADMINISTRATOR') {
        adminTable.innerHTML += `
        <tr  data-user-id="${response.id}">
            <td class="usernameUser" >${response.username}</td>
            <td class="emailUser" >${response.email}</td>
            <td>
                <a href="/users/${response.id}">
                    <button type="button" class="btn btn-primary detailsButton">Details</button>
                </a>
            </td>
            <td>
                <button type="button" class="btn btn-danger deleteButton">Delete</button>
            </td>
        </tr>
        `
    } else {
        moderatorTable.innerHTML += `
        <tr  data-user-id="${response.id}">
            <td class="usernameUser" >${response.username}</td>
            <td class="emailUser" >${response.email}</td>
            <td>
                <a href="/users/${response.id}">
                    <button type="button" class="btn btn-primary detailsButton">Details</button>
                </a>
            </td>
            <td>
                <button type="button" class="btn btn-danger deleteButton">Delete</button>
            </td>
        </tr>
        `
    }
    addEventListeners();
    window.scrollTo({top: 0, behavior: 'smooth'});
}
