package javasample03;

public class sampleClass03 {
	
	public static void main(String[] args) {
		// Javaの様々なデータ型について見ていく
		
        // コメントの書き方
		// ここにコメント
		/*
		 　ここにコメント
		 　この書き方だと複数行のコメントが書ける
		*/
		
		// mainメソッドの中では主に下記のような処理行なっていく
		// 1. 変数の宣言
		// 2. 計算
		// 3. 命令や関数の実行（System .out .printlnなど）
		// 4. 制御文（if文、for文など）
		
		
		// Javaでは変数を使う際に、データ型を宣言する。（phpでは$だったが、javaでは「どんなデータか？」を決めておかないといけない）
		// 「データの種類」や「データのサイズ」によって、データ型を使い分ける。
		
		// long    整数（64バイト）
		// int     整数（32バイト）
		// short   整数（16バイト）
		// byte    整数（8バイト）
		// double  小数（64バイト）
		// float   小数（32バイト）
		// boolean 真偽（falseまたはtrue）
		// char    1文字
		// String  文字列
		
		
		// 定められたデータのサイズを超えたり、データ型の宣言をしないと、エラーが出る。
		int number = 1234567890;
		
		// charは1文字、Stringは複数文字列が可能。charはシングルクォーテーション、Stringはダブルクォーテーションで囲んで使う。
		char letter = 'あ';
		String text = "あああいいう";
		
		// 変数名の付け方には命名規則がある。
		// 命名規則は「全て小文字」「複数文字の場合は、区切りは大文字」「予約後は使えない」
		// 予約後の例「abstract」「case」「do」「new」「int」「short」「boolean」「this」
		short shortNumber = 1111;
		
		System.out.println(shortNumber);
		System.out.println(number);
		System.out.println(letter);
		System.out.println(text);
		
		number = 1 + 2;
		System.out.println(number);
		
		// 配列の場合は、[]を使って宣言する
		// 下記のどちらかの方法が使える
		String[] hairetu  = new String[3];
		String hairetu2[] = new String[3];
		
		// 配列を宣言するときは、配列のサイズを指定して初期化する（固定長配列）
		// サイズを指定せずに配列を宣言する方法（可変長配列）は、のちのレッスンで説明
		
		hairetu[0] = "林檎";
		hairetu[1] = "蜜柑";
		hairetu[2] = "葡萄";
		// hairetu[3] = "桃";
		
		System.out.println(hairetu[0]);
		System.out.println(hairetu[1]);
		System.out.println(hairetu[2]);
		
		
		// 最初から配列の変数に値を入れて宣言することもできる
		// この場合は配列の要素数を指定する必要はない
		int[] hairetu3 = {5,4,3,2};
		System.out.println(hairetu3[3]);
		
		//  配列のサイズを取得したい場合は、lengthを使う
		System.out.println("配列のサイズ：" + hairetu3.length);
		
		
		// 厳密にいうと、データ型には「基本型（プリミティブ型）」「参照型」の２種類に分けて考えられる
		
		// 「基本型」は先頭が小文字で、変数の中に値を直接保持している。
		// 「基本型」は上記のString以外の8つだけ。
		// 「参照型」は先頭が大文字で、変数はデータが実際に存在しているパソコン上の場所を参照しているだけで、値を直接的に保持しているわけではない
		// 「参照型」は、StringやIntegerといったものや、配列などが当てはまる
		// 「参照型」だと変数の中にnullを入れられたり、メソッドを呼び出したりできる。
		// 例えば、Integerはintと同様に整数を格納できるデータ型だが、intと違い、nullを入れることができる。
		
		// int num1 = null;  これはできない
		Integer num2 = null;   // コレはできる
		
		String text2 = "Javaの学習中です";
		
		System.out.println("text2の5文字目:" + text2.charAt(5) );   // charAt()で引数の部分から抜き出す
		System.out.println("text2の6文字目から8文字目の切り出し:" + text2.substring(5,8) );   // substring(引数1, 引数2)で、引数1〜引数2までを取得
		System.out.println("text2の文字列の文字数を取得:" + text2.length() );
		System.out.println("text2に「Java」という文字が含まれるか検索:" + text2.contains("Java") );
		System.out.println("text2に「PHP」という文字が含まれるか検索:" + text2.contains("PHP") );
		
		// コレまで見てきた「変数」は、値を後から自由に変更できるが
		// 先頭に「final」というキーワードを付与すると「定数」を作ることができる。
		// 定数の命名規則は「すべて大文字」「複合文字の場合は区切りにアンダースコア」を入れる
		
		final String GREETING_MESSAGE = "こにちわ";
		
		// GREETING_MESSAGE = "こんばんや"; としてもエラーになる。定数だからね。
		
		
	}

}
