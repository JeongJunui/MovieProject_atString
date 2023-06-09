
package com.example.movieProject.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResDTO<T> {
    private Integer code;
    private String message;
    private T data;
}
