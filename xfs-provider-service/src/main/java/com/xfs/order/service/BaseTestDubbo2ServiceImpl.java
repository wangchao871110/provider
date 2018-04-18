package com.xfs.order.service;

import org.springframework.stereotype.Service;






@Service("baseTestDubbo2Service")
public class BaseTestDubbo2ServiceImpl implements IBaseTestDubbo2Service{

	public void test() {
		System.out.println("2 test：Hello Dubbo");
	}
	
	public void test2() {
		System.out.println("2 test2：Hello Dubbo");
	}
	
	public void test3(String name) {
		System.out.println("2 test3：Hello Dubbo:"+name);
	}

	public String test4() {
		System.out.println("2 test4：Hello Dubbo:");
		return "2 test4：Hello Dubbo";
	}
	
	public String test5(String name) {
		String returnStr = "2 test5：Hello Dubbo:"+name;
		System.out.println(returnStr);
		return returnStr;
	}
	
	
	public String test6(String name) {
		int i = Integer.valueOf(name);
		String returnStr = "2 test6：Hello Dubbo:"+name+" a:"+i;
		System.out.println(returnStr);
		return returnStr;
	}
	

	

	


}
