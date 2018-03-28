package by.cnti.printing.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cartridge_replacement")
@ToString
@EqualsAndHashCode(callSuper = true)
public class CartridgeReplacement extends BaseEntity {

    @Column(name = "date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "cartridge_id")
    private Cartridge cartridge;
}
