// import {csrfToken} from "../cookie.js";

const deleteActivityButtons = document.querySelectorAll('.btn-outline-danger');
const editActivityButtons = document.querySelectorAll('.btn-outline-primary');

//DELETE ACTIVITY
async function deleteActivity(event) {
    const activityId = event.target.getAttribute('data-activity-id') || event.target.closest('button').getAttribute('data-activity-id');
    const response = await fetch(`/api/activities/${activityId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
            // ,...csrfToken()
        },
    });
    if (response.ok) {
        document.querySelector(`.activity-item-${activityId}`).remove();
        return;
    }
}

deleteActivityButtons.forEach(el => el.addEventListener('click', deleteActivity));


//UPDATE ACTIVITY

async function updateActivity(event) {

}

deleteActivityButtons.forEach(el => el.addEventListener('click', updateActivity));

// ADD NEW ACTIVITY
