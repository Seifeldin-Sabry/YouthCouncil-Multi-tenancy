import {csrfToken} from "../cookie.js";

const postcodeButton = document.getElementById('postcodeButton');
const postcodeInput = document.getElementById('postcode');
const postcodeOutput = document.getElementById('resultPostcode');
const currentMunName = document.getElementById('currentMunName');
const domainURL = window.location.href;
console.log(domainURL)


const checkOnPostcode = async (event) => {
    const postcode = postcodeInput.value;
    if (postcode > 9999) {
        postcodeOutput.innerText = 'Postcode cant be bigger than 9999!'
        return
    }
    const options = {
        method: 'GET',
        headers: {
            'Accept-Type': 'application/json',
            ...csrfToken()
        }
    }
    console.log(options)
    const response = await fetch(`/api/municipalities/${postcode}/postcode`, options);
    if (response.status === 302) {
        const municipalityReturned = await response.json();
        console.log(municipalityReturned)
        console.log(currentMunName.innerText)
        const newURL = domainURL.replace(currentMunName.innerText.toString().toLowerCase(), municipalityReturned.name.toString().toLowerCase())
        console.log(newURL)
        postcodeOutput.innerHTML = `${municipalityReturned.name} has a platform with that postcode! Go to <a href="${newURL.replace('/join-youth-council', '')}">${municipalityReturned.name.toString().toLowerCase()}</a> to discover the platform of this municipality!`
        return
    }
    if (response.status === 404) {
        postcodeOutput.innerText = 'No municipality has a platform with that postcode!'
        return
    }
}

postcodeButton.addEventListener('click', checkOnPostcode);
