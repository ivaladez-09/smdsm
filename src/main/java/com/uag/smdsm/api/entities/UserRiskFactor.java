package com.uag.smdsm.api.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_risk_factor")
public class UserRiskFactor implements Serializable {

    @EmbeddedId
    private UserRiskFactorKey id;

    @Column(name = "active", nullable = false)
    private Boolean isActive = false;

    @Column(name = "rate")
    private Integer rate;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @MapsId("riskFactorId")
    @JoinColumn(name = "id_risk_factor")
    private RiskFactor riskFactor;
}
