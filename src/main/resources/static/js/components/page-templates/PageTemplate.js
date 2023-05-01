const elementTypeToImage = {
    'LIST': '/images/placeholders/list-placeholder.png',
    'TEXT': '/images/placeholders/text-placeholder.png',
    'IMAGE': '/images/placeholders/image-placeholder.png',
}


export class PageTemplate {
    #element;

    constructor(elementType, index) {
        const element = document.createElement('div')
        element.classList.add('element', 'container-element')
        element.dataset.element = elementType
        element.id = `container-${index}`

        const image = document.createElement('img')
        image.src = elementTypeToImage[elementType]
        image.classList.add('image-fluid')
        image.style.height = '100px'

        element.appendChild(image)

        const formDiv = document.createElement('div')
        formDiv.classList.add('form-check')

        const formInput = document.createElement('input')
        formInput.type = 'checkbox'
        formInput.classList.add('form-check-input')

        element.appendChild(formDiv)
        formDiv.appendChild(formInput)

        this.#element = element;
    }

    get element() {
        return this.#element;
    }
}