package com.company;


public class MonopolySpil {

    private List				players		 = new ArrayList( PLAYERS_TOTAL );
    private Board					board		= new Board(  );
    private Terning					dice		= ;

    public MonopolySpil(  )
    {
        Player p;
        p = new Player( "Horse", dice, board );
        players.add( p );
        p = new Player( "Car", dice, board  );
        players.add( p );
    }

    public void playGame(  )
    {
        for ( int i = 0; i < ROUNDS_TOTAL; i++ )
        {
            playRound();
        }
    }

    public List getPlayers(  )
    {
        return players;
    }

    private void playRound(  )
    {
        for ( Iterator iter = players.iterator(  ); iter.hasNext(  ); )
        {
            Player player = (Player) iter.next();
            player.takeTurn();
        }
    }
}
}
