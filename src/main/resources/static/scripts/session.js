

function toggleModal(modalId){
    const modal = document.getElementById(modalId);
    if(modal){
        modal.classList.toggle('hidden');
        modal.classList.toggle('flex');
    }
}

const modalToggles = document.querySelectorAll('[data-modal-toggle]');
modalToggles.forEach((toggle) => {
    toggle.addEventListener('click', function() {
        const modalId = this.dataset.modalToggle;
        toggleModal(modalId);
    });
});

const modalCloseButtons = document.querySelectorAll('[data-modal-hide]');
modalCloseButtons.forEach((button) => {
    button.addEventListener('click', function() {
        const modalId = this.dataset.modalHide;
        toggleModal(modalId);
    })
});

window.addEventListener('keydown', (event) => {
    if(event.key === 'Escape'){
        const modals = document.querySelectorAll('.fixed[tabindex]');
        modals.forEach((modal) => {
            if(!modal.classList.contains('hidden')){
                modal.classList.add('hidden');
                modal.classList.remove('flex');
            }
        })
    }
})



const addSessionBtn = document.getElementById('addSessionForm');

addSessionBtn.addEventListener('submit', function(e) {
    e.preventDefault();

    const formData = new FormData(this);

    fetch('/sessions/add', {
        method:'POST',
        body: formData,
        headers: {
            'X-Requested-With': 'XMLHttpRequest'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            updateSessionGrid(data);
        })
        .catch((error) => {
            console.log('Error: ' , error)
        });
});


function updateSessionGrid(session){
    const sessionsGrid = document.getElementById("sessionsGrid");

    // Create the main session card div
    const sessionCard = document.createElement("div");
    sessionCard.className = "max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700";

    // Add the image
    const imageLink = document.createElement("a");
    imageLink.href = "#";
    const img = document.createElement("img");
    img.src = "https://flowbite.s3.amazonaws.com/docs/gallery/square/image.jpg"; // Use session-specific image if available
    img.className = "h-auto max-w-full rounded-lg";
    img.alt = ""; // Provide a meaningful alt attribute
    imageLink.appendChild(img);

    // Create the content div
    const contentDiv = document.createElement("div");
    contentDiv.className = "p-5";

    // Session title
    const titleLink = document.createElement("a");
    titleLink.href = "#";
    const title = document.createElement("h5");
    title.className = "mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white";
    title.textContent = session.name; // Session name
    titleLink.appendChild(title);

    // Session description
    const description = document.createElement("p");
    description.className = "mb-3 font-normal text-gray-700 dark:text-gray-400";
    description.textContent = session.description; // Session description

    // Session author (example: "By: Abdisalam Kadir")
    const author = document.createElement("p");
    author.className = "mb-3 font-normal text-gray-700 dark:text-gray-400";
    author.textContent = "By: " + session.author; // Author information, adjust as necessary

    // Date and cost (adjust these elements according to your session data)
    const dateSpan = document.createElement("span");
    dateSpan.className = "col-span-2 py-2.5 px-5 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700";
    dateSpan.textContent = "FEB 23, 3:30 PM PEST"; // Adjust to use session-specific date

    // Add elements to content div
    contentDiv.appendChild(titleLink);
    contentDiv.appendChild(description);
    contentDiv.appendChild(author);
    contentDiv.appendChild(dateSpan);

    // Assuming there's more to add like cost and a 'Going' button, continue appending as needed...

    // Assemble the session card
    sessionCard.appendChild(imageLink);
    sessionCard.appendChild(contentDiv);

    // Append the new session card to the sessions grid
    sessionsGrid.appendChild(sessionCard);

}
