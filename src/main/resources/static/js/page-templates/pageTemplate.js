import {PageTemplate} from "../components/page-templates/PageTemplate.js";

const state = {}
const pageTemplateId = document.querySelector('.page-template-id').dataset.pageTemplateId

const componentList = document.querySelector('.container-list')
const initialElements = [...document.querySelectorAll('.element')]
let currentElementIndex = initialElements.length

const addElementBtn = document.querySelector('.add-element-btn')
const addElementDropdown = document.querySelector('.add-element-dropdown')

const savePageTemplateBtn = document.querySelector('#save-btn')
const deleteCheckedElementsBtn = document.querySelector('#delete-selected-btn')

const enableOrDisableSaveBtn = (enable = true) => {
    savePageTemplateBtn.disabled = !enable
}

const enableDeleteBtn = () => {
    deleteCheckedElementsBtn.disabled = false
}

function disableButtonIfNoCheckedElements() {
    const checkedElements = [...document.querySelectorAll('.form-check-input:checked')]
    if (checkedElements.length === 0) {
        deleteCheckedElementsBtn.disabled = true
    }
}

const addToState = (element) => {
    /**
     * @type {HTMLInputElement}
     */
    const checkbox = element.querySelector('.form-check-input')
    console.log(checkbox)
    checkbox.addEventListener('change', function () {
        console.log(this.checked)
        console.log("here")
        if (this.checked) {
            enableDeleteBtn()
            return
        }
        disableButtonIfNoCheckedElements()
    });
    state[element.id] = {
        element
    }
}

initialElements.forEach((element, index) => {
    addToState(element, index)
})

console.log(state)

const addElement = () => {
    const typeChosen = addElementDropdown.value
    console.log(typeChosen)
    const index = currentElementIndex++
    const element = new PageTemplate(typeChosen, index)
    console.log(element.element)
    addToState(element.element, index)
    componentList.appendChild(element.element)
}


addElementBtn.addEventListener('click', addElement)
deleteCheckedElementsBtn.addEventListener('click', () => {
    const checkedElements = [...document.querySelectorAll('.form-check-input:checked')]
    checkedElements.forEach((element) => {
        console.table(state)
        element = element.closest('.element')
        console.log(element.id)
        const elementToDelete = state[element.id].element
        elementToDelete.remove()
        delete state[element.id]
    })
    disableButtonIfNoCheckedElements()
    enableOrDisableSaveBtn()
})

savePageTemplateBtn.addEventListener('click', () => {
    const elements = [...document.querySelectorAll('.element')]

    enableOrDisableSaveBtn(false)
}