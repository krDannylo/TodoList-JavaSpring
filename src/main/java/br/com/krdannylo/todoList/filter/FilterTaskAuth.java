package br.com.krdannylo.todoList.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.krdannylo.todoList.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Autowired
  IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    var servletPath = request.getServletPath();
    if (!servletPath.startsWith("/tasks/")) {
      filterChain.doFilter(request, response);
      return;
    }

    var authorization = request.getHeader("Authorization");
    if (authorization == null || !authorization.startsWith("Basic ")) {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header not found");
      return;
    }
    try {
      var authEncoded = authorization.substring("Basic".length()).trim();
      byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

      var authString = new String(authDecoded);

      String[] credentials = authString.split(":");
      String username = credentials[0];
      String password = credentials[1];

      var user = this.userRepository.findByUsername(username);
      if (user == null) {
        response.sendError(401);
        return;
      }

      var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
      if (passwordVerify.verified == false) {
        response.sendError(401);
        return;
      }

      request.setAttribute("userId", user.getId());
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      response.sendError(401);
    }
  }
}