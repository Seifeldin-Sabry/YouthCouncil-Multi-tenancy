import {csrfToken} from "../cookie.js";

const deleteActivityButtons = document.querySelectorAll('.btn-outline-danger');
let modalDeleteButton = document.getElementById('confirmDeleteButton');

const editActivityButtons = document.querySelectorAll('.btn-outline-primary');


// DELETE ACTIVITY
async function deleteActivity(activityId) {
    // const activityId = id.target.getAttribute('data-activity-id') || id.target.closest('button').getAttribute('data-activity-id');
    console.log(activityId);
    const response = await fetch(`/api/calendar-activities/${activityId}`, {
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


// UPDATE ACTIVITY
async function updateActivity(event) {
    console.log("EDIT BUTTON CLICKED!")
    const activityId =
        event.target.getAttribute('data-activity-id') ||
        event.target.closest('button').getAttribute('data-activity-id');
    console.log(`Activity id = ${activityId}`);
    // Get the elements to modify
    const activityBody = document.getElementById(`${activityId}`);
    const titleEl = activityBody.querySelector('#title');
    const dateEl = activityBody.querySelector('#date');
    const startTimeEl = activityBody.querySelector('.start-time');
    const endTimeEl = activityBody.querySelector('.end-time');
    const descriptionEl = activityBody.querySelector('#description');

    console.log(activityBody)
    console.log(titleEl)
    console.log(dateEl)
    console.log(startTimeEl)
    console.log(endTimeEl)
    console.log(descriptionEl)
    console.log(event.target.textContent)

    // If the edit button was clicked, turn fields into inputs
    if (String(event.target.textContent) === 'Edit') {
        console.log("Entered if statement")

        // Change button text to "Save"
        event.target.textContent = 'Save';

        // Turn fields into inputs
        titleEl.innerHTML = `<input type="text" class="form-control" value="${titleEl.textContent}" id="title-input-${activityId}">`;
        dateEl.innerHTML = `<input type="date" class="form-control" value="${dateEl.textContent}" id="date-input-${activityId}">`;
        startTimeEl.innerHTML = `<input type="time" class="form-control" value="${startTimeEl.textContent}" id="start-time-input-${activityId}">`;
        endTimeEl.innerHTML = `<input type="time" class="form-control" value="${endTimeEl.textContent}" id="end-time-input-${activityId}">`;
        descriptionEl.innerHTML = `<textarea class="form-control" id="description-input-${activityId}" rows="3">${descriptionEl.textContent}</textarea>`;


    } else if (event.target.textContent === 'Save') {

        // console.log(document.getElementById(`title-input-${activityId}`).value)
        // console.log(document.getElementById(`date-input-${activityId}`).value)
        // console.log(document.getElementById(`start-time-input-${activityId}`).value)
        // console.log(document.getElementById(`end-time-input-${activityId}`).value)
        // console.log(document.getElementById(`description-input-${activityId}`).value)

        // Get the updated activity data
        const updatedActivityData = {
            title: document.getElementById(`title-input-${activityId}`).value,
            date: document.getElementById(`date-input-${activityId}`).value,
            startTime: document.getElementById(`start-time-input-${activityId}`).value,
            endTime: document.getElementById(`end-time-input-${activityId}`).value,
            description: document.getElementById(`description-input-${activityId}`).value,
        };

        // Send PUT request to update the activity
        console.log(updatedActivityData)
        const response = await fetch(`/api/calendar-activities/${activityId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                ...csrfToken()
            },
            body: JSON.stringify(updatedActivityData)
        });

        if (response.ok) {
            // Update the activity with the new data
           activityBody.innerHTML = ` <div class="d-flex gap-3">
                <button type="button" class="btn btn-outline-primary" data-activity-id="${activityId}"
                        id="'edit-activity-'+${activityId}">Edit
                </button>
                <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal"
                        data-bs-target="#deleteModal" data-activity-id="${activityId}"
                        id="'delete-activity-'+${activityId}">Delete</button>
            </div>
            <div class="d-flex justify-coclassName-between mb-3">
                <h4 class="mb-3" id="title">${updatedActivityData.title}</h4>
            </div>
            <p class="time">
                <spclassNameass="date" id="date">${updatedActivityData.date}</span>
                <br />
                <div class="form-group start-className>
							<label for=" start-time">From:</htmlForel>
							<span id="start-time">${updatedActivityData.startTime}</span>
						</div>
						<div class=" form-group end-ticlassName
						<label for="end-time">Till:</lahtmlFor>
                <span id="end-time">${updatedActivityData.endTime}</span>
            </div>
        </p>
         <p id="description">${updatedActivityData.description}</p>
            `
        }
    }
}
editActivityButtons.forEach(el => el.addEventListener('click', updateActivity));


// ADD NEW ACTIVITY  (WORKING ON IT)
// addActivityButton.addEventListener('click', (event) => {
//     console.log("Add button clicked!")
//     event.preventDefault();
//
//     // Get the form data
//     const formData = new FormData(addActivityForm);
//
//     // Convert the date and time fields to a single date object
//     const date = formData.get('date');
//     const startTime = formData.get('start-time');
//     const dateTime = new Date(`${date} ${startTime}`);
//     const endTime = formData.get('end-time');
//     dateTime.setHours(dateTime.getHours(), dateTime.getMinutes(), 0, 0);
//
//     // Create the activity object
//     const activity = {
//         title: formData.get('title'),
//         description: formData.get('description'),
//         date: dateTime.toISOString(), // error invalid date
//         startTime: startTime,
//         endTime: endTime
//     };
//
//     // Make a POST request to add the activity to the server
//     fetch('/api/calendar-activities/add', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//             ...csrfToken()
//         },
//         body: JSON.stringify(activity)
//     })
//         .then(response => response.json())
//         .then(data => {
//             // Reload the page to display the new activity
//             window.location.reload();
//         })
//         .catch(error => {
//             console.error('Error:', error);
//         });
// });


const addActivityButton = document.getElementById('add-activity-button');
const addActivityForm = document.getElementById('add-activity-form');
const addActivityTitleInput = document.getElementById('add-activity-title');
const addActivityDateInput = document.getElementById('add-activity-date');
const addActivityStartTimeInput = document.getElementById('add-activity-start-time');
const addActivityEndTimeInput = document.getElementById('add-activity-end-time');
const addActivityDescriptionInput = document.getElementById('add-activity-description');
const confirmAddActivityButton = document.getElementById('confirm-add-activity-button');

console.log(addActivityButton)
console.log(addActivityForm)
console.log(addActivityTitleInput)
console.log(addActivityDateInput)
console.log(addActivityStartTimeInput)
console.log(addActivityEndTimeInput)
console.log(addActivityDescriptionInput)
console.log(confirmAddActivityButton)

// Add a listener to the add activity button
addActivityButton.addEventListener('click', () => {
    // Reset the form when the modal is opened
    addActivityForm.reset();
});

// Add a listener to the confirm add activity button
confirmAddActivityButton.addEventListener('click', () => {
    // Get the values from the form
    const title = addActivityTitleInput.value;
    const date = addActivityDateInput.value;
    const startTime = addActivityStartTimeInput.value;
    const endTime = addActivityEndTimeInput.value;
    const description = addActivityDescriptionInput.value;

    console.log(title)
    console.log(date)
    console.log(startTime)
    console.log(endTime)
    console.log(description)


    // Make a POST request to the server to add the activity
    fetch('/api/calendar-activities/add', {
        method: 'POST',
        body: JSON.stringify({
            title: title,
            date: date,
            startTime: startTime,
            endTime: endTime,
            description: description
        }),
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        }
    })
        .then(response => {
            if (response.ok) {
                // Reload the page to see the new activity
                location.reload();
                console.log("ACTIVITY ADDED!")
            } else {
                // Display an error message
                console.error('Failed to add activity');
            }
        });
});