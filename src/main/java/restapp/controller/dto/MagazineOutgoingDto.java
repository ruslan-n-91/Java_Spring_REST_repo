package restapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

public class MagazineOutgoingDto {
    private Integer id;
    private String title;
    private Integer quantity;

    @JsonIgnoreProperties("magazines")
    private PublisherOutgoingDto publisher;

    public MagazineOutgoingDto() {
    }

    public MagazineOutgoingDto(Integer id, String title, Integer quantity, PublisherOutgoingDto publisher) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.publisher = publisher;
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

    public PublisherOutgoingDto getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherOutgoingDto publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "MagazineOutgoingDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MagazineOutgoingDto that = (MagazineOutgoingDto) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
