import {csrfToken} from "../cookie.js";

const actionPoints = document.querySelectorAll(".action-point");
const filterDropdown = document.getElementById('subThemeFilter');
const resultContainer = document.querySelector('.action-points');
const maxLength =100

function handleFilter(actionPoints) {
    // clear previous results
    resultContainer.innerHTML = "";
    actionPoints.forEach(actionPoint => {
        console.log(actionPoint)
        console.log(actionPoint.images[0])
        if(actionPoint.description.length > maxLength){
            resultContainer.innerHTML += `
            <div class="row mb-4 action-point" data-action-point-id="${actionPoint.id}">
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
                                <a href="@{/action-points/{uuid}(uuid = ${actionPoint.uuid})}">
                                    <button class="btn btn-primary" type="button"> Details </button>
                                </a>
                            </div>                                                               
                        </div>
                        <div class="card-body">                                               
                         
                              <img class="d-block w-100" alt="Picture1" src="${actionPoint.images[0]}"/>
                            <hr>
                            <div class="d-flex justify-content-between">
                                <div>                                                      
                                    <button class="btn follow-btn"
                                            data-action-point-id="${actionPoint.id}"
                                            data-followed="${actionPoint.followed}"
                                           >Follow
                                    </button>
                               </div>
                                <div>
                                    <button class="btn btn-outline-secondary text-danger like-btn"
             
                                        data-action-point-id="${actionPoint.id}"
                                        data-liked="${actionPoint.liked}">👍 <span class="like-count" text="${actionPoint.likeCount}"></span></button>
                               </div>
                          </div>
                     </div>
                  <div class="card-footer" with="maxLength=100">
                      <p class="card-text">${actionPoint.description.substring(actionPoint.description, 0, maxLength)}</p>
                  </div>
             </div>
        </div>
   </div>          
  `
        }else{
            resultContainer.innerHTML += `
            <div class="row mb-4 action-point" data-action-point-id="${actionPoint.id}">
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
                                <a href="@{/action-points/{uuid}(uuid = ${actionPoint.uuid})}">
                                    <button class="btn btn-primary" type="button"> Details </button>
                                </a>
                            </div>                                                               
                        </div>
                        <div class="card-body">                                               
                           <img class="d-block w-100" alt="Picture1" src="${actionPoint.images[0]}"/>
                            <hr>
                            <div class="d-flex justify-content-between">
                                <div>                                                      
                                    <button class="btn follow-btn"
                                            data-action-point-id="${actionPoint.id}"
                                            data-followed="${actionPoint.followed}"
                                           >Follow
                                    </button>
                               </div>
                                <div>
                                    <button class="btn btn-outline-secondary text-danger like-btn"
             
                                        data-action-point-id="${actionPoint.id}"
                                        data-liked="${actionPoint.liked}">👍 <span class="like-count" text="${actionPoint.likeCount}"></span></button>
                               </div>
                          </div>
                     </div>
                  <div class="card-footer" with="maxLength=100">
                      <p class="card-text">${actionPoint.description}</p>
                  </div>
             </div>
        </div>
   </div>          
  `
        }
    })
}

async function filterOnSubTheme(event) {
    console.log('ENTERED FILTER FUNCTION!');
    const subtheme = filterDropdown.value;
    console.log(subtheme)

    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            ...csrfToken
        }
    };

    const response = await fetch(`/api/action-points/${subtheme}/subtheme`, options);
    const filteredActionPoints = await response.json();
    handleFilter(filteredActionPoints);
}

filterDropdown.addEventListener('change', filterOnSubTheme);