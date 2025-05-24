document.addEventListener('DOMContentLoaded', function() {
    const navbar = document.querySelector('.navbar');
    let lastScrollTop = 0;

    // Xử lý scroll
    window.addEventListener('scroll', function() {
        let scrollTop = window.pageYOffset || document.documentElement.scrollTop;
        
        // Thêm class scrolled khi cuộn xuống
        if (scrollTop > 25) {
            navbar.classList.add('scrolled');
        } else {
            navbar.classList.remove('scrolled');
        }

        // Ẩn/hiện navbar khi cuộn
        if (scrollTop > lastScrollTop && scrollTop > 50) {
            // Cuộn xuống
            navbar.classList.add('hide-nav');
        } else {
            // Cuộn lên
            navbar.classList.remove('hide-nav');
        }
        
        lastScrollTop = scrollTop;
    });

    // Xử lý dropdown menu
    document.querySelectorAll('.dropdown').forEach(function(drop) {
        const menu = drop.querySelector('.dropdown-menu');
        let timeout;

        drop.addEventListener('mouseenter', function() {
            clearTimeout(timeout);
            if(menu) menu.classList.add('show');
        });

        drop.addEventListener('mouseleave', function(e) {
            // Kiểm tra xem chuột có đang ở trong menu không
            const toElement = e.relatedTarget;
            if (!menu.contains(toElement)) {
                timeout = setTimeout(() => {
                    menu.classList.remove('show');
                }, 100); // Delay 100ms để người dùng có thể di chuyển chuột vào menu
            }
        });

        // Xử lý khi chuột rời khỏi menu
        menu.addEventListener('mouseleave', function(e) {
            const toElement = e.relatedTarget;
            if (!drop.contains(toElement)) {
                timeout = setTimeout(() => {
                    menu.classList.remove('show');
                }, 100);
            }
        });
    });

    // Xử lý mobile menu
    const navbarToggler = document.querySelector('.navbar-toggler');
    const navbarCollapse = document.querySelector('.navbar-collapse');
    
    if (navbarToggler && navbarCollapse) {
        navbarToggler.addEventListener('click', function() {
            navbarCollapse.classList.toggle('show');
        });

        // Đóng menu khi click bên ngoài
        document.addEventListener('click', function(event) {
            if (!navbarCollapse.contains(event.target) && !navbarToggler.contains(event.target)) {
                navbarCollapse.classList.remove('show');
            }
        });
    }
}); 