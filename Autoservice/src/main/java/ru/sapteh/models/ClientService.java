package ru.sapteh.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table (name = "clientservice")
public class ClientService {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @NonNull
    private int id;
    @NonNull
    @Column
    private Date startTime;
    @NonNull
    @Column
    private String comment;


    @ManyToOne
    @JoinColumn (name = "ClientID")
    @NonNull
    private Client client;
    @ManyToOne
    @JoinColumn (name = "ServiceID")
    @NonNull
    private Service service;

    @Override
    public String toString() {
        return "ClientService{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", comment='" + comment + '\'' +
                ", client=" + client +
                ", service=" + service +
                '}';
    }
}
