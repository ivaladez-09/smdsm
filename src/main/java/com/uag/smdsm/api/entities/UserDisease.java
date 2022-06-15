package com.uag.smdsm.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_disease")
public class UserDisease implements Serializable {

    @EmbeddedId
    private UserDiseaseKey id;

    @Column(name = "active", nullable = false)
    private Boolean isActive = false;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @MapsId("diseaseId")
    @JoinColumn(name = "id_disease")
    private Disease disease;
}
