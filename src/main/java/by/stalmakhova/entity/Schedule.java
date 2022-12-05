package by.stalmakhova.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "master_id", nullable = false)
    private Master master;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "procedure_id", nullable = false)
    private ProcedureTable procedure;

    @NotNull
    @ManyToOne( optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @NotNull
    @Column(name = "date_", nullable = false)
    private String date;

    @NotNull
    @Column(name = "time_", nullable = false)
    private String time;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;



}