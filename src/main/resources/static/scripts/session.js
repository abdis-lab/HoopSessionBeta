

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

const dropdownBtn = document.querySelectorAll('.dropdownButton');

// Add click event listener to the dropdown button
dropdownBtn.forEach((button) => {
    button.addEventListener('click', function() {
        // Get the related dropdown menu for this button
        let dropdownMenu = button.nextElementSibling;

        dropdownMenu.classList.toggle('block');
        dropdownMenu.classList.toggle('hidden');

    });
})
