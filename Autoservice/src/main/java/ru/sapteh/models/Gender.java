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
public class Gender {
    @Id
    @NonNull
    private char code;
    @NonNull
    @Column
    private char name;

    @OneToMany (mappedBy = "gender")
    Set<Client> clients;

    @Override
    public String toString() {
        return String.format("%s", code);
    }
}
