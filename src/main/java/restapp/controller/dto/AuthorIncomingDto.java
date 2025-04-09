package restapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.Set;

public class AuthorIncomingDto {
    private Integer id;
    private String name;
    @JsonIgnoreProperties("authors")
    private Set<BookIncomingDto> books;

    public AuthorIncomingDto() {
    }

    public AuthorIncomingDto(Integer id, String name, Set<BookIncomingDto> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookIncomingDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookIncomingDto> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "AuthorIncomingDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuthorIncomingDto that = (AuthorIncomingDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}