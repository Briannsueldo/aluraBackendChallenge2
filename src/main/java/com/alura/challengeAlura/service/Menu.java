package com.alura.challengeAlura.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.alura.challengeAlura.model.AuthorData;
import com.alura.challengeAlura.model.BookData;
import com.alura.challengeAlura.repository.AuthorDataRepository;
import com.alura.challengeAlura.repository.BookDataRepository;

public class Menu {

    private BookDataRepository bookRepository;
    private AuthorDataRepository authorRepository;

    public Menu(BookDataRepository bookRepository, AuthorDataRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    
    public void mainMenu() {
        Scanner scan = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("""
                    1 - Buscar libro por titulo
                    2 - Mostrar libros registrados
                    3 - Mostrar autores registrados
                    4 - Mostrar autores vivos en determinado año
                    5 - Mostrar libros por idioma
                    6 - Salir
                    """);

            try {
                option = scan.nextInt();
                scan.nextLine();
    
                switch (option) {
                    case 1 -> {
                        System.out.println("Ingrese el nombre del libro a buscar (Se seleccionara el primero que resulte de la busqueda unicamente)");
    
                        String bookName = scan.nextLine();
    
                        boolean bookFlag = findBookByName(scan, bookName);
    
                        if (bookFlag) {
                            searchBook(scan, bookName);
                        }
    
                    }
                    case 2 -> {
                        findBooks();
                    }
                    case 3 -> {
                        showAuthors(scan);
                    }
                    case 4 -> {
                        findAuthors(scan);
                    }
                    case 5 -> {
                        findByLanguage(scan);
                    }
                    default -> {
                        System.out.println("Opcion incorrecta");
                        System.out.println("---------------------");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Caracter no reconocido, solo numeros");
                scan.nextLine();
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
        
                newAuthor.setBook(firstBook);

                newAuthors.add(newAuthor);
            });
    
            firstBook.setAuthors(newAuthors);

            bookRepository.save(firstBook);
    
            System.out.println(firstBook.formatBookData());
        } else {
            System.out.println("No se encontró el libro.");
        }

    }

    public void findBooks() {
        List<BookData> allBooksListed = bookRepository.findAllBooks();

        if (allBooksListed.size() == 0) {
            System.out.println("No se encuentran libros guardados");
        }

        allBooksListed.forEach(book -> {
            System.out.println(book.formatBookData());
        });
    }

    public boolean findBookByName(Scanner scan, String bookName) {

        List<BookData> booksFinded = bookRepository.findBookByName(bookName);

        booksFinded.forEach(book -> {
            System.out.println(book.formatBookData());
        });

        if (booksFinded.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void showAuthors(Scanner scan) {

        List<AuthorData> authorsRegistered = authorRepository.findAuthorName();

        authorsRegistered.forEach(author -> {
            System.out.println(author.getName());
        });
    }

    public void findAuthors(Scanner scan) {
        System.out.println("Ingrese el año a buscar");
        Integer yearToSearch = scan.nextInt();

        List<AuthorData> authorsAlive = authorRepository.findAuthorsByYear(yearToSearch);

        authorsAlive.forEach(author -> {
            System.out.println(author.getName() + " || Vivo durante: " + author.getbYear() + " - " + author.getdYear());
        });

    }

    public void findByLanguage(Scanner scan) {
        Integer option;
        do {
            System.out.println("""
                    Seleccione el lenguaje

                    1 - Ingles
                    2 - Español
                    3 - Salir

                    """);
            option = scan.nextInt();

            switch (option) {
                case 1 -> {
                    List<BookData> booksFinded = bookRepository.findByLanguage("en");

                    booksFinded.forEach(book -> {
                        System.out.println(book.formatBookData());
                        System.out.println("---------------------");
                    });

                    if (booksFinded.isEmpty()) {
                        System.out.println("No se encuentran libros en Ingles");
                    }
                } case 2 -> {
                    List<BookData> booksFinded = bookRepository.findByLanguage("es");

                    booksFinded.forEach(book -> {
                        System.out.println(book.formatBookData());
                        System.out.println("---------------------");
                    });

                    if (booksFinded.isEmpty()) {
                        System.out.println("No se encuentran libros en Español");
                    }
                }
            }
        } while (option != 3);
    }
}
