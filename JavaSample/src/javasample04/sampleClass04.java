package javasample04;

public class sampleClass04 {

	public static void main(String[] args) {
		// Javaの比較演算子・論理演算子の書き方を見ていく〜
		
		// 四則演算や数値の比較は、基本的にPHPと同様に行うことができる
		System.out.println("1 == 1:" + (1 == 1) );  // true
		System.out.println("1 != 1:" + (1 != 1) );  // false
		System.out.println("2 > 1:" + (2 > 1) );  // true
		System.out.println("1 > 2:" + (1 > 2) );  // false
		
		
		int i = 1;
		System.out.println("i:" + i);  // 1
		i++;
		System.out.println("i(1回インクリ):" + i);  // 2
		i += 3;
		System.out.println("i(さっきのに3を足すと)" + i);  // 5
		
		
		// PHPでは、文字列の結合は、「.」を使っていたが、Javaでは「+」を使う
		String str1 = "Java";
		String str2 = "勉強中";
		
		// 下記はエラーになる
		// System.out.println(str1 . str2);
		System.out.println(str1 + str2); // 希望の挙動になる
		
		// こういった形で文字連結することも可能
		str1 += str2;
		System.out.println(str1); // Java勉強中
		
		
	    // Javaでは値の比較をするときに「===」は使えない
		// 以下はエラーになる
		// System.out.println(1 === 1);
		
		// Javaは「==」で、厳密比較を行なっている。(JavaScriptの「===」と同様）
		// Javaでは文字列と数値をそのまま「==」でつないで比較することはできない。
		// System.out.println(1 == "1"); はエラーになる
		
		// 文字列と数値を比較したい場合は、「文字列を数値に変換する」か「数値を文字列に変換する」ことで、型を合わせてから比較する
		
		// 「文字列を数値に変換する」場合はString.valueof()を使う
		// ここで注意だが、String型などの「参照型」の一致判定を行う場合は、「==」を使わずに「.equals」を使わないとうまくいかない場合がある。
		// 基本的にString型などの「参照型」の一致判定を行う際は、「.equals」を使うようにする
		System.out.println("String.valueof(1) == \"1\" :" + (String.valueOf(1) == "1") ); //false
		System.out.println("(String.valueof(1)).equals(\"1\"):" + (String.valueOf(1).equals("1") ) ); //true

		
		// 「==」と「.equals」の違いをもっと見てみよう！
		String text1 = "Java";
		String text2 = "Java";
		if(text1 == text2) {
			System.out.println("text1とtext2とは等しい");
		}else {
			System.out.println("text1とtext2はちがーう");
		}
		
		text1 += "の勉強中です！";
		text2 += "の勉強中です〜！";
		
		if(text1 == text2) {
			System.out.println("text1とtext2はスーパー等し君");
		}else {
			System.out.println("text1とtext2は同じじゃない〜");
		}
		
		
		// PHPでは、値そのものを使って、真か偽かを判定して条件分岐を行うことができたが、Javaではできない。
		// Boolean値のみ、そのまま使える。
		
		String s = "";
		
		// 次の書き方はエラーになる。
		// if(s){ ... }
		
		// Javaでは以下のように比較しないといけない
		if(s.equals("") ) {
			System.out.println("sはから文字です");
		} else {
			System.out.println("sは空文字じゃありませーん");
		}
		
		// Booleanの場合は、その変数を使って条件判定することができる。
		boolean b = true;
		if(b) {
			System.out.println("bは真です");
		} else {
			System.out.println("bは偽です");
		}
	}
}
