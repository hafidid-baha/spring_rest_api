package agh.hafid.com.payroll.controllers;

import agh.hafid.com.payroll.helpers.AuthRequest;
import agh.hafid.com.payroll.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public String generateToken(@RequestBody AuthRequest request) throws Exception {
        try {
            // trying to authenticate the user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),request.getPassword()
            ));
        }catch (Exception ex){
            throw new Exception("invalidate username or password");
        }

        // generate user token
        return jwtUtil.generateToken(request.getUsername());
    }
}
