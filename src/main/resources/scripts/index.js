const toggleElements = document.querySelectorAll('[data-drawer-toggle]');
const hideElements = document.querySelectorAll('[data-drawer-hide]');


const toggleDrawer = (id, action) => {
    const drawer = document.getElementById(id);
    if (!drawer) return;

    if (action === 'toggle') {
        drawer.classList.toggle('-translate-x-full');
    } else if (action === 'hide') {
        drawer.classList.add('-translate-x-full');
    }
};

toggleElements.forEach(btn => {
    btn.addEventListener('click', () => toggleDrawer(btn.getAttribute('data-drawer-toggle'), 'toggle'));
});

hideElements.forEach(btn => {
    btn.addEventListener('click', () => toggleDrawer(btn.getAttribute('data-drawer-hide'), 'hide'));
});