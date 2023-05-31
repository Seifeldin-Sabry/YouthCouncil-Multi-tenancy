import {csrfToken} from "../cookie.js";

let currentPageId;
const pageList = document.getElementById("pageList");
const editPageBtns = document.querySelectorAll('.edit-btn')
const activePageInputs = document.querySelectorAll('.active-page-input')

editPageBtns.forEach(btn => {
    currentPageId = btn.dataset.id
})

activePageInputs.forEach(input => {
    input.addEventListener('change', event => {
        toggleActive(event.target.dataset.id, event.target.checked);
    })
})

// Delete a page.
function deletePage(pageId) {
    fetch('/api/pages/' + pageId, {
        headers: {
            ...csrfToken()
        },
        method: 'DELETE'
    })  // replace this with your actual API endpoint
        .then(response => {
            if (response.ok) {
                document.getElementById("page" + pageId).remove();
            } else {
                alert("Failed to delete page.");
            }
        });
}

// Save changes to a page's title.
document.getElementById("savePageTitle").onclick = function () {
    var newTitle = document.getElementById("editPageTitle").value;
    fetch('/api/pages/' + currentPageId, {  // replace this with your actual API endpoint
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({
            title: newTitle
        })
    })
        .then(response => {
            if (response.ok) {
                bootstrap.Modal.getOrCreateInstance(document.getElementById("editPageModal")).hide();
                document.getElementById("page" + currentPageId).querySelector(".page-title").textContent = newTitle;
            } else {
                alert("Failed to save changes.");
            }
        });
};


// Create a new page.
document.getElementById("createPage").onclick = function () {
    var title = document.getElementById("newPageTitle").value;
    fetch('/api/pages', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({
            title: title
        })
    })
        .then(response => response.json())
        .then(data => {
            // Create a new page element and append it to the list.
            var pageEntry = document.createElement("div");
            pageEntry.className = "page-entry";
            pageEntry.id = "page" + data.id;
            pageEntry.innerHTML = `
      <div class="row">
				<div class="col-8">
					<h4 class="page-title">${data.title}</h4>
				</div>
				<div class="col-2">
					<div class="form-check form-switch">
						<input class="form-check-input active-page-input"
							   data-id="${data.id}" type="checkbox"
						>
					</div>
				</div>
				<div class="col-2">
				    <a href="/builder/${data.pageName}">
                        <button class="btn btn-primary edit-btn"
                                data-id="${data.id}" type="button"
                                
                        >Edit</button></a>
					<button class="btn btn-danger delete-page-btn" data-id="${data.id}" type="button">Delete
					</button>
				</div>
			</div>
    `;
            pageEntry.querySelector('.edit-btn').addEventListener('click', event => {
                currentPageId = event.target.dataset.id;
            })
            pageEntry.querySelector('.delete-page-btn').addEventListener('click', event => {
                deletePage(event.target.dataset.id)
            })
            pageEntry.querySelector('.active-page-input').addEventListener('change', event => {
                toggleActive(event.target.dataset.id, event.target.checked);
            })
            pageList.appendChild(pageEntry);
            document.getElementById("newPageTitle").value=""
            bootstrap.Modal.getOrCreateInstance(document.getElementById("newPageModal")).hide();
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Failed to create page.");
        });
}

// Toggle the active status of a page.
function toggleActive(pageId, isActive) {
    fetch('/api/pages/' + pageId + '/active', {  // replace this with your actual API endpoint
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({
            active: isActive
        })
    })
        .then(response => {
            if (response.ok) {
                document.getElementById("page" + pageId).querySelector(".active-page-input").checked = isActive;
            } else {
                alert("Failed to update active status.");
            }
        });
}

document.querySelectorAll('.delete-page-btn').forEach(item => {
    item.addEventListener('click', event => {
        deletePage(item.dataset.id);
    })
})
