<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSPを使ってみよう！</title>
	</head>
	<body>
		<h2>JSPを使ってみよう！</h2>
		<div>
		<% 
		// JSPではスクリプトレットという書き方をすることにより、HTMLの書き方の中でJavaコードを埋め込んでいくことができます。
		String text1 = "JSPではサーブレットよりも簡単にHTMLを出力できます！";
		// out.println()という形で画面上に出力していきます。
		out.println(text1);
		
		String text2 = "コンソールへ出力します";
		
		System.out.println(text2);
		%>
		<br>
		<br>
		<%
		String text3 = "JSPでは、HTML内にJavaの処理を書いていきます。<br>";
		String text4 = "JSPでのimport文は、<%@ page を頭につけて書きます。<br>";
		// <%@というのは、ディレクティブと呼び、文字コードを指定したり外部ファイルを読み込む時に使う。
		
		List<String> strList = new ArrayList<>();
		strList.add(text3);
		strList.add(text4);
		for (String str : strList) {
			out.println(str);
		}
		%>
		</div>
		
		<!-- HTMLとしてのコメント（ブラウザに渡すHTMLファイルに出力される） -->
		<%-- JSPとしてのコメント（ブラウザに渡すHTMLファイルに出力されない） --%>
		
		<%-- 下記のようにスクリプトレットの<%のうしろに=をつけることでも値を出力することができます。 --%>
		<p><%= "頭に=をつけると、変数や文字列といったものの出力を簡単に行うこともできます。" %></p>
		
		
		<%
		int i = 3 + 5;
		%>
		<p><span>3 + 5 = </span><%= i %></p>
	</body>
</html>