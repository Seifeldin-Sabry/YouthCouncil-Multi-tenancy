import {csrfToken} from "../cookie.js";


let editActivityButtons = document.querySelectorAll('.editButton');
// ADD ACTIVITY
const addActivityButton = document.getElementById('add-activity-button');
const addActivityForm = document.getElementById('add-activity-form');
const addActivityTitleInput = document.getElementById('add-activity-title');
const addActivityDateInput = document.getElementById('add-activity-date');
const addActivityStartTimeInput = document.getElementById('add-activity-start-time');
const addActivityEndTimeInput = document.getElementById('add-activity-end-time');
const addActivityDescriptionInput = document.getElementById('add-activity-description');
const confirmAddActivityButton = document.getElementById('modal-add-activity-button');
const modalDeleteButton = document.getElementById('confirmDeleteButton');
let currentActivityId;
const errorToast = document.querySelector('.error-toast');
const errorToastBody = errorToast.querySelector('.toast-body');
const successToast = document.querySelector('.success-toast');
const successToastBody = successToast.querySelector('.toast-body');

console.log(addActivityButton)
console.log(addActivityForm)
console.log(addActivityTitleInput)
console.log(addActivityDateInput)
console.log(addActivityStartTimeInput)
console.log(addActivityEndTimeInput)
console.log(addActivityDescriptionInput)
console.log(confirmAddActivityButton)

// DELETE ACTIVITY
async function deleteActivity(event) {
    console.log("DELETE ACTIVITY FUNCTION ENTERED:")
    console.log(event);// pointer event
    console.log(currentActivityId)
    const response = await fetch(`/api/calendar-activities/${currentActivityId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
    });
    if (response.ok) {
        document.getElementById(`${currentActivityId}`).remove();
        const deleteModal = document.getElementById('deleteModal');
        bootstrap.Modal.getOrCreateInstance(deleteModal).hide();
    }
}

function reassignCurrentActivityId(event) {
    currentActivityId = event.target.closest('button').getAttribute('data-activity-id') || event.target.getAttribute('id') || event.target.closest('button').getAttribute('id')
    console.log("ReassignCurrentActivityIdFunction:")
    console.log(currentActivityId)
}

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
    const startTimeEl = activityBody.querySelector('#start-time');
    const endTimeEl = activityBody.querySelector('#end-time');
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
        descriptionEl.innerHTML = `<textarea class="form-control" id="description-input-${activityId}" rows="4">${descriptionEl.textContent}</textarea>`;

    } else if (event.target.textContent === 'Save') {

        // Get the updated activity data
        const updatedActivityData = {
            title: document.getElementById(`title-input-${activityId}`).value,
            date: document.getElementById(`date-input-${activityId}`).value,
            startTime: document.getElementById(`start-time-input-${activityId}`).value,
            endTime: document.getElementById(`end-time-input-${activityId}`).value,
            description: document.getElementById(`description-input-${activityId}`).value,
        };

        const dateFormatted = new Date(updatedActivityData.date)
        let dateToday = new Date()
        const splitStart= updatedActivityData.startTime.toString().split(':')
        const splitEnd = updatedActivityData.endTime.toString().split(':')
        dateFormatted.setHours(0, 0, 0, 0);
        dateToday.setHours(0, 0, 0, 0);
        if (dateFormatted.getTime()<dateToday.getTime()){
            const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
            errorToastBody.textContent = 'Date has to be in the present or future';
            bootstrapErrorToast.show();
            return
        }
        dateToday = new Date()
        const dateCompare = dateToday
        dateCompare.setHours(0,0,0,0)
        console.log(dateCompare, 'date formatted', dateFormatted)
        if (dateCompare.getTime()===dateFormatted.getTime()) {
            if (Number(splitStart[0]) <= Number(dateToday.getHours().toString())) {
                const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
                if (Number(splitStart[0]) < Number(dateToday.getHours().toString())) {
                    errorToastBody.textContent = 'Hour of start time has to be in the present or future';
                    bootstrapErrorToast.show();
                    return;
                }
                if (Number(splitStart[1]) <= Number(dateToday.getMinutes().toString())) {
                    errorToastBody.textContent = 'Minutes of start time has to be in the future';
                    bootstrapErrorToast.show();
                    return;
                }
            }
        }
        if (Number(splitStart[0])>=Number(splitEnd[0])){
            const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
            if (Number(splitStart[0])>Number(splitEnd[0])){
                errorToastBody.textContent = 'End time needs to be past start time';
                bootstrapErrorToast.show();
                return;
            }
            if (Number(splitStart[1])>=Number(splitEnd[1])){
                errorToastBody.textContent = 'End time needs to be past start time';
                bootstrapErrorToast.show();
                return;
            }
        }

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
            activityBody.innerHTML = ` 
            <div class="d-flex gap-3">
                <button type="button" class="btn btn-outline-primary editButton" id="edit-activity-${activityId}" data-activity-id="${activityId}">Edit</button>
                <button type="button" class="btn btn-outline-danger deleteButtonModal" data-bs-toggle="modal"
                data-bs-target="#deleteModal" id="delete-activity-${activityId}" data-activity-id="${activityId}">Delete
                </button>
            </div>
            <div class="d-flex justify-content-between mb-3">
                <h4 class="mb-3" id="title">${updatedActivityData.title}</h4>
            </div>
            <p class="time">
                <span class="date" id="date">${updatedActivityData.date}</span>
                <br>
                <div class="form-group start-time" id="start-time">
                    <label for="start-time">From:</label>
                    <span>${updatedActivityData.startTime}</span>
                </div>
                <div class="form-group end-time" id="end-time">
                    <label for="end-time">Till:</label>
                    <span>${updatedActivityData.endTime}</span>
                </div>
            </p>
            <p id="description">${updatedActivityData.description}</p>
            `
            const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
            bootstrapErrorToast.hide();
            const bootstrapSuccessToast = bootstrap.Toast.getOrCreateInstance(successToast);
            successToastBody.textContent = 'Activity edited successfully';
            bootstrapSuccessToast.show();
            addEventListeners()
        }
    }
}

editActivityButtons.forEach(el => el.addEventListener('click', updateActivity));


async function addActivity() {
    // Get the values from the form
    const title1 = addActivityTitleInput.value;
    const date1 = addActivityDateInput.value;
    const startTime1 = addActivityStartTimeInput.value;
    const endTime1 = addActivityEndTimeInput.value;
    const description1 = addActivityDescriptionInput.value;
    const dateFormatted = new Date(date1)
    let dateToday = new Date()
    const splitStart= startTime1.toString().split(':')
    const splitEnd = endTime1.toString().split(':')
    dateFormatted.setHours(0, 0, 0, 0);
    dateToday.setHours(0, 0, 0, 0);
    if (dateFormatted.getTime()<dateToday.getTime()){
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        errorToastBody.textContent = 'Date has to be in the present or future';
        bootstrapErrorToast.show();
        return
    }
    dateToday = new Date()
    const dateCompare = dateToday
    dateCompare.setHours(0,0,0,0)
    console.log(dateCompare, 'date formatted', dateFormatted)
    if (dateCompare.getTime()===dateFormatted.getTime()){
        if (Number(splitStart[0])<=Number(dateToday.getHours().toString())){
            const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
            if (Number(splitStart[0])<Number(dateToday.getHours().toString())){
                errorToastBody.textContent = 'Hour of start time has to be in the present or future';
                bootstrapErrorToast.show();
                return;
            }
            if (Number(splitStart[1])<=Number(dateToday.getMinutes().toString())){
                errorToastBody.textContent = 'Minutes of start time has to be in the future';
                bootstrapErrorToast.show();
                return;
            }
        }
    }

    if (Number(splitStart[0])>=Number(splitEnd[0])){
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        if (Number(splitStart[0])>Number(splitEnd[0])){
            errorToastBody.textContent = 'End time needs to be past start time';
            bootstrapErrorToast.show();
            return;
        }
        if (Number(splitStart[1])>=Number(splitEnd[1])){
            errorToastBody.textContent = 'End time needs to be past start time';
            bootstrapErrorToast.show();
            return;
        }
    }



    // Make a POST request to the server to add the activity
    const response = await fetch('/api/calendar-activities', {
        method: 'POST',
        body: JSON.stringify({
            title: title1,
            date: date1,
            startTime: startTime1,
            endTime: endTime1,
            description: description1
        }),
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        }
    })

    if (response.ok) {
        response.json().then(handleAddedActivity)
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        bootstrapErrorToast.hide();
        const bootstrapSuccessToast = bootstrap.Toast.getOrCreateInstance(successToast);
        successToastBody.textContent = 'Activity created successfully';
        bootstrapSuccessToast.show();

        const addModal = document.getElementById('addModal');
        bootstrap.Modal.getOrCreateInstance(addModal).hide();
    }

}


function handleAddedActivity(activity) {
    console.log("ACTIVITY ADDED!");

    // Create a new li element for the activity
    const newActivity = document.createElement('li');
    newActivity.classList.add('event');
    newActivity.id = activity.id;

    // Set the inner HTML of the new activity
    newActivity.innerHTML = `
    <div class="d-flex gap-3">
      <button type="button" class="btn btn-outline-primary editButton" id="edit-activity-${activity.id}" data-activity-id="${activity.id}">Edit</button>
      <button type="button" class="btn btn-outline-danger deleteButtonModal" data-bs-toggle="modal"
        data-bs-target="#deleteModal" id="delete-activity-${activity.id}" data-activity-id="${activity.id}">Delete
      </button>
    </div>
    <div class="d-flex justify-content-between mb-3">
      <h4 class="mb-3" id="title">${activity.title}</h4>
    </div>
    <p class="time">
      <span class="date" id="date">${activity.date}</span>
      <br />
      <div class="form-group start-time">
        <label for="start-time">From:</label>
        <span id="start-time">${activity.startTime}</span>
      </div>
      <div class="form-group end-time">
        <label for="end-time">Till:</label>
        <span id="end-time">${activity.endTime}</span>
      </div>
    </p>
    <p id="description">${activity.description}</p>
  `;

    // Hide the new activity initially
    newActivity.style.opacity = 0;
    newActivity.style.transform = 'translateX(100%)';

    // Get the reference to the ul element
    const activityList = document.querySelector('.timeline-1');

    // Append the new activity to the list
    activityList.appendChild(newActivity);

    // Apply the fade-in animation
    setTimeout(() => {
        newActivity.style.opacity = 1;
        newActivity.style.transform = 'none';
    }, 300);

    addEventListeners();
}

confirmAddActivityButton.addEventListener('click', addActivity);

function addEventListeners() {
    console.log("event listeners entered!")
    let deleteActivityButtons = document.querySelectorAll('.deleteButtonModal');
    let editActivityButtons = document.querySelectorAll('.editButton');
    deleteActivityButtons.forEach(el => el.addEventListener('click', reassignCurrentActivityId));
    modalDeleteButton.addEventListener('click', deleteActivity);
    editActivityButtons.forEach(el => el.addEventListener('click', updateActivity));
}

addEventListeners()




//CARDS ANIMATION
document.addEventListener('DOMContentLoaded', function() {
    var events = document.querySelectorAll('.event');

    // Add fade-in class to each event after a delay
    for (var i = 0; i < events.length; i++) {
        (function(index) {
            setTimeout(function() {
                events[index].classList.add('fade-in');
            }, 300 * index);
        })(i);
    }
});
