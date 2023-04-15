import {csrfToken} from "../cookie.js";
import {insertActionPoint} from "./actionPoints.js";

const form = document.querySelector('.add-action-point-form');
const submitBtn = document.querySelector('.submit-btn');

const actionPointModal = document.querySelector('#addActionPointModal');

const errorToast = document.querySelector('.error-toast');
const errorToastBody = errorToast.querySelector('.toast-body');

const successToast = document.querySelector('.success-toast');
const successToastBody = successToast.querySelector('.toast-body');

const addProposalBtn = document.querySelector('#add-proposal');
const proposalList = document.querySelector('.proposal-list');
let currentIndex = 0;

const proposals = [...document.querySelectorAll('.proposal')];

const title = document.querySelector('#title');
const description = document.querySelector('#description');
const subTheme = document.querySelector('#subTheme');
const images = document.querySelector('#images');

const addProposal = () => {
    const proposalRow = document.createElement('tr')
    const proposal = document.createElement('td')
    proposal.classList.add('proposal')
    const textArea = document.createElement('textarea')
    textArea.setAttribute('name', `actionPointProposals[${currentIndex}]`)
    textArea.classList.add('form-control', 'proposal-textarea')
    textArea.id = `actionPointProposals${currentIndex}`
    proposalRow.appendChild(proposal)
    proposal.appendChild(textArea)
    proposalList.appendChild(proposalRow)
    currentIndex += 1
    proposals.push(proposal)
}

addProposalBtn.addEventListener('click', addProposal)


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

function emptyProposals() {
    proposals.forEach((proposal) => {
        proposal.remove();
    })
    currentIndex = 0;
}

/**
 * @param {Event} event
 */
const addActionPoint = async (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append('title', title.value);
    formData.append('description', description.value);
    formData.append('subTheme', subTheme.value);
    formData.append('actionPointProposals', proposals.map((proposal) => proposal.querySelector('.proposal-textarea').value));
    for (let i = 0; i < images.files.length; i++) {
        formData.append('images', images.files[i]);
    }
    const options = {
        method: 'POST',
        body: formData,
        headers: {
            ...csrfToken()
        },
    };
    const response = await fetch('/api/actionpoints', options);

    if (response.status === 201) {
        hideAllErrors();
        emptyProposals();
        let bootstrapModal = bootstrap.Modal.getInstance(actionPointModal);
        bootstrapModal.hide();
        const bootstrapSuccessToast = bootstrap.Toast.getOrCreateInstance(successToast);
        successToastBody.textContent = 'Action point created successfully';
        bootstrapSuccessToast.show();
        insertActionPoint(await response.json());
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

form.addEventListener('submit', addActionPoint)
submitBtn.addEventListener('click', addActionPoint)
