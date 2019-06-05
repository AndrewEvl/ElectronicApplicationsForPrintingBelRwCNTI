package by.cnti.printing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PlotterDto {

    private String documentName;
    private Double cyanColor;
    private Double magentaColor;
    private Double yellowColor;
    private Double keyColor;
    private Double lightMagentaColor;
    private Double lightCyanColor;
    private Long departmentId;
    private LocalDate date;
    private Double rollWidth;
    private Long typeOfPaperId;
}
