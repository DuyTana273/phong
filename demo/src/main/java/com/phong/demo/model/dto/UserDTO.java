package com.phong.demo.model.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

public class UserDTO implements Validator {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String fullName;
    private Date dob;
    private String gender;
    private String address;

    public UserDTO() {}

    public UserDTO(Long id, String username, String password, String phone, String email, String fullName, Date dob, String gender, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.fullName = fullName;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;

        // Validate username
        String username = userDTO.getUsername();
        if (username.trim().isEmpty()) {
            errors.rejectValue("username", "input.null");
        }

        // Validate password
        String password = userDTO.getPassword();
        if (userDTO.getId() == null) {
            if (password.trim().isEmpty()) {
                errors.rejectValue("password", "input.null", "Vui lòng nhập mật khẩu!");
            } else if (password.length() < 3 || password.length() > 20) {
                errors.rejectValue("password", "", "Mật khẩu phải từ 3 đến 20 ký tự!");
            }
        }

        // Validate email
        String email = userDTO.getEmail();
        if (email.trim().isEmpty()) {
            errors.rejectValue("email", "input.null", "Vui lòng nhập email!");
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
            errors.rejectValue("email", "", "Email phải có dạng hợp lệ, ví dụ: a@gmail.com!");
        }

        // Validate phone
        String phone = userDTO.getPhone();
        if (phone.trim().isEmpty()) {
            errors.rejectValue("phone", "input.null", "Vui lòng nhập số điện thoại!");
        } else if (!phone.matches("^\\d{2,15}$")) {
            errors.rejectValue("phone", "", "Số điện thoại phải là số và từ 2 đến 15 chữ số!");
        }

        // Validate fullName
        String fullName = userDTO.getFullName();
        if (fullName.trim().isEmpty()) {
            errors.rejectValue("fullName", "input.null");
        } else if (!fullName.matches("^([\\p{L}\\s]+)$")) {
            errors.rejectValue("fullName", "", "Họ và tên chỉ chứa ký tự chữ và không có ký tự đặc biệt hoặc số!");
        } else if (fullName.length() < 3 || fullName.length() > 50) {
            errors.rejectValue("fullName", "", "Họ và tên phải từ 3 đến 50 ký tự!");
        }

        // Validate Address
        String address = userDTO.getAddress();
        if (address.trim().isEmpty()) {
            errors.rejectValue("address", "input.null");
        }

    }
}
