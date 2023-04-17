import {csrfToken} from "../cookie.js";

const deleteActivityButtons = document.querySelectorAll('.btn-outline-danger');
const editActivityButtons = document.querySelectorAll('.btn-outline-primary');
const addActivityButton = document.getElementById('add-activity-button');
let modalDeleteButton = document.getElementById('confirmDeleteButton');

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


//UPDATE ACTIVITY  (WORKING ON IT)
async function updateActivity(event) {
    const activityId = event.target.getAttribute('data-activity-id') || event.target.closest('button').getAttribute('data-activity-id');
    console.log(activityId);
    const response = await fetch(`/api/calendar-activities/${activityId}/update`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify(    // Pass the updated activity data here
            // Make sure the property names match the UpdatedCalendarActivityDTO fields
            {
                "title": document.getElementById("title").value,
                "date": document.getElementById("date").value,
                "startTime": document.getElementById("start-time").value,
                "endTime": document.getElementById("end-time").value,
                "description": document.getElementById("description").value,
            })
    });
    if (response.ok) {
        //set current content to updated content
        //EXAMPLE FROM THEMES:  themeAccordion.querySelector(`.theme-${themeId} .theme-name`).textContent = themeName;
    }
}

editActivityButtons.forEach(el => el.addEventListener('click', updateActivity));


// ADD NEW ACTIVITY
// async function addNewActivity(event) {
//
// }
//
// addActivityButton.addEventListener('click', addNewActivity);


