package com.phong.demo.service;

import com.phong.demo.model.dto.ContactFormDTO;

public interface IEmailService {
    /**
     * Gửi email cảm ơn đến người dùng đã điền form liên hệ
     * @param to Địa chỉ email người nhận
     * @param name Tên người nhận
     * @param subject Tiêu đề email
     * @param content Nội dung tin nhắn
     * @return true nếu gửi thành công, false nếu thất bại
     */
    boolean sendThankYouEmail(String to, String name, String subject, String content);
    
    /**
     * Gửi email thông báo đến admin khi có người dùng điền form liên hệ
     * @param contactFormDTO Đối tượng chứa thông tin người dùng đã điền
     * @return true nếu gửi thành công, false nếu thất bại
     */
    boolean sendNotificationToAdmin(ContactFormDTO contactFormDTO);
} 