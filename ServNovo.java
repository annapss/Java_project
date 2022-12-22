
package br.com.site.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
 
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
@MultipartConfig( //Configuração das imagens recebidas
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
	            System.out.println("Essa é a pasta: " + uploadFilePath);
	            fileName = nome + ".jpg";
	            part.write(uploadFilePath + "webapp\\" + fileName);
        	}
        	i++;
        }
        
		System.out.println("Cientista: " + nome);
        System.out.println("Texto: " + texto);
        System.out.println("Área: " + categoria);
        
        String resultado = Cadastro.escrever(nome, categoria, texto);
 
        response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String htmlRespone = "<html>";
        htmlRespone += "<h2>O nome da mulher é: " + nome + "<br/>";
        htmlRespone += "<h2>A área de conhecimento dela é: " + categoria + "<br/>"; 
        htmlRespone += "A história dela: " + texto + "<br/></h2>";
        htmlRespone += "<h1 style=\"color: red\">" + resultado + "</h1>";
        htmlRespone += "</html>";
        
        // return response
        writer.println(htmlRespone);
        
        
        
        
	}

}
