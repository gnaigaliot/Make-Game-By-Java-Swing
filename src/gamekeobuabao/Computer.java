package gamekeobuabao;

import java.util.Random;

public class Computer implements Player{
	public int Choose; 	//1 is paper, 2 is scissors, 3 is rock
	
	public Computer() {
		super();
		this.createPlayer();
	}

	@Override
	public int createPlayer() {
		Random rand = new Random();
		this.Choose = rand.nextInt(2) + 1;
		return this.Choose;
	}

	@Override
	public String toString() {
		return "Computer [Choose=" + Choose + "]";
	}
	
}
