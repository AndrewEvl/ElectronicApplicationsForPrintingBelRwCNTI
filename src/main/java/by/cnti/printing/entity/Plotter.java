package by.cnti.printing.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "plotter")
public class Plotter extends BaseEntity {

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "cyan_color")
    private double cyanColor;

    @Column(name = "magenta_color")
    private double magentaColor;

    @Column(name = "yellow_color")
    private double yellowColor;

    @Column(name = "key_color")
    private double keyColor;

    @Column(name = "light_magenta_color")
    private double lightMagentaColor;

    @Column(name = "light_cyan_color")
    private double lightCyanColor;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "roll_width")
    private Long rollWidth;

    @Column(name = "footage_paper  ")
    private String footagePaper;
}
