package restapp.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Magazine")
public class Magazine {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Magazine() {
    }

    public Magazine(Integer id, String title, Integer quantity, Publisher publisher) {
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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Magazine magazine = (Magazine) o;
        return Objects.equals(id, magazine.id) && Objects.equals(title, magazine.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
