package com.spider.user.service.impl;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith( SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:context/*.xml",
})
@Transactional
//@TransactionConfiguration(transactionManager="txManager",defaultRollback=true)
@Rollback
public class SpiderBaseTest {

}