package restapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.Set;

public class PublisherOutgoingDto {
    private Integer id;
    private String name;

    @JsonIgnoreProperties("publisher")
    private Set<MagazineOutgoingDto> magazines;

    public PublisherOutgoingDto() {
    }

    public PublisherOutgoingDto(Integer id, String name, Set<MagazineOutgoingDto> magazines) {
        this.id = id;
        this.name = name;
        this.magazines = magazines;
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

    public Set<MagazineOutgoingDto> getMagazines() {
        return magazines;
    }

    public void setMagazines(Set<MagazineOutgoingDto> magazines) {
        this.magazines = magazines;
    }

    @Override
    public String toString() {
        return "PublisherOutgoingDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PublisherOutgoingDto that = (PublisherOutgoingDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
