package dev.bieger.controller;

import dev.bieger.model.dao.impl.AuthorDao;
import dev.bieger.model.entity.Author;

import java.util.Scanner;

public class AuthorController {

    Scanner sc = new Scanner(System.in);
    Author author = new Author();
    AuthorDao authorDao = new AuthorDao();

    public void insertAuthor() {
        System.out.println("Digite o nome do autor: ");
        author.setName(sc.nextLine());

        authorDao.insertAuthor(author);
    }

    public void selectAllAuthor() {
        authorDao.selectAllAuthor();
    }

    public void selectByIdAuthor() {
        System.out.println("Digite o ID do autor: ");
        authorDao.selectByIdAuthor(sc.nextLong());
    }

    public void updateAuthor() {
        Long id;

        System.out.println("Digite o ID do autor a ser atualizado: ");
        id = sc.nextLong();

        System.out.println("Digite o nome do autor: ");
        sc.nextLine();
        author.setName(sc.nextLine());

        authorDao.updateAuthor(id, author);
    }

    public void deleteAuthor() {
        System.out.println("Digite o ID do autor: ");
        authorDao.deleteAuthor(sc.nextLong());
    }

}
