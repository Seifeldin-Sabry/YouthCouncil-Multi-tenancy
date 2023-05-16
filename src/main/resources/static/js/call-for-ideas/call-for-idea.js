import {csrfToken} from "../cookie.js";

const submitButton = document.getElementById('submitCall');
const modal = document.getElementById('callForIdeaModal');
let activeSwapButtons = document.querySelectorAll('.changeActive');
const form = document.querySelector('form');

const errorToast = document.querySelector('.error-toast');
const errorToastBody = errorToast.querySelector('.toast-body');

const successToast = document.querySelector('.success-toast');
const successToastBody = successToast.querySelector('.toast-body');

function showError(key, value) {
    const divInput = document.querySelector(`.${key}-err`);
    const error = document.querySelector(`.${key}-invalid-feedback`);
    error.textContent = value;
    divInput.classList.add('is-invalid');
}

function hideAllErrors() {
    const errorContainers = document.querySelectorAll('.validation-container');
    errorContainers.forEach((errorContainer) => {
        const error = errorContainer.querySelector('.validation');
        if (error === null) {
            errorContainer.textContent = '';
        } else if (error.tagName !== 'SELECT') {
            error.value = '';
        } else {
            error.selectedIndex = 0;
        }
        errorContainer.classList.remove('is-invalid');
    });
}

const swapActive = async (event) => {
    // console.log(event.target)
    // console.log(event.target.parentNode)
    // console.log(event.target.parentNode.parentNode)
    const callId = event.target.parentNode.parentNode.id;
    console.log(callId)

    const options = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        }
    }
    console.log(options)
    const response = await fetch(`/api/call-for-ideas/${callId}/active`, options);
    if (response.ok) {
        if (event.target.classList.contains("deActivate")) {
            handleDeactivate(event.target.parentNode);
        } else {
            handleActivate(event.target.parentNode);
        }
    }
}

addEventListeners();

function addEventListeners() {
    let activeSwapButtons = document.querySelectorAll('.changeActive');
    for (let button of activeSwapButtons) {
        button.addEventListener('click', swapActive);
    }
}

function handleDeactivate(target) {
    target.querySelector('.changeActive').remove()
    target.innerHTML += `
    <td>
        <button type="button" class="btn btn-primary changeActive">
            Activate
        </button>
    </td>
    `
    console.log('de-activated')
    addEventListeners();
}

function handleActivate(target) {
    target.querySelector('.changeActive').remove()
    target.innerHTML += `
    <td>
        <button type="button" class="btn btn-primary changeActive deActivate">
            De-Activate
        </button>
    </td>
    `
    console.log('activated')
    addEventListeners();
}


const postCall = async (event) => {
    event.preventDefault();
    // console.log(event.target.dataset.uuid);
    console.log('helo')
    const titleCall = document.getElementById('title').value;
    const descriptionCall = document.getElementById('description').value;
    const themeCall = document.getElementById('theme').value;
    console.log(titleCall)
    console.log(descriptionCall)
    const formData = new FormData();
    formData.append('title', titleCall);
    formData.append('description', descriptionCall);
    formData.append('theme', themeCall);


    const options = {
        method: 'POST',
        headers: {
            ...csrfToken()
        },
        body: formData
    }
    console.log(options)
    const response = await fetch(`/api/call-for-ideas`, options);
    if (response.status === 201) {
        hideAllErrors();
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        bootstrapErrorToast.hide();
        let bootstrapModal = bootstrap.Modal.getInstance(modal);
        bootstrapModal.hide();
        const bootstrapSuccessToast = bootstrap.Toast.getOrCreateInstance(successToast);
        successToastBody.textContent = 'Action point created successfully';
        bootstrapSuccessToast.show();
        response.json().then(handleAddedCall)
        return;
    }
    if (response.status === 403) {
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        errorToastBody.textContent = 'Unauthorized, make sure you are logged in and have the correct permissions';
        bootstrapErrorToast.show();
        return;
    }
    if (response.status === 400) {
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        const error = await response.json();
        console.log(error);
        for (const [key, value] of Object.entries(error)) {
            showError(key, value);
        }
        bootstrapErrorToast.show();
        return;

    }
    errorToastBody.textContent = 'Something went wrong while processing your request';
    const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
    bootstrapErrorToast.show();


}

function handleAddedCall(call) {
    const tbody = document.querySelector('.tbodyCalls');
    tbody.innerHTML += `
    <tr id="${call.id}">
        <td class="title" >
            <a href="call-for-ideas-dashboard/${call.uuid}/ideas">${call.title}</a>
        </td>
        <td class="description">${call.description}</td>
        <td class="theme">${call.theme.themeName}</td>
        <td>
            <button type="button" class="btn btn-primary changeActive">
                Activate
            </button>
        </td>
    </tr>
    `
    document.getElementById('title').value = '';
    document.getElementById('description').value = '';
    addEventListeners();


}

submitButton.addEventListener('click', postCall);
