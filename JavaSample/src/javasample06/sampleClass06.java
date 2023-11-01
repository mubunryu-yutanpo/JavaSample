package javasample06;

public class sampleClass06 {

	public static void main(String[] args) {
		// Javaでの関数・メソッドの扱いについて見ていく。。
		
		// クラスには様々な関数（メソッド）や変数を使うことできる。
		// メソッドは下記のように書く
		
		/*
		 修飾子　戻り値の型　メソッド名（引数）{
		   命令文;
		   return 式;
		 }
		 */
		
		// 引数は省略可能
		// 修飾子には、「アクセス修飾子」といった
		
		// ・public →　全てのクラスからアクセスできる
		// ・protected　→　現在のクラスとサブクラス（子クラス）からアクセスできる
		// ・private　→　現在のクラスからのみアクセスできる
		// ・付けない場合　→　現在のクラスと同じパッケージのクラスからアクセスできる
		
		// のようなものであったり、他にも
		// 「static修飾子」、「final修飾子」といったものも存在する。
		
		
		// addメソッドを呼び出して、戻り値を変数resultに格納する
		int result = add(3, 7);
		print(result);
		
		print(add("4", "9")); // 下記のaddメソッドの文字列バージョンの方を呼び出して、その戻り値を計算している
	}
	
	
		// メソッド内部で使われる変数ではなく、クラス内に直接変数を書いて定義できる
		// コレらをメンバ変数（フィールド変数）とよび、メソッド内でのみ使われる変数をローカル変数という
		private static final String CALC_MSG_BEFORE = "計算結果は";
		private static final String CALC_MSG_AFTER  = "です〜";
		
		private static int add(int a, int b) {
			return a + b;
		}
		
		private static int add(String a, String b){
			// 引数の数やデータ型が違えば、同じメソッド名で複数のメソッドを定義できる。
			// これをオーバーロードという
			return Integer.parseInt(a) + Integer.parseInt(b);
		}
		
		private static void print(int calcResult) {
			// 戻り値がない場合はvoidを使う
			System.out.println(CALC_MSG_BEFORE + String.valueOf(calcResult) + CALC_MSG_AFTER);
		}
}

