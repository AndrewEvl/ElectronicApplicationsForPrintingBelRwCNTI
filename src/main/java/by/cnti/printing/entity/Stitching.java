package by.cnti.printing.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "bid_stitching")
@ToString(callSuper = true)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "bid_id")
public class Stitching extends Bid {

    @Column(name = "unibind")
    private String unibind;
    @Column(name = "springs")
    private String springs;
    @Column(name = "covers")
    private String covers;
    @Column(name = "substrate")
    private String substrate;
}
