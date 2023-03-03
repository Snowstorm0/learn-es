package com.spring.es.modeles;

import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * es存储user,定义user与es索引映射关系
 *
 * @author 代码的路
 * @date 2023/2/23
 */
@Repository("esUserRepository")
public interface ESUserRepository extends ElasticsearchRepository<ESUser, String> {

    List<ESUser> findByNameOrJob(String name, String job);

    @Highlight(fields = {
            @HighlightField(name = "name"),
            @HighlightField(name = "job")
    })
    @Query("{\"match\":{\"name\":\"?0\"}}")
    SearchHits<ESUser> find(String keyword);
}