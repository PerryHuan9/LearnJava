package com.learn.mybatis.bean;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Article implements Serializable {
    private static final long serialVersionUID = -2530220790795676472L;
    private long id;
    @NonNull
    private String title;
    @NonNull
    private String author;
    @NonNull
    private String articleType;
    @NonNull
    private LocalDate publishDate;
}
