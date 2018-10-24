package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Column(name = "has_hard_cover")
    private Boolean hasHardCover;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    //dopisujemy dzięki kluczowi obcemu
    //join column przy relacjach, jak nazwa pola jest inna
    /*@OneToOne
    @JoinColumn(name = "author_id")
    private Author mainAuthor;*/

    //wiele ksiazek jeden autor
/*    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author mainAuthor;*/

    //manytomany, jointable - tabela łącząca
    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;


    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", hasHardCover=" + hasHardCover +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
