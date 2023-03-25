import {ThemeCard} from "../components/themes/ThemeCard.js";
import {SubThemeCard} from "../components/themes/SubThemeCard.js";
import {csrfToken} from "../cookie.js";

const saveThemeButton = document.querySelector('.save-theme-btn');
const saveSubthemeButton = document.querySelector('.save-subtheme-btn');
const addSubthemeButtons = document.querySelectorAll('.add-subtheme-btn');
const editThemeButtons = document.querySelectorAll('.edit-theme-btn');
const deleteThemeButtons = document.querySelectorAll('.delete-theme-confirm-btn');
const editSaveThemeButtons = document.querySelectorAll('.edit-theme-confirm-btn');

const themeAccordion = document.querySelector('#themeAccordion');
const addThemeModal = document.querySelector('#addThemeModal');
const addSubThemeModal = document.querySelector('#addSubThemeModal');

const deleteSubthemeButtons = document.querySelectorAll('.delete-subtheme-btn');
const saveSubthemeChangesButtons = document.querySelectorAll('.save-subtheme-changes-btn');

let currentThemeId;


const state = {}


async function getSubThemes(themeId) {
    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        }
    }
    const response = await fetch(`/api/themes/${themeId}/subthemes`, options);
    return await response.json();
}

document.querySelectorAll('.card-theme').forEach(async (theme) => {
    let themeId = theme.getAttribute('data-theme-id');
    let subThemes = await getSubThemes(themeId);
    state[themeId] = {
        id: themeId,
        name: theme.querySelector('.theme-name').textContent,
        subThemes: new Map(subThemes.map(subTheme => [subTheme.id, subTheme]))
    }
})

const renderTheme = (theme) => {
    const newTheme = new ThemeCard(theme);
    let element = newTheme.getElement();
    element.querySelector('.add-subtheme-btn').addEventListener('click', reassignCurrentThemeId)
    element.querySelector('.edit-theme-btn').addEventListener('click', reassignCurrentThemeId)
    element.querySelector('.delete-theme-confirm-btn').addEventListener('click', deleteTheme)
    element.querySelector('.edit-theme-confirm-btn').addEventListener('click', editTheme)
    themeAccordion.appendChild(element);
    state[theme.id] = {
        id: theme.id,
        name: theme.name,
        subThemes: new Map()
    }
}

const renderSubtheme = (subtheme) => {
    const subTheme = new SubThemeCard(subtheme, currentThemeId);
    state[currentThemeId].subThemes.set(subtheme.id, subtheme);
    let subthemes = themeAccordion.querySelector(`.theme-${currentThemeId} .subtheme-list`);
    subthemes.appendChild(subTheme.element);
    let deleteSubthemeBtn = subTheme.element.querySelector('.delete-subtheme-btn');
    deleteSubthemeBtn.addEventListener('click', reassignCurrentThemeId)
    deleteSubthemeBtn.addEventListener('click', deleteSubtheme)
    let saveSubthemeChangesBtn = subTheme.element.querySelector('.save-subtheme-changes-btn');
    saveSubthemeChangesBtn.addEventListener('click', editSubtheme)
}


saveThemeButton.addEventListener('click', async () => {
    // get the value of the input field
    let modalInput = document.getElementById('themeName');
    const themeName = modalInput.value;
    // send a POST request to the server with the theme name
    const response = await fetch('/api/themes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({themeName})
    });
    // if the request was successful, close modal and render the new theme, otherwise display an error at the input field
    let modalDivError = document.getElementById(`themeName-error`);
    if (response.ok) {
        // hide modal with vanilla JS
        bootstrap.Modal.getInstance(addThemeModal).hide();
        addThemeModal.querySelector('#themeName').value = '';
        const theme = await response.json();
        modalInput.classList.remove('is-invalid');
        modalDivError.textContent = '';
        renderTheme(theme);
        return;
    }
    const error = await response.json();
    for (const [key, value] of Object.entries(error)) {
        modalInput.classList.add('is-invalid');
        modalDivError.textContent = value;
    }
});

saveSubthemeButton.addEventListener('click', async () => {
    // get the value of the input field
    const subThemeName = document.getElementById('subThemeName').value;
    // send a POST request to the server with the theme name
    const response = await fetch(`/api/themes/${currentThemeId}/subthemes`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({subThemeName})
    });
    // if the request was successful, close modal and render the new theme, otherwise display an error at the input field
    if (response.ok) {
        // hide modal with vanilla JS
        bootstrap.Modal.getInstance(addSubThemeModal).hide();
        addSubThemeModal.querySelector('#subThemeName').value = '';
        const subtheme = await response.json();
        renderSubtheme(subtheme);
        return;
    }
    const error = await response.json();
    for (const [key, value] of Object.entries(error)) {
        document.getElementById(key).classList.add('is-invalid');
        document.getElementById(`${key}-error`).textContent = value;
    }
});

function reassignCurrentThemeId(event) {
    currentThemeId = event.target.closest('button').getAttribute('data-theme-id') || event.target.getAttribute('id') || event.target.closest('button').getAttribute('id')
}

async function deleteTheme(event) {
    const themeId = event.target.getAttribute('data-theme-id');
    const response = await fetch(`/api/themes/${themeId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...csrfToken()
        }
    });
    let deleteModal = bootstrap.Modal.getInstance(themeAccordion.querySelector(`#deleteThemeModal${themeId}`));
    if (response.ok) {
        themeAccordion.querySelector(`.theme-${themeId}`).remove();
        deleteModal.hide();
        return;
    }
    deleteModal.hide();
    const errorMessage = "Something went wrong. Please try again later.";
    const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));
    document.getElementById('errorMessage').textContent = errorMessage;
    errorModal.show();
}

async function deleteSubtheme(event) {
    const subthemeId = event.target.getAttribute('data-subtheme-id') || event.target.closest('button').getAttribute('data-subtheme-id');
    const response = await fetch(`/api/themes/${currentThemeId}/subthemes/${subthemeId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...csrfToken()
        },
    });
    if (response.ok) {
        themeAccordion.querySelector(`.subtheme-item-${subthemeId}`).remove();
        return;
    }
    const errorMessage = "Something went wrong. Please try again later.";
    const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));
    document.getElementById('errorMessage').textContent = errorMessage;
    errorModal.show();
}

async function editTheme(event) {
    const themeId = event.target.getAttribute('data-theme-id') || event.target.closest('button').getAttribute('data-theme-id');
    const themeName = themeAccordion.querySelector(`#edit-ThemeName-${themeId}`).value;
    if (themeName === state[themeId].name) {
        let editModal = bootstrap.Modal.getInstance(themeAccordion.querySelector(`#editThemeModal${themeId}`));
        editModal.hide();
        return;
    }
    const response = await fetch(`/api/themes/${themeId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({themeName})
    });
    let editModal = bootstrap.Modal.getInstance(themeAccordion.querySelector(`#editThemeModal${themeId}`));
    let modalInput = document.getElementById(`edit-ThemeName-${themeId}`);
    let divError = document.getElementById(`edit-themeName-${themeId}-error`);
    if (response.ok) {
        editModal.hide();
        themeAccordion.querySelector(`.theme-${themeId} .theme-name`).textContent = themeName;
        modalInput.classList.remove('is-invalid');
        divError.textContent = '';
        return;
    }
    const error = await response.json();
    for (let [key, value] of Object.entries(error)) {
        if (Array.isArray(value)) {
            value = value[0]
        }
        modalInput.classList.add('is-invalid');
        divError.textContent = value;
    }
}

async function editSubtheme(e) {
    const themeId = e.target.getAttribute('data-theme-id') || e.target.closest('button').getAttribute('data-theme-id');
    const subthemeId = +e.target.getAttribute('data-subtheme-id') || e.target.closest('button').getAttribute('data-subtheme-id');
    const subthemeName = themeAccordion.querySelector(`#subthemeName-${subthemeId}`).value;
    const subtheme = state[themeId].subThemes.get(subthemeId)
    if (subthemeName === subtheme.subThemeName) {
        let editModal = bootstrap.Modal.getInstance(themeAccordion.querySelector(`#editSubthemeModal${subthemeId}`));
        editModal.hide();
        return;
    }
    const subthemeData = {
        subThemeName: subthemeName
    }

    const response = await fetch(`/api/themes/${themeId}/subthemes/${subthemeId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify(subthemeData)
    });

    let editModal = bootstrap.Modal.getInstance(themeAccordion.querySelector(`#editSubThemeModal${subthemeId}`));
    let modalInput = document.getElementById(`subthemeName-${subthemeId}`);
    let divError = document.getElementById(`edit-subThemeName-${subthemeId}-error`);
    if (response.ok) {
        editModal.hide();
        let subThemeElement = themeAccordion.querySelector(`.subtheme-item-${subthemeId} .card-title`);
        modalInput.classList.remove('is-invalid');
        divError.textContent = '';
        subThemeElement.textContent = subthemeName;
        return;
    }
    const error = await response.json();
    console.log(error);
    for (let [key, value] of Object.entries(error)) {
        if (Array.isArray(value)) {
            value = value[0]
        }
        modalInput.classList.add('is-invalid');
        divError.textContent = value;
    }
}


addSubthemeButtons.forEach(el => el.addEventListener('click', reassignCurrentThemeId));
editThemeButtons.forEach(el => el.addEventListener('click', reassignCurrentThemeId));
deleteThemeButtons.forEach(el => el.addEventListener('click', deleteTheme));
editSaveThemeButtons.forEach(el => el.addEventListener('click', editTheme));
deleteSubthemeButtons.forEach(el => el.addEventListener('click', reassignCurrentThemeId));
deleteSubthemeButtons.forEach(el => el.addEventListener('click', deleteSubtheme));
saveSubthemeChangesButtons.forEach(el => el.addEventListener('click', editSubtheme));
