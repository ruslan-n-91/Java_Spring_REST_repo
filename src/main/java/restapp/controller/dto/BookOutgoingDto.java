package restapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.Set;

public class BookOutgoingDto {
    private Integer id;
    private String title;
    private Integer quantity;
    
    @JsonIgnoreProperties("books")
    private Set<AuthorOutgoingDto> authors;

    public BookOutgoingDto() {
    }

    public BookOutgoingDto(Integer id, String title, Integer quantity, Set<AuthorOutgoingDto> authors) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.authors = authors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<AuthorOutgoingDto> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorOutgoingDto> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "BookOutgoingDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookOutgoingDto that = (BookOutgoingDto) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
