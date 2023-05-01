import {csrfToken} from "../cookie.js";

const state = {}
const pageTemplateRows = document.querySelectorAll('.page-template');

const successToast = document.querySelector('.success-toast');
const successToastBody = successToast.querySelector('.toast-body');

const errorToast = document.querySelector('.error-toast');
const errorToastBody = errorToast.querySelector('.toast-body');


const deletePageTemplate = async (e) => {
    const pageTemplateId = +e.target.dataset.pageTemplateId;
    const pageTemplate = state[pageTemplateId];
    const {row} = pageTemplate;
    const options = {
        method: 'DELETE',
        headers: {
            ...csrfToken()
        }
    }
    const response = await fetch(`/api/page-templates/${pageTemplateId}`, options);
    if (response.ok) {
        row.remove();
        const bootstrapSuccessToast = bootstrap.Toast.getOrCreateInstance(successToast);
        successToastBody.textContent = 'Page template deleted successfully';
        bootstrapSuccessToast.show();
        delete state[pageTemplateId];
        return;
    }
    const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
    errorToastBody.textContent = 'Something went wrong';
    bootstrapErrorToast.show();
}

pageTemplateRows.forEach(row => {
    const pageTemplateId = +row.dataset.pageTemplateId;
    state[pageTemplateId] = {
        row,
        deleteBtn: row.querySelector('.delete-page-template-btn')
    }
    state[pageTemplateId].deleteBtn.addEventListener('click', deletePageTemplate);
});