package com.ccc.base.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PageUtil
{
    /**
     * Ĭ�ϵ�һҳ
     */
    public static final Integer DEFAULT_CURRENT_PAGE = 1;

    /**
     * Ĭ��һҳ����
     */
    public static final Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * ��ȡ��ǰ��ҳ��
     * @param request ����
     * @return ��ǰ��ҳ��
     */
    public Integer getPage(HttpServletRequest request)
    {
        Integer currentPage;
        try
        {
            currentPage = Integer.valueOf(request.getParameter("page"));
        }
        catch (Exception e)
        {
            currentPage = DEFAULT_CURRENT_PAGE;
        }
        return countOffset(getPageSize(request), currentPage);
    }

    /**
     * ��ȡһҳ����
     * @param request ����
     * @return һҳ����
     */
    public Integer getPageSize(HttpServletRequest request)
    {
        Integer pageSize;
        try
        {
            pageSize = Integer.valueOf(request.getParameter("pageSize"));
        }
        catch (Exception e)
        {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    /**
     * ���㵱ǰҳ��ʼ��¼
     * @param pageSize ÿҳ��¼��
     * @param currentPage ��ǰ�ڼ�ҳ
     * @return ��ǰҳ��ʼ��¼��
     */
    public int countOffset(final int pageSize, final int currentPage)
    {
        final int offset = pageSize * (currentPage - 1);
        return offset;
    }

    /**
     * ��ȡ��ѯ����
     * @param request ����
     * @return ��ѯ����
     */
    public String getQuery(HttpServletRequest request)
    {
        String q;
        try
        {
            q =  new String(request.getParameter("q").getBytes("iso-8859-1"), "utf-8");
        }
        catch (Exception e)
        {
            q = "";
        }
        return q;
    }
}
