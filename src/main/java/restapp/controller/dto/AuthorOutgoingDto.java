package restapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.Set;

public class AuthorOutgoingDto {
    private Integer id;
    private String name;
    
    @JsonIgnoreProperties("authors")
    private Set<BookOutgoingDto> books;

    public AuthorOutgoingDto() {
    }

    public AuthorOutgoingDto(Integer id, String name, Set<BookOutgoingDto> books) {
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

    public Set<BookOutgoingDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookOutgoingDto> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "AuthorOutgoingDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuthorOutgoingDto that = (AuthorOutgoingDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

