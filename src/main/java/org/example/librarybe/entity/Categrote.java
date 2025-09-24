package org.example.librarybe.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Categrote {
    private Integer id;
    private String name;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-8")
    private LocalDate createTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-8")
    private LocalDate updateTime;
}
