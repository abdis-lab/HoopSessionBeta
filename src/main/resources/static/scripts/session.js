

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


//
// const addSessionBtn = document.getElementById('addSessionForm');
//
// addSessionBtn.addEventListener('submit', function(e) {
//     e.preventDefault();
//
//     const formData = new FormData(this);
//
//     fetch('/sessions/add', {
//         method:'POST',
//         body: formData,
//         headers: {
//             'X-Requested-With': 'XMLHttpRequest'
//         }
//     })
//         .then(response => response.json())
//         .then(data => {
//             console.log('Success:', data);
//             updateSessionGrid(data);
//         })
//         .catch((error) => {
//             console.log('Error: ' , error)
//         });
// });
//
//
//     function updateSessionGrid(sessions) {
//         const sessionsGrid = document.getElementById("sessionGrid"); // Ensure this ID matches your HTML
//
//         // Clear existing sessions to avoid duplication
//         sessionsGrid.innerHTML = '';
//
//         // Iterate over each session
//         sessions.forEach(session => {
//             // Create the main session card div
//             const sessionCard = document.createElement("div");
//             sessionCard.className = "max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700";
//
//             // Add the image
//             const imageLink = document.createElement("a");
//             imageLink.href = "#";
//             const img = document.createElement("img");
//             img.src = session.imageUrl || "https://flowbite.s3.amazonaws.com/docs/gallery/square/image.jpg"; // Fallback to default if imageUrl is not provided
//             img.className = "h-auto max-w-full rounded-lg";
//             img.alt = session.title; // Use the session title as alt text
//             imageLink.appendChild(img);
//
//             // Create the content div
//             const contentDiv = document.createElement("div");
//             contentDiv.className = "p-5";
//
//             // Session title
//             const titleLink = document.createElement("a");
//             titleLink.href = "#";
//             const title = document.createElement("h5");
//             title.className = "mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white";
//             title.textContent = session.title; // Ensure property names match your data structure
//             titleLink.appendChild(title);
//
//             // Session description
//             const description = document.createElement("p");
//             description.className = "mb-3 font-normal text-gray-700 dark:text-gray-400";
//             description.textContent = session.description; // Ensure property names match your data structure
//
//             // Optionally add more details like date, author, etc., similar to the existing code...
//
//             // Add elements to content div
//             contentDiv.appendChild(titleLink);
//             contentDiv.appendChild(description);
//
//             // Append contentDiv to sessionCard
//             sessionCard.appendChild(imageLink);
//             sessionCard.appendChild(contentDiv);
//
//             // Append the new session card to the sessions grid
//             sessionsGrid.appendChild(sessionCard);
//         });
// }});