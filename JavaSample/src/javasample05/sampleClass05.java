package javasample05;

public class sampleClass05 {
	
	public static void main(String[] args) {
		// Javaでのif文・for文の書き方を見ていくぅ〜
		
		// if文
		if("A".equals("A") ) {
			System.out.println("if文の中に入りました");
		}
		
		if("A".equals("A") ) System.out.println("if文の処理が1行の場合は、{}を省略できます");

		if("A".equals("B") ) {
			// ここは処理は入らない
		} else if("A".equals("C")) { //elseとifの間に半角スペースが入るので注意
			// ここも処理は入らない
		} else {
			System.out.println("else文の中にいらっしゃい。");
		}
		
		
		// 三項演算子も使える
		// (条件式)　? (条件式がtrueの場合の値)　: (条件式がfalseの場合の値);
		String str = "A".equals("A") ? "三項演算子の条件式がtrue" : "三項演算子の条件式がfalse";
		System.out.println(str);
		
		// switch文
		int number = 3;
		
		switch (number) {
		case 1:
		   System.out.println("1です");
		   break;
		   
		case 2:
		   System.out.print("2です");
		   break;
		   
		case 3:
		   System.out.println("3です");
		   break;
		   
		default:
		   System.out.println("1~3以外の値です");
		}
		
		// 繰り返し処理
		String[] fruits = {"林檎", "バナナ", "蜜柑", "桃", "パイナップル"};
		
		System.out.println("for文");
		for (int i = 0; i < fruits.length; i++) {
			System.out.println(fruits[i]);
		}
		
		System.out.println("for文 + break");
		for (int i = 0; i < fruits.length; i++) {
			if(fruits[i].equals("桃")) {
				System.out.println("桃が見つかったので繰り返し処理おしまい");
				break;
			}
			System.out.println(fruits[i]);
		}
		
		System.out.println("for文 + continue");
		for (int i = 0; i < fruits.length; i++) {
			if(fruits[i].equals("桃")) {
				System.out.println("桃が見つかったので皇族の処理をスキップします");
				continue;
			}
			System.out.println(fruits[i]);
		}
		
		System.out.println("拡張for文");
		for (String fruit : fruits) {
			// 拡張for文と呼ばれる
			// 配列ないの全ての要素を取り出して処理を行う
			System.out.println(fruit);
		}
		
		// while文
		int i = 0;
		System.out.println("while文");
		while (i < 3) {
			// whileの後ろの条件式がtrueであるかぎり処理が続く
			System.out.println(fruits[i]);
			i++;
		}
		
	}
}
