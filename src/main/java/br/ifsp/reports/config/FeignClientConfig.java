package br.ifsp.reports.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignClientConfig implements RequestInterceptor {

  @Autowired
  HttpServletRequest request;

  @Override
  public void apply(RequestTemplate template) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null) {
      template.header("Authorization", authHeader);
    }
  }

}
