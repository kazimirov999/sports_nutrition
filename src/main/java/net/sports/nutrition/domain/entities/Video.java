package net.sports.nutrition.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represents Video.
 * <p>
 * It's marked as an entity class, and  provides the ability to store
 * Video objects in the database and retrieve Video objects from the database.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Entity
@Table(name = "videos")
public class Video implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @Column(columnDefinition = "TEXT")
    private String playerCode;

    /**
     * Creates new empty instance of the Video.
     *
     * @see Video#Video(String, String, String)
     */
    public Video() {
    }

    /**
     * Creates new empty instance of the Video.
     *
     * @param name        - name of video
     * @param description - description of video
     * @param playerCode  - playerCode of video
     * @see Video#Video(String, String, String)
     */
    public Video(String name, String description, String playerCode) {
        this.name = name;
        this.description = description;
        this.playerCode = playerCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video)) return false;

        Video video = (Video) o;

        if (id != null ? !id.equals(video.id) : video.id != null) return false;
        if (name != null ? !name.equals(video.name) : video.name != null) return false;
        if (description != null ? !description.equals(video.description) : video.description != null) return false;
        return !(playerCode != null ? !playerCode.equals(video.playerCode) : video.playerCode != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (playerCode != null ? playerCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlayerCode() {
        return playerCode;
    }

    public void setPlayerCode(String playerCode) {
        this.playerCode = playerCode;
    }
}
