import {csrfToken} from "../cookie.js";
const formID = Number(window.location.href.charAt(window.location.href.length-1));
let createButtons = document.querySelectorAll('.create');
let submitButtons = document.querySelectorAll('.submit');


const addQuestion = async (event) =>{
    const tableRow = event.target.parentNode;
    const tableContainer = tableRow.parentNode.parentNode;
    const title = tableRow.querySelector('.questionText').value;
    const order = tableRow.querySelector('.questionOrder').value;
    const requiredCheck = tableRow.querySelector('.questionRequired');
    const required = requiredCheck.checked;
    const type = tableContainer.children[0].innerHTML;
    let choices = null;
    //console.log(required);
    if (tableContainer.classList.contains('choice') || tableContainer.classList.contains('radio')) {
        choices = tableRow.querySelector('.questionChoices').value;
    }

    const formData = new FormData();
    formData.append("questionText", title);
    formData.append("questionType", type);
    formData.append("required", required);
    formData.append("order", order);
    formData.append("choices", choices);
    formData.append("formId", String(formID));

    const options = {
        method: 'POST',
        headers: {
            ...csrfToken()
        },
        body: formData
    }
    const response = await fetch(`/api/question`, options);
    if (response.status === 201) {
        tableRow.remove();
        response.json()
            .then(handleAddedQuestion);
    } else {
        alert("Error in form found client-side");
    }
}

setFormLinkUsers();

function setFormLinkUsers(){
    var frag = document.createDocumentFragment(),
        temp = document.createElement('div');
    temp.innerHTML = `<h6 class="header">Link For users: http://localhost:8080/user-form?formId=${formID}</h6>`;
    while (temp.firstChild) {
        frag.appendChild(temp.firstChild);
    }
    document.body.insertBefore(frag, document.body.childNodes[2]);

}
addEventListeners();

function addEventListeners(){
    submitButtons = document.querySelectorAll('.submit');
    for (const createButton of createButtons){
        createButton.addEventListener('click', addQuestionInput)
    }
    for (const submitButton of submitButtons){
        submitButton.addEventListener('click', addQuestion)
    }
}

function addQuestionInput(event) {
    const tableContainer = event.target.parentNode;
    const rows = tableContainer.querySelector('.rows');
    const button = tableContainer.querySelector('.create');
    if (button.classList.contains('textInputButton') || button.classList.contains('numericInputButton')) {
        rows.innerHTML += `
    <div class="row">
        <button type="button" class="btn btn-success submit col">submit</button>
        <input name="title" type="text" id="title" class="form-control col questionText">
        <input name="order" type="number" id="order" class="form-control col questionOrder">
        <input name="required" type="checkbox" id="required" class="form-check-input col questionRequired checkbox">
        <div class="col invisible">
        </div>  
    </div>
    `
    } else {
        rows.innerHTML += `
    <div class="row">
        <button type="button" class="btn btn-success submit col">submit</button>
        <input name="title" type="text" id="title" class="form-control col questionText">
        <input name="order" type="number" id="order" class="form-control col questionOrder">
        <input name="required" type="checkbox" id="required" class="form-check-input col questionRequired checkbox">
        <input name="choices" type="text" id="choices" class="form-control col questionChoices">
        </div>  
    </div>
    `
    }
    addEventListeners()
}

function handleAddedQuestion(question){
        console.log(question);
        let rows;
        if(question.questionType==='MULTIPLE_CHOICE_QUESTION'){
            rows= document.querySelector('.choice').querySelector('.rows');
            rows.innerHTML+=`
        <div class="row">
                    <div class="col ID" >${question.questionId}</div>
                    <div class="col text" >${question.questionText}</div>
                    <div class="col questionOrder" >${question.order}</div>
                    <div class="col isRequired" >${question.required}</div>
                    <div class="col choices" >${question.choices}</div>
        </div>
        `
        } else if (question.questionType==='RADIO_QUESTION') {
            rows= document.querySelector('.radio').querySelector('.rows');
            rows.innerHTML+=`
        <div class="row">
                    <div class="col ID" >${question.questionId}</div>
                    <div class="col text" >${question.questionText}</div>
                    <div class="col questionOrder" >${question.order}</div>
                    <div class="col isRequired" >${question.required}</div>
                    <div class="col choices" >${question.choices}</div>
        </div>
        
        `
        }
        else if (question.questionType==='TEXT_QUESTION'){
            rows= document.querySelector('.textInput').querySelector('.rows');
            rows.innerHTML+=`
        <div class="row">
                    <div class="col ID" >${question.questionId}</div>
                    <div class="col text" >${question.questionText}</div>
                    <div class="col questionOrder" >${question.order}</div>
                    <div class="col isRequired" >${question.required}</div>
                    <div class="col invisible"></div>
        </div>
        `
        } else {
            rows= document.querySelector('.numericInput').querySelector('.rows');
            rows.innerHTML+=`
                <div class="row">
                    <div class="col ID" >${question.questionId}</div>
                    <div class="col text" >${question.questionText}</div>
                    <div class="col questionOrder" >${question.order}</div>
                    <div class="col isRequired" >${question.required}</div>
                    <div class="col invisible"></div>
                </div>
        
        `
        }

    }

