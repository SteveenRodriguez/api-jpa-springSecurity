package com.platzimarket.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static  final String KEY = "ap1steveen";

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) //envia nombre usuario
                .setIssuedAt(new Date()) // se envia fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // fecha de expiración
                .signWith(SignatureAlgorithm.HS256, KEY) // tipo de codificación que se va a utilizar
                .compact(); // construye y serializa el JWT
    }

    // método para validar un token
    public Boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(extractUsermame(token)) && !isTokenExpired(token);
    }

    //obtenemos el Username de la petición
    public String extractUsermame(String token) {
        return getClaims(token).getSubject(); // en el subject() esta el usuario de la petición
    }

    //
    public Boolean isTokenExpired(String token) {
        return getClaims(token)
                .getExpiration()// obtenemos la fecha de expiración
                .before(new Date());// se compara la fecha de expiración con la fecha actual
    }

    // retorna los objetos dentro del JWT
    private Claims getClaims(String token) {
         return Jwts
                 .parser() // parseamos la firma con la KEY
                 .setSigningKey(KEY) // seteamos la KEY y verifica que la firma sea correcta
                 .parseClaimsJws(token)
                 .getBody();// obtenemos el body del token separando cada uno de los objetos
    }
}
