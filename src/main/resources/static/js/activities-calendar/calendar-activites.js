import {csrfToken} from "../cookie.js";

let deleteActivityButtons = document.querySelectorAll('.btn-outline-danger');
let modalDeleteButton = document.getElementById('confirmDeleteButton');
let editActivityButtons = document.querySelectorAll('.btn-outline-primary');
// ADD ACTIVITY
const addActivityButton = document.getElementById('add-activity-button');
const addActivityForm = document.getElementById('add-activity-form');
const addActivityTitleInput = document.getElementById('add-activity-title');
const addActivityDateInput = document.getElementById('add-activity-date');
const addActivityStartTimeInput = document.getElementById('add-activity-start-time');
const addActivityEndTimeInput = document.getElementById('add-activity-end-time');
const addActivityDescriptionInput = document.getElementById('add-activity-description');
const confirmAddActivityButton = document.getElementById('modal-add-activity-button');

console.log(addActivityButton)
console.log(addActivityForm)
console.log(addActivityTitleInput)
console.log(addActivityDateInput)
console.log(addActivityStartTimeInput)
console.log(addActivityEndTimeInput)
console.log(addActivityDescriptionInput)
console.log(confirmAddActivityButton)

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
        console.log(activityId)
        deleteActivity(activityId)
    });
}

// deleteActivityButtons.forEach(el => el.addEventListener('click', activateModal));


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
    console.log(startTimeEl) //null
    console.log(endTimeEl) //null
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
            activityBody.innerHTML = ` 
                <div class="d-flex gap-3">
                    <button type="button" class="btn btn-outline-primary editButton" id="edit-activity-${activityId}" data-activity-id="${activityId}">Edit</button>
                    <button type="button" class="btn btn-outline-danger deleteButtonModal" data-bs-toggle="modal"
                    data-bs-target="#deleteModal" id="delete-activity-${activityId}" data-activity-id="${activityId}">Delete
                    </button>
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

    console.log(title1)
    console.log(date1)
    console.log(startTime1)
    console.log(endTime1)
    console.log(description1)

    // Make a POST request to the server to add the activity
    const response = await fetch('/api/calendar-activities/add', {
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

        const addModal = document.getElementById('addModal');
        bootstrap.Modal.getOrCreateInstance(addModal).hide();
    }

}

function handleAddedActivity(activity){
    console.log("ACTIVITY ADDED!")
    // Update the activity with the new data
    // Get the reference to the ul element
    const activityList = document.querySelector('.timeline-1');


    // Set the innerHTML of the new activity
    activityList.innerHTML += `
   <li class="event" id="${activity.id}">
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
   </li>
`;
    addEventListeners()
}

confirmAddActivityButton.addEventListener('click', addActivity);

function addEventListeners(){
    console.log("event listeners entered!")
    let deleteActivityButtons = document.querySelectorAll('.deleteButtonModal');
    let editActivityButtons = document.querySelectorAll('.editButton');
    deleteActivityButtons.forEach(el => el.addEventListener('click', activateModal));
    editActivityButtons.forEach(el => el.addEventListener('click', updateActivity));
}

addEventListeners()


//EDITING A NEW ACTIVITY NOT WORKING UPDATED_ACTIVITY_DTO ID IS NULL