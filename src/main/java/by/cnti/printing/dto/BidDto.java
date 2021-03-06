package by.cnti.printing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BidDto {

    private Long departmentId;
    private String customerOder;
    private String allow;
    private String documentName;
    private String stitching;
    private Long edition;
    private Long numberOfPages;
    private Long paperSizeId;
    private Long paperDensityId;
    private Long statusWorkId;
    private Long printerModelId;
    private String twoSidedPrinting;
}
