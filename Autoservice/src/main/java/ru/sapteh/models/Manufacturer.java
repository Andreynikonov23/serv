package ru.sapteh.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class Manufacturer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @NonNull
    private int id;
    @NonNull
    @Column
    private String name;
    @NonNull
    @Column
    private Date startTime;

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
