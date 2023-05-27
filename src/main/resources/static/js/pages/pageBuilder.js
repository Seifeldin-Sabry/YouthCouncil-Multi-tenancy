function addElement() {
    // Get the selected element type and customization options.
    var elementType = document.getElementById("elementSelect").value;
    var elementText = document.getElementById("elementText").value;
    var elementSrc = document.getElementById("elementSrc").value;

    // The content container.
    var content = document.getElementById("content");

    // Create the new element.
    var newElement;
    switch (elementType) {
        case "heading":
            newElement = document.createElement("h1");
            newElement.textContent = elementText;
            break;
        case "list":
            newElement = document.createElement("ul");
            var listItem = document.createElement("li");
            listItem.textContent = elementText;
            newElement.appendChild(listItem);
            document.getElementById("listManipulation").style.display = "block";
            break;
        case "paragraph":
            newElement = document.createElement("p");
            newElement.textContent = elementText;
            break;
        case "image":
            newElement = document.createElement("img");
            newElement.src = elementSrc;
            break;
        default:
            alert("Invalid element type.");
            return;
    }

    // Append the new element to the page.
    content.appendChild(newElement);
}

// A function to add a list item.
function addListItem() {
    // Get the text content.
    var elementText = document.getElementById("elementText").value;

    // The content container.
    var content = document.getElementById("content");

    // Find the last list in the content container and add a new list item.
    var lists = content.getElementsByTagName("ul");
    if (lists.length > 0) {
        var lastList = lists[lists.length - 1];
        var listItem = document.createElement("li");
        listItem.textContent = elementText;
        lastList.appendChild(listItem);
    }
}

// A function to remove a list item.
function removeListItem() {
    // The content container.
    var content = document.getElementById("content");

    // Find the last list in the content container and remove the last list item.
    var lists = content.getElementsByTagName("ul");
    if (lists.length > 0) {
        var lastList = lists[lists.length - 1];
        if (lastList.children.length > 0) {
            lastList.removeChild(lastList.lastChild);
        }
    }
}

// Attach the functions to the buttons.
document.getElementById("addButton").onclick = addElement;
document.getElementById("addListItem").onclick = addListItem;
document.getElementById("removeListItem").onclick = removeListItem;

function moveElementUp(element) {
    var previousSibling = element.previousElementSibling;
    if (previousSibling) {
        element.parentNode.insertBefore(element, previousSibling);
    }
}

function moveElementDown(element) {
    var nextSibling = element.nextElementSibling;
    if (nextSibling) {
        element.parentNode.insertBefore(nextSibling, element);
    }
}

// Other JavaScript code...

// Append the new element to the page.
content.appendChild(newElement);

// Add move up/down buttons to the new element.
var moveUpButton = document.createElement("button");
moveUpButton.textContent = "Move Up";
moveUpButton.onclick = function () {
    moveElementUp(newElement);
};
newElement.appendChild(moveUpButton);

var moveDownButton = document.createElement("button");
moveDownButton.textContent = "Move Down";
moveDownButton.onclick = function () {
    moveElementDown(newElement);
};
newElement.appendChild(moveDownButton);
