// Add class to trigger animation one by one with a delay
window.addEventListener('load', function () {
    var videoCards = document.querySelectorAll('.card');
    videoCards.forEach(function (card, index) {
        setTimeout(function () {
            card.classList.add('fade-in');
        }, 300 * index);
    });
});
