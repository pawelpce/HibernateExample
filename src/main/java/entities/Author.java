package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    //manytomany, zadeklarowane w books (pole authors)
    @ManyToMany(mappedBy = "authors")
    private List<Books> books;

    //przy relacji dodajemy mappedby by przekazac ktora kolumna ma
    // klucz główny (authors w klasie Books)
    /*@OneToOne(mappedBy = "mainAuthor")
    private Books firstBook;*/

    //relacja jeden autor wiele ksiazek
/*    @OneToMany(mappedBy = "mainAuthor")
    private List<Books> books;*/

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author() {
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
