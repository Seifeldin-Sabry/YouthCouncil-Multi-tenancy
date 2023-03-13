export class SubThemeCard {
    constructor(subtheme) {
        this.subtheme = subtheme;
        this.element = this.render();
    }

    render() {
        const listItem = document.createElement('li');
        listItem.classList.add('subtheme-list-item');
        listItem.innerHTML = `
            <li th:class="subtheme-item-${this.subtheme.id}">
							<div class="card subtheme-card">
								<div class="card-body">
									<h5 class="card-title">${this.subtheme.subThemeName}</h5>
									<div class="d-flex justify-content-between">
										<button type="button" class="btn btn-primary edit-subtheme-btn"
												th:data-bs-toggle="modal" th:data-bs-target="#editSubThemeModal${this.subtheme.id}"
										>
											<i class="bi bi-pencil-square"></i> Edit
										</button>
										<button type="button" class="btn btn-danger delete-subtheme-btn" data-subtheme-id="${this.subtheme.id}">
											<i class="bi bi-trash"></i> Delete
										</button>
									</div>
								</div>
							</div>

							<div class="modal subtheme-modal fade" th:id="editSubThemeModal${this.subtheme.id}" tabindex="-1"
								 th:aria-labelledby="editSubThemeModalLabel${this.subtheme.id}" aria-hidden="true"
							>
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" th:id="editSubThemeModalLabel${this.subtheme.id}">Edit Subtheme</h5>
											<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<form>
												<div class="mb-3">
													<label for="subthemeName" class="form-label">Subtheme Name</label>
													<input type="text" class="form-control" th:id="'subthemeName'+${this.subtheme.id}"
														   th:value="${this.subtheme.subThemeName}" />
												</div>
											</form>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
											<button type="button" class="btn btn-primary save-subtheme-changes-btn"
													th:data-subtheme-id="${this.subtheme.id}"
											>Save Changes
											</button>
										</div>
									</div>
								</div>
							</div>
			</li>
        `
        return listItem;
    }

    getElement() {
        return this.element;
    }
}

customElements.define('subtheme-card', SubThemeCard, {extends: 'li'});
