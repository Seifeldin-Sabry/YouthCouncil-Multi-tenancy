import {csrfToken} from "../cookie.js";

const changeElectionPhaseButton = document.getElementById('changeMunicipality');

const patchElectionPhase = async (event) => {

    const options = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        }
    }
    console.log(options)
    const response = await fetch(`/api/municipalities/change-election-phase`, options);
    if (response.ok) {
        console.log('ElectionPhase changed')
    }
    else {
        console.log('ElectionPhase not changed')
    }
}

changeElectionPhaseButton.addEventListener('click', patchElectionPhase)