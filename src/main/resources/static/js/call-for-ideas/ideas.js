import {csrfToken} from "../cookie.js";
const submitButton = document.getElementById('submitIdea');
const modal = document.getElementById('callForIdeaModal');
const callForIdeaId= document.getElementById('callForIdeaId').innerText;


const errorToast = document.querySelector('.error-toast');
const errorToastBody = errorToast.querySelector('.toast-body');

const successToast = document.querySelector('.success-toast');
const successToastBody = successToast.querySelector('.toast-body');

const ideaContainer=document.querySelector('.ideaContainer')
const images = document.querySelector('#images');

const filterSelect = document.getElementById('subThemeFilter')
const maxLength=100;


const filterOnSubTheme = async (event) => {
    const subThemeIdea = filterSelect.value;
    const options = {
        method: 'GET',
        headers: {
            'Accept-Type': 'application/json',
            ...csrfToken()
        }
    }
    //console.log(options)
    const response = await fetch(`/api/call-for-ideas/${callForIdeaId}/ideas/sub-theme/${subThemeIdea}`, options);
    ideaContainer.innerHTML=''
    response.json().then((ideas) => {
        for (let idea of ideas){
            const date = new Date(idea.dateCreated)
            console.log(idea)
            const dateValues =[
                date.getDate(),
                (date.getMonth()+1),
                date.getFullYear(),
                date.getHours(),
                date.getMinutes()
            ]
            if (idea.content.length>maxLength){
                ideaContainer.innerHTML+=`
    <div class="row mb-2">
        <div class="col-md-12 col-lg-5 ms-4 me-auto">
            <div class="card shadow-sm">
                <div class="card-header">
                    <div class="d-flex justify-content-start">
                        <p class="card-text h5" >${idea.creator.username}</p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p class="card-text text-muted">${dateValues[0]}/${dateValues[1]}/${dateValues[2]}, ${dateValues[3]}:${dateValues[4]}</p>
                        <div class="bg-info rounded-3 p-1">
                            <i class="bi bi-tag"></i>
                            <span >${idea.subTheme.subThemeName}</span>
                        </div>
                        <div>
                            <button class="btn btn-outline-secondary text-danger like-btn"
                            data-idea-uuid="${idea.uuid}"
                            data-liked="idea.liked"
                            >👍 <span class="like-count">${idea.likers.length}</span></button>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <p class="card-text"
                    >${idea.content.substring( 0, maxLength) + '...'}</p>
                </div>
            </div>
        </div>
    </div>
`
            }
            else {
                ideaContainer.innerHTML+=`
    <div class="row mb-2">
        <div class="col-md-12 col-lg-5 ms-4 me-auto">
            <div class="card shadow-sm">
               <div class="card-header">
                    <div class="d-flex justify-content-start">
                        <p class="card-text h5" >${idea.creator.username}</p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p class="card-text text-muted">${dateValues[0]}/${dateValues[1]}/${dateValues[2]}, ${dateValues[3]}:${dateValues[4]}</p>
                        <div class="bg-info rounded-3 p-1">
                            <i class="bi bi-tag"></i>
                            <span >${idea.subTheme.subThemeName}</span>
                        </div>
                        <div>
                            <button class="btn btn-outline-secondary text-danger like-btn"
                            data-idea-uuid="${idea.uuid}"
                            data-liked="${idea.liked}"
                            >👍 <span class="like-count">${idea.likers.length}</span></button>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <p class="card-text"
                    >${idea.content}</p>
                </div>
            </div>
        </div>
    </div>
`
            }
        }
        addEventListeners()
        for (let child of ideaContainer.children){
            const likeButton = child.querySelector('.like-btn');
            const liked = likeButton.dataset.liked
            if (liked==='true'){
                likeButton.classList.add('btn-primary')
            }
        }
    })
}
filterSelect.addEventListener('change', filterOnSubTheme)

const likeIdea = async (event) => {
    let button =event.target;
    if (event.target.classList.contains('like-count')){
        button = event.target.parentNode;
    }

    const id = button.dataset.ideaId
    console.log(button.dataset)
    const options = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({
            like: button.dataset.liked
        })
    }
    console.log(options)
    const response = await fetch(`/api/ideas/${id}/like`, options);
    if (button.classList.contains('btn-primary')){
        button.classList.remove('btn-primary')
        let likes =  Number(button.children[0].innerHTML)
        likes-=1;
        button.children[0].innerHTML=likes
    }
    else {
        button.classList.add('btn-primary')
        let likes =  Number(button.children[0].innerHTML)
        likes+=1;
        button.children[0].innerHTML=likes
    }
    addEventListeners()
}
addEventListeners()

function addEventListeners(){
    const likeButtons = document.querySelectorAll('.like-btn');
    for (let likeButton of likeButtons){
        likeButton.addEventListener('click', likeIdea);
    }
}

function showError(key, value) {
    const divInput = document.querySelector(`.${key}-err`);
    const error = document.querySelector(`.${key}-invalid-feedback`);
    error.textContent = value;
    divInput.classList.add('is-invalid');
}

function hideAllErrors() {
    const errorContainers = document.querySelectorAll('.validation-container');
    errorContainers.forEach((errorContainer) => {
        const error = errorContainer.querySelector('.validation');
        if (error === null) {
            errorContainer.textContent = '';
        } else if (error.tagName !== 'SELECT') {
            error.value = '';
        } else {
            error.selectedIndex = 0;
        }
        errorContainer.classList.remove('is-invalid');
    });
}



const submitIdea= async (event) =>{
    const descriptionIdea = document.getElementById('description').value;
    const subThemeIdea = document.getElementById('subTheme').value;
    const formData = new FormData();
    formData.append('description', descriptionIdea);
    formData.append('subTheme', subThemeIdea);
    for (let i = 0; i < images.files.length; i++) {
        formData.append('images', images.files[i]);
    }
    console.log(descriptionIdea);
    console.log(subThemeIdea);

    const options = {
        method: 'POST',
        headers: {
            ...csrfToken()
        },
        body: formData
    }
    console.log(options)
    // alert(callForIdeaId)
    const response = await fetch(`/api/call-for-ideas/${callForIdeaId}/ideas`, options);
    if (response.status === 201) {
        hideAllErrors();
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        bootstrapErrorToast.hide();
        let bootstrapModal = bootstrap.Modal.getInstance(modal);
        bootstrapModal.hide();
        const bootstrapSuccessToast = bootstrap.Toast.getOrCreateInstance(successToast);
        successToastBody.textContent = 'Idea created successfully';
        bootstrapSuccessToast.show();
        handleAddedIdea(await response.json())
        return;
    }
    if (response.status === 403) {
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        errorToastBody.textContent = 'Unauthorized, make sure you are logged in and have the correct permissions';
        bootstrapErrorToast.show();
        return;
    }
    if (response.status === 400) {
        const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        const error = await response.json();
        console.log('error: ')
        console.log(error);
        for (const [key, value] of Object.entries(error)) {
            console.log('key: ')
            console.log(key)
            console.log('value: ')
            console.log(value)
            showError(key, value);
        }
        bootstrapErrorToast.show();
        return;

    }
    errorToastBody.textContent = 'Something went wrong while processing your request';
    const bootstrapErrorToast = bootstrap.Toast.getOrCreateInstance(errorToast);
    bootstrapErrorToast.show();


}
function handleAddedIdea(idea){
    const username = document.getElementById('username').innerText;
    let ideaDate = new Date();
    console.log(ideaDate.toLocaleString("en-BE"));
    console.log(ideaContainer);
    console.log(idea.content);
    console.log(idea.subTheme.subThemeName);
    if (idea.content.length>maxLength){
        ideaContainer.innerHTML+=`
    <div class="row mb-2">
        <div class="col-md-12 col-lg-5 ms-4 me-auto">
            <div class="card shadow-sm">
                <div class="card-header">
                    <div class="d-flex justify-content-start">
                        <p class="card-text h5" >${username}</p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p class="card-text text-muted">${ideaDate.toLocaleString("en-BE")}</p>
                        <div class="bg-info rounded-3 p-1">
                            <i class="bi bi-tag"></i>
                            <span >${idea.subTheme.subThemeName}</span>
                        </div>
                        <div>
                            <button class="btn btn-outline-secondary text-danger like-btn"
                            data-idea-id="${idea.id}"
                            data-liked="false"
                            >👍 <span class="like-count">0</span></button>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <p class="card-text"
                    >${idea.content.substring( 0, maxLength) + '...'}</p>
                    <img class="d-block w-100" alt="ideaImage"
                        src="${idea.image}"
                    />
                </div>
            </div>
        </div>
    </div>
`
    }
    else {
        ideaContainer.innerHTML+=`
    <div class="row mb-2">
        <div class="col-md-12 col-lg-5 ms-4 me-auto">
            <div class="card shadow-sm">
               <div class="card-header">
                    <div class="d-flex justify-content-start">
                        <p class="card-text h5" >${username}</p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p class="card-text text-muted">${ideaDate.toLocaleString("en-BE")}</p>
                        <div class="bg-info rounded-3 p-1">
                            <i class="bi bi-tag"></i>
                            <span >${idea.subTheme.subThemeName}</span>
                        </div>
                        <div>
                            <button class="btn btn-outline-secondary text-danger like-btn"
                            data-idea-id="${idea.id}"
                            data-liked="false"
                            >👍 <span class="like-count">0</span></button>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <p class="card-text"
                    >${idea.content}</p>
                    <img class="d-block w-100" alt="ideaImage"
                        src="${idea.image}"
                    />
                </div>
            </div>
        </div>
    </div>
`
    }

    document.getElementById('description').value='';
    addEventListeners()
}
submitButton.addEventListener('click', submitIdea);