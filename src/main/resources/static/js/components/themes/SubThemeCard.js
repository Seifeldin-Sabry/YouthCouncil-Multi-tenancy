export class SubThemeCard {
    #element;
    constructor(subtheme, currentThemeId) {
        this.subtheme = subtheme;
        this.currentThemeId = currentThemeId;
        const listItem = document.createElement('li');
        listItem.classList.add(`subtheme-list-item`, `subtheme-item-${this.subtheme.id}`);
        listItem.innerHTML = `
                <div class="card subtheme-card">
                    <div class="card-body">
                        <h5 class="card-title">${subtheme.subThemeName}</h5>
                        <div class="d-flex justify-content-between">
                            <button type="button" class="btn btn-primary edit-subtheme-btn"
                                    data-bs-toggle="modal"
                                    data-bs-target="#editSubThemeModal${subtheme.id}"
                            >
                                <i class="bi bi-pencil-square"></i> Edit
                            </button>
                            <button type="button" class="btn btn-danger delete-subtheme-btn"
                                    data-theme-id="${currentThemeId}" data-subtheme-id="${subtheme.id}"
                            >
                                <i class="bi bi-trash"></i> Delete
                            </button>
                        </div>
                    </div>
				</div>

                <div class="modal subtheme-modal fade" id="editSubThemeModal${subtheme.id}"
								 tabindex="-1"
								 aria-labelledby="editSubThemeModalLabel${subtheme.id}" aria-hidden="true"
							>
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="editSubThemeModalLabel${subtheme.id}">
												Edit Subtheme</h5>
											<button type="button" class="btn-close" data-bs-dismiss="modal"
													aria-label="Close"
											></button>
										</div>
										<div class="modal-body">
											<div class="mb-3">
													<label for="subthemeName" class="form-label">Subtheme Name</label>
													<input type="text" class="form-control"
														   id="subthemeName-${subtheme.id}"
														   th:value="${subtheme.subThemeName}"
													/>
													<div class="invalid-feedback"
														 id="edit-subThemeName-${subtheme.id}-error"
													>

													</div>
												</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
												Cancel
											</button>
											<button type="button" class="btn btn-primary save-subtheme-changes-btn"
													data-subtheme-id="${subtheme.id}" data-theme-id="${currentThemeId}"
											>Save Changes
											</button>
										</div>
									</div>
								</div>
							</div>
        `
        this.#element = listItem
    }



    get element() {
        return this.#element;
    }
}

customElements.define('subtheme-card', SubThemeCard, {extends: 'li'});
