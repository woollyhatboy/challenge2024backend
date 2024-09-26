package com.sanofi.project.controller;

import com.sanofi.project.domain.user.*;
import com.sanofi.project.infra.security.DadosTokenJWT;
import com.sanofi.project.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/login")
public class UserControler {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationData authenticationData) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationData.email(), authenticationData.password());

        Authentication authentication =  manager.authenticate(authenticationToken);

        String tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity registerUser(@RequestBody @Valid UserData userData, UriComponentsBuilder uriBuilder) {

        User user = new User(userData);

        userRepository.save(user);

        URI uri = uriBuilder.path("/login/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body("Usuario cadastrado com sucesso!");
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);

        user.delete();

        return ResponseEntity.ok("Usuario deletado com sucesso !");
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UserUpdateData userUpdateData) {
        User user = userRepository.getReferenceById(userUpdateData.id());

        user.update(userUpdateData);

        return ResponseEntity.ok(userUpdateData);
    }
}
