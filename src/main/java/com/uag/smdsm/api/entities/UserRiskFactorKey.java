package com.uag.smdsm.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserRiskFactorKey implements Serializable {
    @Column(name = "id_user", nullable = false)
    private Long userId;

    @Column(name = "id_risk_factor", nullable = false)
    private Long riskFactorId;
}
