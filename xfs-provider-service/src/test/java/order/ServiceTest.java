package order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xfs.order.service.IBaseTestDubbo2Service;
import com.xfs.order.service.IBaseTestDubboService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
 "classpath:/spring/applicationContext.xml",
//"classpath:/spring/applicationContext-dubbo-consumer.xml"
// "file:src/main/webapp/WEB-INF/spring/webmvc-config.xml"
})
public class ServiceTest {


    @Autowired
    private IBaseTestDubboService baseTestDubboService;

    @Autowired
    private IBaseTestDubbo2Service baseTestDubbo2Service;

    // @Autowired
    // IOrderService orderService;

    @Test
    public void test1() {
        String returnStr = baseTestDubboService.test5("5");
        String returnStr1 = null;
        try {
            returnStr1 = baseTestDubboService.test6("6");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

     //   String returnStr2 = baseTestDubbo2Service.test6("6");
      //  System.out.println(returnStr2);
        System.out.println(returnStr);
        System.out.println(returnStr1);
      
        
  
        while(true){
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
     
    }

    // @Test
    // public void saveOrder(){
    // orderService.save(cti,null);
    //
    // }

}
