package chess_game;

public class FieldEnsure {
	
	public static Field[][] fieldFiller (Field[][] field){
		for (int i = 0; i < 8; i++){
			for( int j = 0; j < 8; j++){
				if (field[i][j] == null)
					field[i][j] = new Field(i,j,null);
				
			}
		}
		return field;
	}

}
