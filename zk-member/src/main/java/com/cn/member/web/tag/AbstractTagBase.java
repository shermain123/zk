package com.cn.member.web.tag;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created with IntelliJ IDEA.
 * User: zhenhuag
 * Date: 14-3-19
 */
public abstract class AbstractTagBase extends SimpleTagSupport {

	private int width = 100;
	private String showType ;
	private String name = "tagSelect";
	private String defaultValue = "";
	private String changeSelectedFunctionName = "";
	private String disab = "0";
	private String className="";
	private String layVerify="";
	private String layFilter = "";
	
	private boolean dropDownList = true;
	
	protected Object getBean(String name){
		ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		return wac.getBean(name);
	}

	public void doTag() throws IOException {

		LinkedHashMap<String, String> dataSource = getDataSource();
		if (dataSource == null) {
			return;
		}

		StringBuffer sb = new StringBuffer();

		if (this.dropDownList) {
			generateDropDownList(sb, dataSource);
		} else {
			generateRadioButtonList(sb, dataSource);
		}

		getJspContext().getOut().write(sb.toString());
	}

	public String generateBlank(int blankQuantity) {
		String blank = "&nbsp;&nbsp;&nbsp;&nbsp;";
		String returnValue = "";
		for (int i = 0; i < blankQuantity; i++) {
			returnValue += blank;
		}

		return returnValue;
	}

	public abstract LinkedHashMap<String, String> getDataSource();

	private void generateDropDownList(StringBuffer sb,
			LinkedHashMap<String, String> dataSource) {
		sb.append("<select ");
		if (StringUtils.isNotBlank(className)) {
			sb.append(" class='" + className+ "' ");
		}
		if (StringUtils.isNotBlank(layFilter)) {
			sb.append(" lay-filter='" + layFilter + "' ");
		}
		if (StringUtils.isNotBlank(layVerify)) {
			sb.append(" lay-verify='" + layVerify+ "' ");
		}
		if (StringUtils.isNotBlank(changeSelectedFunctionName)) {
			sb.append(" onChange='" + changeSelectedFunctionName+ "' ");
		}
		sb.append(" name='" + name + "' id='" + name + "' ");
		if("1".equals(disab)){
			sb.append("disabled='disabled'");
		}
		sb.append(" style='width:" + width + "px;'>");

		if ("1".equals(showType)) {
			sb.append(" <option value='' >全部</option>");
		}else if("2".equals(showType)) {
			sb.append(" <option value='' >请选择</option>");
		}
		
		for (Iterator<Map.Entry<String, String>> iterator = dataSource
				.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator
					.next();
			sb.append(" <option value='" + entry.getKey() + "'");
			if (this.compareValue(defaultValue, entry.getKey())) {
				sb.append(" selected='selected'");
			}
			sb.append(">" + entry.getValue() + "</option>");
		}

		sb.append("</select>");
	}

	private void generateRadioButtonList(StringBuffer sb,
			LinkedHashMap<String, String> dataSource) {
		Integer i = 0;
		for (Iterator<Map.Entry<String, String>> iterator = dataSource
				.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator
					.next();
			sb.append(" <label><input type=\"radio\" name=\"" + name
					+ "\" id=\"" + name + i.toString() + "\" value=\""
					+ entry.getKey() + "\" ");
			if (this.compareValue(defaultValue, i.toString())) {
				sb.append(" checked=\"checked\"");
			}
			sb.append(" />" + entry.getValue() + "</label>");

			i++;
		}
	}
	
	private boolean compareValue(String source, String target) {
		if (source == null) {
			source = "";
		}
		if (target == null) {
			target = "";
		}

		source = source.trim().toLowerCase();
		target = target.trim().toLowerCase();
		boolean result = source.equals(target);
		return result;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getChangeSelectedFunctionName() {
		return changeSelectedFunctionName;
	}

	public void setChangeSelectedFunctionName(String changeSelectedFunctionName) {
		this.changeSelectedFunctionName = changeSelectedFunctionName;
	}

	public boolean isDropDownList() {
		return dropDownList;
	}

	public void setDropDownList(boolean dropDownList) {
		this.dropDownList = dropDownList;
	}

	public String getDisab() {
		return disab;
	}

	public void setDisab(String disab) {
		this.disab = disab;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getLayVerify() {
		return layVerify;
	}

	public void setLayVerify(String layVerify) {
		this.layVerify = layVerify;
	}

	public String getLayFilter() {
		return layFilter;
	}

	public void setLayFilter(String layFilter) {
		this.layFilter = layFilter;
	}
	
	
}
