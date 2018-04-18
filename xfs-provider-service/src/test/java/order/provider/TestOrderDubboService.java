//package order.provider;
//
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.framelib.utils.Page;
//import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
//
//
//import com.qianqian.apply.dto.BrandApplyDTO;
//import com.qianqian.apply.dto.MemApplyDTO;
//import com.qianqian.apply.service.IApplyDubboService;
//import com.qianqian.order.dto.Query;
//import com.qianqian.order.service.IOrderDubboService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spring/applicationContext.xml") 
//public class TestOrderDubboService {
//	@Resource
//	IOrderDubboService orderDubboService;
//	Long ordersId=95553500359952147l;
//	PageBounds pageBounds =new PageBounds(10);
//	Long userId=5l;
//	
////	@Test
////	public void testGetAllChildOrderByProductNameAndSubmitOrderTime(){
////		
////		int s =orderDubboService.getAllChildOrderByProductNameAndSubmitOrderTime(1," 123", new Date(1111), new Date(111123), 200,pageBounds).size();
////		System.out.println(s);
////	}
//
//	@Test
//	public void testGetAllChildOrderByOrdersCode(){
//		int s=orderDubboService.getAllChildOrderByOrdersCode(ordersId).size();
//		System.out.println(s);
//	}
////	@Test
////	public void testGetAllDrawbackChildOrderByProductNameAndSubmitOrderTime(){
////		
////		int s=orderDubboService.getAllDrawbackChildOrderByProductNameAndSubmitOrderTime("123", new Date(1111), new Date(111123), pageBounds).size();
////		System.out.println(s);
////	}
////	@Test
////	public void testGetAllDrawBackChildOrderByUserId(){
////		int s=orderDubboService.getAllDrawBackChildOrderByUserId(userId, pageBounds).size();
////		System.out.println(s);
////	}
////	@Test
////	public void testGetAllFreeOrderByUserId(){
////	orderDubboService.getAllFreeOrderByUserId(userId, pageBounds);
////	
////	}
//	//测试模糊查询订单
//	@Test
//	public void testGetOrderState(){
//		Query query=new Query();
//		query.setPageNum(1);
//		query.setPageSize(2);
//		query.setProductName("梅西");
//		query.setUserId(5l);
//		orderDubboService.getOrderByQuery(query);
//		
//	}
//	
//
//}
