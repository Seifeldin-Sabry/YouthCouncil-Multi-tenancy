const searchButton = document.getElementById('search-btn');
const searchInput = document.getElementById('search');
const titles = document.querySelectorAll('.card-title');

searchButton.addEventListener('click', searchActionPoint)

function searchActionPoint(){
    const regex = new RegExp(searchInput.value.toString().toLowerCase());
    titles.forEach(title => {
        const titleString=title.innerText.toString().toLowerCase();
        const container = title.parentNode.parentNode.parentNode.parentNode;
        console.log(regex)
        console.log(titleString)
        if(regex.test(titleString)){
            if (container.hasAttribute('hidden')){
                container.removeAttribute('hidden');
            }
        }
        else {
            container.setAttribute('hidden', true);
        }
    })
}