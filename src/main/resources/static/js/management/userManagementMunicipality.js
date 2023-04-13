import {csrfToken} from "../cookie.js";

const users = document.querySelectorAll('.user')
const promoteBtns = document.querySelectorAll('.promote')
const banBtns = document.querySelectorAll('.ban-btn')

const errorToast = document.querySelector('.toast');
const moderators = {};
const banned = {};

const state = {}

users.forEach(user => {
    let membershipId = user.dataset.membershipId;
    let promoteBtn = user.querySelector('.promote')
    let banBtn = user.querySelector('.ban-btn')
    state[membershipId] = {
        promoteBtn,
        isPromoted: promoteBtn.dataset.isPromoted === 'true',
        banBtn: user.querySelector('.ban-btn'),
        isBanned: banBtn.dataset.isBanned === 'true',
        userRole: user.querySelector('.user-role')
    }
    if (state[membershipId].isPromoted) {
        moderators[membershipId] = user;
    }
    if (state[membershipId].isBanned) {
        banned[membershipId] = user;
    }
})

console.log(state)

promoteBtns.forEach(btn => {
    btn.addEventListener('click', promoteOrDemote)
})

banBtns.forEach(btn => {
    btn.addEventListener('click', banOrUnban)
})

async function promoteOrDemote(event) {
    const membershipId = event.target.dataset.membershipId;
    const membership = state[membershipId];
    const {promoteBtn, isPromoted, userRole} = membership;

    const options = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({
            promote: !isPromoted
        })
    }
    const response = await fetch(`/api/memberships/${membershipId}/promote`, options);
    if (!response.ok) {
        const bootStrapToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        bootStrapToast.show();
        return;
    }
    if (isPromoted) {
        membership.isPromoted = false;
        promoteBtn.textContent = 'Promote to Moderator';
        promoteBtn.classList.remove('btn-danger');
        promoteBtn.classList.add('btn-success');
        moderators[membershipId] = membership;
        userRole.textContent = 'User';

    } else {
        membership.isPromoted = true;
        promoteBtn.textContent = 'Demote Moderator';
        promoteBtn.classList.remove('btn-success');
        promoteBtn.classList.add('btn-danger');
        delete moderators[membershipId];
        userRole.textContent = 'Youth Council Moderator';
    }
}


async function banOrUnban(event) {
    const membershipId = event.target.dataset.membershipId;
    const membership = state[membershipId];
    const {banBtn, isBanned} = membership;

    const options = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({
            ban: !isBanned
        })
    }
    const response = await fetch(`/api/memberships/${membershipId}/ban`, options);
    if (!response.ok) {
        const bootStrapToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        bootStrapToast.show();
        return;
    }
    if (isBanned) {
        membership.isBanned = false;
        banBtn.textContent = 'Ban';
        banBtn.classList.remove('btn-success');
        banBtn.classList.add('btn-danger');
        banned[membershipId] = membership;
    } else {
        membership.isBanned = true;
        banBtn.textContent = 'Unban';
        banBtn.classList.remove('btn-danger');
        banBtn.classList.add('btn-success');
        delete banned[membershipId];
    }
}
