package com.spider.scrawl.provider.service;

import com.spider.scrawl.provider.entity.Student;
import org.bson.types.ObjectId;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentTest {
    private static MongoTemplate mongoTemplate;
    static {
        //实例化mongoTemplate
        mongoTemplate = (MongoTemplate) new ClassPathXmlApplicationContext("classpath:context/mongo.xml").getBean("mongoTemplate");
    }

    public static void main(String[] args) {
        insert();
//        delete();
//        update();
        query();
    }

    public static void insert() {
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName("张三"+i);
            student.setAge(16+i);
            student.setAddTime(new Date());
            list.add(student);
        }
        mongoTemplate.insert(list, Student.class);
    }

    public static void update() {
        //实体类运行时类名作为参数
        mongoTemplate.updateFirst(Query.query(new Criteria("name").is("张三5")), 
                Update.update("addTime", new Date()).update("age", 33), Student.class);

        //集合名作为参数
        mongoTemplate.updateFirst(Query.query(new Criteria("name").is("张三6")), 
                Update.update("a_time", new Date()).update("age", 36), "student_info");

        //若不存在，先添加，再修改
        mongoTemplate.upsert(Query.query(new Criteria("name").is("张三11")), 
                Update.update("age", 11), Student.class);

    }

    public static void delete() {
        //删除对象
        mongoTemplate.remove(Query.query(new Criteria("name").is("张三0")), Student.class);

        //删除并返回
        Student student =  mongoTemplate.findAndRemove(Query.query(new Criteria("name").is("张三2")), Student.class);
        System.out.println(student);

        //删除并返回集合
        List<Student> students =  mongoTemplate.findAllAndRemove(Query.query(new Criteria("age").is(19)), Student.class);
        System.out.println(students);

        //删除集合
        mongoTemplate.dropCollection(Student.class);

        //删库
//        mongoTemplate.getDb().drop();
    }

    public static void query() {
        //查询记录数
        long count = mongoTemplate.count(Query.query(new Criteria("age").is(18)), Student.class);
        System.out.println(count);

        //查询第一条
        Student student = mongoTemplate.findOne(Query.query(new Criteria("age").is(18)), Student.class);
        System.out.println(student);

        //查询所有记录
        List<Student> list = mongoTemplate.findAll(Student.class);
        for (Student stu : list) {
            System.out.println(stu);
        }

        //带条件查询
        List<Student> list2 = mongoTemplate.find(Query.query(new Criteria("age").is(18)), Student.class);
        for (Student stu : list2) {
            System.out.println(stu);
        }

        //分页查询,过掉第1条,再取2条
        List<Student> list3 = mongoTemplate.find(Query.query(new Criteria("age").is(18)).skip(1).limit(2), Student.class);
        for (Student stu : list3) {
            System.out.println(stu);
        }

        //根据ID查询
        Student student2 = mongoTemplate.findById(new ObjectId("5b62c6f55a41f60d93ffe6c2"), Student.class);
        System.out.println(student2);

        //in查询
        List<Student> list4 = mongoTemplate.find(Query.query(new Criteria("age").in(18,19)), Student.class);
        for (Student stu : list4) {
            System.out.println(stu);
        }


        //or查询
        List<Student> list5 = mongoTemplate.find(Query.query(new Criteria().orOperator(
                    new Criteria("age").is(18),
                    new Criteria("age").is(19)
                )),
                Student.class);
        for (Student stu : list5) {
            System.out.println(stu);
        }

        //and查询
        Student student3 = mongoTemplate.findOne(Query.query(new Criteria().andOperator(
                    new Criteria("age").is(18),
                    new Criteria("name").is("张三1")
                )), Student.class);
        System.out.println(student3);

    }
}