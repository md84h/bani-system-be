package com.example.lungi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;

    private String type = "Bearer";

    private Long id;

    private String username;

    private Long mobile;

    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, Long mobile, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.mobile = mobile;
        this.roles = roles;

    }
}
