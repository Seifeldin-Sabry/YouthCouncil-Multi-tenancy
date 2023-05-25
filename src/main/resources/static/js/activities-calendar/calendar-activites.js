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

        const addModal = document.getElementById('addModal');
        bootstrap.Modal.getOrCreateInstance(addModal).hide();
    }

}

// function handleAddedActivity(activity) {
//     console.log("ACTIVITY ADDED!")
//     // Update the activity with the new data
//     // Get the reference to the ul element
//     const activityList = document.querySelector('.timeline-1');
//
//     // Set the innerHTML of the new activity
//     activityList.innerHTML += `
//    <li class="event" id="${activity.id}">
//     <div class="d-flex gap-3">
//         <button type="button" class="btn btn-outline-primary editButton" id="edit-activity-${activity.id}" data-activity-id="${activity.id}">Edit</button>
//         <button type="button" class="btn btn-outline-danger deleteButtonModal" data-bs-toggle="modal"
//           data-bs-target="#deleteModal" id="delete-activity-${activity.id}" data-activity-id="${activity.id}">Delete
//         </button>
//     </div>
//     <div class="d-flex justify-content-between mb-3">
//         <h4 class="mb-3" id="title">${activity.title}</h4>
//     </div>
//     <p class="time">
//         <span class="date" id="date">${activity.date}</span>
//         <br />
//         <div class="form-group start-time">
//             <label for="start-time">From:</label>
//             <span id="start-time">${activity.startTime}</span>
//         </div>
//         <div class="form-group end-time">
//             <label for="end-time">Till:</label>
//             <span id="end-time">${activity.endTime}</span>
//         </div>
//     </p>
//     <p id="description">${activity.description}</p>
//    </li>
// `;
//     addEventListeners()
// }


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
