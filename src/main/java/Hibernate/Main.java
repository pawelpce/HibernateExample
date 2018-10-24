package Hibernate;

import entities.Author;
import entities.Books;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        StandardServiceRegistry registry = new
                StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        SessionFactory sessionFactory = new
                MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();


/*        Books book = new Books();
        book.setTitle("Hibernate: A Developers Notebook");
        book.setNumberOfPages(192);
        book.setHasHardCover(true);
        book.setReleaseDate(LocalDate.of(2004, 5, 20));
        System.out.println(book);

        session.beginTransaction();
        session.persist(book); //przy dodawaniu musi być persist i beginTransaction
        session.getTransaction().commit();*/

        Books book = session.get(Books.class, 1L);
        System.out.println(book);

        /*List<Books> allBooks = session.createQuery("from Books", Books.class).list();
        allBooks.forEach(System.out::println);*/

        /*Query<Books> query2 = session.createQuery("from Books where numberOfPages > 150");
        List<Books> booksWithMoreThan150Pages = query2.list();
        booksWithMoreThan150Pages.forEach(System.out::println);*/

       /* System.out.println(">150");
        findBooksWithPageNumberGreaterThan(session, 150).forEach(System.out::println);
        System.out.println(">100");
        findBooksWithPageNumberGreaterThan(session, 100).forEach(System.out::println);*/

       //beginTransaction, commit i merge bo zmieniamy coś w bazie, (dodawanie - persist)
        /*session.beginTransaction();
        Books book = session.get(Books.class, 1L);
        book.setTitle("Zmieniony tytuł");
        session.merge(book);
        session.getTransaction().commit();*/

        //nadpisanie obiektu (pola nie napisane będą nullami), merge może dodać nowy obiekt
        // jak nie znajdzie już utworzonego do zmiany, np. pominiemy id
        /*session.beginTransaction();
        Books book = new Books();
        //book.setId(1L);
        book.setTitle("Podmieniona");
        session.merge(book);
        session.getTransaction().commit();*/

        //usuwanie
        /*session.beginTransaction();
        Books book = session.get(Books.class, 2L);
        session.remove(book);
        session.getTransaction().commit();*/

        /*Author author = session.get(Author.class, 1L);
        System.out.println(author.getFirstBook());*/

        /*Books book = session.get(Books.class, 1L);
        System.out.println(book.getAuthor());*/

        //ManyToOne wyswietlanie ksiazek
        /*List<Books> books = session.createQuery("from Books").list();
        books.forEach(it -> System.out.println(it.getTitle() + " " + it.getMainAuthor()));*/

        //OneToMany wyswietlanie wszystkich ksiazek autorów
        /*Author author = session.get(Author.class, 2L);
        author.getBooks().forEach(System.out::println);*/


        //ManyToMany
        /*Books book = session.get(Books.class, 4L);
        book.getAuthors().forEach(System.out::println);

        Author author = session.get(Author.class, 2L);
        author.getBooks().forEach(System.out::println);*/


        session.close();
        sessionFactory.close();
    }

    static List<Books> findBooksWithPageNumberGreaterThan(Session session, Integer number) {

        Query<Books> query = session.createQuery("from Books where numberOfPages > :num");
        query.setParameter("num", number);
        return query.list();
    }

}
