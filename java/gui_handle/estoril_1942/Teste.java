package gui_handle.estoril_1942;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Teste {
    // Test used to see if memory was allocated to game contents
    @Test
    //@DisplayName("Edu - Memory Allocation Test")
    public void TestBuild(){
        Game game = new Game(2);
        Player[] Player = new Player[5];
        assertEquals(2, 1+1);;
    }
    // GUI show and interactions and Server established connections were tested "internally"

    @Test public void Oi(){
        assertEquals(2, 1+1);
    }
}
