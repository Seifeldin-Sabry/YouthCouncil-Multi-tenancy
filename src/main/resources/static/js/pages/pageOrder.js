


// source: https://stackoverflow.com/questions/9778899/how-to-order-divs-by-id-in-javascript
// edited to conform to the projects needs
var mylist = document.getElementById('content');
console.log(mylist)
var divs = mylist.children;
console.log(divs)
var listitems = [];
for (i = 0; i < divs.length; i++) {
    console.log(i)
    listitems.push(divs.item(i));
}
listitems.sort(function(a, b) {
    console.log(a)
    console.log(b)
    var compA = a.dataset.order;
    var compB = b.dataset.order;
    return (compA < compB) ? -1 : (compA > compB) ? 1 : 0;
});
for (i = 0; i < listitems.length; i++) {
    mylist.appendChild(listitems[i]);
}
