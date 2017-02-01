
package baloncesto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import persistence.ConectarJDBC;
import model.Player;
import model.Ranking;
import model.Team;

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
             Team t1 = new Team ("FC Barcelona", "Barcelona", LocalDate.of(2016, 5, 16));
             Team t2 = new Team ("Ath. Bilbao", "Bilbao", LocalDate.of(2010, 4, 10));
             Team t3 = new Team ("Ath. Madrid", "Madrid", LocalDate.of(1965, 6, 10));
             
             Player p1 = new Player("Pedro", LocalDate.of(1985, Month.MARCH, 6), 25, 30, 15, "Alero", t2);
             Player p2 = new Player("Ivan", LocalDate.of(1987, Month.APRIL, 16), 30, 35, 45, "Pivot", t1);
             Player p3 = new Player("David", LocalDate.of(1982, Month.APRIL, 16), 30, 35, 45, "Pivot", t1);  
            
             gestor.insertarTeam(t1);
             gestor.insertarTeam(t2);
             gestor.insertarTeam(t3);
             
             gestor.insertarPlayer(p1);
             gestor.insertarPlayer(p2);
             gestor.insertarPlayer(p3);
             
             //3
             System.out.println("Modificando canastas, asistencias y rebotes de " + p1.getName());
             p1.setNbaskets(100);
             p1.setNassists(100);
             p1.setNrebounds(100);
             
             //4
             System.out.println("Modificando equipo a " + p2.getName());
             p1.setTeam(t3);
             
             //5
             System.out.println("Borrando el jugador " + p1.getName());
             gestor.borrarPlayer(p3);
             
             //6
             System.out.println("El Jugador : " + p2.getName());
             gestor.selectPlayerbyName(p2.getName()).toString();
             
             //7
             System.out.println("Relacion de jugadores : ");
             gestor.selectAllPlayers("D");
             
             //8
             System.out.println("El jugador con mayor puntuacion es : ");
             //gestor.selectPlayerGreaterThan();
             
             //9
             System.out.println("Los jugadores que esta entre 25 y 50 son : ");
             gestor.selectPlayerAssistsBetween();
             
             //10
             System.out.println("Los jugadores de la posicion Alero son : ");
             gestor.selectPlayerByPosition("Alero");
             
             //11
             System.out.println("Los jugadores nacidos antes de esta fecha son : ");
             List<Player> selectPlayerByBirth = gestor.selectPlayerByBirth(LocalDate.of(1980,12,6));
             selectPlayerByBirth.stream().forEach((players) -> {
                 System.out.println(players);
             });
             //12
             System.out.println("Las medias de los jugadores por la posicion Pivot son : ");
             List<Player>selectPlayerByGroupBy = gestor.selectPlayerByGroupBy("Alero");
             selectPlayerByGroupBy.stream().forEach((players) -> {
                 System.out.println(players);
             });
             //13
             System.out.println("El ranking de los jugadores por canastas son : ");
             List<Player>selectPlayerByBaskets = gestor.selectPlayerByBaskets();
             selectPlayerByBaskets.stream().forEach((players) -> {
                 System.out.println(players);
             });
             //14
             System.out.println("La posicion del ranking de los jugadores por canastas son : ");
              List<Ranking>selectPlayerByBasketsRanking = gestor.selectPlayerByBasketsRanking();
              selectPlayerByBasketsRanking.stream().forEach((rankings) -> {
                  System.out.println(rankings);
              });
             //15
             System.out.println("Los equipos de la localidad : ");
             List<Team>selectTeamByCity = gestor.selectTeamByCity("Barcelona");
             selectTeamByCity.stream().forEach((t) -> {
                 System.out.println(t);
             });
             //16
             System.out.println("Los jugadores del equipo Ath. Bilbao son :");
             List<Player>selectPlayerTeam = gestor.selectPlayerTeam("FC Barcelona");
             selectPlayerTeam.stream().forEach((players) -> {
                 System.out.println(players);
             });
             //17
             System.out.println("Los jugadores del Ath. Madrid que juegan en la posicion alero son : ");
             List<Player>selectPlayerByTeamPosition = gestor.selectPlayerByTeamAndPosition("alero", "Ath. Madrid");
             selectPlayerByTeamPosition.stream().forEach((Player players)-> {
                System.out.println(players);
            });
             //18
             System.out.println("El jugador que ha hecho mas canastas es : ");
             System.out.println(gestor.selectPlayerMaxNbaskets());
             
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
