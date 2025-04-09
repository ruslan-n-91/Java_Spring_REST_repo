package restapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Publisher")
public class Publisher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "publisher_id")
    @Fetch(FetchMode.SUBSELECT)
    private List<Magazine> magazines;

    public Publisher() {
    }

    public Publisher(int id, String name, List<Magazine> magazines) {
        this.id = id;
        this.name = name;
        this.magazines = magazines;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id == publisher.id && Objects.equals(name, publisher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
