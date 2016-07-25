package blank.bbs.util;

import java.math.BigDecimal;

/**
 *   分页查询工具类
 */
public class PageHelp { 
	
	
	/**
	 * 根据总行数、每页大小显示条数，计算总页数
	 * @return
	 */
	public static Long getRowNoByTotalAndPageSize(Long totalRow, String pageSize){
		Long totalPage = 0L;
		if(totalRow.compareTo(0L)>0){
			totalPage = totalRow / Long.valueOf(pageSize);
			if((totalRow % Long.valueOf(pageSize)) > 0)
				totalPage++;
		}else
			totalPage = 1L;
		return totalPage;
	}

	/**
	 * 根据总页数，每页显示条数，第几页，计算起始行 
	 * @param pageSize 每页显示条数
	 * @param currentPage :第几页
	 * @param numPage : 总页数
	 * @return
	 */
	public static Long getStatrRow(Long pageSize, Long currentPage, Long numPage){
		if(currentPage.compareTo(numPage)>0)
			currentPage = numPage;
		return new BigDecimal(pageSize).multiply(
			   new BigDecimal(currentPage).subtract(
			   new BigDecimal(1))).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();	
	}
	
}
