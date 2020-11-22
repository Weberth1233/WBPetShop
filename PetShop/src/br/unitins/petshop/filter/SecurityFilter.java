package br.unitins.petshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unitins.petshop.model.Funcionario;


/*Assim vai percorrer todas as paginas no filter*/
@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/faces/*"})
public class SecurityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SecurityFilter Iniciado.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		/*chain.doFilter(request, response);
		return;*/

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url = httpServletRequest.getRequestURI();
		System.out.println(url);
		if(url.equals("/PetShop/faces/login.xhtml")) {

			chain.doFilter(request, response);
			return;
		}
		HttpSession session = httpServletRequest.getSession(false);

		Funcionario funcionario = null;
		if(session != null) {
			funcionario = (Funcionario) session.getAttribute("funcionarioLogado");
		}
		if(funcionario == null) {
			((HttpServletResponse) response).sendRedirect("/PetShop/faces/login.xhtml");
		}else {
			chain.doFilter(request, response);
			return;
		}
	}
	@Override
	public void destroy() {
	}
}
