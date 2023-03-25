import {csrfToken} from "../cookie.js";

const saveProfile = document.getElementById('save-profile-button');
const firstName = document.getElementById('first-name-input');
const surname = document.getElementById('surname-input');
const username = document.getElementById('username-input');
const email = document.getElementById('email-input');
const toastEl = document.querySelector('.toast');

let state = {
    'first-name-input': {
        value: firstName.value,
        element: firstName
    },
    'surname-input': {
        value: surname.value,
        element: surname
    },
    'username-input': {
        value: username.value,
        element: username
    },
    'email-input': {
        value: email.value,
        element: email
    }
};

const checkInputChange = () => {
    let isChanged = false;
    Object.keys(state).forEach(key => {
        if (state[key].value !== state[key].element.value || state[key].element.value === '') {
            isChanged = true;
        }
    });
    return isChanged;
}

function updateState() {
    state = {
        'first-name-input': {
            value: firstName.value,
            element: firstName
        },
        'surname-input': {
            value: surname.value,
            element: surname
        },
        'username-input': {
            value: username.value,
            element: username
        },
        'email-input': {
            value: email.value,
            element: email
        }
    }
}

[firstName, surname, username, email].forEach(input => {
    input.addEventListener('input', (e) => {
        saveProfile.disabled = !checkInputChange()
    });
});

saveProfile.addEventListener('click', async (e) => {
    let userId = e.target.getAttribute('data-id');
    console.log(userId);
    const res = await fetch(`/api/users/${userId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({
            firstName: firstName.value,
            surname: surname.value,
            username: username.value,
            email: email.value
        }),
    })

    if (res.ok) {
        const toast = new bootstrap.Toast(toastEl);
        toast.show();
        updateState();
        return;
    }
    const errors = await res.json();
    console.log(errors);

});