package by.cnti.printing.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cartridge")
@ToString
@EqualsAndHashCode(callSuper = true)
public class Cartridge extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "printer_model_id")
    private PrinterModel printerModel;

    @Column(name = "name_cartridge")
    private String name;
}
