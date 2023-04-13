// ////SORTING
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
// ////FILTERING
//
