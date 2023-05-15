import {csrfToken} from "../cookie.js";
const removeButtons = document.querySelectorAll('.removeButton')
const banButtons = document.querySelectorAll('.banButton')
const reports = document.querySelectorAll('.report');
const municipality = window.location.href.substring(7, window.location.href.indexOf('.'));
// console.log(municipality)

const removeIdea = async (event) => {
    let button =event.target;
    const callForIdeaId = button.parentElement.parentElement.parentElement.parentElement.dataset.callId;

    const ideaId = button.dataset.ideaId
    const options = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        }
    }
    console.log(options)

    const response = await fetch(`/api/call-for-ideas/${callForIdeaId}/ideas/${ideaId}/delete`, options);
    if (response.status===204){
        for (const report of reports) {
            if (report.dataset.ideaId === ideaId){
                report.remove();
            }
        }
    }
}

const banUser = async (event) => {
    let button =event.target;
    let membershipId;
    const ideaId = button.parentElement.parentElement.parentElement.parentElement.parentElement.dataset.ideaId;

    const membershipsFull = button.dataset.userId;
    const membershipsId = membershipsFull.match(/[(]+[^)]+[(]+/g);
    // console.log(membershipsId)
    let memberships = membershipsFull.match(/[(]+[^()@]+[)]+/g);
    //console.log(button.dataset.userId.match(/Membership.*/))
    for (let i = 0; i < memberships.length; i++) {
        const membership = memberships[i];
        const municipalityMembership = membership.substring(membership.indexOf('name=')+5,membership.indexOf(', logo'))
        // console.log(municipalityMembership, ' = ', municipality)
        if (municipalityMembership.toLowerCase() === municipality.toLowerCase()){
            const membershipsIdCurrent = membershipsId[i];
            membershipId=membershipsIdCurrent.substring(membershipsIdCurrent.indexOf('id=')+3,membershipsIdCurrent.indexOf(','))
            console.log(membershipId)
        }

    }

    const options = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({
            ban: true
        })
    }
    console.log(options)

    const response = await fetch(`/api/memberships/${membershipId}/ban`, options);
    if (response.status===200){
        alert('User was banned')
    }
    if (response.status===400){
        alert('Moderator can only ban users, ban did not go through')
    }
}
for (const banButton of banButtons) {
    banButton.addEventListener('click', banUser)
}

for (const removeButton of removeButtons){
    removeButton.addEventListener('click', removeIdea);
}

