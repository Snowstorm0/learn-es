package com.spring.es.modeles;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 定义user对应的实体类
 *
 * @author 代码的路
 * @date 2023/2/23
 */
@Data
@Document(indexName = "user")
public class ESUser {
    @Id
    @Field(type = FieldType.Text)
    private String id;
    @Field(analyzer = "ik_max_word")
    private String name;
    @Field(analyzer = "ik_max_word")
    private String job;
    @Field(type = FieldType.Double)
    private Double deposit;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date processTime;

}
