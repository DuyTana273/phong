/**
 * @param {string} type - Loại thông báo: 'success', 'error', 'warning', 'info'
 * @param {string} message - Nội dung thông báo
 * @param {number} duration - Thời gian hiển thị (mili giây), mặc định 3000ms
 */
function showToast(type, message, duration = 3000) {
    // Lấy container và template
    const container = document.querySelector('.toast-container');
    const template = document.getElementById('toast-template');
    
    if (!container || !template) {
        console.error('Toast container hoặc template không tồn tại.');
        return;
    }
    
    // Tạo một toast mới từ template
    const toast = template.cloneNode(true);
    toast.classList.remove('d-none');
    toast.id = '';
    
    // Thiết lập kiểu và icon dựa trên loại thông báo
    toast.classList.add(type);
    const icon = toast.querySelector('.toast-icon');
    
    // Thiết lập icon và tiêu đề theo loại
    switch (type) {
        case 'success':
            icon.classList.add('fa-solid', 'fa-circle-check');
            toast.querySelector('.toast-title').innerHTML = '<strong>Thành Công</strong>';
            break;
        case 'error':
            icon.classList.add('fa-solid', 'fa-circle-xmark');
            toast.querySelector('.toast-title').innerHTML = '<strong>Lỗi</strong>';
            break;
        case 'warning':
            icon.classList.add('fa-solid', 'fa-triangle-exclamation');
            toast.querySelector('.toast-title').innerHTML = '<strong>Cảnh Báo</strong>';
            break;
        case 'info':
            icon.classList.add('fa-solid', 'fa-circle-info');
            toast.querySelector('.toast-title').innerHTML = '<strong>Thông Tin</strong>';
            break;
        default:
            icon.classList.add('fa-solid', 'fa-circle-info');
            toast.querySelector('.toast-title').innerHTML = '<strong>Thông Báo</strong>';
    }
    
    // Đặt nội dung thông báo
    toast.querySelector('.toast-message').textContent = message;
    
    // Thêm toast vào container
    container.appendChild(toast);
    
    // Thêm lớp show để hiển thị toast
    setTimeout(() => {
        toast.classList.add('show');
    }, 10);
    
    // Thiết lập sự kiện cho nút đóng
    toast.querySelector('.close-btn').addEventListener('click', function() {
        toast.remove();
    });
    
    // Tự động xóa toast sau khoảng thời gian
    setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => {
            toast.remove();
        }, 300);
    }, duration);
} 