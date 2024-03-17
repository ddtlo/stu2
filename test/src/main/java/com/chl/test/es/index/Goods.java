package com.chl.test.es.index;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/28
 */
@Data
@NoArgsConstructor
//indexName设置索引名称  type定义类型  shards分片数量  replicas副本 每个分片的复制
@Document(indexName = "goods")
public class Goods {
    public Goods(Integer id, Map name, String width, String weight) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.weight = weight;
    }

    @Id
    private Integer id;
    //type设置数据映射类型  analyzer定义分词片
    @Field(type = FieldType.Object)
    private Map<String, String> name;
    @Field(type = FieldType.Text)
    private String width;
    //keyword另类text类型，不进行分词和索引,可以进行过滤、排序、聚合操作
    @Field(type = FieldType.Text)
    private String weight;
    //    private Map<String,Object> propertyMap;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word")
    private String properties;
}
