package restapp.model;

import org.hibernate.Remove;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Author.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            //remove a book from the author
//            Author author = session.find(Author.class, 11);
//            System.out.println(author.getBooks());
//            Book bookToRemove = author.getBooks().get(0);
//            author.getBooks().remove(0);
//            bookToRemove.getAuthors().remove(author);

            //add a book and add existing author to it
//            Book book = new Book(null, "Other book 888", 15, null);
//            Author author = session.find(Author.class, 11);
//            book.setAuthors(new ArrayList<>(List.of(author)));
//            author.getBooks().add(book);
//            session.persist(book);

            //find book and print its authors
//            Book book = session.find(Book.class, 7);
//            System.out.println(book.getAuthors());

            //add book and two authors
//            Book book = new Book(null, "Some book 515", 55, null);
//            Author author1 = new Author(null, "Author 111", null);
//            Author author2 = new Author(null, "Author 222", null);
//            book.setAuthors(new ArrayList<>(List.of(author1, author2)));
//            author1.setBooks(new ArrayList<>(List.of(book)));
//            author2.setBooks(new ArrayList<>(List.of(book)));
//            session.persist(book);
//            session.persist(author1);
//            session.persist(author2);

            session.getTransaction().commit();
        }
    }
}
