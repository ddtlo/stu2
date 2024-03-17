//package com.chl.test.es;
//
//import com.chl.test.es.dao.GoodsDAO;
//import com.chl.test.es.index.Goods;
//import org.junit.After;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * @Description
// * @Author 陈汉霖
// * @Date 2024/2/28
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class Test {
//    @Autowired  //持久层对象
//    private GoodsDAO esDao;
//    @Autowired  //ES模板对象
//    private ElasticsearchTemplate template;
//
//    @After
//    public void finish(){
//        System.out.println("已完成。。。。");
//    }
//
//    @org.junit.Test
//    public void test(){
//        //创建索引,会根据 Goods 类的@Document注解信息来创建
//        template.createIndex(Goods.class);
//        //映射配置,会根据 Goods 类中的id、Field等字段来自动完成映射
//        template.putMapping(Goods.class);
//    }
//    @org.junit.Test//插入单个对象
//    public void test2(){
//        Goods emp = new Goods(1000, "红色布1000", "1000CM", "1000KG/M");
//        esDao.save(emp);
//    }
//    @org.junit.Test//批量插入
//    public void test3(){
//        List<Goods> list = new ArrayList<>();
//        list.add(new Goods(1001, "红色布1001", "1001CM", "1001KG/M"));
//        list.add(new Goods(1002, "红色布1002", "1002CM", "1002KG/M"));
//        list.add(new Goods(1003, "红色布1003", "1003CM", "1003KG/M"));
//        esDao.saveAll(list);//saveAll可以插入用集合批量插入
//    }
////    @org.junit.Test //排序查询
////    public void test4(){
////        //查询所有
////        //Iterable<Goods> list = esDao.findAll();
////        //进行排序查询
////        Iterable<Goods> list = esDao.findAll(Sort.by(Sort.Direction.DESC,"sal"));
////        list.forEach(System.out::println);
////    }
////    @org.junit.Test	//自定义sql语句方法
////    public void  test5(){
////        //findByNameLike() Dao层自定义方法  根据name进行模糊查询
////        //List<Goods> list = esDao.findByNameLike("x");
////        //根据name，sex，进行name模糊查询和指定sex查询
////        List<Goods> list1 = esDao.findByNameLikeAndSex("x","x");
////        list1.forEach(System.out::println);
////    }
////    @org.junit.Test//ES分词查询
////    public void  test7(){
////        //词条条件  查询条件
////        MatchQueryBuilder builder = QueryBuilders.matchQuery("address", "中国广东");
////        //进行查询
////        Iterable<Goods> list = esDao.search(builder);
////        list.forEach(System.out::println);
////    }
////    @org.junit.Test//综合分页查询
////    public void  test8(){
////        //构建查询条件对象  可以理解为存放查询条件的一个容器
////        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
////        //设置查询条件  设置条件根据address字段将 中国xx 进行分词查询
////        queryBuilder.withQuery(QueryBuilders.matchQuery("address","中国xx"));
////
////        //设置分页条件
////        int pageNo = 0;
////        int pageSize = 3;
////        //PageRequest.of()可以在这个方法内放入需要分页的条件
////        queryBuilder.withPageable(PageRequest.of(pageNo,pageSize));
////
////        //排序
////        queryBuilder.withSort(SortBuilders.fieldSort("sal").order(SortOrder.DESC));
////
////        //按照条件进行查询 返回 page 对象实现分页
////        Page<Goods> page = esDao.search(queryBuilder.build());
////        //输出查询的数量
////        System.out.println(page.getTotalElements());
////        //输出查询的数据详情
////        page.getContent().forEach(System.out::println);
////    }
////    @org.junit.Test
////    public void  test9(){
////        //查询构建器
////        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
////        //不要明细
////        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
////        //添加聚合  进行分组
////        queryBuilder.addAggregation(AggregationBuilders.terms("sexGroup").field("sex")
////                .subAggregation(AggregationBuilders.max("maxSal").field("sal")));
////
////        //执行聚合
////        AggregatedPage<Goods> search = (AggregatedPage)esDao.search(queryBuilder.build());
////        //获取桶信息
////        StringTerms sexGroup = (StringTerms) search.getAggregation("sexGroup");
////        List<StringTerms.Bucket> buckets = sexGroup.getBuckets();
////        //遍历桶数据
////        for (StringTerms.Bucket bucket : buckets){
////            System.out.println(bucket.getKeyAsString());
////            System.out.println(bucket.getDocCount());
////            InternalMax max = (InternalMax) bucket.getAggregations().asMap().get("maxSal");
////            System.out.println(max.getValue());
////        }
////    }
//}
