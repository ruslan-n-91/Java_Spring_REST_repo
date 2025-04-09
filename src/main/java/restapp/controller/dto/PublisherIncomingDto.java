package restapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

public class PublisherIncomingDto {
    private int id;
    private String name;

    @JsonIgnoreProperties("publisher")
    private List<MagazineIncomingDto> magazines;

    public PublisherIncomingDto() {
    }

    public PublisherIncomingDto(int id, String name, List<MagazineIncomingDto> magazines) {
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

    public List<MagazineIncomingDto> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<MagazineIncomingDto> magazines) {
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
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
