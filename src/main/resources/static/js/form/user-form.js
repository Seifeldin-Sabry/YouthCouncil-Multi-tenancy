import {csrfToken} from "../cookie.js";
const cookie = csrfToken();

const submitButton = document.querySelector('.submit');
const formId = Number(window.location.href.charAt(window.location.href.length-1));
const questions = document.querySelectorAll('.row');
const container = document.querySelector('.container');




const addSubmission = async (event) =>{

    const requestBody = {
        formId: formId,
        userId: 1
    }
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...cookie
        },
        body: JSON.stringify(requestBody)
    }
    const response = await fetch(`/api/form/formSubmission`, options);

    if (response.status === 201) {
        console.log('201 formSubmission')
        response.json()
            .then(handleSubmission)
    }
    else{
        alert("Error in form found client-side");
    }

}
submitButton.addEventListener('click', addSubmission);


const addAnswers = async (event) =>{
    let textInput='', numberInput=null, listOfInput='', questionId=null;
    let errorsFound=false;
    const submissionID =  Number(document.querySelector('.submissionId').innerHTML);
    console.log(submissionID)

    for (const question of questions){
        questionId=Number(question.querySelector('.questionId').innerHTML);
        if(question.querySelector("input").type==='text'){
            textInput= question.querySelector("input").value;
            const requestBody = {
                formSubmissionId: submissionID,
                questionId: questionId,
                userAnswerText: textInput
            }
            console.log('getting ...cookie')
            const options = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    ...cookie
                },
                body: JSON.stringify(requestBody)
            }
            console.log('done getting ...cookie')
            const response = await fetch(`/api/form/textSubmission`, options);
            if (response.status === 201) {
                console.log('201 textSubmission')
            }
            else{
                errorsFound=true;
                alert("Error in form found client-side");
            }
        } else if (question.querySelector("input").type==='number'){
            numberInput=question.querySelector("input").value
            const requestBody = {
                formSubmissionId: submissionID,
                questionId: questionId,
                userAnswerNumeric: numberInput
            }
            const options = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    ...cookie
                },
                body: JSON.stringify(requestBody)
            }
            const response = await fetch(`/api/form/numericSubmission`, options);
            if (response.status === 201) {
                console.log('201 numericSubmission')
            }
            else{
                errorsFound=true;
                alert("Error in form found client-side");
            }
        } else if (question.querySelector("input").type==='checkbox'){
            question.querySelectorAll("input").forEach(checkbox => {
                if (checkbox.checked){
                    listOfInput+=`"${checkbox.id}", `
                }
            })
            listOfInput = listOfInput.substring(0, listOfInput.length-2);
            const requestBody = {
                formSubmissionId: submissionID,
                questionId: questionId,
                userAnswerChoices: [
                    listOfInput
                ]
            }
            const options = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    ...cookie
                },
                body: JSON.stringify(requestBody)
            }
            const response = await fetch(`/api/form/listSubmission`, options);
            if (response.status === 201) {
                console.log('201 listSubmission')
            }
            else{
                errorsFound=true;
                alert("Error in form found client-side");
            }
        } else {
            question.querySelectorAll("input").forEach(radio => {
                if (radio.checked){
                    textInput=radio.id;
                } else {textInput=''}
            })
            const requestBody = {
                formSubmissionId: submissionID,
                questionId: questionId,
                userAnswerText: textInput
            }
            const options = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    ...cookie
                },
                body: JSON.stringify(requestBody)
            }
            const response = await fetch(`/api/form/radioSubmission`, options);
            if (response.status === 201) {
                console.log('201 radioSubmission')
            }
            else{
                errorsFound=true;
                alert("Error in form found client-side");
            }
        }
    }
    if (!errorsFound){
        container.innerHTML=`
                 <h1>Submission Succesful</h1>
                 `;
    }
}
function handleSubmission(submissionId){
    container.innerHTML+=`
    <div class="submissionId invisible">${submissionId}</div>
    `;
    addAnswers();
}

sortTable()




// source: https://www.w3schools.com/howto/howto_js_sort_table.asp
// edited to conform to the projects needs
function sortTable() {
    var table, rows, switching, i, x, y, shouldSwitch;
    table = document.querySelector('.userTable');
    switching = true;
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.querySelector('.rows').querySelectorAll('.row');
        // Loop through all rows
        for (i = 0; i < (rows.length - 1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare,
            one from current row and one from the next: */
            x = rows[i].querySelector('.questionOrder');
            y = rows[i + 1].querySelector('.questionOrder');
            // Check if the two rows should switch place:
            if (x.innerHTML > y.innerHTML) {
                // If so, mark as a switch and break the loop:
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch) {
            /* If a switch has been marked, make the switch
            and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}