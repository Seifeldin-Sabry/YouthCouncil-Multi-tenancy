import {ThemeCard} from "../components/themes/ThemeCard.js";
import {SubThemeCard} from "../components/themes/SubThemeCard.js";
import {csrfToken} from "../cookie.js";

const saveThemeButton = document.querySelector('.save-theme-btn');
const saveSubthemeButton = document.querySelector('.save-subtheme-btn');
const addSubthemeButtons = document.querySelectorAll('.add-subtheme-btn');
const editThemeButtons = document.querySelectorAll('.edit-theme-btn');
const deleteThemeButtons = document.querySelectorAll('.delete-theme-btn');
const editSaveThemeButtons = document.querySelectorAll('.edit-theme-confirm-btn');

const themeAccordion = document.querySelector('#themeAccordion');
const addThemeModal = document.querySelector('#addThemeModal');
const addSubThemeModal = document.querySelector('#addSubThemeModal');

const deleteSubthemeButtons = document.querySelectorAll('.delete-subtheme-btn');
const saveSubthemeChangesButtons = document.querySelectorAll('.save-subtheme-changes-btn');

let currentThemeId;


const state = {}


function getSubThemes(theme, themeId) {
    return theme.querySelectorAll(`.sub${themeId}`)
}

document.querySelectorAll('.card-theme').forEach((theme) => {
    let themeId = theme.getAttribute('data-theme-id');
    let deleteThemeBtn = theme.querySelector('.delete-theme-btn');
    let isActive = theme.dataset.isActive === 'true';
    let subThemesEl = getSubThemes(theme, themeId);
    console.log(subThemesEl)
    state[themeId] = {
        id: themeId,
        deleteThemeBtn,
        isActive,
        theme,
        name: theme.querySelector('.theme-name').textContent,
        subThemes: new Map([...subThemesEl].map(subTheme => {
            const subthemeNameEl = subTheme.querySelector('.card-subtheme-name');
            const subThemeDeleteBtn = subTheme.querySelector('.delete-subtheme-btn');
            return [subTheme.dataset.subthemeId, {
                id: subTheme.dataset.subthemeId,
                subthemeNameEl,
                subThemeName: subthemeNameEl.textContent,
                subThemeDeleteBtn,
                subTheme,
                isActive: subTheme.dataset.isActive === 'true'
            }];
        }))
    }
})


const renderTheme = (theme) => {
    const newTheme = new ThemeCard(theme);
    let element = newTheme.getElement();
    element.querySelector('.add-subtheme-btn').addEventListener('click', reassignCurrentThemeId)
    element.querySelector('.edit-theme-btn').addEventListener('click', reassignCurrentThemeId)
    element.querySelector('.delete-theme-btn').addEventListener('click', activateOrDeactivateTheme)
    element.querySelector('.edit-theme-confirm-btn').addEventListener('click', editTheme)
    themeAccordion.appendChild(element);
    state[theme.id] = {
        id: theme.id,
        name: theme.name,
        isActive: theme.active,
        theme: element,
        subThemes: new Map()
    }
}

const renderSubtheme = (subtheme) => {
    const subTheme = new SubThemeCard(subtheme, currentThemeId);
    const subThemeDeleteBtn = subTheme.element.querySelector('.delete-subtheme-btn');
    state[currentThemeId].subThemes.set(`${subtheme.id}`, {
        id: subtheme.id,
        subthemeNameEl: subTheme.element.querySelector('.card-subtheme-name'),
        subThemeName: subtheme.subThemeName,
        subThemeDeleteBtn,
        subTheme: subTheme.element,
        isActive: subtheme.active
    });
    let subthemes = themeAccordion.querySelector(`.theme-${currentThemeId} .subtheme-list`);
    subthemes.appendChild(subTheme.element);
    subThemeDeleteBtn.addEventListener('click', reassignCurrentThemeId)
    subThemeDeleteBtn.addEventListener('click', activateOrDeactivateSubTheme)
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
        modalDivError.textContent += value + '\n';
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

async function activateOrDeactivateTheme(event) {
    const themeId = event.target.dataset.themeId || event.target.closest('button').dataset.themeId;
    const theme = state[themeId];
    const {deleteThemeBtn, isActive, theme: themeEl} = theme;
    console.log(themeId, isActive, deleteThemeBtn, theme)
    const response = await fetch(`/api/themes/${themeId}/deactivate`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({active: !isActive})
    });
    if (!response.ok) {
        const errorMessage = "Something went wrong. Please try again later.";
        const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));
        document.getElementById('errorMessage').textContent = errorMessage;
        errorModal.show();
        return;
    }
    if (isActive) {
        deleteThemeBtn.textContent = 'Activate';
        deleteThemeBtn.classList.remove('btn-danger');
        deleteThemeBtn.classList.add('btn-success');
        themeEl.classList.add('bg-light');
        theme.isActive = false;
    } else {
        deleteThemeBtn.textContent = 'Deactivate';
        deleteThemeBtn.classList.remove('btn-success');
        deleteThemeBtn.classList.add('btn-danger');
        themeEl.classList.remove('bg-light');
        theme.isActive = true;
    }
}

async function activateOrDeactivateSubTheme(event) {
    const subthemeId = event.target.getAttribute('data-subtheme-id') || event.target.closest('button').getAttribute('data-subtheme-id');
    const subtheme = state[currentThemeId].subThemes.get(subthemeId);
    console.log(typeof subthemeId, subtheme, currentThemeId)
    const {subThemeDeleteBtn, isActive, subtheme: subthemeEl} = subtheme;
    const response = await fetch(`/api/themes/${currentThemeId}/subthemes/${subthemeId}/deactivate`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({active: !isActive})
    });
    if (!response.ok) {
        const errorMessage = "Something went wrong. Please try again later.";
        const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));
        document.getElementById('errorMessage').textContent = errorMessage;
        errorModal.show();
        return;
    }
    if (isActive) {
        subThemeDeleteBtn.textContent = 'Activate';
        subThemeDeleteBtn.classList.remove('btn-danger');
        subThemeDeleteBtn.classList.add('btn-success');
        subtheme.isActive = false;
    } else {
        subThemeDeleteBtn.textContent = 'Deactivate';
        subThemeDeleteBtn.classList.remove('btn-success');
        subThemeDeleteBtn.classList.add('btn-danger');
        subtheme.isActive = true;
    }
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
        modalInput.classList.add('is-invalid');
        divError.textContent += value + '\n';
    }
}

async function editSubtheme(e) {
    const themeId = e.target.getAttribute('data-theme-id') || e.target.closest('button').getAttribute('data-theme-id');
    const subthemeId = e.target.getAttribute('data-subtheme-id') || e.target.closest('button').getAttribute('data-subtheme-id');
    const subtheme = state[themeId].subThemes.get(subthemeId);
    const {subthemeNameEl, subThemeName, subTheme} = subtheme;
    const modal = subTheme.querySelector(`#editSubThemeModal${subthemeId}`);
    let editModal = bootstrap.Modal.getInstance(modal);
    const modalSubthemeName = modal.querySelector(`#subthemeName-${subthemeId}`);
    if (modalSubthemeName.textContent === subThemeName) {
        editModal.hide();
        return;
    }
    const subthemeData = {
        subThemeName: modalSubthemeName.value
    }
    console.log(subthemeData)
    const response = await fetch(`/api/themes/${themeId}/subthemes/${subthemeId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify(subthemeData)
    });
    let modalInput = document.getElementById(`subthemeName-${subthemeId}`);
    let divError = document.getElementById(`edit-subThemeName-${subthemeId}-error`);
    if (response.ok) {
        editModal.hide();
        modalInput.classList.remove('is-invalid');
        divError.textContent = '';
        subthemeNameEl.textContent = modalSubthemeName.value;
        subtheme.subThemeName = subthemeNameEl.textContent;
        return;
    }
    const error = await response.json();
    console.log(error);
    for (let [key, value] of Object.entries(error)) {
        modalInput.classList.add('is-invalid');
        divError.textContent += value + '\n';
    }
}


addSubthemeButtons.forEach(el => el.addEventListener('click', reassignCurrentThemeId));
editThemeButtons.forEach(el => el.addEventListener('click', reassignCurrentThemeId));
deleteThemeButtons.forEach(el => el.addEventListener('click', activateOrDeactivateTheme));
editSaveThemeButtons.forEach(el => el.addEventListener('click', editTheme));
deleteSubthemeButtons.forEach(el => el.addEventListener('click', reassignCurrentThemeId));
deleteSubthemeButtons.forEach(el => el.addEventListener('click', activateOrDeactivateSubTheme));
saveSubthemeChangesButtons.forEach(el => el.addEventListener('click', editSubtheme));
