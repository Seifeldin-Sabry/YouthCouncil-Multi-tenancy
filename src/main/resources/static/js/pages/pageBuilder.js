import {csrfToken} from "../cookie.js";

const typeSelector = document.getElementById('elementSelect');
const imageInput = document.getElementById("image");
const imageInputContainer = document.getElementById("imageInputContainer");
const textInputContainer = document.getElementById("textInputContainer");
const domainURL = window.location.href.toString();
const pageName = domainURL.substring(domainURL.lastIndexOf('/')+1, domainURL.length)
const content = document.getElementById("content");
console.log(pageName)

const postElement = async (formData) =>{
    const options = {
        method: 'POST',
        headers: {
            ...csrfToken()
        },
        body: formData
    }
    console.log(options)
    return await fetch(`/api/pages/${pageName}/elements`, options);
}

async function addElement() {
    // Get the selected element type and customization options.
    var elementType = document.getElementById("elementSelect").value;
    var elementTextInput = document.getElementById("elementText");

    if (typeSelector.value === 'image') {
        var elementSrc = document.getElementById("image");
    } else {
        var elementText = elementTextInput.value;
    }


    var elementReturned;

    // Create the new element.
    var newElement;
    const formData = new FormData();
    const order = content.childNodes.length
    switch (elementType) {
        case "heading":
            newElement = document.createElement("h1");
            newElement.textContent = elementText;
            newElement = addMoveUpAndDown(newElement)
            formData.append('text', elementText);
            formData.append('elementType', elementType);
            formData.append('order', order);
            const response1 = await postElement(formData)
            elementTextInput.value=''
            response1.json().then(element => {
                newElement.id=element.id
                newElement.dataset.order= element.order
                newElement.dataset.type = elementType
            })
            break;
        case "list":
            newElement = document.createElement("div");
            const listElement = document.createElement("ul");
            newElement.appendChild(listElement)
            var listItem = document.createElement("li");
            listItem.textContent = elementText;
            listElement.appendChild(listItem);
            newElement = addMoveUpAndDown(newElement)
            newElement = addListManipulation(newElement)
            formData.append('text', elementText);
            formData.append('elementType', elementType);
            formData.append('order', order);
            const response2 = await postElement(formData)
            elementTextInput.value=''
            response2.json().then(element => {
                newElement.id=element.id
                newElement.dataset.order= element.order
                newElement.dataset.type = elementType
            })
            break;
        case "paragraph":
            newElement = document.createElement("p");
            newElement.textContent = elementText;
            newElement = addMoveUpAndDown(newElement)
            formData.append('text', elementText);
            formData.append('elementType', elementType);
            formData.append('order', order);
            const response3 = await postElement(formData)
            elementTextInput.value=''
            response3.json().then(element => {
                newElement.id=element.id
                newElement.dataset.order= element.order
                newElement.dataset.type = elementType
            })
            break;
        case "image":
            newElement = document.createElement("div");
            for (let i = 0; i < elementSrc.files.length; i++) {
                formData.append('src', elementSrc.files[i]);
            }
            formData.append('src', elementSrc);
            formData.append('elementType', elementType);
            formData.append('order', order);
            const response = await postElement(formData)
            response.json().then(element => {
                let newImage = document.createElement("img");
                newImage.style=`display: inline-block;
                                max-width:300px;
                                max-height:200px;
                                width: auto;
                                height: auto;`
                newImage.src = element.src;
                newElement.appendChild(newImage)
                elementSrc.value=''
                newElement.id=element.id
                newElement.dataset.order= element.order
                newElement.dataset.type = elementType
                })
            newElement = addMoveUpAndDown(newElement)
            break;
        default:
            alert("Invalid element type.");
            return;
    }

    // Append the new element to the page.
    content.appendChild(newElement);
}



function addMoveUpAndDown(newElement){
    console.log(newElement)
    // Add move up/down buttons to the new element.
    var moveUpButton = document.createElement("button");
    moveUpButton.textContent = "Move Up";
    moveUpButton.onclick = function () {
        moveElementUp(newElement);
    };
    newElement.appendChild(moveUpButton);

    var moveDownButton = document.createElement("button");
    moveDownButton.textContent = "Move Down";
    moveDownButton.onclick = function () {
        moveElementDown(newElement);
    };
    newElement.appendChild(moveDownButton);
    return newElement
}

const postListItem = async (formData, listId) =>{
    const options = {
        method: 'PATCH',
        headers: {
            ...csrfToken()
        },
        body: formData
    }
    console.log(options)
    return await fetch(`/api/pages/${pageName}/elements/${listId}/list-item`, options);
}

// A function to add a list item.
function addListItem(event) {
    // Get the text content.
    var elementText = document.getElementById("elementText");

    // Find the last list in the content container and add a new list item.
    var parentdiv = event.target.parentNode.parentNode;
    console.log(parentdiv)
    var list = parentdiv.firstElementChild;
    var listId= parentdiv.id
    const formData = new FormData();
    formData.append("item", elementText.value)
    postListItem(formData, listId)

    var listItem = document.createElement("li");
    listItem.textContent = elementText.value;
    list.appendChild(listItem);
    elementText.value=''
}

const removeLastItem = async (id) =>{
    const options = {
        method: 'DELETE',
        headers: {
            ...csrfToken()
        }
    }
    console.log(options)
    await fetch(`/api/pages/${pageName}/elements/${id}/remove-last-item`, options);
}

// A function to remove a list item.
function removeListItem(event) {
    // The content container.
    const parentDiv = event.target.parentNode.parentNode
    console.log(parentDiv)

    // Find the last list in the content container and remove the last list item.
    var list = parentDiv.firstElementChild
    console.log(list)
    if (list.childNodes.length > 0) {
        list.removeChild(list.lastElementChild);
        removeLastItem(parentDiv.id)
    }
}

function addListManipulation(newElement){
    const newDiv = document.createElement('div')
    newElement.appendChild(newDiv)
    const newLi = document.createElement('button')
    newLi.classList.add('btn', 'btn-secondary', 'addListItem')
    newLi.innerText='Add list item'
    newLi.onclick = addListItem;
    newDiv.appendChild(newLi)
    const remLi = document.createElement('button')
    remLi.classList.add('btn', 'btn-secondary', 'removeListItem')
    remLi.innerText='Remove last list item'
    remLi.onclick = removeListItem;
    newDiv.appendChild(remLi)
    return newElement
}

// Attach the functions to the buttons.
document.getElementById("addButton").onclick = addElement;

const updateOrder = async (element) =>{
    const formData = new FormData();
    formData.append("order", element.dataset.order)
    formData.append("type", element.dataset.type)
    const options = {
        method: 'PATCH',
        headers: {
            ...csrfToken()
        },
        body: formData
    }
    console.log(options)
    await fetch(`/api/pages/${pageName}/elements/${element.id}/order`, options);
}

function moveElementUp(element) {
    var previousSibling = element.previousElementSibling;
    if (previousSibling) {
        element.parentNode.insertBefore(element, previousSibling);
        const numberOrderCurrent=Number(element.dataset.order)
        const numberOrderPrevious=Number(previousSibling.dataset.order)
        element.dataset.order=String(numberOrderCurrent-1)
        previousSibling.dataset.order=String(numberOrderPrevious+1)
        updateOrder(element)
        updateOrder(previousSibling)
    }
}

function moveElementDown(element) {
    var nextSibling = element.nextElementSibling;
    if (nextSibling) {
        element.parentNode.insertBefore(nextSibling, element);
        const numberOrderCurrent=Number(element.dataset.order)
        const numberOrderNext=Number(nextSibling.dataset.order)
        element.dataset.order=String(numberOrderCurrent+1)
        nextSibling.dataset.order=String(numberOrderNext-1)
        updateOrder(element)
        updateOrder(nextSibling)
    }
}

typeSelector.onchange = handleSelectChange

function handleSelectChange(event){
    if (typeSelector.value==='image'){
        imageInputContainer.removeAttribute('hidden')
        textInputContainer.setAttribute('hidden', 'true')
    }
    else {
        imageInputContainer.setAttribute('hidden', 'true')
        textInputContainer.removeAttribute('hidden')
    }

}

const moveButtonUp = document.querySelectorAll('.up')
const moveButtonDown = document.querySelectorAll('.down')
const addListButtons = document.querySelectorAll('.addListItem')
const removeListButtons = document.querySelectorAll('.removeListItem')
// setup page for ycadmin controls on reload
for (const button of moveButtonDown) {
    const element = button.parentNode
    button.onclick = function () {moveElementDown(element)}
}
for (const button of moveButtonUp) {
    const element = button.parentNode
    button.onclick = function () {moveElementUp(element)}
}

for (const button of removeListButtons) {
    button.addEventListener('click', removeListItem)
}

for (const button of addListButtons) {
    button.addEventListener('click', addListItem)
}


