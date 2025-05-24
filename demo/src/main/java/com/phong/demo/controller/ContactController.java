package com.phong.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.phong.demo.model.dto.ContactFormDTO;
import com.phong.demo.service.IEmailService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private IEmailService emailService;

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactFormDTO());
        return "home/contact";
    }

    @PostMapping("/contact/submit")
    public String submitContactForm(@Valid @ModelAttribute("contactForm") ContactFormDTO contactForm,
                                    BindingResult bindingResult,
                                    @RequestParam(value = "termsCheck", required = false, defaultValue = "false") boolean termsCheck,
                                    RedirectAttributes redirectAttributes,
                                    HttpSession session,
                                    Model model) {
        if (!termsCheck) {
            model.addAttribute("termsError", "Bạn phải đồng ý với điều khoản của chúng tôi");
        }
        
        if (bindingResult.hasErrors() || !termsCheck) {
            model.addAttribute("contactForm", contactForm);
            model.addAttribute("messageType", "error");
            model.addAttribute("message", "Có lỗi xảy ra!");
            return "home/contact";
        }

        try {
            // Gửi email cảm ơn đến người dùng
            boolean emailSent = emailService.sendThankYouEmail(
                    contactForm.getEmail(),
                    contactForm.getName(),
                    "Cảm ơn bạn đã liên hệ với chúng tôi",
                    contactForm.getMessage()
            );
            
            // Gửi email thông báo cho admin
            boolean notificationSent = emailService.sendNotificationToAdmin(contactForm);

            if (emailSent && notificationSent) {
                // Sử dụng session thay vì redirectAttributes
                session.setAttribute("SUCCESS_MESSAGE", "Cảm ơn bạn đã gửi thông tin liên hệ. Email xác nhận đã được gửi đến địa chỉ của bạn.");
            } else {
                session.setAttribute("ERROR_MESSAGE", "Không thể gửi email xác nhận. Vui lòng thử lại sau.");
            }
            return "redirect:/contact";
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            session.setAttribute("ERROR_MESSAGE", "Đã xảy ra lỗi: " + e.getMessage());
            return "redirect:/contact";
        }
    }
}