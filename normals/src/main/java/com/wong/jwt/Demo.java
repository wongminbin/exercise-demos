package com.wong.jwt;

import java.security.Key;
import java.security.KeyPair;

import org.junit.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * create by: HuangZhiBin
 * 2019年1月25日 下午5:45:48
 * 
 * JSON WEB TOKEN
 */
public class Demo {

	@Test
	public void test() {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		
		String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
		
		System.out.println(jws);
		
		Jws<Claims> parseJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jws);
		
		System.out.println(parseJws.getBody().getSubject());
	}
	
	@Test
	public void testRsa() {
		KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
		Key privateKey = keyPair.getPrivate();
		Key publicKey = keyPair.getPublic();
		
		String jws = Jwts.builder().setSubject("Joe").signWith(privateKey, SignatureAlgorithm.RS256).compact();
		System.out.println(jws);
		
		Jws<Claims> parseJws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jws);
		
		System.out.println(parseJws.getBody().getSubject());
	}
	
}
