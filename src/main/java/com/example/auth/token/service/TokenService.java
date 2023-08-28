package com.example.auth.token.service;

import com.example.auth.token.dto.TokenDto;
import com.example.auth.token.entity.Token;
import com.example.auth.token.mapper.TokenMapper;
import com.example.auth.token.repository.TokenRepository;
import com.example.auth.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {

    private final TokenRepository tokenRepository;
    private final TokenMapper tokenMapper;

    public void revokeAllUserTokens(User user){
        var validTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if(validTokens.isEmpty()){
            return;
        }
        validTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        } );
        tokenRepository.saveAll(validTokens);
    }

    public Token saveUserToken(User savedUser, String jwtToken, Date expireDate) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .revoked(false)
                .expired(false)
                .expireDate(expireDate)
                .build();
     return  tokenRepository.save(token);
    }

    public Optional<Token> findByToken(String jwt) {
        return tokenRepository.findByToken(jwt);
    }

    public void save(Token storedToken) {
        tokenRepository.save(storedToken);
    }

    public TokenDto toDto(Token token){
        return tokenMapper.toDto(token);
    }
}
