import java.util.ArrayList;
import java.util.Scanner;

public class DBLesson01{
	@SuppressWarnings("resource")
	public static void main(String[] args) {
    ArrayList<Word> words = new ArrayList<>();

      System.out.println("英単語と日本語をスペースで区切って入力して下さい");
      Scanner sc = new Scanner(System.in);
      String input = sc.nextLine();
      //"りんご apple"
      int index = 0;
      try{
        while (!input.equals("e")){
          String[] tmp = new String[2];
          tmp = input.split(" ");
          //tmp[0]"りんご",tmp[1]"apple"
          Word wd = new Word(tmp[0],tmp[1]);
          words.add(wd);
          index++;
          System.out.println("次の英単語と日本語を入力してください。eで終了します。");
          input = sc.nextLine();
        }
      }catch  (ArrayIndexOutOfBoundsException e){
        System.out.println("字数制限を超えました。登録済みのデータは以下になります。");
      }
      WordDAO dao = new WordDAO();
      int result = dao.registWords(words);

      System.out.println(result + "件、登録しました。");

    }

}
