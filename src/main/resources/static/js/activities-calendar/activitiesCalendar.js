//////ACTIONS FOR ALL USERS
//SORTING  (not working but still needs implementation)
// function sortByStartDate() {
//     const activities = document.querySelectorAll(".event");
//     const sortedActivities = Array.from(activities).sort((a, b) => {
//         const aStartDate = new Date(a.querySelector(".start-date").getAttribute("data-start-date"));
//         const bStartDate = new Date(b.querySelector(".start-date").getAttribute("data-start-date"));
//         return aStartDate - bStartDate;
//     });
//     const content = document.querySelector("#content");
//     content.innerHTML = "";
//     sortedActivities.forEach(activity => content.appendChild(activity));
// }
//
// // Function to sort activities by title
// function sortByTitle() {
//     const activities = document.querySelectorAll(".event");
//     const sortedActivities = Array.from(activities).sort((a, b) => {
//         const aTitle = a.querySelector(".title").textContent.toLowerCase();
//         const bTitle = b.querySelector(".title").textContent.toLowerCase();
//         return aTitle.localeCompare(bTitle);
//     });
//     const content = document.querySelector("#content");
//     content.innerHTML = "";
//     sortedActivities.forEach(activity => content.appendChild(activity));
// }
//
// // Function to sort activities by municipality
// function sortByMunicipality() {
//     const activities = document.querySelectorAll(".event");
//     const sortedActivities = Array.from(activities).sort((a, b) => {
//         const aMunicipality = a.querySelector(".municipality").textContent.toLowerCase();
//         const bMunicipality = b.querySelector(".municipality").textContent.toLowerCase();
//         return aMunicipality.localeCompare(bMunicipality);
//     });
//     const content = document.querySelector("#content");
//     content.innerHTML = "";
//     sortedActivities.forEach(activity => content.appendChild(activity));
// }
//
// // Add event listeners to the sorting dropdown items
// const sortStartDateItem = document.querySelector("#sort-by-start-date");
// sortStartDateItem.addEventListener("click", sortByStartDate);
//
// const sortTitleItem = document.querySelector("#sort-by-title");
// sortTitleItem.addEventListener("click", sortByTitle);
//
// const sortMunicipalityItem = document.querySelector("#sort-by-municipality");
// sortMunicipalityItem.addEventListener("click", sortByMunicipality);
//

////FILTERING (NOT WORKING)
// const filterByMunicipality = (event) => {
//     console.log("filter municipalities function called!")
//     const selectedMunicipality = event.target.getAttribute('data-municipality');
//
//     // Get all activities and filter by selected municipality
//     const activities = document.querySelectorAll('.event');
//     activities.forEach((activity) => {
//         const municipality = activity.querySelector('span').innerText.trim();
//         if (selectedMunicipality === 'all' || municipality === selectedMunicipality) {
//             activity.style.display = 'block';
//         } else {
//             activity.style.display = 'none';
//         }
//     });
// };
//
// // Add event listener to the filter by municipality dropdown
// const filterByMunicipalityDropdown = document.querySelector('#filter-by-municipality ul.dropdown-menu');
// const filterByMunicipalityLinks = filterByMunicipalityDropdown.querySelectorAll('a');
// filterByMunicipalityLinks.forEach((link) => {
//     link.addEventListener('click', filterByMunicipality);
// });

// const filterByMunicipality = (event) => {
//     console.log("filter municipalities function called!")
//     const selectedMunicipality = event.target.getAttribute('data-municipality');
//
//     // Get all activities and filter by selected municipality
//     // const activities = document.querySelectorAll('.event');
//     const activities = document.querySelectorAll('li.event');
//
//     activities.forEach((activity) => {
//         const municipality = activity.querySelector('a').getAttribute('data-municipality');
//         if (selectedMunicipality === 'all' || municipality === selectedMunicipality) {
//             activity.style.display = 'block';
//         } else {
//             activity.style.display = 'none';
//         }
//     });
// };
//
// // Add event listener to the filter by municipality dropdown
// const filterByMunicipalityDropdown = document.querySelector('#filter-by-municipality ul.dropdown-menu');
// const filterByMunicipalityLinks = filterByMunicipalityDropdown.querySelectorAll('a');
// filterByMunicipalityLinks.forEach((link) => {
//     link.addEventListener('click', filterByMunicipality);
// });



////ACTIONS FOR YOUTH COUNCIL ADMIN

//EDIT



//ADD



//DELETE
