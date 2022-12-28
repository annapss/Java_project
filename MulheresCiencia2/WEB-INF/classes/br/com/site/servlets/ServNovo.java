
package br.com.site.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import br.com.site.utils.Cadastro;

/**
 * Servlet implementation class ServNovo

*/
@MultipartConfig( //Configura��o das imagens recebidas
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
 

public class ServNovo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServNovo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String nome = request.getParameter("nome");
		String categoria = request.getParameter("categoria");
		String texto = request.getParameter("texto");
		
        String uploadFilePath = Cadastro.absolutePathStart; //request.getServletContext().getRealPath("");
        String imagemPath= "";
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        
        String fileName = null;
        //Get all the parts from request and write it to the file on server
        int i = 0;
        for (Part part : request.getParts()) { //Maneira pouco otimizada de isolar a Part da imagem
        	if (i == 3) {
	            fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); //Convers�o grande para assegurar que pega apenas o nome do arquivo independente do tipo de browser
	            String [] fileType = fileName.split("((?=\\.))");
	            fileName = nome + fileType[1];
	            imagemPath = uploadFilePath + "webapp\\images\\";
	            part.write(imagemPath + fileName);
        	}
        	i++;
        }
        
        
		System.out.println("Cientista: " + nome);
        System.out.println("Texto: " + texto);
        System.out.println("�rea: " + categoria);
        
        String resultado = Cadastro.escrever(nome, categoria, texto, fileName);
 
        response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String htmlRespone = "<html>";
        htmlRespone += "<h2>O nome da mulher �: " + nome + "<br/>";
        htmlRespone += "<h2>A �rea de conhecimento dela �: " + categoria + "<br/>"; 
        htmlRespone += "A hist�ria dela: " + texto + "<br/></h2>";
        htmlRespone += "<h1 style=\"color: red\">" + resultado + "</h1>";
        htmlRespone += "</html>";
        
        // return response
        writer.println(htmlRespone);
        
        
        
        
	}

}
