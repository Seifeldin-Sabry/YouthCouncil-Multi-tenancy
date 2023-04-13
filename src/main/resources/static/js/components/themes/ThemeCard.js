export class ThemeCard {
    constructor(theme) {
        this.theme = theme;
        this.element = this.createCard();
    }

    createCard() {
        const card = document.createElement('div');
        card.classList.add('card', `theme-${this.theme.id}`, 'card-theme');
        card.setAttribute('data-theme-id', this.theme.id)
        card.setAttribute('data-is-active', this.theme.active)
        card.innerHTML = `
        <div id="theme${this.theme.id}Heading" class="card-header d-flex justify-content-between">
            <h2 class="mb-0">
                <button class="btn btn-link theme-name" type="button" data-bs-toggle="collapse" data-bs-target="#theme${this.theme.id}Collapse" aria-expanded="true" aria-controls="theme${this.theme.id}Collapse">
                    ${this.theme.themeName}
                </button>
            </h2>
            <div>
                <button type="button" class="mx-auto btn btn-primary edit-theme-btn" data-bs-toggle="modal" data-bs-target="#editThemeModal${this.theme.id}">
                    <i class="bi bi-pencil-square"></i> Edit
                </button>
                <button type="button"  class="btn btn-danger delete-theme-btn ${this.theme.active ? 'btn-danger' : 'btn-success'}" data-theme-id="${this.theme.id}">
                    ${this.theme.active ? 'Deactivate' : 'Activate'}
                </button>
            </div>
        </div>
        <div id="theme${this.theme.id}Collapse" class="collapse" aria-labelledby="theme${this.theme.id}Heading" data-bs-parent="#themeAccordion">
            <div class="card-body">
                <ul class="subtheme-list list-unstyled">
                    
                </ul>
                <button id="${this.theme.id}" type="button" class="btn btn-primary add-subtheme-btn" data-bs-toggle="modal" data-bs-target="#addSubThemeModal">
                    <i class="bi bi-plus"></i> Add New Subtheme
                </button>
            </div>
        </div>
		<!--			Edit Theme Modal-->
			<div class="modal theme-modal fade" id="editThemeModal${this.theme.id}" tabindex="-1" aria-labelledby="editThemeModalLabel${this.theme.id}" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="editThemeModalLabel${this.theme.id}">Edit Theme</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
								<div class="form-floating mb-3">
								<input type="text" class="form-control" id="edit-ThemeName-${this.theme.id}"
									   placeholder="Theme name" value="${this.theme.themeName}"
								>
								<label for="editThemeName${this.theme.id}">Theme name</label>
								<div class="invalid-feedback" id="edit-themeName-${this.theme.id}-error">

								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary edit-theme-confirm-btn" data-theme-id="${this.theme.id}">Save changes</button>
						</div>
					</div>
				</div>
			</div>
        `
        return card;
    }

    getElement() {
        return this.element;
    }

}

customElements.define("theme-card", ThemeCard, {extends: "div"});
