package br.com.site.utils;

import java.util.Scanner; //Imports para arquivo
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.nio.file.FileSystems;
import java.io.FileWriter;
import java.io.FileReader;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.io.IOException;

public class Cadastro {
	
	static String separador = " / ";
	public static String absolutePathStart = "C:\\Users\\aluno\\Desktop\\MulheresCiencia2\\"; // preencher com o caminho absoluto da maquina q ira rodar
	static File memorias = new File(absolutePathStart + "webapp\\lista.txt");
	
	public static String escrever(String nome, String categoria, String texto, String fileName) {
		try {
			File no = new File("webapp\\lista.txt"); //----- Linha para fazer virar relativo depois
			Scanner reader = new Scanner(memorias);
			ArrayList conteudo = new ArrayList();
			boolean repetido = false;
			
			
			while(reader.hasNextLine()) {
	        	String dadosLinha = reader.nextLine();
	        	if (dadosLinha.equals("")) {
	        		 break;
	        	 }
	        	 String[] partes = dadosLinha.split(separador);

	        	 if ((nome.toLowerCase()).equals(partes[0].toLowerCase())){ //Detecta se mulheres de mesmo nome já existem no registro geral
					 repetido = true;
	        	 }
	        	 
	        	 conteudo.add(dadosLinha);
			 }
			if (!repetido) {
				conteudo.add(nome + separador + categoria + separador + texto + separador + fileName);
				Path caminhoMemorias = memorias.toPath();
				
				try {
					Files.write(caminhoMemorias, conteudo, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
				}
				catch (IOException o) {
					o.printStackTrace();
				}
				reader.close();
				
				// criarHTML(nome, categoria, texto, fileName);
				updateMateria(nome, categoria, texto, fileName);
				
				return "Sua Mulher Especial foi cadastrada!";
			}
			reader.close();
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "Erro no cadastro";
	}
	
	private static boolean criarHTML(String nome, String categoria, String texto, String fileName) {
		
		String template = "<!DOCTYPE html>\r\n"
				+ "<html lang = \"pt-br\">\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>" + nome + "</title>\r\n"
				+ "<link rel =\" folha de estilo \" href =\"css/styles.css\">\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "<p style=\"color: purple\">" + nome + " - " + categoria + "</p><br/>\r\n"
				+ texto 
				+ "<div class=\"col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2\">\r\n"
				+ "\r\n"
				+ "                <img src=\"images/" + fileName + "\" class=\"img-fluid\" alt=\"Foto de" + nome + "\" width=\"500\">\r\n"
				+ "\r\n"
				+ "              </div>";
		
		String endTemplate = "</body>\r\n"
				+ "</html>";
		File htmlNovo = new File(absolutePathStart + "webapp\\" + nome + ".html");
		ArrayList conteudo = new ArrayList();

		try {
			htmlNovo.createNewFile();
			System.out.println(htmlNovo);
			
			conteudo.add(template);
			conteudo.add(endTemplate);
			
			
			Path caminhoHtmlNovo = htmlNovo.toPath();
			Files.write(caminhoHtmlNovo, conteudo, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	private static boolean updateMateria(String nome, String categoria, String texto, String fileName) {
		System.out.println("Entrou para dar update...");
		// updating pages
		String templateBox = "<div class='container'>\r\n" 
				+ "<div class='row mb-2'>\r\n"
			    + "<div class='col-md-6'>\r\n"
			    +"		<div class='row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative'>\r\n"
			    + "			<div class='col p-4 d-flex flex-column position-static Content'>\r\n"
			    + "				<strong class='d-inline-block mb-2 fontAdds'>Uma Mulher Especial</strong>\r\n"
			    + "				<h3 class='mb-0'>" + nome +"</h3>\r\n"
			    + "				<p class='card-text mb-auto'>" + texto + "</p>\r\n"
			   + "				</p>\r\n"
			   + "				</div>\r\n"
			   + "		<div class='col-auto d-none d-lg-block'>\r\n"
			   + "			<img  width='200' height='250' src=\"images/" + fileName + "\" ></img>\r\n"

			   + "		</div>\r\n"
			   + " 	</div>\r\n"
			    + "</div>\r\n"
		        + "</div>\r\n"
               + "</div>\r\n";
        		//+ "</div>\r\n";


		if(categoria.equals("bio")) {
			System.out.println("entrei em bio");

			//String endTemplateBox = "</body>\r\n"
				//	+ "</html>";
			ArrayList conteudoBox = new ArrayList();

			File f = new File(absolutePathStart + "webapp\\Bio.html");
			Path cam = f.toPath();

			try {

				conteudoBox.add(templateBox);
				Files.write(cam, conteudoBox, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}else if(categoria.equals("astro")) {
			System.out.println("entrei em astro");

			//String endTemplateBox = "</body>\r\n"
				//	+ "</html>";
			ArrayList conteudoBox = new ArrayList();

			File f = new File(absolutePathStart + "webapp\\Astronomia.html");
			Path cam = f.toPath();

			try {

				conteudoBox.add(templateBox);
				Files.write(cam, conteudoBox, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}else if(categoria.equals("cieSau")) {
			System.out.println("entrei em cieSau");

			//String endTemplateBox = "</body>\r\n"
				//	+ "</html>";
			ArrayList conteudoBox = new ArrayList();

			File f = new File(absolutePathStart + "webapp\\CienSau.html");
			Path cam = f.toPath();

			try {

				conteudoBox.add(templateBox);
				Files.write(cam, conteudoBox, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			}catch (IOException e) {
				e.printStackTrace();
			}
		} else if(categoria.equals("cienHum")) {
			System.out.println("entrei em cienHum");


			//String endTemplateBox = "</body>\r\n"
				//	+ "</html>";
			ArrayList conteudoBox = new ArrayList();

			File f = new File(absolutePathStart + "webapp\\CieHum.html");
			Path cam = f.toPath();

			try {

				conteudoBox.add(templateBox);
				Files.write(cam, conteudoBox, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			}catch (IOException e) {
				e.printStackTrace();
			}
		} else if(categoria.equals("engen")) {

			System.out.println("entrei em engen");


			//String endTemplateBox = "</body>\r\n"
				//	+ "</html>";
			ArrayList conteudoBox = new ArrayList();

			File f = new File(absolutePathStart + "webapp\\Engen.html");
			Path cam = f.toPath();

			try {

				conteudoBox.add(templateBox);
				Files.write(cam, conteudoBox, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			}catch (IOException e) {
				e.printStackTrace();

		}  

		}else if(categoria.equals("fis")) {

			System.out.println("entrei em fis");


			//String endTemplateBox = "</body>\r\n"
				//	+ "</html>";
			ArrayList conteudoBox = new ArrayList();

			File f = new File(absolutePathStart + "webapp\\Fis.html");
			Path cam = f.toPath();

			try {

				conteudoBox.add(templateBox);
				Files.write(cam, conteudoBox, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			}catch (IOException e) {
				e.printStackTrace();

		}
	} else if(categoria.equals("qui")) {
		System.out.println("entrei em qui");


		//String endTemplateBox = "</body>\r\n"
			//	+ "</html>";
		ArrayList conteudoBox = new ArrayList();

		File f = new File(absolutePathStart + "webapp\\Qui.html");
		Path cam = f.toPath();

		try {

			conteudoBox.add(templateBox);
			Files.write(cam, conteudoBox, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
		}catch (IOException e) {
			e.printStackTrace();

	}
	}
		return true;

	
	}
}

