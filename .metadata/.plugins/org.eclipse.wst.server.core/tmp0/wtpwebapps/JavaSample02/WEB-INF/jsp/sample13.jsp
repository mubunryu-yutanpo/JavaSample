<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>    
    
<%@ page import="javasample13.Diary"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<% request.setAttribute("title", "Diary");  %>
	
	<!-- 
	JSPでは、アクションタグというものを使っても処理を書いていくことができる。
	アクションタグとは、JSP内でよく使用する処理をタグ形式でかけるようにしたものである。
	例えばincludeディレクティブは下記のように書き換えることができる。 
	他にも、他のファイルに処理を転送する「jsp:forward」などもあるが、
	これらのJavaが提供してくれているアクションタグを標準アクションタグと呼ぶ。
	他にも、開発者がアクションタグを作り、それを第三者に利用してもらうことなどもできる。これを「カスタムタグ」という。
	有名なカスタムタグをまとめたものに「JSTL」というものがある。
	-->
	<%-- <%@ include file="/WEB-INF/jsp/head13.jsp"%> --%>
	<jsp:include page="/WEB-INF/jsp/head13.jsp" />
	<body>
		<header>
			<div class="logo">Diary</div>
		</header>		
		<div class="main">
			<div class="index_type_virtualcard_circle">
			</div>
			<form method="post">
				<div class="mt30">
					<label for="content">投稿内容：</label>
					<textarea id="content" class="content" name="content"></textarea>
				</div>
				<div class="button-area">
					<button type="submit" class="btn btn-success mt30 relative float-right">日記を投稿</button>
				</div>
			</form>
			<h1 class="diary-list-title">日記一覧</h1>			
			<div>
			<!-- 
			ここではJSTLを使っていくが、
			そのためにはまずJSTLのjarファイルをダウンロードしてくる必要がある。
			https://tomcat.apache.org/download-taglibs.cgi
			から
			・taglibs-standard-impl-1.2.5.jar
			・taglibs-standard-spec-1.2.5.jar
			・taglibs-standard-jstlel-1.2.5.jar
			をダウンロードしてくる。
			これらのファイルをWebContent/WEB-INF/libの下に置くと、自動的にビルドパスが通る。
			そしてこのクラスからJSTLを指定してあげることにより、JSTLが使える状態になる。
			 -->
			 
			 <!-- 
			 また、JSPでは、EL式というものが利用できる。
			 EL式とは、スコープに保存されている値を簡単にJSP上で出力するためのもの。
			 ${属性名}とすると、その属性名に格納されている値を表示できる。
			 ${属性名.プロパティ}とすれば、属性名の中プロパティにアクセスできる（getterが自動的に行われる）
			 これまでは、
			 List<Diary> list = (List<Diary>)request.getAttribute("diaryList");
			 としていたものが
			 ${diaryList}と取得できるようになる。
			  -->
			<c:forEach var="diary" items="${diaryList}">
			
				<div class="diary-content">
					<div class="post-date">
						<fmt:formatDate value="${diary.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
					<p>${diary.content}</p>
				</div>			
			</c:forEach>			


			</div>
		</div>
	</body>
</html>