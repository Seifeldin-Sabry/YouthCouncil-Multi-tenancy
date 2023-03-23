import {csrfToken} from "../cookie.js";


const addMemberBtn = document.querySelector('#add-member-btn');
const firstNameInput = document.querySelector('#firstName');
const surname = document.querySelector('#surname');
const email = document.querySelector('#email');
const role = document.querySelector('#role');


const modal = document.querySelector('#addMemberModal');


const showErrorMessage = (field, message) => {
    const errorField = document.querySelector(`#${field}-err`);
    errorField.parentNode.querySelector('.form-floating').classList.add('is-invalid');
    errorField.textContent = message;
    console.log(errorField);
    console.log(`${field}-err`);
}

function hideErrorMessages() {
    const errorFields = document.querySelectorAll('.invalid-feedback');
    errorFields.forEach(errorField => {
        errorField.textContent = '';
        errorField.parentNode.querySelector('.form-floating').classList.remove('is-invalid');
    })
}

const addMember = async (event) => {
    let error = false;
    if (firstNameInput.value === '') {
        showErrorMessage('firstName', 'First name is required');
        error = true;
    }
    if (surname.value === '') {
        showErrorMessage('surname', 'Surname is required');
        error = true;
    }
    if (email.value === '') {
        showErrorMessage('email', 'Email is required');
        error = true;
    }
    if (role.value === '') {
        showErrorMessage('role', 'Role is required');
        error = true;
    }
    if (error) return;

    const cookie = csrfToken();
    const requestBody = {
        firstName: firstNameInput.value,
        surname: surname.value,
        email: email.value,
        role: role.value
    }
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...cookie
        },
        body: JSON.stringify(requestBody)
    }
    console.log(options)
    let municipalityArr = window.location.pathname.split('/');
    let municipalityId = municipalityArr[municipalityArr.length - 1];
    const response = await fetch(`/api/municipalities/${municipalityId}/members`, options);
    if (response.ok) {
        //TODO: add member to table, remove error messages
        hideErrorMessages();
        bootstrap.Modal.getInstance(modal).hide();
        return;
    }
    // TODO: Handle error
    const errorResponse = await response.json();
    console.log(errorResponse);
}


addMemberBtn.addEventListener('click', addMember);