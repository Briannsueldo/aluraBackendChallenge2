package com.alura.challengeAlura.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alura.challengeAlura.model.AuthorData;
import com.alura.challengeAlura.model.BookData;

public class Menu {
    
    public void mainMenu() {
        Scanner scan = new Scanner(System.in);
        int option;
        do {
            System.out.println("""
                    1 - Buscar libro por titulo
                    2 - Mostrar libros registrados
                    3 - Mostrar autores registrados
                    4 - Mostrar autores vivos en determinado año
                    5 - Mostrar libros por idioma
                    6 - Salir
                    """);
            option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("Ingrese el nombre del libro a buscar (Se seleccionara el primero que resulte de la busqueda unicamente)");

                    String bookName = scan.nextLine();

                    searchBook(scan, bookName);
                }
            }
            
        } while (option != 6);
    }

    public void searchBook(Scanner scan, String bookName) {

        String dataJson = ApiRequest.bookApiRequest(bookName);

        FormatData formatDataInstance = new FormatData();

        BookData firstBook = formatDataInstance.getBook(dataJson);

        if (firstBook != null) {
            List<AuthorData> newAuthors = new ArrayList<>();
            
            firstBook.getAuthors().forEach(author -> {
                AuthorData newAuthor = new AuthorData();
                newAuthor.setName(author.getName());
                newAuthor.setbYear(author.getbYear());
                newAuthor.setdYear(author.getdYear());
        
                newAuthors.add(newAuthor);
            });
    
            firstBook.setAuthors(newAuthors);
    
            System.out.println(firstBook.formatBookData());
        } else {
            System.out.println("No se encontró el libro.");
        }

    }
}
