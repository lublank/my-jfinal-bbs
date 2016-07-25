<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
	<div id=nav-bar>
        <div style="width: 1024px; margin: 0 auto; position: relative;">
            <div id="nav">
                <ul>
                    <li class="main" id="nav_index"><a href="/bbs/index"><span>论坛首页</span></a></li>			
                    <li class="nav_more2">${content}</li>
                </ul>
            </div>
                <div id="status">${message}</div>
        </div>
	</div>
