//package order.provider;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.framelib.utils.Page;
//import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
//import com.qianqian.apply.bo.ApplyBO;
//import com.qianqian.apply.bo.BrandApplyBO;
//import com.qianqian.apply.dto.BrandApplyDTO;
//import com.qianqian.apply.dto.MemApplyDTO;
//import com.qianqian.apply.service.IApplyDubboService;
//
//public class ApplyTest {
//	@Test
//	public void getBrandApply(){
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
//		IApplyDubboService service=(IApplyDubboService) ctx.getBean("applyDubboService");
//		Page page=new Page();
//		page.setPageNum(1);
//		Long activityId = 199l;
//		BrandApplyBO bo = new BrandApplyBO();
//		bo.setActivityId(activityId);
//		bo.setPage(page);
//		List<BrandApplyDTO> list =service.getBrandApplyList(bo).getApplyList();
//		System.out.println("################################################");
//		System.out.println(Arrays.toString(list.toArray()));
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		System.out.println(list.size());
//	}
//
//	
//	@Test
//	public void testApply(){
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
//		IApplyDubboService service=(IApplyDubboService) ctx.getBean("applyDubboService");
//		Page page = new Page();
//		Long userId = 110l;
//		ApplyBO bo = new ApplyBO();
//		bo.setUserId(userId);
//		page.setPageNum(2);
//		bo.setPage(page);
//		//bo.setProductName("锋衣");
//		bo.setEndTime(new Date());
//		bo =service.getMemApplyList(bo);
//		System.out.println("################################################");
//		System.out.println(Arrays.toString(bo.getApplyList().toArray()));
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		System.out.println(bo.getPage().getTotalCount()+"     =====   "+bo.getPage().getTotalPage());
//	}
//	
//	@Test
//	public void testApplyAuditPass(){
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
//		IApplyDubboService service=(IApplyDubboService) ctx.getBean("applyDubboService");
//		Long auditBy = 1109l;
//		Long applyId = 95553500359952060l;
//		boolean result = service.applyAuditPass(applyId, auditBy);
//		System.out.println("################################################");
//		System.out.println("result="+result);
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//	}
//
//}
