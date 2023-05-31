<<<<<<<< HEAD:demo/src/main/java/com/example/movieProject/module/board/AgoraDiscussDTO.java
package com.example.movieProject.module.board;
========
package com.example.movieProject.common;
>>>>>>>> 0b361bf7f6446238e5bfe82dd3a4894702cb5a7c:demo/src/main/java/com/example/movieProject/common/ResDTO.java

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<<< HEAD:demo/src/main/java/com/example/movieProject/module/board/AgoraDiscussDTO.java
import java.sql.Date;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class AgoraDiscussDTO {
========
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResDTO<T> {
    private Integer code;
    private String message;
    private T data;
>>>>>>>> 0b361bf7f6446238e5bfe82dd3a4894702cb5a7c:demo/src/main/java/com/example/movieProject/common/ResDTO.java
}
