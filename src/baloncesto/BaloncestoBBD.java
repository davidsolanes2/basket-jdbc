
package baloncesto;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import persistence.ConectarJDBC;
import model.Player;
import model.Ranking;
import model.Team;
import model.averagePlayer;

/**
 *
 * @author david
 */

public class BaloncestoBBD {

    public static void main(String[] args) throws SQLException {
         ConectarJDBC gestor = new ConectarJDBC();
         try{
             gestor.conectar();
             
             System.out.println("Conectando con el servidor");
             System.out.println("");
             
             System.out.println("Reiniciando tablas");           
             gestor.deletePlayer();
             gestor.deleteTeam();
             System.out.println("");
             
             Team t1 = new Team ("FC Barcelona", "Barcelona", LocalDate.of(2016, 5, 16));
             Team t2 = new Team ("Ath. Bilbao", "Bilbao", LocalDate.of(2010, 4, 10));
             Team t3 = new Team ("Ath. Madrid", "Madrid", LocalDate.of(1965, 6, 10));
             
             Player p1 = new Player("Pedro", LocalDate.of(1985, Month.MARCH, 6), 25, 30, 15, "Pivot", t2);
             Player p2 = new Player("Ivan", LocalDate.of(1987, Month.APRIL, 16), 35, 45, 55, "Pivot", t3);
             Player p3 = new Player("David", LocalDate.of(1982, Month.APRIL, 26), 30, 35, 45, "Alero", t1);  
             Player p4 = new Player("Luis", LocalDate.of(1983, Month.APRIL, 5), 30, 35, 45, "Base", t1);
             Player p5 = new Player("Javier", LocalDate.of(1986, Month.APRIL, 18), 35, 35, 45, "Alero", t2);
             Player p6 = new Player("Francisco", LocalDate.of(1988, Month.APRIL, 11), 43, 35, 45, "Base", t3);
             Player p7 = new Player("Julian", LocalDate.of(1989, Month.APRIL, 9), 32, 35, 45, "Alero", t2);
             Player p8 = new Player("Ricardo", LocalDate.of(1984, Month.APRIL, 7), 30, 35, 45, "Pivot", t1);
             Player p9 = new Player("Alex", LocalDate.of(1990, Month.APRIL, 22), 23, 35, 45, "Alero", t3);
             Player p10 = new Player("Alan", LocalDate.of(1992, Month.APRIL, 23), 31, 35, 45, "Base", t2);
             
             gestor.insertarTeam(t1);
             gestor.insertarTeam(t2);
             gestor.insertarTeam(t3);
             
             gestor.insertarPlayer(p1);
             gestor.insertarPlayer(p2);
             gestor.insertarPlayer(p3);
             gestor.insertarPlayer(p4);
             gestor.insertarPlayer(p5);
             gestor.insertarPlayer(p6);
             gestor.insertarPlayer(p7);
             gestor.insertarPlayer(p8);
             gestor.insertarPlayer(p9);
             gestor.insertarPlayer(p10);
             
             System.out.println("Insertando datos en las Entidades");
             System.out.println("");
             
             //3
             System.out.println("3.- Modificando canastas, asistencias y rebotes de " + p1.getName());
             p1.setNbaskets(100);
             p1.setNassists(100);
             p1.setNrebounds(100);
             System.out.println("");
             
             //4
             System.out.println("4.- Modificando equipo a " + p2.getName());
             p1.setTeam(t3);
             System.out.println("");
             
             //5
             System.out.println("5.- Borrando el jugador " + p1.getName());
             gestor.borrarPlayer(p1);
             System.out.println("");
             
             //6
             System.out.println("6.- El Jugador : " + p2.getName());
             System.out.println(gestor.selectPlayerByName(p2.getName()).toString());
             System.out.println("");
         
             //7
             System.out.println("7.- Relacion de jugadores : ");
             System.out.println(gestor.selectAllPlayers("D"));
             System.out.println("");
             
             //8
             System.out.println("8.- El jugador con mayor puntuacion es : ");
             System.out.println(gestor.selectPlayerGreaterThan());
             System.out.println("");
             
             //9
             System.out.println("9.- Los jugadores que estan entre 25 y 50 asistencias son : ");
                for (Player players : gestor.selectPlayerAssistsBetween(25 , 50)) {
                   System.out.println(players.toString());
                }
             System.out.println("");
             
             //10
             System.out.println("10.- Los jugadores de la posicion Alero son : ");
                for (Player players : gestor.selectPlayerByPosition("Alero")) {
                    System.out.println(players.toString());
                }
             System.out.println("");
             
             //11  
             System.out.println("11.- Los jugadores nacidos antes de esta fecha 6/12/1992 son : ");
             for (Player players : gestor.selectPlayerByBirth(Date.valueOf(LocalDate.of(1992, Month.DECEMBER, 6)))) {
                 System.out.println(players.toString());
             }
             System.out.println("");
             
             //12 
             System.out.println("12.- Las medias de los jugadores por la posicion son : ");
             for (averagePlayer averagePlayerList : gestor.selectPlayerByGroupByPosition()) {
                  System.out.println(averagePlayerList.toString());
             }
             System.out.println("");
             
             //13
             System.out.println("13.- El ranking de los jugadores por canastas son : ");
             for (Player players : gestor.selectPlayerByBaskets()) {
                 System.out.println(players.toString());
             }
             System.out.println("");
             
             //14 REVISAR
             System.out.println("14.- La posicion del ranking de los jugadores por canastas son : ");
             for (Ranking rankings : gestor.selectPlayerByBasketsRanking()) {
                 System.out.println(rankings.toString());
             }
             System.out.println("");
              
             //15
             System.out.println("15.- Los equipos de la localidad de Barcelona : ");
             List<Team>selectTeamByCity = gestor.selectTeamByCity("Barcelona");
             selectTeamByCity.stream().forEach((teams) -> {
                System.out.println(teams.toString()); 
             });
             System.out.println("");
             
             //16
             System.out.println("16.- Los jugadores del equipo Ath. Bilbao son :");
             List<Player>selectPlayerTeam = gestor.selectPlayerTeam("Ath. Bilbao");
             selectPlayerTeam.stream().forEach((players) -> {
                 System.out.println(players.toString());
             });
             System.out.println("");
             
             //17
             System.out.println("17.- Los jugadores del Ath. Madrid que juegan en la posicion alero son : ");
             List<Player>selectPlayerByTeamPosition = gestor.selectPlayerByTeamAndPosition("Alero", "Ath. Madrid");
             selectPlayerByTeamPosition.stream().forEach((Player players)-> {
                System.out.println(players.toString());
             });
             System.out.println("");
             
             //18
             System.out.println("18.- El jugador que ha hecho mas canastas del Ath. Bilbao es : ");
             System.out.println(gestor.selectPlayerMaxBasketsByTeam("Ath. Bilbao"));
             System.out.println("");
           
             
         }
         catch (SQLException ex) {
         System.out.println("Error con la BBDD " + ex.getMessage());
         }
         finally {
            try {
                gestor.desconectar();
                System.out.println("Conexión cerrada");
            }
            catch(SQLException ex) {
                System.out.println("Error al desconectar" + ex.getMessage());
            }
        }
    }
    
}
