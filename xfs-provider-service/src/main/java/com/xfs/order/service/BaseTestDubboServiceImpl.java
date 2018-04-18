package com.xfs.order.service;

import org.springframework.stereotype.Service;




@Service("baseTestDubboService")
public class BaseTestDubboServiceImpl implements IBaseTestDubboService{

	public void test() {
		System.out.println("test：Hello Dubbo");
	}
	
	public void test2() {
		System.out.println("test2：Hello Dubbo");
	}
	
	public void test3(String name) {
		System.out.println("test3：Hello Dubbo:"+name);
	}

	public String test4() {
		Integer.valueOf("s");
		System.out.println("开始休眠 test4：Hello Dubbo:");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("休眠完成test4：Hello Dubbo:");
		return "test4：Hello Dubbo";
	}
	
	public String test5(String name) {
		System.out.println("开始休眠 test5：Hello Dubbo:");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String returnStr = "休眠完成test5：Hello Dubbo:"+name;
		System.out.println(returnStr);
		return returnStr;
	}
	
	
	public String test6(String name) throws Exception{
		int i = Integer.valueOf(name);
		String returnStr = "test6：Hello Dubbo:"+name+" a:"+i;
		System.out.println(returnStr);
	//	int k = 1/0;
		return returnStr;
	}
	

	

	


}
