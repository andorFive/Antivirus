package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class antivirus {

    private String FileAbsolute;

    private int len;//文件大小

    private String time;//杀毒的时间
}
