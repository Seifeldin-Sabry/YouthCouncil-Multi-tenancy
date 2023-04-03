import {csrfToken} from "../cookie.js";

const errorToast = document.querySelector('.toast');

const followBtns = document.querySelectorAll('.follow-btn');
const likeBtns = document.querySelectorAll('.like-btn');

const actionPoints = document.querySelectorAll('.action-point');
const state = {}

actionPoints.forEach(actionPoint => {
    const actionPointId = actionPoint.getAttribute('data-action-point-id')
    const followCountEl = actionPoint.querySelector('.follow-count');
    const likeCountEl = actionPoint.querySelector('.like-count');
    const followBtn = actionPoint.querySelector('.follow-btn');
    const isFollowed = followBtn.dataset.followed === 'true';
    const likeBtn = actionPoint.querySelector('.like-btn');
    const isLiked = likeBtn.dataset.liked === 'true';

    state[actionPointId] = {
        followCountEl,
        likeCountEl,
        isFollowed,
        isLiked,
        likeBtn,
        followBtn
    };
})


followBtns.forEach(btn => {
    btn.addEventListener('click', followOrUnfollow);
})

likeBtns.forEach(btn => {
    btn.addEventListener('click', likeOrUnlike);
})


async function followOrUnfollow(event) {
    const actionPointId = event.target.dataset.actionPointId;
    const actionPoint = state[actionPointId];
    const {followCountEl, isFollowed, followBtn} = actionPoint;

    const options = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({
            follow: !actionPoint.isFollowed
        })
    }
    const response = await fetch(`/api/action-points/${actionPointId}/follow`, options);
    if (!response.ok) {
        const bootStrapToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        bootStrapToast.show();
        return;
    }
    if (isFollowed) {
        actionPoint.isFollowed = false;
        followCountEl.innerText = +followCountEl.innerText - 1;
        followBtn.textContent = 'Follow';
        followBtn.classList.remove('btn-danger');
        followBtn.classList.add('btn-outline-primary');
    } else {
        actionPoint.isFollowed = true;
        followCountEl.innerText = +followCountEl.innerText + 1;
        followBtn.textContent = 'Unfollow';
        followBtn.classList.remove('btn-outline-primary');
        followBtn.classList.add('btn-danger');
    }
}

async function likeOrUnlike(event) {
    const actionPointId = event.target.dataset.actionPointId;
    const actionPoint = state[actionPointId];
    const {likeCountEl, isLiked, likeBtn} = actionPoint;

    const options = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            ...csrfToken()
        },
        body: JSON.stringify({
            like: !actionPoint.isLiked
        })
    }
    const response = await fetch(`/api/action-points/${actionPointId}/like`, options);
    if (!response.ok) {
        const bootStrapToast = bootstrap.Toast.getOrCreateInstance(errorToast);
        bootStrapToast.show();
        return;
    }
    if (isLiked) {
        actionPoint.isLiked = false;
        likeCountEl.innerText = +likeCountEl.innerText - 1;
        likeBtn.classList.remove('btn-primary');
    } else {
        actionPoint.isLiked = true;
        likeCountEl.innerText = +likeCountEl.innerText + 1;
        likeBtn.classList.add('btn-primary');
    }
}

