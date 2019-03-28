package com.revature.controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.models.Principal;
import com.revature.util.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@WebFilter("/*")
public class FilterController extends HttpFilter {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FilterController.class);

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		setAccessControlHeaders(resp);

		// 1. Get the HTTP header named "Authorization"
		String header = req.getHeader(JwtConfig.HEADER);
		System.out.println(header);

		// 2. Validate the header values and check the prefix
		if (header == null || !header.startsWith(JwtConfig.PREFIX)) {
			log.info("Request originates from an unauthenticated origin");
			System.out.println("inside null");
			// 2.1: If there is no header, or one that we provided, then go to the next step
			// in the filter chain (target servlet)
			chain.doFilter(req, resp);
			return;
		}
		// 3. Get the token
		String token = header.replaceAll(JwtConfig.PREFIX, "");
		
		
		try {

			// 4. Validate the token
			Claims claims = Jwts.parser()
					.setSigningKey(JwtConfig.signingKey)
					.parseClaimsJws(token)
					.getBody();

			// 5. Obtain the principal/subject stored in the JWT
			Principal principal = new Principal();
			principal.setUserId(Integer.parseInt(claims.getId()));
			principal.setUsername(claims.getSubject());
			principal.setPassword(claims.get("password", String.class));

			// 6. Attach an attribute to the request indicating information about the
			// principal
			req.setAttribute("principal", principal);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		// 7. Send the request to the next filter in the chain (target servlet)
		chain.doFilter(req, resp);
	}

	private void setAccessControlHeaders(HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "Content-type, Authorization");
		resp.setHeader("Access-Control-Expose-Headers", "Authorization");
	}

}