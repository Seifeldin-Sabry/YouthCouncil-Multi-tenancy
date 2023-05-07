import {csrfToken} from "../cookie.js";

let createButton = document.querySelector('.create');
let submitButton = document.querySelector('.submit');
const container =document.querySelector('.container');
const rows = container.querySelector('.rows')

createButton.addEventListener('click', addFormInput)


const addForm = async (event) =>{
    const tableRow = event.target.parentNode;
    const title = tableRow.querySelector('.questionText').value;
    const formData = new FormData();
    formData.append('title', title);


    const options = {
        method: 'POST',
        headers: {
            ...csrfToken()
        },
        body: formData
    }
    const response = await fetch(`/api/form`, options);

    if (response.status === 201) {
        tableRow.remove();
        response.json()
            .then(handleAddedForm);
    } else {
        alert("Error in form found client-side");
    }
}

function addFormInput(event) {
    rows.innerHTML += `
    <div class="row">
        <button type="button" class="btn btn-success submit col">submit</button>
        <input name="title" type="text" id="title" class="form-control col questionText">
        </div>  
    </div>
    `
    createButton.classList.add('invisible');

    addEventListeners()
}

function addEventListeners(){
    submitButton = document.querySelector('.submit');
    createButton.addEventListener('click', addFormInput)
    submitButton.addEventListener('click', addForm)

}

function handleAddedForm(form) {
    rows.innerHTML += `
        <div class="row">
                    <a class="col ID" href="form/?formId=${form.id}">${form.id}
			</a>
			<div class="col title" >${form.title}</div>
        </div>
        `;
    createButton.classList.remove('invisible');
}