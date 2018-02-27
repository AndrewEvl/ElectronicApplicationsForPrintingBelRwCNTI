package by.cnti.printing.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "type_of_paper")
public class TypeOfPaper extends BaseEntity {

    @Column(name = "paper_type")
    private String type;
}
