package com.xfs.fastlist.service;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface FastListService {

	/**
	 * 打包excel并 发送邮件
	 *
	 * @author lifq
	 *
	 *         2016年3月17日 下午2:58:07
	 */
	public void sendMail(String template_id, String email, String fileRootPath,String creator) throws Exception;

	public String uploadFile(File file);

	public boolean publishForm(String templatejson);

	public boolean submitForm(String htmljson);

	public List<Map<String, Object>> queryTplView(String _id);

	public List<Map<String, Object>> queryDataView(String _id);

}
