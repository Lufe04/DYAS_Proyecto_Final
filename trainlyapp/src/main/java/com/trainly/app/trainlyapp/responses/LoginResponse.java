package com.trainly.app.trainlyapp.responses;

public class LoginResponse {
    private String message;
    private String redirect;

    // Constructor
    public LoginResponse(String message, String redirect) {
        this.message = message;
        this.redirect = redirect;
    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
