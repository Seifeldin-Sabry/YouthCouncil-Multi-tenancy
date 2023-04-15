export class ActionPoint {
    #element;

    /**
     Represents an action point with its associated data.
     @param {Object} actionPoint - An object containing the following properties:
     @param {number} actionPoint.id - The unique identifier for the action point.
     @param {string} actionPoint.title - The title of the action point.
     @param {string} actionPoint.uuid - The unique identifier for the action point cryptography.
     @param {string} actionPoint.description - The description of the action point.
     @param {number} actionPoint.followCount - The number of users following the action point.
     @param {number} actionPoint.likeCount - The number of users liking the action point.
     @param {Date} actionPoint.dateCreated - The date the action point was created.
     @param {Object} actionPoint.subTheme - An object containing the following properties:
     @param {number} actionPoint.subTheme.id - The unique identifier for the sub-theme.
     @param {string} actionPoint.subTheme.subThemeName - The name of the sub-theme.
     @param {boolean} actionPoint.subTheme.active - Indicates whether the sub-theme is active or not.
     @param {Array<Object>} actionPoint.actionPointProposals - An array of objects containing the following properties:
     @param {Array<String>} actionPoint.images - An array of objects containing the following properties:
     @param {number} actionPoint.actionPointProposals.id - The unique identifier for the proposal.
     @param {string} actionPoint.actionPointProposals.proposal - The proposal text.
     @param {string} actionPoint.actionPointProposals.status - The status of the proposal.
     @param {number} iter - The index of the action point in the list of action points.
     */
    constructor(actionPoint, iter) {
        const actionPointItem = document.createElement('div');
        actionPointItem.classList.add(`row`, 'mb-4', 'action-point');
        actionPointItem.dataset.actionPointId = `${actionPoint.id}`;
        actionPointItem.innerHTML = `
            <div class="col-md-8 col-lg-6 mx-auto">
				<div class="card shadow-sm">
					<div class="card-header">
						<p class="card-text">${actionPoint.dateCreated}</p>
						<div class="d-flex justify-content-between">
							<div class="bg-info rounded-3 p-1">
								<i class="bi bi-tag"></i>
								<span>${actionPoint.subTheme.subThemeName}</span>
							</div>
							<div>
								<i class="bi bi-people-fill"></i>
								<span class="follow-count">${actionPoint.followCount}</span>
							</div>
						</div>
						<h3 class="card-title mb-0">${actionPoint.title}</h3>
						<div class="d-flex justify-content-end">
							<a href="/action-points/${actionPoint.uuid}">
								<button class="btn btn-primary"
										type="button"
								>
									Details
								</button>
							</a>
						</div>
					</div>
					<div class="card-body">
						<div class="carousel" data-interval="false" id="carouselIndicator${iter}">
							<div class="carousel-inner">
                                ${actionPoint.images.map((image, index) => `
                                <div class="carousel-item ${index === 0 ? 'active' : ''}">
                                    <img class="d-block w-100" alt="'Picture ' + ${index + 1}"
                                         src="${image}"
                                    />
                                </div>
                                `)}
							</div>
							<a class="carousel-control-prev" data-bs-slide="prev"
							   role="button"
							   href="#carouselIndicator${iter}"
							>
								<span aria-hidden="true" class="carousel-control-prev-icon"></span>
								<span class="visually-hidden">Previous</span>
							</a>
							<a class="carousel-control-next" data-bs-slide="next"
							   role="button"
							   href="#carouselIndicator${iter}"
							>
								<span aria-hidden="true" class="carousel-control-next-icon"></span>
								<span class="visually-hidden">Next</span>
							</a>
						</div>
						<hr>
						<div class="d-flex justify-content-between">
							<div>
								<button class="btn follow-btn btn-outline-primary"
										data-action-point-id="${actionPoint.id}"
										data-followed="${actionPoint.followed}"
								>${!actionPoint.followed ? 'Follow' : 'Unfollow'}
								</button>
							</div>
							<div>
								<button class="btn btn-outline-secondary text-danger like-btn"
										data-action-point-id="${actionPoint.id}"
										data-liked="${actionPoint.liked}"
								>👍 <span class="like-count">${actionPoint.likeCount}</span></button>
							</div>
						</div>
					</div>
					<div class="card-footer">
						<p class="card-text">${actionPoint.description}</p>
					</div>
				</div>
            </div>
        `
        this.#element = actionPointItem
    }


    get element() {
        return this.#element;
    }
}

customElements.define('action-point', ActionPoint, {extends: 'div'});
