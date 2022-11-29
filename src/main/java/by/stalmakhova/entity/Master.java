package by.stalmakhova.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@Entity
@Table(name = "master")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_procedure", nullable = false)
    private ProcedureTable idProcedure;

    @Size(max = 40)
    @NotNull
    @Column(name = "name_master", nullable = false, length = 40)
    private String nameMaster;



}
