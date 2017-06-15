
package persistence;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Player;
import model.Team;
import model.Ranking;
import model.averagePlayer;

/*
 * @author david
 */

public class ConectarJDBC {
    
    private Connection conexio;
    
    public ConectarJDBC() {
    
    }
    
    public void deletePlayer() throws SQLException {
        try (Statement st = conexio.createStatement()) {
            st.executeUpdate("delete from player;");
        }
    }
    
    public void deleteTeam() throws SQLException {
        try (Statement st = conexio.createStatement()) {
            st.executeUpdate("delete from team;");
        }
    }
    
    //1
    public void insertarTeam(Team t1) throws SQLException {
        String insert = "insert into team values (?,?,?);";
        try (PreparedStatement ps = conexio.prepareStatement(insert)) {
            ps.setString(1, t1.getName());
            ps.setString(2, t1.getCity());
            ps.setDate(3, java.sql.Date.valueOf(t1.getCreacion()));
            ps.executeUpdate();
            ps.close();
        }
    }
    
    //2
    public void insertarPlayer(Player p1) throws SQLException {
        String insert = "insert into player values (?,?,?,?,?,?,?);";
        try (PreparedStatement ps = conexio.prepareStatement(insert)) {
            ps.setString(1, p1.getName());
            ps.setDate(2, java.sql.Date.valueOf(p1.getBirth()));
            ps.setInt(3, p1.getNbaskets());
            ps.setInt(4, p1.getNassists());
            ps.setInt(5, p1.getNrebounds());
            ps.setString(6, p1.getPosition());
            ps.setString(7, p1.getTeam().getName());
            ps.executeUpdate();
            ps.close();
        }
    }
    
    //3
    public void modificarPlayer1(Player mp1) throws SQLException {
        String update = "update player set nbaskets = ? , nassists = ?, nrebounds = ? where name = ? ";
        try (PreparedStatement ps = conexio.prepareStatement(update)) {
            ps.setString(1, mp1.getName());
            ps.setInt(3, mp1.getNbaskets());
            ps.setInt(4, mp1.getNassists());
            ps.setInt(5, mp1.getNrebounds());
            ps.executeUpdate();
            ps.close();
        }
    }
    
    //4
    public void modificarPlayer2(Player mp2) throws SQLException {
        String update = "update player set team = ? where name = ? ";
        try (PreparedStatement ps = conexio.prepareStatement(update)) {
            ps.setString(1, mp2.getName());
            ps.setString(7, mp2.getTeam().getName());
            ps.executeUpdate();
            ps.close();
        }
    }
    
    //5
    public void borrarPlayer(Player bt) throws SQLException {
        String delete = "delete from player where name = ?";
        try (PreparedStatement ps = conexio.prepareStatement(delete)) {
            ps.setString(1, bt.getName());
            ps.executeUpdate();
            ps.close();
        }
    }
    
    //6
    public Player selectPlayerByName(String name) throws SQLException {
        String query ="select * from player where name=?";
        PreparedStatement ps = conexio.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        Player player = new Player();
        if (rs.next()) {
            player.setName(rs.getString("name"));
            player.setBirth(rs.getDate("birth").toLocalDate());
            player.setNassists(rs.getInt("nassists"));
            player.setNbaskets(rs.getInt("nbaskets"));
            player.setNrebounds(rs.getInt("nrebounds"));
            player.setPosition(rs.getString("position"));
            player.setTeam(new Team(rs.getString("team"))); 
        }
        rs.close();
        ps.close();
        
        return player;
    }
    
    //7
    public List<Player> selectAllPlayers(String name) throws SQLException {
        String query = "select * from player where name like '%"+name+"%'";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setBirth(rs.getDate("birth").toLocalDate());
            p.setNbaskets(rs.getInt("nbaskets"));
            p.setNassists(rs.getInt("nassists"));
            p.setNrebounds(rs.getInt("nrebounds"));
            p.setPosition(rs.getString("position"));
            p.setTeam(new Team(rs.getString("team")));
            players.add(p);
        }
        rs.close();
        st.close();
        return players;
    }
    
    //8
    public List<Player> selectPlayerGreaterThan() throws SQLException {
        String query = "select name, birth, nbaskets, team from player where nbaskets=(select max(nbaskets) from player)";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setBirth(rs.getDate("birth").toLocalDate());
            p.setNbaskets(rs.getInt("nbaskets"));
            p.setTeam(new Team(rs.getString("team")));
            players.add(p);
        }
        rs.close();
        st.close();
        return players;
    }
    
    //9 
    public List<Player> selectPlayerAssistsBetween(int num1, int num2) throws SQLException {
        String query = "select * from player where nassists between "+num1+" and "+num2+" ";
            PreparedStatement ps = conexio.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);
            ArrayList players = new ArrayList<>();
            while (rs.next()) {
                Player p = new Player();
                p.setName(rs.getString("name"));
                p.setBirth(rs.getDate("birth").toLocalDate());
                p.setNbaskets(rs.getInt("nbaskets"));
                p.setNassists(rs.getInt("nassists"));
                p.setNrebounds(rs.getInt("nrebounds"));
                p.setPosition(rs.getString("position"));
                p.setTeam(new Team(rs.getString("team")));
                players.add(p);
            }
            ps.close();
            rs.close();
            return players;
    }
  
    //10 
    public List<Player> selectPlayerByPosition(String aux) throws SQLException {
        String query = "select * from player where player.position='"+aux+"' ";
        PreparedStatement ps = conexio.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setBirth(rs.getDate("birth").toLocalDate());
            p.setNbaskets(rs.getInt("nbaskets"));
            p.setNassists(rs.getInt("nassists"));
            p.setNrebounds(rs.getInt("nrebounds"));
            p.setPosition(rs.getString("position"));
            p.setTeam(new Team(rs.getString("team")));
            players.add(p);
        }
        ps.close();
        rs.close();
        return players;
    }
    
    //11 
    public List<Player> selectPlayerByBirth(Date aux) throws SQLException {
        String query = "select name, birth from player where birth<='"+aux.toLocalDate()+"' ";
        PreparedStatement ps = conexio.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        List<Player> players = new ArrayList<>();
        if (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setBirth(rs.getDate("birth").toLocalDate());
            players.add(p);
        }
        ps.close();
        rs.close();
        return players;
    }
    
    //12 
    public List<averagePlayer> selectPlayerByGroupByPosition() throws SQLException {
        String query = "select position, AVG(nbaskets), AVG(nassists), AVG(nrebounds), "
                + " MAX(nbaskets), MAX(nassists), MAX(nrebounds), "
                + " MIN(nbaskets), MIN(nassists), MIN(nrebounds) from player group by position";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<averagePlayer> averagePlayerList = new ArrayList<>();
        while (rs.next()) {
            averagePlayer averageList = new averagePlayer();
            averageList.setPosition(rs.getString("position"));
            averageList.setAvgBasket(rs.getDouble("avg(nbaskets)"));
            averageList.setAvgAssist(rs.getDouble("avg(nassists)"));
            averageList.setAvgRebounds(rs.getDouble("avg(nrebounds)"));
            averageList.setMaxBasket(rs.getInt("max(nbaskets)"));
            averageList.setMaxAssist(rs.getInt("max(nassists)"));
            averageList.setMaxRebounds(rs.getInt("max(nrebounds)"));
            averageList.setMinBasket(rs.getInt("min(nbaskets)"));
            averageList.setMinAssist(rs.getInt("min(nassists)"));
            averageList.setMinRebounds(rs.getInt("min(nrebounds)"));
            averagePlayerList.add(averageList);
        }
        st.close();
        rs.close(); 
        return averagePlayerList;
    }
    
    //13
    public List<Player> selectPlayerByBaskets() throws SQLException {
        String query = "select name, nbaskets, position, team from player order by nbaskets desc ";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setNbaskets(rs.getInt("nbaskets"));
            p.setPosition(rs.getString("position"));
            p.setTeam(new Team(rs.getString("team")));
            players.add(p);
        }
        st.close();
        rs.close();    
        return players;
    }
    
    //14 REVISAR
    public List<Ranking> selectPlayerByBasketsRanking() throws SQLException {
        String query = "select name, nbaskets from player order by nbaskets desc";
//        String query = "set @num=0; select @num:=@num+1 AS 'Relación', name, nbaskets from player order by nbaskets desc";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Ranking> rankings = new ArrayList<>();
        while (rs.next()) {
            Ranking r = new Ranking();
//            r.setRanking(rs.getInt("ranking"));
            r.setName(rs.getString("name"));
            r.setNbaskets(rs.getInt("nbaskets"));
            rankings.add(r);
        }
        st.close();
        rs.close();
        return rankings;
    }
    
    //15
    public List<Team> selectTeamByCity(String aux) throws SQLException {
        String query = "select * from team where city = '"+aux+"' ";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Team> teams = new ArrayList<>();
        while (rs.next()) {
            Team t = new Team();
            t.setCity(rs.getString("city"));
            t.setName(rs.getString("name"));
            t.setCreacion(rs.getDate("creation").toLocalDate());
            teams.add(t);
        }
        st.close();
        rs.close();
        return teams;
    }
    
    //16
    public List<Player> selectPlayerTeam(String team) throws SQLException {
        String query = "select * from player where team='"+team+"' ";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setPosition(rs.getString("position"));
            p.setTeam(new Team(rs.getString("team")));
            players.add(p);
        }
        st.close();
        rs.close();
        return players;
    }

    //17
    public List<Player> selectPlayerByTeamAndPosition(String aux1, String aux2) throws SQLException {
        String query = "select name, position, team from player where position='"+aux1+"' and team='"+aux2+"' ";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setPosition(rs.getString("position"));
            p.setTeam(new Team(rs.getString("team")));
            players.add(p);
        }
        st.close();
        rs.close();
        return players;
    }
      
    //18
    public ArrayList selectPlayerMaxBasketsByTeam(String aux) throws SQLException {
        String query = "select name, nbaskets, team from player where team='"+aux+"'and nbaskets = (select max(nbaskets) from player where team='"+aux+"')" ;
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setNbaskets(rs.getInt("nbaskets"));
            p.setTeam(new Team(rs.getString("team")));
            players.add(p);
            
        }
        st.close();
        rs.close();
        return players;
    }

    public void conectar() throws SQLException {
        String url="jdbc:mysql://localhost:3306/basket";
        String usr="root";
        String pass="d4v1d001";
        conexio = (Connection) DriverManager.getConnection(url, usr, pass);
    }
    
    public void desconectar() throws SQLException {
            if (conexio != null) {
            conexio.close();
        }
    }

}