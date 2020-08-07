package br.com.selecao.indra.api.security;

public class SecurityConstants {
	public static final String SECRET = "desafio";
	public static final String TOKEN_PREFIX = "token ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "users/sign-up";
	public static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // um dia em milisegundos
}
