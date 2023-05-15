import {csrfToken} from "../cookie.js";

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
        }
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