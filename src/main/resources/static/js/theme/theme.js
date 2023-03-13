import {ThemeCard} from "../components/ThemeCard.js";
import {SubThemeCard} from "../components/SubThemeCard.js";

// add an event listener to the 'Save Changes' button in the modal
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

let currentThemeId;


const state = {}


async function getSubThemes(themeId) {
    const response = await fetch(`/api/themes/${themeId}/subthemes`);
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
    newTheme.getElement().querySelector('.add-subtheme-btn').addEventListener('click', reassignCurrentThemeId)
    newTheme.getElement().querySelector('.edit-theme-btn').addEventListener('click', reassignCurrentThemeId)
    newTheme.getElement().querySelector('.delete-theme-confirm-btn').addEventListener('click', deleteTheme)
    newTheme.getElement().querySelector('.edit-theme-confirm-btn').addEventListener('click', editTheme)
    themeAccordion.appendChild(newTheme.getElement());
    state[theme.id] = {
        id: theme.id,
        name: theme.name,
        subThemes: new Map()
    }
}

const renderSubtheme = (subtheme) => {
    const subTheme = new SubThemeCard(subtheme);
    themeAccordion.querySelector(`.theme-${currentThemeId} .subtheme-list`).appendChild(subTheme.getElement());
    themeAccordion.querySelector('.delete-subtheme-btn').addEventListener('click', reassignCurrentThemeId)
    themeAccordion.querySelector('.delete-subtheme-btn').addEventListener('click', deleteSubtheme)
}


saveThemeButton.addEventListener('click', async () => {
    // get the value of the input field
    const themeName = document.getElementById('themeName').value;
    // send a POST request to the server with the theme name
    const response = await fetch('/api/themes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
            //     TODO: add csrf token
        },
        body: JSON.stringify({themeName})
    });
    // if the request was successful, close modal and render the new theme, otherwise display an error at the input field
    if (response.ok) {
        // hide modal with vanilla JS
        bootstrap.Modal.getInstance(addThemeModal).hide();
        addThemeModal.querySelector('#themeName').value = '';
        const theme = await response.json();
        renderTheme(theme);
        return;
    }
    const error = await response.json();
    for (const [key, value] of Object.entries(error)) {
        document.getElementById(key).classList.add('is-invalid');
        document.getElementById(`${key}-error`).textContent = value;
    }
});

saveSubthemeButton.addEventListener('click', async () => {
    // get the value of the input field
    const subThemeName = document.getElementById('subThemeName').value;
    console.log("currentThemeId: " + currentThemeId, "subThemeName: " + subThemeName)
    // send a POST request to the server with the theme name
    const response = await fetch(`/api/themes/${currentThemeId}/subthemes`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
            //     TODO: add csrf token
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
    console.log("currentThemeId: " + currentThemeId)
}

async function deleteTheme(event) {
    const themeId = event.target.getAttribute('data-theme-id');
    const response = await fetch(`/api/themes/${themeId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
            //     TODO: add csrf token
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
    console.log("currentThemeId: " + currentThemeId, "subthemeId: " + subthemeId)
    const response = await fetch(`/api/themes/${currentThemeId}/subthemes/${subthemeId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
//             TODO: add csrf token
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
    console.log("themeId: " + themeId)
    const themeName = themeAccordion.querySelector(`#editThemeName${themeId}`).value;
    console.log("themeName: " + themeName)
    if (themeName === state[themeId].name) {
        let editModal = bootstrap.Modal.getInstance(themeAccordion.querySelector(`#editThemeModal${themeId}`));
        editModal.hide();
        return;
    }
    const response = await fetch(`/api/themes/${themeId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
//             TODO: add csrf token
        },
        body: JSON.stringify({themeName})
    });
    let editModal = bootstrap.Modal.getInstance(themeAccordion.querySelector(`#editThemeModal${themeId}`));
    if (response.ok) {
        editModal.hide();
        themeAccordion.querySelector(`.theme-${themeId} .theme-name`).textContent = themeName;
        return;
    }
    editModal.hide();
    const error = await response.json();
    console.log(error)
    for (const [key, value] of Object.entries(error)) {
        document.getElementById(key).classList.add('is-invalid');
        document.getElementById(`edit-${key}-${themeId}-error`).textContent = value;
    }
}


addSubthemeButtons.forEach(el => el.addEventListener('click', reassignCurrentThemeId));
editThemeButtons.forEach(el => el.addEventListener('click', reassignCurrentThemeId));
deleteThemeButtons.forEach(el => el.addEventListener('click', deleteTheme));
editSaveThemeButtons.forEach(el => el.addEventListener('click', editTheme));
deleteSubthemeButtons.forEach(el => el.addEventListener('click', reassignCurrentThemeId));
deleteSubthemeButtons.forEach(el => el.addEventListener('click', deleteSubtheme));
