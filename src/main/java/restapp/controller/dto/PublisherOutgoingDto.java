package restapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

public class PublisherOutgoingDto {
    private int id;
    private String name;

    @JsonIgnoreProperties("publisher")
    private List<MagazineOutgoingDto> magazines;

    public PublisherOutgoingDto() {
    }

    public PublisherOutgoingDto(int id, String name, List<MagazineOutgoingDto> magazines) {
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

    public List<MagazineOutgoingDto> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<MagazineOutgoingDto> magazines) {
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
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
