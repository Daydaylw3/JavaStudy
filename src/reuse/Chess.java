package reuse;

class Game{
	Game(int i){
		System.out.println("Game con");
	}
}
class BoardGame extends Game {
	BoardGame(int i){
		super(i);	//一定要有这句
		System.out.println("BoardGame con");
	}
}
public class Chess extends BoardGame {
	Chess(){
		super(11);
		System.out.println("Chess con");
	}
	public static void main(String[] args) {
		Chess x = new Chess();
	}
}
