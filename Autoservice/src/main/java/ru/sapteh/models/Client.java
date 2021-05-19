package ru.sapteh.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class Client {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @NonNull
    private int id;
    @Column
    @NonNull
    private String firstName;
    @Column
    @NonNull
    private String lastName;
    @Column
    @NonNull
    private String patronymic;
    @Column
    @NonNull
    private LocalDate birthday;
    @Column
    @NonNull
    private LocalDate registrationDate;
    @Column
    @NonNull
    private String email;
    @Column
    @NonNull
    private String phone;
    @Column
    @NonNull
    private String photoPath;

    @ManyToOne (fetch = FetchType.EAGER)
    @NonNull
    @JoinColumn (name = "GenderCode")
    private Gender gender;
    @OneToMany (mappedBy = "client")
    Set<ClientService> clientServices;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", gender=" + gender +
                '}';
    }
}
