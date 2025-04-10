package restapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.Set;

public class PublisherIncomingDto {
    private Integer id;
    private String name;

    @JsonIgnoreProperties("publisher")
    private Set<MagazineIncomingDto> magazines;

    public PublisherIncomingDto() {
    }

    public PublisherIncomingDto(Integer id, String name, Set<MagazineIncomingDto> magazines) {
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

    public Set<MagazineIncomingDto> getMagazines() {
        return magazines;
    }

    public void setMagazines(Set<MagazineIncomingDto> magazines) {
        this.magazines = magazines;
    }

    @Override
    public String toString() {
        return "PublisherIncomingDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PublisherIncomingDto that = (PublisherIncomingDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
