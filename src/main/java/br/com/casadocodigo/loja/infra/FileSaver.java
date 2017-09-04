package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

// Quando vc anota com @Component, estamos dizendo que ele é um componente do Spring,
// uma classe que podemos injetar, existe também o @Repository, que é uma nomeclatura para DAOs
@Component
public class FileSaver {
	
	@Autowired
	private HttpServletRequest request;
	
	public String write(String baseFolder, MultipartFile file) {
		// Pega o caminho do completo do servidor
		String realPath = "C:/Users/andre";
				//request.getServletContext().getRealPath("/" + baseFolder);
		String path = realPath + "/" + baseFolder + "/" +file.getOriginalFilename();
		try {
			file.transferTo(new File(path));			
		} catch (IllegalStateException e) {
			Error error = new Error(e.getMessage());
			throw new RuntimeErrorException(error);
		} catch (IOException e) {
			Error error = new Error(e.getMessage());
			throw new RuntimeErrorException(error);
		}
		
		return baseFolder + "/" + file.getOriginalFilename();
	}

}
