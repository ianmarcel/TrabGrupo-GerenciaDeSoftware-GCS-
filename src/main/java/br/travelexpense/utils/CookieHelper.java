package br.travelexpense.utils;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;

public class CookieHelper {
	
	public static final String ID_EMPRESA_COOKIE = "EMPRESA_ID";
	
	public static Long getIdValue(Cookie[] cookies) {
		List<Cookie> cookiesList = Arrays.asList(cookies);
		return Long.parseLong(
				cookiesList.stream().filter(c -> c.getName().equals(ID_EMPRESA_COOKIE)).map(Cookie::getValue).findFirst().orElse("-1"));
	}

}
