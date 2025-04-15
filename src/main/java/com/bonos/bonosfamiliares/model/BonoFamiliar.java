package com.bonos.bonosfamiliares.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "bonos_familiares")
@Data
public class BonoFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de corte es obligatoria")
    private String fechaCorte;

    @NotNull(message = "La entidad técnica / promotor es obligatoria")
    private String entidadTecnicaPromotor;

    @NotNull(message = "El RUC es obligatorio")
    private String ruc;

    @NotNull(message = "La personalidad jurídica es obligatoria")
    private String personalidadJuridica;

//    @NotNull(message = "El nombre del proyecto es obligatorio")
    private String nombreProyecto;

    @NotNull(message = "El código del proyecto es obligatorio")
    private String codigoProyecto;

//    @NotNull(message = "La convocatoria es obligatoria")
    private String convocatoria;

    @NotNull(message = "La modalidad es obligatoria")
    private String modalidad;

    @NotNull(message = "El departamento es obligatorio")
    private String departamento;

    @NotNull(message = "La provincia es obligatoria")
    private String provincia;

    @NotNull(message = "El distrito es obligatorio")
    private String distrito;

    @NotNull(message = "El ubigeo es obligatorio")
    private String ubigeo;

    @NotNull(message = "El monto BFHO es obligatorio")
    private Double montoBfho;

    @NotNull(message = "El valor de la obra/vivienda es obligatorio")
    private Double valorObraVivienda;

    @NotNull(message = "La fecha de desembolso es obligatoria")
    private String fechaDesembolso;
}
