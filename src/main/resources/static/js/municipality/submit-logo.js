import {csrfToken} from "../cookie.js";
const image = document.querySelector('#image');
const logoSubmitButton = document.getElementById('submitLogo')
const logo = document.getElementById('logo')

const submitLogo = async () => {
    const formData = new FormData();
    for (let i = 0; i < image.files.length; i++) {
        formData.append('images', image.files[i]);
    }

    const options = {
        method: 'POST',
        headers: {
            ...csrfToken()
        },
        body: formData
    }
    console.log(options)
    const response = await fetch(`/api/municipalities/logo`, options);
    if (response.ok){
        console.log('success')
        handleLogo(await response.json())
        return
    }
}

function handleLogo(municipality){
    logo.src=municipality.logo
}

logoSubmitButton.addEventListener('click', submitLogo)

