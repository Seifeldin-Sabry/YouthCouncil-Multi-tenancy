let createButton = document.querySelector('.create');
let submitButton = document.querySelector('.submit');
const container =document.querySelector('.container');
const rows = container.querySelector('.rows')

addEventListeners();


function addEventListeners(){
    submitButton = document.querySelector('.submit');
    createButton.addEventListener('click', addFormInput)
    submitButton.addEventListener('click', addForm)

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

function addForm(event) {
    const tableRow = event.target.parentNode;
    const title = tableRow.querySelector('.questionText');
    fetch('/api/form', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "title": title.value
        })
    }).then(response => {
        if (response.status === 201) {
            tableRow.remove();
            response.json()
                .then(handleAddedForm);
        } else {
            alert("Error in form found client-side");
        }
    });
}

function handleAddedForm(form) {
    rows.innerHTML += `
        <div class="row">
                    <a class="col ID" href="form/?formId=${form.formId}">${form.formId}
			</a>
			<div class="col title" >${form.title}</div>
        </div>
        `;
    createButton.classList.remove('invisible');
}