document.addEventListener('DOMContentLoaded', function() {
    // Image Preloading
    const images = [
        '/img/banner/hero-slideshow-image-1.jpg',
        '/img/banner/hero-slideshow-image-2.jpg',
        '/img/banner/hero-slideshow-image-3.jpg'
    ];

    images.forEach(src => {
        const img = new Image();
        img.src = src;
    });

    // Carousel Elements
    const carousel = document.getElementById('mainCarousel');
    const items = carousel.querySelectorAll('.carousel-item');
    const indicators = carousel.querySelectorAll('.carousel-indicators button');
    let currentIndex = 0;
    let isTransitioning = false;
    let autoSlideInterval;

    // Update slide indicators
    function updateIndicators(index) {
        indicators.forEach((indicator, i) => {
            indicator.classList.toggle('active', i === index);
        });
    }

    // Show specific slide with fade effect
    function showSlide(index) {
        if (isTransitioning) return;
        isTransitioning = true;

        const currentItem = items[currentIndex];
        const nextItem = items[index];

        // Update indicators
        updateIndicators(index);

        // Prepare next slide
        nextItem.style.visibility = 'visible';
        nextItem.classList.add('active');

        // Trigger reflow
        nextItem.offsetHeight;

        // Fade in next slide
        nextItem.style.opacity = '1';

        // Fade out current slide
        currentItem.style.opacity = '0';

        setTimeout(() => {
            currentItem.classList.remove('active');
            currentItem.style.visibility = 'hidden';
            currentIndex = index;
            isTransitioning = false;
        }, 800);
    }

    // Auto slide functionality
    function startAutoSlide() {
        stopAutoSlide(); // Clear any existing interval
        autoSlideInterval = setInterval(() => {
            const nextIndex = (currentIndex + 1) % items.length;
            showSlide(nextIndex);
        }, 5000);
    }

    function stopAutoSlide() {
        if (autoSlideInterval) {
            clearInterval(autoSlideInterval);
            autoSlideInterval = null;
        }
    }

    // Initialize carousel state
    items.forEach((item, index) => {
        if (index !== currentIndex) {
            item.style.visibility = 'hidden';
            item.style.opacity = '0';
        }
    });

    // Start auto slide
    startAutoSlide();

    // Event Listeners
    // Indicators click handler
    indicators.forEach((indicator, index) => {
        indicator.addEventListener('click', () => {
            if (index !== currentIndex) {
                stopAutoSlide();
                showSlide(index);
                startAutoSlide();
            }
        });
    });

    // Previous/Next button handlers
    const prevButton = carousel.querySelector('.carousel-control-prev');
    const nextButton = carousel.querySelector('.carousel-control-next');

    prevButton.addEventListener('click', () => {
        const prevIndex = (currentIndex - 1 + items.length) % items.length;
        if (prevIndex !== currentIndex) {
            stopAutoSlide();
            showSlide(prevIndex);
            startAutoSlide();
        }
    });

    nextButton.addEventListener('click', () => {
        const nextIndex = (currentIndex + 1) % items.length;
        if (nextIndex !== currentIndex) {
            stopAutoSlide();
            showSlide(nextIndex);
            startAutoSlide();
        }
    });

    // Pause on hover
    carousel.addEventListener('mouseenter', stopAutoSlide);
    carousel.addEventListener('mouseleave', startAutoSlide);
});