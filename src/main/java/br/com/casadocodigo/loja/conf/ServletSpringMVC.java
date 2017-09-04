package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[]{AppWebConfiguration.class, JPAConfiguration.class};
	}

	@Override
	// Mapear o servlet do spring
	protected String[] getServletMappings() {
		
		return new String[]{"/"};
	}
	
	@Override
	// Adicionado este metodo para resolver a resposta do servidor que não manda UTF-8
	// Desta forma o spring vai retornar os resultados de consulta neste formato
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		
		return new Filter[]{encodingFilter};
	}
	
	@Override
	// Esse é um registro da configuração de multipart, 
	// Multipart é uma classe para enviar arquivos no jsp
	protected void customizeRegistration(Dynamic registration) {
		
		// Quando configuramos o só com espaço, ele não quer que coloque nenhuma forma para o envio, 
		// nenhum caminho, etc. Do jeito que vier o arquivo ele vai mandar
		registration.setMultipartConfig(new MultipartConfigElement(""));;
	}

}
