// Contact Form Validation and Submission
document.addEventListener('DOMContentLoaded', function() {
    const contactForm = document.getElementById('contactForm');
    
    if (contactForm) {
        contactForm.addEventListener('submit', function(event) {
            event.preventDefault();
            
            // Basic front-end validation
            if (validateForm()) {
                // Form is valid, you can submit or show success message
                showSuccessMessage();
                
                // For demo: Reset form after submission
                contactForm.reset();
                
                // In a real application, you would send the data to the server here
                // using fetch or another AJAX method
            }
        });
    }
    
    // Add event listeners for real-time validation feedback
    const inputs = document.querySelectorAll('.form-control, .form-select, input[type="radio"], input[type="checkbox"]');
    inputs.forEach(input => {
        input.addEventListener('blur', function() {
            validateInput(this);
        });
        
        if (input.type !== 'radio' && input.type !== 'checkbox') {
            input.addEventListener('input', function() {
                validateInput(this);
            });
        }
    });
    
    // Function to validate the entire form
    function validateForm() {
        let isValid = true;
        
        // Validate all inputs
        inputs.forEach(input => {
            if (!validateInput(input)) {
                isValid = false;
            }
        });
        
        return isValid;
    }
    
    // Function to validate a single input
    function validateInput(input) {
        // For radio buttons
        if (input.type === 'radio') {
            const name = input.getAttribute('name');
            const radioGroup = document.querySelectorAll(`input[name="${name}"]`);
            let checked = false;
            
            radioGroup.forEach(radio => {
                if (radio.checked) checked = true;
            });
            
            const radioContainer = document.querySelector('.gender-options');
            if (!checked && input.required) {
                radioContainer.classList.add('is-invalid');
                return false;
            } else {
                radioContainer.classList.remove('is-invalid');
                return true;
            }
        }
        
        // For checkbox
        if (input.type === 'checkbox') {
            if (input.required && !input.checked) {
                input.classList.add('is-invalid');
                return false;
            } else {
                input.classList.remove('is-invalid');
                return true;
            }
        }
        
        // For other inputs
        if ((input.required && !input.value) || 
            (input.type === 'email' && input.value && !isValidEmail(input.value))) {
            input.classList.add('is-invalid');
            input.classList.remove('is-valid');
            return false;
        } else if (input.value) {
            input.classList.remove('is-invalid');
            input.classList.add('is-valid');
            return true;
        }
        
        return true;
    }
    
    // Email validation
    function isValidEmail(email) {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    }
    
    // Show success message
    function showSuccessMessage() {
        // Create success alert
        const successAlert = document.createElement('div');
        successAlert.className = 'alert alert-success mt-4';
        successAlert.role = 'alert';
        successAlert.innerHTML = '<i class="fas fa-check-circle me-2"></i> Thông tin của bạn đã được gửi thành công. Chúng tôi sẽ liên hệ với bạn sớm nhất có thể.';
        
        // Insert the alert before the form
        contactForm.parentNode.insertBefore(successAlert, contactForm);
        
        // Scroll to the success message
        successAlert.scrollIntoView({ behavior: 'smooth', block: 'center' });
        
        // Remove the alert after 5 seconds
        setTimeout(() => {
            successAlert.remove();
        }, 5000);
    }
});

// Fix navbar styles for non-home pages
document.addEventListener('DOMContentLoaded', function() {
    const navbar = document.querySelector('.navbar');
    
    if (navbar) {
        navbar.classList.add('navbar-scrolled');
    }
}); 