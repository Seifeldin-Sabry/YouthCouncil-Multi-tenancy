import {csrfToken} from "../cookie.js";

const municipalityId = document.querySelector('.mu-id').dataset.municipalityId;
console.log(municipalityId)

const addMemberBtn = document.querySelector('#add-member-btn');
const firstNameInput = document.querySelector('#firstName');
const surname = document.querySelector('#surname');
const email = document.querySelector('#email');


const modal = document.querySelector('#addMemberModal');
const errorToast = document.querySelector('.toast');
const errorToastBody = errorToast.querySelector('.toast-body');

const users = document.querySelector('.user-list')


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

function createNewMember(memberData) {
    const memberEl = document.createElement('li');
    memberEl.classList.add('list-group-item');
    memberEl.textContent = `${memberData.firstName} ${memberData.surname}`;
    return memberEl;
}

function clearInputFields() {
    firstNameInput.value = '';
    surname.value = '';
    email.value = '';
}

const addMember = async () => {
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
    if (error) return;

    const cookie = csrfToken();
    const requestBody = {
        firstName: firstNameInput.value,
        surname: surname.value,
        email: email.value
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
    const response = await fetch(`/api/municipalities/${municipalityId}/members`, options);
    if (!response.ok) {
        const data = await response.json();
        errorToastBody.textContent = data && data?.email && data.email || 'Something went wrong while processing your request';
        const errorToastInstance = bootstrap.Toast.getOrCreateInstance(errorToast);
        errorToastInstance.show();
        return
    }
    hideErrorMessages();
    bootstrap.Modal.getInstance(modal).hide();
    clearInputFields();
    let memberData = await response.json();
    const memberEl = createNewMember(memberData);
    users.appendChild(memberEl);
}


addMemberBtn.addEventListener('click', addMember);