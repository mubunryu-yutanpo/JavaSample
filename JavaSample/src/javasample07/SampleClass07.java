package javasample07;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import javasample06.sampleClass06;

public class SampleClass07 {

	public static void main(String[] args) {
		// ライブラリの読み込み方と、クラスの使い方を見ていきましょう！
		
		
		// ライブラリは、「標準クラスライブラリ」「外部ライブラリ」に分けられる。

		
		// 「標準クラスライブラリ」とは、Java側でもともと用意してくれているライブラリ
		// 様々な便利機能がパッケージごとにまとめられている
		
		// 例えば、
		// import java.util.List;
		// import java.util.ArrayList;
		// と書くことによって、配列をより便利に扱うためのListクラスやArrayListクラスがこのクラスに読み込まれ、使える状態になる。
		// （参照サイト）https://docs.oracle.com/javase/jp/8/docs/api/
		
		// Listは可変長配列と呼ばれる。配列のサイズを指定しないまま使うことができる。
		List<String> strList = new ArrayList<String>();
		
		// addメソッドを使ってリストに要素を簡単に追加していける
		strList.add("Java");
		strList.add("PHP");
		strList.add("javascript");
		
        System.out.println("リストの中身を表示");
        // 拡張for文
        for(String s : strList) {
            System.out.println(s);
        }
		
        // removeメソッドを使うと、指定した要素を削除できる
        strList.remove(1);
        
        System.out.println("PHPを削除した後のリストの中身を表示");
        for(String s : strList) {
            System.out.println(s);
        }
		
		// 「外部ライブラリ」を使う場合は、jarファイルという、javaのソースコードをまとめたファイルをまず取得して、
		// そのファイルを置いた場所を「ビルドパス」として設定してあげる必要がある（どこのjarをおいているのかプログラム側に教えてあげる必要がある）
		// 今回は、文字列の操作等をシンプルに行うことができる「StringUtils」というライブラリを実際に使ってみる
		// 下記のURLにアクセスして、zipファイルをダウンロードして解凍すると、「commons-lang3-3.9.jar」というjarファイルが取得できる
		// （参照サイト）http://commons.apache.org/proper/commons-lang/download_lang.cgi
		// jarファイルを置く場所は基本的にどこでも問題ないが、プロジェクトで使うjarファイルはプロジェクト内に配置したほうが管理しやすい。
		// プロジェクト名を右クリックして「新規 > フォルダ」を選択し、「lib」という名前のフォルダを作成して、そこにjarファイルをドラッグ&ドロップでコピーして配置してみましょう。
		// jarファイルをクリックして「ビルドパス > ビルドパスに追加」として設定してあげましょう。
		// 下記のように、StringUtilsと書いた上で、Command + Shift + OとするとEclipseが自動的にimport文を書いてくれる。
        
        String str1 = "";
        String str2 = null;
        String str3 = "あああ";

        // その文字列が、nullであっても空文字であっても、「true」で返してくれるStringUtils.isEmpty()というメソッドが使える
        // これを使わないと、毎回
        // str1 == ""
        // str1 == null
        // のように空文字の場合もnullの場合もチェックする必要が出てきたりする
        System.out.println("StringUtils.isEmpty()を使った、空文字の判定結果:" + StringUtils.isEmpty(str1));
        System.out.println("StringUtils.isEmpty()を使った、nullの判定結果:" + StringUtils.isEmpty(str2));
        System.out.println("StringUtils.isEmpty()を使った、「あああ」の判定結果:" + StringUtils.isEmpty(str3));


        String str4 = "abc";
        String str5 = "123";
        
        // 文字列の中身がアルファベットかどうかを判定できる
        System.out.println("StringUtils.isAlpha()を使った「abc」の判定結果:" + StringUtils.isAlpha(str4));
        System.out.println("StringUtils.isAlpha()を使った「123」の判定結果:" + StringUtils.isAlpha(str5));
		
        // 文字列の中身が数値かどうかを判定できる
        System.out.println("StringUtils.isNumeric()を使った「abc」の判定結果:" + StringUtils.isNumeric(str4));
        System.out.println("StringUtils.isNumeric()を使った「123」の判定結果:" + StringUtils.isNumeric(str5));
		
        
        
        
        // クラスの使い方について
        // 「自分で作った他のクラスファイル」の読み込み
        // パッケージ名やクラス名を指定して、importしてあげれば良い。
        // 前回のレッスンで使ったsampleClass06を読み込むためには、
        // import javasample06.sampleClass06;とする
        // そうするとsampleClass06が呼び出せる状態になるので、そのクラスを実際に使うために「new」演算子を使ってインスタンス化する（この辺りは後ほど説明していきます）
        sampleClass06 sc6 = new sampleClass06();
        // sampleClass06のmainメソッドを呼び出してみる。
        // 呼び出しの時は、Stringの配列を引数で渡さないといけないので、空の配列を作って渡す。
        String[] str = {};
        sc6.main(str);

        
        // Javaを使う上で、クラスの考え方は必須。
		// クラスとは変数や関数をまとめたもの
		// ただ、クラスそのままでは、そのクラスの変数や関数を使用できないので、
		// クラスからオブジェクト（インスタンス）を作ってあげることで実際に使えるようになる。（インスタンスを作ることを「インスタンス化」という）
		// php・オブジェクト指向部で詳しく触れるので詳細はそちらを参照。
		// Javaでクラスからオブジェクト（インスタンス）を作る書き方は下記の通り。
		// クラス 変数 = new クラス();
        
        // publicなメソッドや変数には外部のクラスからアクセスできるが、privateなメソッドや関数には外部からアクセスできない。
        // sc6.CALC_MSG_BEFORE
        
        //　厳密には、メソッドや関数に「static」をつけている場合は、インスタンス化をしないでアクセスできるので、
        // sampleClass06のmainメソッドなどにはインスタンス化せずに直接アクセスした方が良い
        sampleClass06.main(str);
        
        // 「static」がついていない場合は、インスタンス化してからそのメソッドや関数を呼び出す必要がある。
        // どんな場合にstaticを使うのかはphp・オブジェクト指向部で触れているので、そちらを参照すること。
        
        
	}

}
