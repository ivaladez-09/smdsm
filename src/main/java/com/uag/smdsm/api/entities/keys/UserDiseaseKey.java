package com.uag.smdsm.api.entities.keys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class UserDiseaseKey implements Serializable {
    @Column(name = "id_user")
    private Long userId;

    @Column(name = "id_disease")
    private Long diseaseId;
}
