package com.example.marketplace.authentification;

import com.example.marketplace.Configuration.JwtService;
import com.example.marketplace.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.marketplace.Entities.Role.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = null;
        if (request.getRole()==acheteur){
            user = User.builder()
                    .nomUser(request.getName())
                    .emailUser(request.getEmail())
                    .numdetelUser(request.getPhoneNumber())
                    .passwordUser(passwordEncoder.encode(request.getPassword()))
                    .role(acheteur)
                    .build();
            repository.save(user);
        }
        else if (request.getRole()==vendeur){
            user = User.builder()
                    .nomUser(request.getName())
                    .emailUser(request.getEmail())
                    .numdetelUser(request.getPhoneNumber())
                    .passwordUser(passwordEncoder.encode(request.getPassword()))
                    .role(vendeur)
                    .build();
            repository.save(user);
        }
        else if (request.getRole()==admin){
            user = User.builder()
                    .nomUser(request.getName())
                    .emailUser(request.getEmail())
                    .numdetelUser(request.getPhoneNumber())
                    .passwordUser(passwordEncoder.encode(request.getPassword()))
                    .role(admin)
                    .build();
            repository.save(user);
        }
        else if (request.getRole()==moderateur){
            user = User.builder()
                    .nomUser(request.getName())
                    .emailUser(request.getEmail())
                    .numdetelUser(request.getPhoneNumber())
                    .passwordUser(passwordEncoder.encode(request.getPassword()))
                    .role(moderateur)
                    .build();
            repository.save(user);
        }
        else if (request.getRole()==livreur){
            user = User.builder()
                    .nomUser(request.getName())
                    .emailUser(request.getEmail())
                    .numdetelUser(request.getPhoneNumber())
                    .passwordUser(passwordEncoder.encode(request.getPassword()))
                    .role(acheteur)
                    .build();
            repository.save(user);
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmailUser(request.getEmail());
        var jwtToken = jwtService.generateToken(user.get());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
