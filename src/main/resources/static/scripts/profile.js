const tabs = document.querySelectorAll('[data-tabs-target]');
const tabContents = document.querySelectorAll('[id]');
const dropdownButton = document.getElementById('dropdownButton');
const dropdownMenu = document.getElementById('dropdown');



dropdownButton.addEventListener('click', () => {
    dropdownMenu.classList.toggle('hidden');
})

window.addEventListener('click', (e) => {
    if(!dropdownButton.contains(e.target) && !dropdownMenu.contains(e.target)){
        dropdownMenu.classList.add('hidden');
    }
})


tabs.forEach(tab => {
    tab.addEventListener('click', () => {
        const target = document.querySelector(tab.dataset.tabTarget);
        tabContents.forEach(tapCon => {
            tabCon.classList.add('hidden');
        });

        target.classList.remove('hidden');

        tabs.forEach(t => {
            t.classList.remove('active');
            t.setAttribute('aria-selected', false);
        });

        tab.classList.add('active');
        tab.setAttribute('aria-selected', true);
    })
})
