package ru.sapteh.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class Service {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @NonNull
    private int id;
    @NonNull
    @Column
    private String title;
    @NonNull
    @Column
    private double cost;
    @NonNull
    @Column
    private int durationInSecond;
    @NonNull
    @Column
    private String description;
    @NonNull
    @Column
    private double discount;
    @NonNull
    @Column
    private String mainImagePath;

    @OneToMany (mappedBy = "service")
    Set<ClientService> clientServices;

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", durationInSecond=" + durationInSecond +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", mainImagePath='" + mainImagePath + '\'' +
                '}';
    }
}
