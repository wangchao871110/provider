package com.xfs.user.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xfs.common.util.StringUtils;
import com.xfs.user.model.SysFunction;
import com.xfs.user.model.SysFunctionCategory;

public class SessionMenu implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Map<SysFunctionCategory, List<SysFunction>> menus;

	public SessionMenu(Map<SysFunctionCategory, List<SysFunction>> menus) {
		this.menus = menus;
	}

	private String requestURI;

	/**
	 * 获取一级菜单和二级菜单
	 * @return
     */
	public Map<SysFunctionCategory, List<SysFunction>> getCategoryAndFun(){
		return this.menus;
	}

	// 设置请求URI
	public void setRequestURI(String curUri) {
		if(null == menus || menus.isEmpty()){
			return ;
		}
		for (SysFunctionCategory cat : menus.keySet()) {
			for (SysFunction fun : menus.get(cat)) {
				// 请求地址在二级菜单中，则认为已进入新菜单，否则认为仍然在原菜单内部跳转
				if (isRequestContainFunURL(curUri, fun))
					this.requestURI = curUri;
			}
		}
				
	}

	// 根据请求URI获取一级菜单实例
	public SysFunctionCategory getCurCategory(String cururl) {
		for (SysFunctionCategory cat : menus.keySet()) {
			for (SysFunction fun : menus.get(cat)) {
				if (isRequestContainFunURL(cururl, fun))
					return cat;
			}
		}
		return null;
	}

	private boolean isRequestContainFunURL(String cururl, SysFunction fun) {
		if(StringUtils.isBlank(cururl) || null == fun)
			return false;
		if (cururl.indexOf(fun.getUrl())>=0) {
			return true;
		}
		String baseurl;
		if (fun.getUrl().indexOf("/") >= 0)
			baseurl = fun.getUrl().substring(0, fun.getUrl().lastIndexOf("/") + 1);
		else
			baseurl = fun.getUrl();
		if(cururl.indexOf("/") >= 0){
			cururl = cururl.substring(0, cururl.lastIndexOf("/") + 1);
		}
		if (cururl.indexOf(baseurl) >= 0) {
			return true;
		}
		return false;
	}

	// 根据请求URI获取当前一级菜单实例
	public SysFunctionCategory getCurCategory() {
		return getCurCategory(requestURI);
	}

	// 根据URI地址获取菜单实例
	public SysFunction getCurFunction(String cururl) {
		for (SysFunctionCategory cat : menus.keySet()) {
			for (SysFunction fun : menus.get(cat)) {
				if (isRequestContainFunURL(cururl, fun))
					return fun;
			}
		}
		return null;
	}

	// 根据请求URI获取当前二级菜单
	public SysFunction getCurFunction() {
		return getCurFunction(requestURI);
	}

	// 根据请求URI获取当前一级菜单下所有二级菜单集合
	public List<SysFunction> getCurFunctions() {
		return menus.get(getCurCategory());
	}

	// 获取所有一级菜单
	public List<SysFunctionCategory> getCategorys() {
		List<SysFunctionCategory> cats = new ArrayList<SysFunctionCategory>();
		for (SysFunctionCategory cat : menus.keySet()) {
			cats.add(cat);
		}
		return cats;
	}

	// 获取所有二级菜单
	public List<SysFunction> getFunctions() {
		List<SysFunction> funcs = new ArrayList<SysFunction>();
		List<SysFunction> temp = null;
		for (Map.Entry<SysFunctionCategory,List<SysFunction>> entry : menus.entrySet()) {
			if(null == entry.getKey())
				temp = entry.getValue();
			else
				funcs.addAll(entry.getValue());
		}
		if(null != temp)
			funcs.addAll(temp);
		return funcs;
	}

	// 获取一级菜单下的二级菜单集合
	public List<SysFunction> getFunction(SysFunctionCategory cat) {
		return menus.get(cat);
	}

}
