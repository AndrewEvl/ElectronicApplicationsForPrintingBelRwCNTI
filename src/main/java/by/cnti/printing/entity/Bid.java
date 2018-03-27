package by.cnti.printing.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
@Table(name = "bid")
public class Bid extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "customer_order")
    private String customerOder;

    @Column(name = "allow")
    private String allow;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "stitching")
    private Stitching stitching;

    @Column(name = "edition")
    private Long edition;

    @OneToOne
    @JoinColumn(name = "paper_size_id")
    private PaperSize paperSize;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "paper_density_id")
    private PaperDensity paperDensity;

    @OneToOne
    @JoinColumn(name = "status_work_id")
    private StatusWork statusWork;

    @OneToOne
    @JoinColumn(name = "printer_model_id")
    private PrinterModel printer;

    @Override
    public String toString() {
        if (stitching != null) {
            return "№" + getId() +
                    " Отдел " + department.getName() +
                    ", ФИО заказчика " + customerOder + '\'' +
                    ", Название документа " + documentName + '\'' +
                    ", Сшивка " + stitching + '\'' +
                    ", Тираж " + edition +
                    ", Формат " + paperSize.getSize() +
                    ", Дата " + date +
                    ", Плотность бумаги " + paperDensity.getDensity() +
                    ", Принтер " + printer.getModel() + "\r";
        }
        return "№" + getId() +
                " Отдел " + department.getName() +
                ", ФИО заказчика " + customerOder + '\'' +
                ", Разрешение " + allow + '\'' +
                ", Название документа " + documentName + '\'' +
                ", Тираж " + edition +
                ", Формат " + paperSize.getSize() +
                ", Дата " + date +
                ", Плотность бумаги " + paperDensity.getDensity() +
                ", Принтер " + printer.getModel() + "\r";
    }
}
