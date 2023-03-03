package com.spring.es.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 数据库存储user
 *
 * @author 代码的路
 * @date 2023/2/23
 */
@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String job;
    private Double deposit;
    private Date processTime = new Date();
}
