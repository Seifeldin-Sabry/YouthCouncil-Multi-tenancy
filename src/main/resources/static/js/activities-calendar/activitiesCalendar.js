//////ACTIONS FOR ALL USERS

//SORTING

// Get the list of activities
const activitiesList = document.querySelectorAll('.event');


// Sort activities by duration (DOES NOT WORK)
// function filterActivitiesByDuration() {
//     activitiesList.forEach((activity) => {
//         const durationEl = activity.querySelector(".duration");
//         if (durationEl) {
//             const duration = parseInt(durationEl.getAttribute("data-duration"));
//             if (duration <= 1) {
//                 activity.style.display = "none";
//             } else {
//                 activity.style.display = "block";
//             }
//         }
//     });
// }
function filterActivitiesByDuration() {
    // Convert activitiesList to an array
    const activitiesArray = Array.from(activitiesList);

    // Sort the array by duration
    activitiesArray.sort((a, b) => {
        const durationA = parseInt(a.getAttribute("data-duration"));
        const durationB = parseInt(b.getAttribute("data-duration"));
        return durationA - durationB;
    });

    // Loop through the sorted array and update the display
    activitiesArray.forEach((activity) => {
        const durationEl = activity.querySelector(".duration");
        if (durationEl) {
            const duration = parseInt(durationEl.getAttribute("data-duration"));
            if (duration <= 1) {
                activity.style.display = "none";
            } else {
                activity.style.display = "block";
            }
        }
    });
}




// Sort activities by today (DOES NOT WORK)
function sortByToday() {
    const events = document.querySelectorAll('.event');
    const today = new Date();

    events.forEach(event => {
        const activityDate = new Date(event.querySelector('span').textContent);

        if (activityDate.getFullYear() === today.getFullYear() &&
            activityDate.getMonth() === today.getMonth() &&
            activityDate.getDate() === today.getDate()) {
            event.style.display = 'block';
        } else {
            event.style.display = 'none';
        }
    });
}


// Sort activities by week (DOES NOT WORK)
function sortByWeek() {
    const today = new Date();
    const nextWeek = new Date(today.getTime() + 7 * 24 * 60 * 60 * 1000);

    activitiesList.forEach(activity => {
        const date = activity.querySelector('.date');
        const activityDate = new Date(date.innerHTML);

        if (activityDate >= today && activityDate < nextWeek) {
            activity.style.order = 0;
        } else {
            activity.style.order = 1;
        }
    });
}


document.getElementById("sort-by-duration").addEventListener("click", filterActivitiesByDuration);
document.getElementById('sort-by-today').addEventListener('click', sortByToday);
document.getElementById('sort-by-week').addEventListener('click', sortByWeek);



////ACTIONS FOR YOUTH COUNCIL ADMIN

//EDIT



//ADD



//DELETE
