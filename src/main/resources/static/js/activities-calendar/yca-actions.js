import {csrfToken} from "../cookie.js";

const deleteActivityButtons = document.querySelectorAll('.btn-outline-danger');
let modalDeleteButton = document.getElementById('confirmDeleteButton');

const editActivityButtons = document.querySelectorAll('.btn-outline-primary');

const addActivityButton = document.getElementById('add-activity-button');
// Add activity form
const addActivityForm = document.querySelector('#add-activity-form');
const titleInput = document.querySelector('#add-activity-title');
const dateInput = document.querySelector('#add-activity-date');
const startTimeInput = document.querySelector('#add-activity-start-time');
const endTimeInput = document.querySelector('#add-activity-end-time');
const descriptionInput = document.querySelector('#add-activity-description');


//DELETE ACTIVITY
async function deleteActivity(activityId) {
    // const activityId = id.target.getAttribute('data-activity-id') || id.target.closest('button').getAttribute('data-activity-id');
    console.log(activityId);
    const response = await fetch(`/api/calendar-activities/${activityId}/delete`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
    });
    if (response.ok) {
        document.getElementById(`${activityId}`).remove();
        const deleteModal = document.getElementById('deleteModal');
        bootstrap.Modal.getOrCreateInstance(deleteModal).hide();
    }
}


function activateModal(event) {
    modalDeleteButton = document.getElementById('confirmDeleteButton');
    const activityId = event.target.getAttribute('data-activity-id') || event.target.closest('button').getAttribute('data-activity-id');
    modalDeleteButton.addEventListener('click', () => {
        deleteActivity(activityId)
    });
}

deleteActivityButtons.forEach(el => el.addEventListener('click', activateModal));


//UPDATE ACTIVITY  (WORKING ON IT) change edit button to save and make all fields input then make them back to normal
async function updateActivity(event) {
    console.log("EDIT BUTTON CLICKED!")
    const activityId =
        event.target.getAttribute('data-activity-id') ||
        event.target.closest('button').getAttribute('data-activity-id');

    // Get the elements to modify
    const titleEl = document.getElementById(`title-${activityId}`);
    const dateEl = document.getElementById(`date-${activityId}`);
    const startTimeEl = document.getElementById(`start-time-${activityId}`);
    const endTimeEl = document.getElementById(`end-time-${activityId}`);
    const descriptionEl = document.getElementById(`description-${activityId}`);

    // If the edit button was clicked, turn fields into inputs
    if (event.target.textContent === 'Edit') {
        // Change button text to "Save"
        event.target.textContent = 'Save';

        // Turn fields into inputs
        titleEl.innerHTML = `<input type="text" class="form-control" value="${titleEl.textContent}" id="title-input-${activityId}">`;
        dateEl.innerHTML = `<input type="date" class="form-control" value="${dateEl.textContent}" id="date-input-${activityId}">`;
        startTimeEl.innerHTML = `<input type="time" class="form-control" value="${startTimeEl.textContent}" id="start-time-input-${activityId}">`;
        endTimeEl.innerHTML = `<input type="time" class="form-control" value="${endTimeEl.textContent}" id="end-time-input-${activityId}">`;
        descriptionEl.innerHTML = `<textarea class="form-control" id="description-input-${activityId}" rows="3">${descriptionEl.textContent}</textarea>`;

        // Enable inputs
        document.getElementById(`title-input-${activityId}`).readOnly = false;
        document.getElementById(`date-input-${activityId}`).readOnly = false;
        document.getElementById(`start-time-input-${activityId}`).readOnly = false;
        document.getElementById(`end-time-input-${activityId}`).readOnly = false;
        document.getElementById(`description-input-${activityId}`).readOnly = false;
    } else if (event.target.textContent === 'Save') {
        // Get the updated activity data
        const updatedActivityData = {
            title: document.getElementById(`title-input-${activityId}`).value,
            date: document.getElementById(`date-input-${activityId}`).value,
            startTime: document.getElementById(`start-time-input-${activityId}`).value,
            endTime: document.getElementById(`end-time-input-${activityId}`).value,
            description: document.getElementById(`description-input-${activityId}`).value,
        };

        // Send PUT request to update the activity
        const response = await fetch(`/api/calendar-activities/${activityId}/update`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
                ...csrfToken(),
            },
            body: JSON.stringify(updatedActivityData),
        });

        if (response.ok) {
            // Update the activity with the new data
            titleEl.textContent = updatedActivityData.title;
            dateEl.textContent = updatedActivityData.date;
            startTimeEl.textContent = updatedActivityData.startTime;
            endTimeEl.textContent = updatedActivityData.endTime;
            descriptionEl.textContent = updatedActivityData.description;

            // Turn inputs back into fields
            titleEl.innerHTML = updatedActivityData.title;
            dateEl.innerHTML = updatedActivityData.date;
            startTimeEl.innerHTML = updatedActivityData.startTime;
            endTimeEl.innerHTML = updatedActivityData.endTime;
            descriptionEl.innerHTML = updatedActivityData.description;

            // Change button text back to "Edit"
            event.target.textContent = 'Edit';

            // Disable inputs
            document.getElementById(`title-input-${activityId}`).readOnly = true;
            document.getElementById(`date-input-${activityId}`).readOnly = true;
            document.getElementById(`start-time-input--${activityId}`).readOnly = true;
            document.getElementById(`end-time-input-${activityId}`).readOnly = true;
            document.getElementById(`description-input-${activityId}`).readOnly = true;
        }

    }
}
editActivityButtons.forEach(el => el.addEventListener('click', updateActivity));


// ADD NEW ACTIVITY  (NOT WORKING SOME ERRORS)
//Make an add modal activity which adds the fields
// async function addNewActivity(event) {
//
// }
//
// addActivityButton.addEventListener('click', addNewActivity);


// Add an event listener to the add activity button
        addActivityButton.addEventListener('click', (event) => {
            event.preventDefault();

            // Get the form data
            const formData = new FormData(addActivityForm);

            // Convert the date and time fields to a single date object
            const date = formData.get('date');
            const startTime = formData.get('start-time');
            const endTime = formData.get('end-time');
            const dateTime = new Date(`${date} ${startTime}`);
            dateTime.setHours(dateTime.getHours(), dateTime.getMinutes(), 0, 0);

            // Create the activity object
            const activity = {
                title: formData.get('title'),
                description: formData.get('description'),
                date: dateTime.toISOString(), // error invalid date
                startTime: startTime,
                endTime: endTime
            };

            // Make a POST request to add the activity to the server
            fetch('/api/calendar-activities/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    ...csrfToken()
                },
                body: JSON.stringify(activity)
            })
                .then(response => response.json())
                .then(data => {
                    // Reload the page to display the new activity
                    window.location.reload();
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });


