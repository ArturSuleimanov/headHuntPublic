document.addEventListener("DOMContentLoaded", () => {
    const button = document.getElementById("falling-nav-button");
    const sideBar = document.getElementById("side-bar");

    button.addEventListener("click", () => {
        toggleTwoClasses(sideBar, "is-visible", "is-hidden", 300);
    });
});

function toggleTwoClasses(element, first, second, timeOfAnimation) {
    if (!element.classList.contains(first)) {
        element.classList.add(first);
        element.classList.remove(second);
    } else {
        element.classList.add(second);
        window.setTimeout(function() {
            element.classList.remove(first);
        }, timeOfAnimation);
    }
}