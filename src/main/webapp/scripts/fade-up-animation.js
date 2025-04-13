// Functionality for fading up transition

document.addEventListener('DOMContentLoaded', function () {
const faders = document.querySelectorAll('.fade-up');

const observer = new IntersectionObserver(entries => {
entries.forEach(entry => {
if (entry.isIntersecting) {
entry.target.classList.add('active');
}
});
}, {
threshold: 0.2
});

faders.forEach(fade => observer.observe(fade));
});
