const submitButton = document.querySelector('.submit');
const formId = Number(window.location.href.charAt(window.location.href.length-1));
const questions = document.querySelectorAll('.row');
const container = document.querySelector('.container');

submitButton.addEventListener('click', addSubmission);


function addSubmission(event){
    fetch('/api/form/formSubmission', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "formId": formId,
            "userId": 1
        })
    }).then(response => {
        if (response.status === 201) {
            response.json()
                .then(handleSubmission)
        }
        else{
            alert("Error in form found client-side");
        }
    });
}

function handleSubmission(submissionId){
    container.innerHTML+=`
    <div class="submissionId invisible">${submissionId}</div>
    `;
    addAnswers();
}

function addAnswers(){
    let textInput='', numberInput=null, listOfInput='', questionId=null;
    let errorsFound=false;
    const submissionID =  Number(document.querySelector('.submissionId').innerHTML);
    console.log(submissionID)

    for (const question of questions){
        questionId=Number(question.querySelector('.questionId').innerHTML);
        if(question.querySelector("input").type==='text'){
            textInput= question.querySelector("input").value;
            fetch('/api/form/textSubmission', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "formSubmissionId": submissionID,
                    "questionId": questionId,
                    "userAnswerText": textInput
                })
            }).then(response => {
                if (response.status === 201) {
                }
                else{
                    errorsFound=true;
                    alert("Error in form found client-side");
                }
            });
        } else if (question.querySelector("input").type==='number'){
            numberInput=question.querySelector("input").value
            fetch('/api/form/numericSubmission', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "formSubmissionId": submissionID,
                    "questionId": questionId,
                    "userAnswerNumeric": numberInput
                })
            }).then(response => {
                if (response.status === 201) {
                }
                else{
                    errorsFound=true;
                    alert("Error in form found client-side");
                }
            });
        } else if (question.querySelector("input").type==='checkbox'){
            question.querySelectorAll("input").forEach(checkbox => {
                if (checkbox.checked){
                    listOfInput+=`"${checkbox.id}", `
                }
            })
            listOfInput = listOfInput.substring(0, listOfInput.length-2);
            fetch('/api/form/listSubmission', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "formSubmissionId": submissionID,
                    "questionId": questionId,
                    "userAnswerChoices": [
                        listOfInput
                    ]
                })
            }).then(response => {
                if (response.status === 201) {
                }
                else{
                    errorsFound=true;
                    alert("Error in form found client-side");
                }
            });
        } else {
            question.querySelectorAll("input").forEach(radio => {
                if (radio.checked){
                    textInput=radio.id;
                } else {textInput=''}
            })
            fetch('/api/form/radioSubmission', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "formSubmissionId": submissionID,
                    "questionId": questionId,
                    "userAnswerText": textInput
                })
            }).then(response => {
                if (response.status === 201) {
                }
                else{
                    errorsFound=true;
                    alert("Error in form found client-side");
                }
            });
        }
    }
    if (!errorsFound){
        container.innerHTML=`
                 <h1>Submission Succesful</h1>
                 `;
    }
}

sortTable()





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