import {csrfToken} from "../cookie.js";
const removeButtons = document.querySelectorAll('.removeButton')
const banButtons = document.querySelectorAll('.banButton')
const reports = document.querySelectorAll('.report');
const municipality = window.location.href.substring(7, window.location.href.indexOf('.'));
// console.log(municipality)

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

const removeIdea = async (event) => {
    let button =event.target;
    const callForIdeaId = button.parentElement.parentElement.parentElement.parentElement.dataset.callId;

    const ideaId = button.dataset.ideaId
    const options = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        }
    }
    console.log(options)

    const response = await fetch(`/api/ideas/${ideaId}`, options);
    if (response.status===204){
        hideAllErrors();
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        bootstrapErrorToast.hide();
        const bootstrapSuccessToast = bootstrap.Toast.getOrCreateInstance(successToast);
        successToastBody.textContent = 'Idea deleted successfully';
        bootstrapSuccessToast.show();
        for (const report of reports) {
            if (report.dataset.ideaId === ideaId){
                report.remove();
            }
        }
    }
}

const banUser = async (event) => {
    let button =event.target;
    let membershipId;
    const ideaId = button.parentElement.parentElement.parentElement.parentElement.parentElement.dataset.ideaId;

    const membershipsFull = button.dataset.userId;
    const membershipsId = membershipsFull.match(/[(]+[^)]+[(]+/g);

    for (let i = 0; i < membershipsId.length; i++) {
        console.log('before: ', membershipsId[i])
        membershipsId[i] = membershipsId[i].charAt(4);
        console.log('after: ', membershipsId[i])
    }
    let memberships = membershipsFull.match(/[(]+[^()@]+[)]+/g);
    // console.log(button.dataset.userId.match(/Membership.*/))
    for (let i = 0; i < memberships.length; i++) {
        const membership = memberships[i];
        const municipalityMembership = membership.substring(membership.indexOf('name=')+5,membership.indexOf(', latitude'))
        console.log(municipalityMembership, ' = ', municipality)
        if (municipalityMembership.toLowerCase() === municipality.toLowerCase()){
            const membershipsIdCurrent = membershipsId[i];
            membershipId=membershipsIdCurrent.substring(membershipsIdCurrent.indexOf('id=')+3,membershipsIdCurrent.indexOf(','))
            console.log(membershipId)
        }
    }

    const options = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({
            ban: true
        })
    }
    console.log(options)

    const response = await fetch(`/api/memberships/${membershipId}/ban`, options);
    if (response.status===200){
        hideAllErrors();
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        bootstrapErrorToast.hide();
        const bootstrapSuccessToast = bootstrap.Toast.getOrCreateInstance(successToast);
        successToastBody.textContent = 'User banned successfully';
        bootstrapSuccessToast.show();
        for (const report of reports) {
            if (report.dataset.ideaId === ideaId){
                report.remove();
            }
        }
    }
    if (response.status===400){
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        errorToastBody.textContent = 'Moderator can only ban users, ban did not go through';
        bootstrapErrorToast.show();
    }
}
for (const banButton of banButtons) {
    banButton.addEventListener('click', banUser)
}

for (const removeButton of removeButtons){
    removeButton.addEventListener('click', removeIdea);
}

